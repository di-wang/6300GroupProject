package edu.gatech.seclass.tourneymanager.mode;


import android.content.Context;
import android.database.MatrixCursor;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.tourneymanager.db.DatabaseHelper;
import edu.gatech.seclass.tourneymanager.model.Deck;
import edu.gatech.seclass.tourneymanager.model.Match;
import edu.gatech.seclass.tourneymanager.model.Player;
import edu.gatech.seclass.tourneymanager.model.Tournament;
import edu.gatech.seclass.tourneymanager.utils.TourneyCalcAlgorithm;

public class ManagerMode extends Mode {
    public ManagerMode(Context context) {
        super(context);
    }

    public void addPlayerToSystem(String username, String name, String phoneNumber, Deck deck) {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Player, String> playerDao= dbHelper.getPlayerRuntimeExceptionDao();

        playerDao.create(new Player(username, name, phoneNumber, deck));

        OpenHelperManager.releaseHelper();
    }

    public void removePlayerFromSystem(String username) throws SQLException {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Player, String> playerDao= dbHelper.getPlayerRuntimeExceptionDao();

        DeleteBuilder db = playerDao.deleteBuilder();
        db.where().eq("username", username);
        playerDao.delete(db.prepare());

        OpenHelperManager.releaseHelper();
    }

    public Map<String, Integer> showTournamentInfo(int entranceFee, int numEntrants, int housePercentage) {
        // Use hashmap instead of array for easier reference
        Map<String, Integer> tournamentInfo = new HashMap<String, Integer>();

        int totalAmount = TourneyCalcAlgorithm.calcTotalAmount(entranceFee, numEntrants);
        int houseCut = TourneyCalcAlgorithm.calcHouseCut(totalAmount, housePercentage);
        int[] prizes = TourneyCalcAlgorithm.calcPrizes(totalAmount, houseCut);

        tournamentInfo.put("houseCut", houseCut);
        tournamentInfo.put("firstPrize", prizes[0]);
        tournamentInfo.put("secondPrize", prizes[1]);
        tournamentInfo.put("thirdPrize", prizes[2]);

        return tournamentInfo;
    }

    public String getOngoingTournamentCurrentRound() {
        return this.ongoingTournament.getCurrentRoundInString();
    }

    public void createAndStartTournament(Map<String, Integer> tournamentInfo, List<Player> playerList) {
        //TODO: change usernames to be stored as many to many relationship instead of string
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();

        Tournament tournament = new Tournament(tournamentInfo.get("houseCut"),
                tournamentInfo.get("firstPrize"), tournamentInfo.get("secondPrize"),
                tournamentInfo.get("thirdPrize"), playerList.size());

        tournamentDao.create(tournament);

        OpenHelperManager.releaseHelper();

        initializeMatches(tournament, playerList);
    }

    public void endOngoingTournament() {
        if (this.ongoingTournament == null) {
            throw new IllegalStateException("There is no ongoing tournament.");
        }

        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();

        //TODO: check if tournament has ended prematurely and money needs to be refunded
        if (this.ongoingTournament.didEndedPrematurely()) {
            this.ongoingTournament.endTournamentPrematurely();
        }
        else {
            RuntimeExceptionDao<Match, Integer> matchDao= dbHelper.getMatchRuntimeExceptionDao();
            RuntimeExceptionDao<Player, String> playerDao= dbHelper.getPlayerRuntimeExceptionDao();
            try {
                Match finalMatch = matchDao.queryBuilder().where()
                        .eq("tournament_id", ongoingTournament.getId().toString()).and()
                        .eq("round", 2).query().get(0);
                Match thirdMatch = matchDao.queryBuilder().where()
                        .eq("tournament_id", ongoingTournament.getId().toString()).and()
                        .eq("round", 3).query().get(0);

                Player firstPlace = playerDao.queryForId(finalMatch.getWinner().getUsername());
                Player secondPlace = playerDao.queryForId(finalMatch.getLoser().getUsername());
                Player thirdPlace = playerDao.queryForId(thirdMatch.getWinner().getUsername());

                this.ongoingTournament.endTournament(firstPlace, secondPlace, thirdPlace);

                // Add prizes to players
                firstPlace.addTotalPrize(this.ongoingTournament.getFirstPrize());
                secondPlace.addTotalPrize(this.ongoingTournament.getSecondPrize());
                thirdPlace.addTotalPrize(this.ongoingTournament.getThirdPrize());

                playerDao.update(firstPlace);
                playerDao.update(secondPlace);
                playerDao.update(thirdPlace);
            }
            catch (SQLException e) {
                //TODO: handle error
                Log.e("End tournament", e.getStackTrace().toString());
            }
        }
        tournamentDao.update(this.ongoingTournament);

        OpenHelperManager.releaseHelper();
    }

    public void startMatch(Match match) {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Match, Integer> matchDao= dbHelper.getMatchRuntimeExceptionDao();

        match.startMatch();
        matchDao.update(match);

        OpenHelperManager.releaseHelper();
    }

    public void endMatch(Match match, Player winner) {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Match, Integer> matchDao= dbHelper.getMatchRuntimeExceptionDao();

        match.endMatch(winner);
        matchDao.update(match);

        // Decrement current round match count for tournament
        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();
        ongoingTournament.matchEnded();
        tournamentDao.update(ongoingTournament);

        OpenHelperManager.releaseHelper();

        // Next round only when there is no game left and also current round is not final
        if (!ongoingTournament.isGameLeftForCurrentRound() && (ongoingTournament.getCurrentRound() != 2)) {
            try {
                initializeNextRound();
            }
            catch (SQLException e) {
                //TODO: handle SQLException
                Log.e("initializeNextRound", e.getStackTrace().toString());
            }
        }
    }

    public List<Tournament> viewPastProfits() throws SQLException {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();

        List<Tournament> tournamentList = tournamentDao.queryBuilder()
                .where().eq("status", Tournament.TournamentStatus.COMPLETE).query();

        OpenHelperManager.releaseHelper();

        return tournamentList;
    }

    public MatrixCursor viewPrizesOfPlayer(String username) throws SQLException {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();

        String[] columns = {"_id", "end_date", "prize"};
        MatrixCursor cursor = new MatrixCursor(columns);
        Integer id = 1;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        // Case where player is first place
        List<Tournament> tournamentList = tournamentDao.queryBuilder()
                .where().eq("firstWinner_id", username).query();
        for (int i = 0; i < tournamentList.size(); i++) {
            Tournament tournament = tournamentList.get(i);
            cursor.addRow(new Object[] {id.toString(),
                    df.format(tournament.getEndDate()), tournament.getFirstPrize()});
            id++;
        }

        // Case where player is second place
        tournamentList = tournamentDao.queryBuilder()
                .where().eq("secondWinner_id", username).query();
        for (int i = 0; i < tournamentList.size(); i++) {
            Tournament tournament = tournamentList.get(i);
            cursor.addRow(new Object[] {id.toString(),
                    df.format(tournament.getEndDate()), tournament.getSecondPrize()});
            id++;
        }

        // Case where player is third place
        tournamentList = tournamentDao.queryBuilder()
                .where().eq("thirdWinner_id", username).query();
        for (int i = 0; i < tournamentList.size(); i++) {
            Tournament tournament = tournamentList.get(i);
            cursor.addRow(new Object[] {id.toString(),
                    df.format(tournament.getEndDate()), tournament.getThirdPrize()});
            id++;
        }

        return cursor;
    }

    private void initializeMatches(Tournament tournament, List<Player> playerList) {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Match, Integer> matchDao= dbHelper.getMatchRuntimeExceptionDao();

        // First round of tournament
        for (int i = 0; i < playerList.size() / 2; i++) {
            Match match = new Match(tournament, playerList.get(2 * i), playerList.get(2 * i + 1),
                    playerList.size());
            matchDao.create(match);
        }

        OpenHelperManager.releaseHelper();
    }

    private void initializeNextRound() throws SQLException {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Match, Integer> matchDao= dbHelper.getMatchRuntimeExceptionDao();

        Integer lastRound = this.ongoingTournament.getCurrentRound();
        this.ongoingTournament.nextRound();
        Integer nextRound = this.ongoingTournament.getCurrentRound();

        List<Match> matchList = matchDao.queryBuilder().where()
                .eq("tournament_id", ongoingTournament.getId().toString()).and()
                .eq("round", lastRound).query();

        for (int i = 0; i < matchList.size() / 2; i++) {
            Match match = new Match(this.ongoingTournament, matchList.get(2 * i).getWinner(),
                    matchList.get(2 * i + 1).getWinner(), nextRound);
            matchDao.create(match);
        }

        // If tournament is in finals, initialize 3rd place match
        if (nextRound == 2) {
            Match match = new Match(this.ongoingTournament, matchList.get(0).getLoser(),
                    matchList.get(1).getLoser(), 3);
            matchDao.create(match);
        }


        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();
        tournamentDao.update(ongoingTournament);

        OpenHelperManager.releaseHelper();
    }
    
    /*
 //Di added these methods for use
    public Map<Integer, Integer> showHouseProfit() throws SQLException{
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();

        List<Tournament> tournamentList = tournamentDao.queryBuilder()
                .where().eq("status", Tournament.TournamentStatus.COMPLETE).query();
        OpenHelperManager.releaseHelper();

        Map<Integer, Integer> houseProfit = new HashMap<Integer, Integer>();

        int n = tournamentList.size();
        //Query all complete tournaments, put the tournament ID and tournament profits in hashmap
        for (int i=0; i<n; i++){
            houseProfit.put(tournamentList.get(i).getId(),tournamentList.get(i).getTotalProfit());
        }
        return houseProfit;
    } */

    public Map<String, Integer> showPlayerTotalPrize() throws SQLException{
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Player, String> playerDao= dbHelper.getPlayerRuntimeExceptionDao();

        List<Player> playerList = playerDao.queryForAll();
        OpenHelperManager.releaseHelper();

        Map<String, Integer> playerTotalPrize = new HashMap<String, Integer>();

        int n = playerList.size();
        //Query all players, put the player username and player total prize in hashmap
        for (int i=0; i<n; i++){
            playerTotalPrize.put(playerList.get(i).getUsername(),playerList.get(i).getTotalPrize());
        }
        return playerTotalPrize;
    }
}

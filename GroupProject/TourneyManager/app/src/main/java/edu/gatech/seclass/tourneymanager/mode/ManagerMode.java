package edu.gatech.seclass.tourneymanager.mode;


import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.tourneymanager.db.DatabaseHelper;
import edu.gatech.seclass.tourneymanager.model.Deck;
import edu.gatech.seclass.tourneymanager.model.Match;
import edu.gatech.seclass.tourneymanager.model.Player;
import edu.gatech.seclass.tourneymanager.model.Tournament;
import edu.gatech.seclass.tourneymanager.utils.TourneyCalcAlgorithm;

public class ManagerMode {
    Context context;
    Tournament ongoingTournament;

    public ManagerMode(Context context) {
       this.context = context;
    }

    public boolean thereIsOngoingTournament() throws SQLException {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();

        List<Tournament> tournamentList = tournamentDao.queryBuilder()
                .where().eq("status", Tournament.TournamentStatus.ONGOING).query();

        OpenHelperManager.releaseHelper();

        if (tournamentList.size() == 0) {
            return false;
        }
        else {
            // Save for other use in the class
            ongoingTournament = tournamentList.get(0);

            return true;
        }
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

    public void createAndStartTournament(Map<String, Integer> tournamentInfo, List<Player> playerList) {
        //TODO: change usernames to be stored as many to many relationship instead of string
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();

        Tournament tournament = new Tournament(tournamentInfo.get("houseCut"),
                tournamentInfo.get("firstPrize"), tournamentInfo.get("secondPrize"),
                tournamentInfo.get("thirdPrize"));

        tournamentDao.create(tournament);

        RuntimeExceptionDao<Match, Integer> matchDao = dbHelper.getMatchRuntimeExceptionDao();
        matchDao.create(new Match(tournament, playerList.get(0), playerList.get(1)));

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

        this.ongoingTournament.endTournament();
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

    public void endMatch(Match match) {

    }

    public List<Tournament> viewPastProfits() throws SQLException {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();

        List<Tournament> tournamentList = tournamentDao.queryBuilder()
                .where().eq("status", Tournament.TournamentStatus.COMPLETE).query();

        OpenHelperManager.releaseHelper();

        return tournamentList;
    }

    private void initializeMatches(Tournament tournament, List<Player> playerList) {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Match, Integer> matchDao= dbHelper.getMatchRuntimeExceptionDao();

        // First round of tournament
        for (int i = 0; i < playerList.size() / 2; i++) {
            Match match = new Match(tournament, playerList.get(2 * i), playerList.get(2 * i + 1));
            matchDao.create(match);
        }

        OpenHelperManager.releaseHelper();
    }
}
package edu.gatech.seclass.tourneymanager.db;


import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import edu.gatech.seclass.tourneymanager.model.Deck;
import edu.gatech.seclass.tourneymanager.model.Match;
import edu.gatech.seclass.tourneymanager.model.Player;
import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.model.Tournament;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "tourneyManager.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Deck, String> deckDao =null;
    private Dao<Player, String> playerDao =null;
    private Dao<Tournament, Integer> tournamentDao =null;
    private Dao<Match, Integer> matchDao =null;
    private RuntimeExceptionDao<Deck, String> deckRuntimeDao = null;
    private RuntimeExceptionDao<Player, String> playerRuntimeDao = null;
    private RuntimeExceptionDao<Tournament, Integer> tournamentRuntimeDao = null;
    private RuntimeExceptionDao<Match, Integer> matchRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Deck.class);
            TableUtils.createTable(connectionSource, Player.class);
            TableUtils.createTable(connectionSource, Tournament.class);
            TableUtils.createTable(connectionSource, Match.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

        try {
            RuntimeExceptionDao<Deck, String> deckDao = getDeckRuntimeExceptionDao();
            deckDao.create(new Deck("Engineer"));
            deckDao.create(new Deck("Buzz"));
            deckDao.create(new Deck("Sideways"));
            deckDao.create(new Deck("Wreck"));
            deckDao.create(new Deck("T"));
            deckDao.create(new Deck("RAT"));

            Log.i(DatabaseHelper.class.getName(), "created Deck entries in onCreate");

            List<Deck> deckList = deckDao.queryForAll();

            RuntimeExceptionDao<Player, String> playerDao = getPlayerRuntimeExceptionDao();
            playerDao.create(new Player("player1", "Player One", "111-111-1111", deckList.get(0)));
            playerDao.create(new Player("player2", "Player Two", "222-222-2222", deckList.get(1)));
            playerDao.create(new Player("player3", "Player Three", "333-333-3333", deckList.get(2)));
            playerDao.create(new Player("player4", "Player Four", "444-444-4444", deckList.get(3)));
            playerDao.create(new Player("player5", "Player Five", "555-555-5555", deckList.get(4)));
            playerDao.create(new Player("player6", "Player Six", "666-666-6666", deckList.get(5)));
            playerDao.create(new Player("player7", "Player Seven", "777-777-7777", deckList.get(1)));
            playerDao.create(new Player("player8", "Player Eight", "888-888-8888", deckList.get(2)));
            playerDao.create(new Player("player9", "Player Nine", "111-111-1111", deckList.get(0)));
            playerDao.create(new Player("player10", "Player Ten", "222-222-2222", deckList.get(1)));
            playerDao.create(new Player("player11", "Player Eleven", "333-333-3333", deckList.get(2)));
            playerDao.create(new Player("player12", "Player Twelve", "444-444-4444", deckList.get(3)));
            playerDao.create(new Player("player13", "Player Thirteen", "555-555-5555", deckList.get(4)));
            playerDao.create(new Player("player14", "Player Fourteen", "666-666-6666", deckList.get(5)));
            playerDao.create(new Player("player15", "Player Fifteen", "777-777-7777", deckList.get(1)));
            playerDao.create(new Player("player16", "Player Sixteen", "888-888-8888", deckList.get(2)));

            Log.i(DatabaseHelper.class.getName(), "created Player entries in onCreate");

            List<Player> playerList = playerDao.queryForAll();

            RuntimeExceptionDao<Tournament, Integer> tournamentDao = getTournamentRuntimeExceptionDao();
            tournamentDao.create(new Tournament(100, 100, "222-333-4444"));

            Log.i(DatabaseHelper.class.getName(), "created Tournament entries in onCreate");

            List<Tournament> tournamentList = tournamentDao.queryForAll();

            RuntimeExceptionDao<Match, Integer> matchDao = getMatchRuntimeExceptionDao();
            matchDao.create(new Match(tournamentList.get(0), playerList.get(0), playerList.get(1)));

            Log.i(DatabaseHelper.class.getName(), "created Match entries in onCreate");
        } catch (RuntimeException e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Deck.class, true);
            TableUtils.dropTable(connectionSource, Player.class, true);
            TableUtils.dropTable(connectionSource, Tournament.class, true);
            TableUtils.dropTable(connectionSource, Match.class, true);

            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVersion + " to new "
                    + newVersion, e);
            throw new RuntimeException(e);
        }
    }

    public Dao<Deck, String> getDeckDao() throws SQLException {
        if (deckDao == null){
            deckDao= getDao(Deck.class);
        }
        return deckDao;
    }

    public Dao<Player, String> getPlayerDao() throws SQLException {
        if (playerDao == null){
            playerDao= getDao(Player.class);
        }
        return playerDao;
    }

    public Dao<Tournament, Integer> getTournamentDao() throws SQLException {
        if (tournamentDao == null){
            tournamentDao= getDao(Tournament.class);
        }
        return tournamentDao;
    }

    public Dao<Match, Integer> getMatchDao() throws SQLException {
        if (matchDao == null){
            matchDao= getDao(Match.class);
        }
        return matchDao;
    }

    public RuntimeExceptionDao<Deck, String> getDeckRuntimeExceptionDao(){
        if(deckRuntimeDao == null){
            deckRuntimeDao = getRuntimeExceptionDao(Deck.class);

        }
        return deckRuntimeDao;
    }

    public RuntimeExceptionDao<Player, String> getPlayerRuntimeExceptionDao(){
        if(playerRuntimeDao == null){
            playerRuntimeDao = getRuntimeExceptionDao(Player.class);

        }
        return playerRuntimeDao;
    }

    public RuntimeExceptionDao<Tournament, Integer> getTournamentRuntimeExceptionDao(){
        if(tournamentRuntimeDao == null){
            tournamentRuntimeDao = getRuntimeExceptionDao(Tournament.class);

        }
        return tournamentRuntimeDao;
    }

    public RuntimeExceptionDao<Match, Integer> getMatchRuntimeExceptionDao(){
        if(matchRuntimeDao == null){
            matchRuntimeDao = getRuntimeExceptionDao(Match.class);

        }
        return matchRuntimeDao;
    }

    @Override
    public void close() {
        super.close();
        deckDao = null;
        playerDao = null;
        tournamentDao = null;
        matchDao = null;
        deckRuntimeDao = null;
        playerRuntimeDao = null;
        tournamentRuntimeDao = null;
        matchRuntimeDao = null;
    }
}

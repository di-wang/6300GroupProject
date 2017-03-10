package edu.gatech.seclass.tourneymanager.mode;


import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import edu.gatech.seclass.tourneymanager.db.DatabaseHelper;
import edu.gatech.seclass.tourneymanager.model.Deck;
import edu.gatech.seclass.tourneymanager.model.Match;
import edu.gatech.seclass.tourneymanager.model.Player;
import edu.gatech.seclass.tourneymanager.model.Tournament;

public class PlayerMode extends Mode {
    public PlayerMode(Context context) {
        super(context);
    }

    public List<Player> showAllPlayersTotalPrizes() throws SQLException  {
        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Player, String> playerDao= dbHelper.getPlayerRuntimeExceptionDao();

        List<Player> playerList = playerDao.queryBuilder()
                .orderBy("totalPrize", false).query();

        OpenHelperManager.releaseHelper();

        return playerList;
    }

    public List<Match> showMatchList() throws SQLException  {
        thereIsOngoingTournament();

        if (this.ongoingTournament == null) {
            // This means no ongoingTournament, so should return empty string
            return Collections.<Match>emptyList();
        }

        DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        RuntimeExceptionDao<Match, Integer> matchDao= dbHelper.getMatchRuntimeExceptionDao();

        List<Match> matchList = matchDao.queryBuilder()
                .where().eq("tournament_id", this.ongoingTournament.getId()).query();

        OpenHelperManager.releaseHelper();

        return matchList;
    }
}
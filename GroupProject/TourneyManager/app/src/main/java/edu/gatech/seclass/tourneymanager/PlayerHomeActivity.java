package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.adapter.MatchAdapter;
import edu.gatech.seclass.tourneymanager.adapter.PlayerAdapter;
import edu.gatech.seclass.tourneymanager.mode.CurrentMode;
import edu.gatech.seclass.tourneymanager.model.Match;
import edu.gatech.seclass.tourneymanager.model.Player;

public class PlayerHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_home);

        //TODO: if there is no ongoing tournament
        try {
            boolean ongoingTournament = checkOngoingTournament();

            if (ongoingTournament) {
                whenOngoingTournament();
            }
            else {
                whenNoOngoingTournament();
            }
        } catch (SQLException e) {
            //TODO: handler error
        }
    }

    private boolean checkOngoingTournament() throws SQLException {
        return CurrentMode.getPlayerMode().thereIsOngoingTournament();
    }

    private void whenNoOngoingTournament() throws SQLException {
        View noOngoingTournamentView = findViewById(R.id.no_ongoing_tournament_view);
        noOngoingTournamentView.setVisibility(View.VISIBLE);

        View ongoingTournamentView = findViewById(R.id.ongoing_tournament_view);
        ongoingTournamentView.setVisibility(View.GONE);

        initializePlayerList();
    }

    private void whenOngoingTournament() throws SQLException {
        View noOngoingTournamentView = findViewById(R.id.no_ongoing_tournament_view);
        noOngoingTournamentView.setVisibility(View.GONE);

        View ongoingTournamentView = findViewById(R.id.ongoing_tournament_view);
        ongoingTournamentView.setVisibility(View.VISIBLE);

        initializeMatchList();
    }

    private void initializePlayerList() throws SQLException {
        List<Player> playerList = (List<Player>) getAllPlayerList();

        initializePlayerListView(playerList);
    }

    private void initializeMatchList() throws SQLException {
        List<Match> matchList = (List<Match>) getMatchList();

        initializeMatchListView(matchList);
    }

    private List<Player> getAllPlayerList() throws SQLException {
        return CurrentMode.getPlayerMode().showAllPlayersTotalPrizes();
    }

    private List<Match> getMatchList() throws SQLException {
        return CurrentMode.getPlayerMode().showMatchList();
    }

    private void initializePlayerListView(List<Player> playerList) {
        ListView listview = (ListView) findViewById(R.id.player_list);

        PlayerAdapter adapter = new PlayerAdapter(this, (ArrayList<Player>) playerList);

        listview.setAdapter(adapter);
    }

    private void initializeMatchListView(List<Match> matchList) {
        ListView listview = (ListView) findViewById(R.id.match_list);

        MatchAdapter adapter = new MatchAdapter(this, (ArrayList<Match>) matchList);

        listview.setAdapter(adapter);
    }
}

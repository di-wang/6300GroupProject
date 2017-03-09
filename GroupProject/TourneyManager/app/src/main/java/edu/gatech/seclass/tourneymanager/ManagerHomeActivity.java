package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.adapter.MatchAdapter;
import edu.gatech.seclass.tourneymanager.mode.CurrentMode;
import edu.gatech.seclass.tourneymanager.model.Match;
import edu.gatech.seclass.tourneymanager.model.Player;

public class ManagerHomeActivity extends AppCompatActivity {
    Match selectedMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home);

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

    public void showPlayerListButtonClickHandler(View view) {
        Intent intent = new Intent(this, PlayerListActivity.class);
        startActivity(intent);
    }

    public void startTournamentButtonClickHandler(View view) {
        Intent intent = new Intent(this, StartTournamentActivity.class);
        startActivity(intent);
    }

    public void addPlayerButtonClickHandler(View view) {
        Intent intent = new Intent(this, AddPlayerActivity.class);
        startActivity(intent);
    }

    public void endOngoingTournamentButtonClickHandler(View view) {
        CurrentMode.getManagerMode().endOngoingTournament();

        Intent intent = new Intent(this, ManagerHomeActivity.class);
        startActivity(intent);
    }

    public void viewPastProfitButtonClickHandler(View view) {
        Intent intent = new Intent(this, ViewPastProfitActivity.class);
        startActivity(intent);
    }

    public void startMatchButtonClickHandler(View view) {
        CurrentMode.getManagerMode().startMatch(this.selectedMatch);

        Intent intent = new Intent(this, ManagerHomeActivity.class);
        startActivity(intent);
    }

    public void endMatchButtonClickHandler(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.winner_spinner);

        Player winner = (Player) spinner.getSelectedItem();
        CurrentMode.getManagerMode().endMatch(this.selectedMatch, winner);

        Intent intent = new Intent(this, ManagerHomeActivity.class);
        startActivity(intent);
    }

    private boolean checkOngoingTournament() throws SQLException {
        return CurrentMode.getManagerMode().thereIsOngoingTournament();
    }

    private void whenNoOngoingTournament() throws SQLException {
        View noOngoingTournamentView = findViewById(R.id.manager_no_ongoing_tournament_view);
        noOngoingTournamentView.setVisibility(View.VISIBLE);

        View ongoingTournamentView = findViewById(R.id.manager_ongoing_tournament_view);
        ongoingTournamentView.setVisibility(View.GONE);
    }

    private void whenOngoingTournament() throws SQLException {
        View noOngoingTournamentView = findViewById(R.id.manager_no_ongoing_tournament_view);
        noOngoingTournamentView.setVisibility(View.GONE);

        View ongoingTournamentView = findViewById(R.id.manager_ongoing_tournament_view);
        ongoingTournamentView.setVisibility(View.VISIBLE);

        // Show current round
        Integer currentRound = CurrentMode.getManagerMode().getOngoingTournamentCurrentRound();
        TextView ongoingTournamentStatusView = (TextView) findViewById(R.id.ongoingTournamentStatusTextView);
        ongoingTournamentStatusView.setText(ongoingTournamentStatusView.getText() + currentRound.toString());

        initializeMatchList();
    }

    private void initializeMatchList() throws SQLException {
        List<Match> matchList = (List<Match>) getMatchList();

        initializeMatchListView(matchList);
    }

    private List<Match> getMatchList() throws SQLException {
        return CurrentMode.getPlayerMode().showMatchList();
    }

    private void initializeMatchListView(List<Match> matchList) {
        ListView listview = (ListView) findViewById(R.id.manager_match_list);

        MatchAdapter adapter = new MatchAdapter(this, (ArrayList<Match>) matchList);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Match match = (Match) parent.getItemAtPosition(position);

                View startMatchView = (View) findViewById(R.id.start_match_linear_layout);
                View endMatchView = (View) findViewById(R.id.end_match_linear_layout);

                if (match.isMatchReady()) {
                    startMatchView.setVisibility(View.VISIBLE);
                    endMatchView.setVisibility(View.GONE);
                }
                else if (match.isMatchOngoing()) {
                    startMatchView.setVisibility(View.GONE);
                    endMatchView.setVisibility(View.VISIBLE);

                    updateMatchPlayersSpinner(match.getPlayers());
                }
                else {
                    startMatchView.setVisibility(View.GONE);
                    endMatchView.setVisibility(View.GONE);
                }

                selectedMatch = match;
            }
        });
    }

    private void updateMatchPlayersSpinner(List<Player> players) {
        Spinner spinner = (Spinner) findViewById(R.id.winner_spinner);

        ArrayAdapter<Player> dataAdapter = new ArrayAdapter<Player>(this,
                android.R.layout.simple_spinner_item, players);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}

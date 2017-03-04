package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.sql.SQLException;

import edu.gatech.seclass.tourneymanager.mode.CurrentMode;

public class ManagerHomeActivity extends AppCompatActivity {

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
    }
}

package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.tourneymanager.db.DatabaseHelper;
import edu.gatech.seclass.tourneymanager.mode.CurrentMode;
import edu.gatech.seclass.tourneymanager.model.Deck;
import edu.gatech.seclass.tourneymanager.model.Player;

public class StartTournamentActivity extends OrmLiteBaseActivity<DatabaseHelper> {
    List<Player> playerList = new ArrayList<Player>();
    List<Player> availablePlayers;
    Spinner spinner;
    boolean tournamentInfoErrorFlag;
    Map<String, Integer> tournamentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_tournament);

        // For reference in other methods
        spinner = (Spinner) findViewById(R.id.player_spinner);

        // All the players in the database would be available players
        RuntimeExceptionDao<Player, String> playerDao = getHelper().getPlayerRuntimeExceptionDao();
        this.availablePlayers = playerDao.queryForAll();

        updatePlayerSpinner();
    }

    public void addPlayerButtonClickHandler(View view) {
        if (availablePlayers.size() > 0) {
            addSelectedPlayer();

            updateAvailablePlayers();

            updatePlayerListTextView();
            updatePlayerSpinner();
        }
    }

    public void showTournamentInfoButtonClickHandler(View view) {
        tournamentInfoErrorFlag = false;

        int housePercentage = getHousePercentageFromEditText();
        int entrancePrice = getEntrancePriceFromEditText();
        int numberOfEntrants = getNumberOfEntrants();

        if (!this.tournamentInfoErrorFlag) {
            this.tournamentInfo = CurrentMode.getManagerMode().showTournamentInfo(
                    entrancePrice, numberOfEntrants, housePercentage
            );

            updateTournamentInfoTextViews(this.tournamentInfo);

            Button startTournamentButton = (Button) findViewById(R.id.startTournamentButton);
            startTournamentButton.setClickable(true);
        }
    }

    public void startTournamentButtonClickHandler(View view) {
        CurrentMode.getManagerMode().createAndStartTournament(this.tournamentInfo, playerList);

        Intent intent = new Intent(this, ManagerHomeActivity.class);
        startActivity(intent);
    }

    private void updatePlayerSpinner() {
        ArrayAdapter<Player> dataAdapter = new ArrayAdapter<Player>(this,
                android.R.layout.simple_spinner_item, this.availablePlayers);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void addSelectedPlayer() {
        Player player = (Player) spinner.getSelectedItem();
        playerList.add(player);
    }

    private void updateAvailablePlayers() {
        // Cannot have already selected player in the list
        availablePlayers.remove(spinner.getSelectedItemPosition());
    }

    private void updatePlayerListTextView() {
        TextView playerListTextView = (TextView) findViewById(R.id.playerListTextView);
        playerListTextView.setText(TextUtils.join(", ", playerList));
    }

    private int getHousePercentageFromEditText() {
        EditText housePercentageEditText = (EditText) findViewById(R.id.housePercentageEditText);

        try {
            int housePercentage = Integer.parseInt(housePercentageEditText.getText().toString());
            if (housePercentage <= 0 || housePercentage >= 100) {
                housePercentageEditText.setError("House percentage must be an integer between 0 and 100");
                this.tournamentInfoErrorFlag = true;
            }

            return housePercentage;
        }
        catch (NumberFormatException e) {
            housePercentageEditText.setError("House percentage must be an integer between 0 and 100");
            this.tournamentInfoErrorFlag = true;
            return 0;
        }
    }

    private int getEntrancePriceFromEditText() {
        EditText entryPriceEditText = (EditText) findViewById(R.id.entryPriceEditText);

        try {
            int entryPrice = Integer.parseInt(entryPriceEditText.getText().toString());
            if (entryPrice <= 0) {
                entryPriceEditText.setError("Entrance fee must be a positive integer");
                this.tournamentInfoErrorFlag = true;
            }

            return entryPrice;
        }
        catch (NumberFormatException e) {
            entryPriceEditText.setError("Entrance fee must be a positive integer");
            this.tournamentInfoErrorFlag = true;

            return 0;
        }
    }

    private int getNumberOfEntrants() {
        int numberOfEntrants = this.playerList.size();

        if (numberOfEntrants != 8 && numberOfEntrants != 16) {
            TextView playerListTextView = (TextView) findViewById(R.id.numEntrantsErrorTextView);
            playerListTextView.setVisibility(View.VISIBLE);
            this.tournamentInfoErrorFlag = true;
        }
        else {
            TextView playerListTextView = (TextView) findViewById(R.id.numEntrantsErrorTextView);
            playerListTextView.setVisibility(View.GONE);
        }

        return numberOfEntrants;
    }

    private void updateTournamentInfoTextViews(Map<String, Integer> tournamentInfo) {
        TextView houseCutTextView = (TextView) findViewById(R.id.houseCutTextView);
        TextView firstPrizeTextView = (TextView) findViewById(R.id.firstPrizeTextView);
        TextView secondPrizeTextView = (TextView) findViewById(R.id.secondPrizeTextView);
        TextView thirdPrizeTextView = (TextView) findViewById(R.id.thirdPrizeTextView);

        houseCutTextView.setText(tournamentInfo.get("houseCut").toString());
        firstPrizeTextView.setText(tournamentInfo.get("firstPrize").toString());
        secondPrizeTextView.setText(tournamentInfo.get("secondPrize").toString());
        thirdPrizeTextView.setText(tournamentInfo.get("thirdPrize").toString());
    }
}

package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.db.DatabaseHelper;
import edu.gatech.seclass.tourneymanager.mode.CurrentMode;
import edu.gatech.seclass.tourneymanager.model.Deck;
import edu.gatech.seclass.tourneymanager.model.Player;

public class StartTournamentActivity extends OrmLiteBaseActivity<DatabaseHelper> {
    List<Player> playerList = new ArrayList<Player>();
    List<Player> availablePlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_tournament);

        RuntimeExceptionDao<Player, String> playerDao = getHelper().getPlayerRuntimeExceptionDao();
        this.availablePlayers = playerDao.queryForAll();

        updatePlayerSpinner();
    }

    public void addPlayerButtonClickHandler(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.player_spinner);
        Player player = (Player) spinner.getSelectedItem();

        playerList.add(player);
        availablePlayers.remove(spinner.getSelectedItemPosition());

        TextView playerListTextView = (TextView) findViewById(R.id.playerListTextView);
        playerListTextView.setText(TextUtils.join(", ", playerList));

        updatePlayerSpinner();
    }

    private void updatePlayerSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.player_spinner);

        ArrayAdapter<Player> dataAdapter = new ArrayAdapter<Player>(this,
                android.R.layout.simple_spinner_item, this.availablePlayers);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}

package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.sql.SQLException;

import edu.gatech.seclass.tourneymanager.mode.CurrentMode;
import edu.gatech.seclass.tourneymanager.mode.ManagerMode;

public class PlayerProfileActivity extends AppCompatActivity {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);

        // Obtain user profile
        Intent intent = getIntent();
        String username = intent.getStringExtra(PlayerListActivity.PLAYER_USERNAME);
        String name = intent.getStringExtra(PlayerListActivity.PLAYER_NAME);
        String phoneNumber = intent.getStringExtra(PlayerListActivity.PLAYER_PHONE_NUMBER);
        String deck = intent.getStringExtra(PlayerListActivity.PLAYER_DECK);

        // Store username for use in class
        this.username = username;

        // Display user profile
        TextView usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        TextView phoneNumberTextView = (TextView) findViewById(R.id.phoneNumberTextView);
        TextView deckTextView = (TextView) findViewById(R.id.deckTextView);

        usernameTextView.setText(username);
        nameTextView.setText(name);
        phoneNumberTextView.setText(phoneNumber);
        deckTextView.setText(deck);

        ListView listview = (ListView) findViewById(R.id.player_prizes_list);

        String[] columns = {"_id", "name", "prize"};
        MatrixCursor cursor = new MatrixCursor(columns);
        cursor.addRow(new Object[] { "1", "Player1", "$100" });
        cursor.addRow(new Object[] { "2", "Player2", "$200" });
        cursor.addRow(new Object[] { "3", "Player3", "$300" });

        String[] fromColumns = {"name", "prize"};
        int[] toViews = {R.id.player_name_text_view, R.id.total_prize_text_view};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.player_list_row, cursor, fromColumns, toViews, 0);

        listview.setAdapter(adapter);
    }

    public void removePlayerButtonClickHandler(View view) throws SQLException {
        CurrentMode.getManagerMode().removePlayerFromSystem(this.username);

        Intent intent = new Intent(this, PlayerListActivity.class);
        startActivity(intent);
    }
}

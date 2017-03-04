package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.adapter.PlayerAdapter;
import edu.gatech.seclass.tourneymanager.db.DatabaseHelper;
import edu.gatech.seclass.tourneymanager.model.Deck;
import edu.gatech.seclass.tourneymanager.model.Player;

public class PlayerListActivity extends OrmLiteBaseActivity<DatabaseHelper> {
    public static final String PLAYER_USERNAME = "edu.gatech.seclass.tourneymanager.PLAYER_USERNAME";
    public static final String PLAYER_NAME = "edu.gatech.seclass.tourneymanager.PLAYER_NAME";
    public static final String PLAYER_PHONE_NUMBER = "edu.gatech.seclass.tourneymanager.PLAYER_PHONE_NUMBER";
    public static final String PLAYER_DECK = "edu.gatech.seclass.tourneymanager.PLAYER_DECK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        ListView listview = (ListView) findViewById(R.id.playerlist);

        RuntimeExceptionDao<Player, String> playerDao = getHelper().getPlayerRuntimeExceptionDao();

        ArrayList<Player> playerList = (ArrayList<Player>) playerDao.queryForAll();


        PlayerAdapter adapter = new PlayerAdapter(this, playerList);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Player player = (Player) parent.getItemAtPosition(position);

                Intent intent = new Intent(PlayerListActivity.this, PlayerProfileActivity.class);
                intent.putExtra(PLAYER_USERNAME, player.getUsername());
                intent.putExtra(PLAYER_NAME, player.getName());
                intent.putExtra(PLAYER_PHONE_NUMBER, player.getPhoneNumber());
                intent.putExtra(PLAYER_DECK, player.getDeck().toString());
                startActivity(intent);
            }
        });
    }
}

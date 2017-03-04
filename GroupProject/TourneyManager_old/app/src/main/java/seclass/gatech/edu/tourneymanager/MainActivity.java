package seclass.gatech.edu.tourneymanager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.List;

import seclass.gatech.edu.tourneymanager.data.DatabaseHelper;
import seclass.gatech.edu.tourneymanager.data.Player;
import seclass.gatech.edu.tourneymanager.data.Tournament;

public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {
    DatabaseHelper dbHelper;
    private Button addPlayer, addTournament, viewPlayer,viewTournament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*// Initialization of all four buttons of the Main screen
        addPlayer = (Button) findViewById(R.id.addPlayer);
        addTournament = (Button) findViewById(R.id.addTournament);
        viewPlayer = (Button) findViewById(R.id.viewPlayer);
        viewTournament = (Button) findViewById(R.id.viewTournament);*/
/* Please below is a list of methods for manager that directly operate on database. Right now, I dont have the UI, and there actually should intents passed between activities.
So below is basically a list of the methods, that can be associated with each button, and can be copied into other activities for use.*/

//initialize : uncomment when you want to use and initialize it on your machine
      try{
            initialize();
        } catch (SQLException e){
            e.printStackTrace();
        }


//add player
        try{
            addPlayer(00, "newly added player", "180088888", "deck1");
        } catch (SQLException e){
            e.printStackTrace();
        }


//add tournament
       try{
            addTournament(1111, 9,10,"team75");
        } catch (SQLException e){
            e.printStackTrace();
        }



    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    public void initialize() throws SQLException {
        //call dbHelper class and create the RunTime object
        dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        RuntimeExceptionDao<Player, Integer> playerDao= dbHelper.getPlayerRuntimeExceptionDao();

        //create
        playerDao.create(new Player(1, "Di"));
        playerDao.create(new Player(2, "Adric"));
        playerDao.create(new Player(3, "Andrew"));
        playerDao.create(new Player(4, "Ed"));

        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();

        tournamentDao.create(new Tournament(1));
        tournamentDao.create(new Tournament(2));

        //query
        List<Player> playerlist = playerDao.queryForAll();
        Log.d("player", playerlist.toString());
        List<Tournament> tournamentlist = tournamentDao.queryForAll();
        Log.d("tournamentlist", tournamentlist.toString());
        OpenHelperManager.releaseHelper();

    }
    public void addPlayer(int id, String name, String phonenumber, String deck) throws SQLException {
        //call dbHelper class and create the RunTime object
        dbHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        RuntimeExceptionDao<Player, Integer> playerDao= dbHelper.getPlayerRuntimeExceptionDao();
        //create
        playerDao.create(new Player(id, name, phonenumber, deck));
        //query
        List<Player> playerlist = playerDao.queryForAll();
        Log.d("player", playerlist.toString());

        OpenHelperManager.releaseHelper();

    }

    public void addTournament(int id, int houseCut, int entryPrice, String allUsername) throws SQLException {
        //call dbHelper class and create the RunTime object

        RuntimeExceptionDao<Tournament, Integer> tournamentDao= dbHelper.getTournamentRuntimeExceptionDao();

        tournamentDao.create(new Tournament(id, houseCut, entryPrice, allUsername));
        //query
        List<Tournament> tournamentlist = tournamentDao.queryForAll();
        Log.d("tournamentlist", tournamentlist.toString());
        OpenHelperManager.releaseHelper();

    }


   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}

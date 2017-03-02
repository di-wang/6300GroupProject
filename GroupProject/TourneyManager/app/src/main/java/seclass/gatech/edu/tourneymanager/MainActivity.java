package seclass.gatech.edu.tourneymanager;
import android.os.Bundle;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.List;

import seclass.gatech.edu.tourneymanager.data.DatabaseHelper;
import seclass.gatech.edu.tourneymanager.data.Player;
import seclass.gatech.edu.tourneymanager.data.Tournament;

;

public class MainActivity extends OrmLiteBaseActivity<DatabaseHelper> {
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            doNoteDataStuff();
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

    public void doNoteDataStuff() throws SQLException {
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

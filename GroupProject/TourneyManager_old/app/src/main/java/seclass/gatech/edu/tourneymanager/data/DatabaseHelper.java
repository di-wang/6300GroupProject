//This helper file converts SQL process or query into Java methods ready to call

package seclass.gatech.edu.tourneymanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import seclass.gatech.edu.tourneymanager.R;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "db";
	private static final int DATABASE_VERSION = 1;

	private Dao<Player, Integer> playerDao =null;
	private Dao<Tournament, Integer> tournamentDao =null;
	private RuntimeExceptionDao<Player, Integer> playerRuntimeDao = null;
	private RuntimeExceptionDao<Tournament, Integer> tournamentRuntimeDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource coonectionSource) {
		// TODO Auto-generated method stub
		try {
			TableUtils.createTable(connectionSource, Player.class);
			TableUtils.createTable(connectionSource, Tournament.class);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(DatabaseHelper.class.getName(), "Unable to create databases", e);
		}
	}
	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		try {
			TableUtils.dropTable(connectionSource, Player.class, true);
			TableUtils.dropTable(connectionSource, Tournament.class, true);
			onCreate(database, connectionSource);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVersion + " to new "
					+ newVersion, e);
		}
	}
	

	public Dao<Player, Integer> getPlayerDao() throws SQLException {
		if (playerDao == null){
			playerDao= getDao(Player.class);
		}
		return playerDao;
	}

	public Dao<Tournament, Integer> getTournamentDao() throws SQLException {
		if (tournamentDao == null){
			tournamentDao= getDao(Tournament.class);
		}
		return tournamentDao;
	}

	public RuntimeExceptionDao<Player, Integer> getPlayerRuntimeExceptionDao(){
		if(playerRuntimeDao == null){
			playerRuntimeDao = getRuntimeExceptionDao(Player.class);

		}
		return playerRuntimeDao;
	}


	public RuntimeExceptionDao<Tournament, Integer> getTournamentRuntimeExceptionDao(){
		if(tournamentRuntimeDao == null){
			tournamentRuntimeDao = getRuntimeExceptionDao(Tournament.class);

		}
		return tournamentRuntimeDao;
	}
}

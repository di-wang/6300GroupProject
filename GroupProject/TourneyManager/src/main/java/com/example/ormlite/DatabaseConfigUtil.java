// write table into txt file

package com.example.ormlite;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
	
	private static final Class<?>[] classes = new Class[]{Player.class, Tournament.class};

	public static void main(String[] args) throws SQLException, IOException{
		
		writeConfigFile("ormlite_config.txt", classes);
		
		
	}
}

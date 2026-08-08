package com.creatio.crm.framework.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtils {

	// method to run our query with connected database stored in 'config.properties'
	// file
	public static ResultSet getTable(String query) {
		CachedRowSet queryData = null;
		Properties prop = PropUtils.propReadData("config.properties");
		String db_url = prop.getProperty("db_url");
		String db_username = prop.getProperty("db_username");
		String db_password = prop.getProperty("db_password");
		try (Connection connectDB = DriverManager.getConnection(db_url, db_username, db_password);
				ResultSet liveQueryData = connectDB.createStatement().executeQuery(query)) {
			// copying liveData
			queryData = RowSetProvider.newFactory().createCachedRowSet();
			queryData.populate(liveQueryData);
		}   // try block finish means connection is lost now too
		catch (SQLException e) {
			e.printStackTrace();
		}
		return queryData;
	}
}
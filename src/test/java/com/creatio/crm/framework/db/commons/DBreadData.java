package com.creatio.crm.framework.db.commons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creatio.crm.framework.utilities.DBUtils;

public class DBreadData {
	
	//method to get query data from a table
	public static List<Map<String , String>> dbReadData(String query) throws SQLException{
		
		List<Map<String , String>> list = new ArrayList<>();
	    //getting the table first
		ResultSet table = DBUtils.getTable(query);
		while(table.next()){
			Map<String , String> cellData = new HashMap<>();
			for(int column = 1 ; column <= table.getMetaData().getColumnCount() ; column++) {
				String columnName = table.getMetaData().getColumnName(column);
				String columnData = table.getString(columnName);
				cellData.put(columnName, columnData);
			}
			list.add(cellData);
		}
		return list;
	}

}

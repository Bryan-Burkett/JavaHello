package com.srk.pkg;

import java.util.List;

public interface TimeSeriesDatabaseServiceSession {
	public void testConnection() throws Exception;
	public List<Object> getDeviceDataById(String id) throws Exception;
	public String insertData(TimeSeriesDatabaseServiceData data) throws Exception;
	public List<String>	removeData(String id) throws Exception;
	public String dropTable(String tableName) throws Exception;
	public List<TimeSeriesDatabaseServiceData> listAllDevices() throws Exception;

}

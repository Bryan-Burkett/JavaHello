package com.srk.pkg;

import java.util.List;

public interface TimeSeriesDatabaseServiceSession {
	public void testConnection() throws Exception;
	public List<Object> getDeviceDataById(String id) throws Exception;
	public String putDeviceData(TimeSeriesDatabaseServiceData data) throws Exception;
	public List<String>	removeDeviceData(String id) throws Exception;
	public List<String> removeAllDevices() throws Exception;
	public List<TimeSeriesDatabaseServiceData> listAllDevices() throws Exception;

}

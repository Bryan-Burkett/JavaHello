package com.srk.pkg;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import com.srk.pkg.TimeSeriesDatabaseServiceData;
import com.ibm.bluemix.informix.Service;
import com.ibm.bluemix.informix.Services;
import com.informix.jdbc.IfxDriver;

//@author
//Kevin J. Smith

public class TimeSeriesDatabaseServiceJDBCSession implements Serializable, TimeSeriesDatabaseServiceSession{
	private static final long serialVersionUID = 959609439786983912L;
	
	public static final String customerTable = "customer_ts_data";
	public static final String baseTable = "ts_data";
	public static String url = "jdbc:informix-sqli://baratheon.lenexa.ibm.com:9088/sysmaster:INFORMIXSERVER=server1;user=informix;password=Ifmx4spd";
	public static String host = "baratheon.lenexa.ibm.com";
	public static int port = 9088;
	public static int mongoPort = 27017;
	public static String database = "sysmaster";
	public static String user = "informix";
	public static String password = "Ibm4pass";


	private Connection getConnection() throws Exception {
				//Service service = Services.getInstance().getAllServiceInfos().get(0);
				Properties prop = new Properties();
				/*
				if(!service.getHost().contains("localhost")) {
					prop.put("DB_LOCALE", "en_US.utf8");
					prop.put("CLIENT_LOCALE", "en_US.utf8");
				}
				*/
				Connection conn = null;
				try {
					conn = new IfxDriver().connect(url, prop);
					System.out.println("Connected");
				} catch(SQLException ex) {
					System.out.println("Execption in creating a Connection to: " + url);
					System.out.println("SQL Error: " + ex.getErrorCode());
					ex.printStackTrace();
					throw ex;
				}
				return conn;
	}
	
	public void testConnection() throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
		} finally {
			if(conn != null)
				conn.close();
		}
	}
	
	public List<Object> getDeviceDataById(String id) throws Exception {
		List<Object> returnList = new ArrayList<Object>();
		Connection conn = null;
		List<TimeSeriesDatabaseServiceData> data = new ArrayList<TimeSeriesDatabaseServiceData>();
		try {
			System.out.println("getDevideDataByIdMethod");
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			String sql = "select * from " + "ts_data" + " where loc_esi_id LIKE '" + id.trim() + "'";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				data.add(new TimeSeriesDatabaseServiceData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getTimestamp(4),rs.getBigDecimal(5)));
			}
			returnList.add(sql);
			returnList.add(data);
		} finally {
			if(conn != null)
				conn.close();
		}
		
		return returnList;
	}
	
	public void createTable(String tableName) throws Exception {
		//List<Object> returnList = new ArrayList<Object>();
		Connection conn = null;
		//List<TimeSeriesDatabaseServiceData> data = new ArrayList<TimeSeriesDatabaseServiceData>();
		try {
			System.out.println("createTableMethod");
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			String sql = "create table " + tableName + "(id varchar(255), measure_unit varchar(255), direction varchar(255), tstmp date, value float)";
			//" (id VARCHAR(255) not null, measure-unit VARCHAR(255), direction VARCHAR(255), tstmp Timestamp, value INTEGER, Primary Key (id))";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
		
		} catch (SQLException ex){
			System.out.println(ex.getMessage());
		}
		finally {
			if(conn != null)
				conn.close();
		}
	}
	
	public String putDeviceData(TimeSeriesDatabaseServiceData data) throws Exception {
		Connection conn = null;
		String sql = null;
		try {
			
			System.out.println("putDeviceDataMethod");
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			sql = "insert into " + "Test5" + " values(?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, data.id);
			statement.setString(2, data.measure_unit);
			statement.setString(3, data.direction);
			statement.setTimestamp(4, data.tstmp);
			statement.setBigDecimal(5, data.value);
			statement.executeUpdate();
			
		} finally {
			if(conn != null)
				conn.close();
		}
		
		return sql;
	}
	
	public List<String> removeDeviceData(String id) throws Exception {
		List<String> returnList = new ArrayList<String>();
		Connection conn = null;
		try {
			System.out.println("removeDeviceDataMethod");
			conn = getConnection();
			/*
			String sql = "delete from " + TimeSeriesDatabaseServiceJDBCSession.customerTable + " where loc_esi_id like '" + id.trim() + "'";
			returnList.add(sql);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			*/
			String sql = "delete from " + "Test5" +  " where id like '" + id.trim() + "'";
			returnList.add(sql);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
		} finally {
			if(conn != null)
				conn.close();
		}
		
		return returnList;
	}

	public List<String> removeAllDevices() throws Exception {
		List<String> returnList = new ArrayList<String>();
		Connection conn = null;
		try {
			System.out.println("removeAllDevices");
			conn = getConnection();
			
			String sql = "truncate table " + TimeSeriesDatabaseServiceJDBCSession.customerTable;
			returnList.add(sql);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			
			sql = "truncate table " + TimeSeriesDatabaseServiceJDBCSession.baseTable;
			returnList.add(sql);
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			
			conn.close();
		} finally {
			if(conn != null)
				conn.close();
		}
		
		return returnList;
	}

	public List<TimeSeriesDatabaseServiceData> listAllDevices() throws Exception {
		List<TimeSeriesDatabaseServiceData> data = new ArrayList<TimeSeriesDatabaseServiceData>();
		Connection conn = null;
		try {
			System.out.println("listAllDevicesMethod");
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			String sql = "select distinct id from " + "Test5";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				data.add(new TimeSeriesDatabaseServiceData(rs.getString(1),null,null,null,null));
			}
			
		} finally {
			if(conn != null)
				conn.close();
		}
		return data;
	}

}


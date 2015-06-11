package com.ibm.example;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.informix.jdbc.IfxDriver;
//import com.ibm.db2.jcc.DB2Driver;
import com.ibm.example.DataFormat;


//@author
//Kevin J. Smith

public class Commands implements Serializable {
	private static final long serialVersionUID = 959609439786983912L;
	
	//public static final String customerTable = "customer_ts_data";
	//public static final String baseTable = "ts_data";
	public static final String SQLIURL = "jdbc:informix-sqli://baratheon.lenexa.ibm.com:9088/sysmaster:INFORMIXSERVER=server1;user=informix;password=Ifmx4spd";
	public static final String DRDAURL = "jdbc:ibs://baratheon.lenexa.ibm.com:9088/sysmaster;user=informix;password=Ifmx4spd";
	
	public static String host = "baratheon.lenexa.ibm.com";
	public static int port = 9088;
	public static int mongoPort = 27017;
	public static String database = "sysmaster";
	public static String user = "informix";
	public static String password = "Ibm4pass";


	private Connection getConnection() throws Exception{
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
					//conn = new DB2Driver().connect(DRDAURL, prop);
					conn = new IfxDriver().connect(SQLIURL, prop);
				} catch(SQLException ex) {
					System.out.println("Execption in creating a Connection to: " + SQLIURL);
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
			System.out.println("Connected");
		} finally {
			if(conn != null)
				conn.close();
		}
	}
	
	public List<DataFormat> findId(String tableName, String id) throws Exception {
		Connection conn = null;
		List<DataFormat> data = new ArrayList<DataFormat>();
		try {
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			String sql = "select * from " + tableName + " where id LIKE '" + id.trim() + "'";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				data.add(new DataFormat(rs.getString(1),rs.getInt(2)));
			}
		} finally {
			if(conn != null)
				conn.close();
		}
		
		return data;
	}
	
	public void createTable(String tableName) throws Exception {
		//List<Object> returnList = new ArrayList<Object>();
		Connection conn = null;
		//List<TimeSeriesDatabaseServiceData> data = new ArrayList<TimeSeriesDatabaseServiceData>();
		try {
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			String sql = "create table " + tableName + "(id varchar(255),  value integer)";
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
	
	public String updateData(String tableName, String id, int value) throws Exception {
		//List<Object> returnList = new ArrayList<Object>();
		Connection conn = null;
		String sql = null;
		//List<TimeSeriesDatabaseServiceData> data = new ArrayList<TimeSeriesDatabaseServiceData>();
		try {
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			sql = "update " + tableName + " set value = ? where id  = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, value);
			statement.setString(2, id);
			statement.executeUpdate();
		
		} catch (SQLException ex){
			System.out.println(ex.getMessage());
		}
		finally {
			if(conn != null)
				conn.close();
		}
		return sql;
	}
	
	public String insertData(String tableName, DataFormat data) throws Exception {

		Connection conn = null;
		String sql = null;
		try {
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			sql = "insert into " + tableName + " values(?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, data.id);
			statement.setInt(2, data.value);
			statement.executeUpdate();
			
		} finally {
			if(conn != null)
				conn.close();
		}
		return sql;
	}
	
	public List<String> insertMultipleData(String tableName, List<DataFormat> listOfData) throws Exception {
		Connection conn = null;
		List<String> returnSQL = new ArrayList<String>();
		try {
		conn = getConnection();
		//Service service = Services.getInstance().getAllServiceInfos().get(0);
		for (DataFormat data : listOfData){
		String sql = "insert into " + tableName + " values(?,?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, data.id);
		statement.setInt(2, data.value);
		statement.executeUpdate();
		returnSQL.add(sql);
		}
		} finally {
		if(conn != null)
		conn.close();
		}
		return returnSQL;
		}
	
	public List<String> removeData(String tableName, String id) throws Exception {
		List<String> returnList = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = getConnection();
			/*
			String sql = "delete from " + TimeSeriesDatabaseServiceJDBCSession.customerTable + " where loc_esi_id like '" + id.trim() + "'";
			returnList.add(sql);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			*/
			String sql = "delete from " + tableName +  " where id like '" + id.trim() + "'";
			returnList.add(sql);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
		} finally {
			if(conn != null)
				conn.close();
		}
		
		return returnList;
	}

	public String dropTable(String tableName) throws Exception {
		//List<String> returnList = new ArrayList<String>();
		String sql = null;
		Connection conn = null;
		try {
			conn = getConnection();
			
			sql = "drop table " + tableName;
			//returnList.add(sql);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			/*
			sql = "truncate table " + TimeSeriesDatabaseServiceJDBCSession.baseTable;
			returnList.add(sql);
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			*/
			conn.close();
		} finally {
			if(conn != null)
				conn.close();
		}
		
		return sql;
	}

	public List<DataFormat> findAll(String tableName) throws Exception {
		//List<Object> returnList = new ArrayList<Object>();
		List<DataFormat> data = new ArrayList<DataFormat>();
		Connection conn = null;
		try {
			conn = getConnection();
			//Service service = Services.getInstance().getAllServiceInfos().get(0);
			String sql = "select * from " + tableName;
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				data.add(new DataFormat(rs.getString(1), rs.getInt("value")));
			}
			//returnList.add(sql);
			//returnList.add(data);
		} finally {
			if(conn != null)
				conn.close();
		}
		return data;
	}
}


package com.ibm.bluemix.informix;

import javax.json.JsonObject;


//import com.mongodb.util.Base64Codec;

public class Service {
	private final String database;
	private final String host;
	private final int jsonPort;
	private final String jsonUrl;
	private final String password;
	private final int port;
	private final String serviceName;
	private final String tableName;
	private final String url;
	private final String userName;
	private final int restPort;
	private final String restUrl;
	
	public Service(final String database, final String host, final int jsonPort, final String jsonUrl, final String password, final int port, final String serviceName, final String tableName, final String url, final String userName, final int restPort, final String restUrl) {
		this.database = database;
		this.host = host;
		this.jsonPort = jsonPort;
		this.jsonUrl = jsonUrl;
		this.password = password;
		this.port = port;
		this.serviceName = serviceName;
		this.tableName = tableName;
		this.url = url;
		this.userName = userName;
		this.restPort = restPort;
		this.restUrl = restUrl;
	}
	
	public String getDatabaseName() {
		return this.database;
	}
	
	public String getHost() {
		return this.host;
	}
	
	public int getJsonPort() {
		return this.jsonPort;
	}
	
	public String getJsonUrl() {
		return this.jsonUrl;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public String getServiceName() {
		return this.serviceName;
	}
	
	public String getTableName() {
		return this.tableName;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public int getRestPort() {
		return this.restPort;
	}
	
	public String getRestUrl() {
		return this.restUrl;
	}
	
	public static Service getNewService(String serviceName, JsonObject credentials) {
		return new Service(
				credentials.getString("db"),
				credentials.getString("host"),
				credentials.getInt("json_port"),
				credentials.getString("json_url"),
				credentials.getString("password"),
				credentials.getInt("sql_port"),
				serviceName,
				credentials.getString("table"),
				credentials.getString("sql_url"),
				credentials.getString("username"),
				credentials.getInt("rest_port"),
				credentials.getString("rest_url"));
	}
}
package com.ibm.bluemix.informix;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class Services {
	private static Services services = null;

	private final List<Service> servicesInfo;
	
	private Services() throws Exception {
		servicesInfo = getSerivceInformation();
	}

	public static Services getInstance() throws Exception {
		if(services == null) {
			services = new Services();
		}
		
		return services;
	}

	private List<Service> getSerivceInformation() throws Exception {
		Map<String, String> env = System.getenv();
		List<Service> info = new ArrayList<Service>();

		if (env.containsKey("VCAP_SERVICES")) {
			// we are running on cloud foundry, let's grab the service details from vcap_services
			StringReader stringReader = new StringReader(env.get("VCAP_SERVICES"));
			JsonReader jsonReader = Json.createReader(stringReader);
//			DBObject vcap = (DBObject) JSON.parse(env.get("VCAP_SERVICES"));
			JsonObject vcap = jsonReader.readObject();
			System.out.println("vcap: " + vcap);
			JsonArray service = null;

			// We don't know exactly what the service is called, but it will contain "timeseries"
			for (Object key : vcap.keySet()) {
				String keyStr = (String) key;
				if (keyStr.toLowerCase().contains("timeseries")) {
					service = vcap.getJsonArray(keyStr);
					break;
				}
			}

			if (service != null) {
				for(JsonValue serviceInstance : service) {
					JsonObject credentials = ((JsonObject)serviceInstance).getJsonObject("credentials");
					info.add(Service.getNewService(((JsonObject)serviceInstance).getString("name"),credentials));
				}
			} else {
				System.err.println("Service was not found.");
			}
		} else {
			System.err.println("VCAP environment variable was not found.");
		}
		return info;
	}

	public List<String> getDatabaseNames() {
		List<String> databases = new ArrayList<String>(this.servicesInfo.size());
		for(Service service : this.servicesInfo) {
			databases.add(service.getDatabaseName());
		}
		return databases;
	}

	public List<String> getDatabaseUrls() {
		List<String> urls = new ArrayList<String>();
		for(Service service : this.servicesInfo) {
			urls.add(service.getUrl());
		}	
		return urls;
	}

	public List<Service> getAllServiceInfos() {
		return this.servicesInfo;
	}

	public String getUrlByDatabaseName(String databaseName) {
		for(Service service : this.servicesInfo) {
			if(service.getDatabaseName().contentEquals(databaseName)) {
				return service.getUrl();
			}
		}
		throw new RuntimeException("Service does not exist with database named: " + databaseName + ".");
	}
}
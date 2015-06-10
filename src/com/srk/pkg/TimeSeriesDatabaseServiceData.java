package com.srk.pkg;

//@author
//Kevin J. Smith

public class TimeSeriesDatabaseServiceData {
	public final String id;
	public final Integer value;
	
	public TimeSeriesDatabaseServiceData(String id, Integer value) {
		this.id = id;
		this.value = value;
	}
	
	public String toString(){
		return "id: " + this.id + " value: " + this.value + " ";
	}
}
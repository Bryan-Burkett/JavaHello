package com.ibm.example;

//@author
//Kevin J. Smith

public class DataFormat {
	public final String id;
	public final int value;
	
	public DataFormat(String id, int value) {
		this.id = id;
		this.value = value;
	}
	
	public String toString(){
		return "id: " + this.id + " value: " + this.value + " ";
	}
}
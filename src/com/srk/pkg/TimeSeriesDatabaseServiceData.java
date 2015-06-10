package com.srk.pkg;

import java.math.BigDecimal;
import java.sql.Timestamp;

//@author
//Kevin J. Smith

public class TimeSeriesDatabaseServiceData {
	public final String id;
	public final String measure_unit;
	public final String direction;
	public final Timestamp tstmp;
	public final BigDecimal value;
	
	public TimeSeriesDatabaseServiceData(String id, String measure_unit, String direction, Timestamp tstmp, BigDecimal value) {
		this.id = id;
		this.measure_unit = measure_unit;
		this.direction = direction;
		this.tstmp = tstmp;
		this.value = value;
	}
	
	public String getId(){
		return this.id;
	}
}
package com.srk.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.math.*;
import com.srk.pkg.TimeSeriesDatabaseServiceData;


import com.ibm.bluemix.informix.*;

/**
 * Servlet implementation class Myservlet
 */
public class Myservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Myservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		TimeSeriesDatabaseServiceJDBCSession tsd = new TimeSeriesDatabaseServiceJDBCSession();
		try{
			
			//tsd.testConnection();
			
			tsd.createTable("table");
			
			List<TimeSeriesDatabaseServiceData> hardCodeData= new ArrayList<TimeSeriesDatabaseServiceData>();
			hardCodeData.add(new TimeSeriesDatabaseServiceData("TestTime",9));
			hardCodeData.add(new TimeSeriesDatabaseServiceData("TestTime1",90));
			hardCodeData.add(new TimeSeriesDatabaseServiceData("TestTime2",99));
			String sqlPMDD = tsd.putMultipleDeviceData(hardCodeData);
			out.print(sqlPMDD);
			
			String sqlPDD = tsd.putDeviceData(new TimeSeriesDatabaseServiceData("TestTime3", 9)); 
			out.print(sqlPDD);
			
			//sqlPDD = tsd.putDeviceData(new TimeSeriesDatabaseServiceData("TestTime1", 90)); 
			//out.print(sqlPDD);
			
			List<String> sqlRDD = tsd.removeDeviceData("TestTime4");
			out.print(sqlRDD);
			
			List<TimeSeriesDatabaseServiceData> listOfDevices = tsd.listAllDevices();
			for (TimeSeriesDatabaseServiceData obj : listOfDevices){
				out.print(obj.toString());
			}
			
			tsd.removeAllDevices();
			
			/*listOfDevices = tsd.listAllDevices();
			for (TimeSeriesDatabaseServiceData obj : listOfDevices){
				out.print(obj.toString());
			}*/
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

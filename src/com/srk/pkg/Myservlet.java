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
<<<<<<< HEAD
		out.print("Informix Database Test:    ");
		TimeSeriesDatabaseServiceJDBCSession tsd = new TimeSeriesDatabaseServiceJDBCSession();
		try{
			tsd.testConnection();
			tsd.createTable("Table_1");
			String sqlPDD = tsd.putDeviceData(new TimeSeriesDatabaseServiceData("TestTime","Seconds","North",new Timestamp(System.currentTimeMillis()),new BigDecimal(9.976))); 
			out.print(sqlPDD);
			sqlPDD = tsd.putDeviceData(new TimeSeriesDatabaseServiceData("TestTime1","Seconds","North",new Timestamp(System.currentTimeMillis()),new BigDecimal(9.976))); 
			out.print(sqlPDD);
			List<String> sqlRDD = tsd.removeDeviceData("TestTime1");
			out.print(sqlRDD);
			List<TimeSeriesDatabaseServiceData> ltest = tsd.listAllDevices();
			for (TimeSeriesDatabaseServiceData obj : ltest){
				out.print(obj.getId());
			}
			
=======
		out.print("Hello hello.");
		TimeSeriesDatabaseServiceJDBCSession jb = new TimeSeriesDatabaseServiceJDBCSession();
		try{
			out.print("Hella");
			//jb.testConnection();
//			jb.createTable("Test5");
			String ptest = jb.putDeviceData(new TimeSeriesDatabaseServiceData("TestTime","Seconds","North",new Timestamp(System.currentTimeMillis()),new BigDecimal(9.976))); 
			//out.print(ptest);
			//List<String> rtest = jb.removeDeviceData("Run Time");
			//out.print(rtest);
			
			List<TimeSeriesDatabaseServiceData> ltest = jb.listAllDevices();
			for (TimeSeriesDatabaseServiceData obj : ltest){
				out.print(obj.getId());
			}
>>>>>>> fd71fe4... Test
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

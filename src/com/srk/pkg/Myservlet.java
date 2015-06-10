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
		out.print("Hello hello.");
		TimeSeriesDatabaseServiceJDBCSession jb = new TimeSeriesDatabaseServiceJDBCSession();
		try{
			//jb.testConnection();
			//jb.createTable("Test5");
			//String ptest = jb.putDeviceData(new TimeSeriesDatabaseServiceData("TestTime", float 9.976)); 
			//out.print(ptest);
			//List<String> rtest = jb.removeDeviceData("Run Time");
			//out.print(rtest);
			/*
			List<TimeSeriesDatabaseServiceData> ltest = jb.listAllDevices();
			for (TimeSeriesDatabaseServiceData obj : ltest){
				out.print(obj.getId());
			}
			*/
			jb.removeAllDevices();
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

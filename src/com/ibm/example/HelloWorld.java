package com.ibm.example;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Myservlet
 */

public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//PrintWriter out = response.getWriter();
		Commands cmd = new Commands();
		try{
			//make sure we have a connection
			cmd.testConnection();
			
			//create a table (tableName)
			cmd.createTable("TestTable");
			
			//create a list of type DataFormat
			List<DataFormat> data= new ArrayList<DataFormat>();
			data.add(new DataFormat("TestId000",0));
			data.add(new DataFormat("TestId001",1));
			data.add(new DataFormat("TestId010",2));
			data.add(new DataFormat("TestId011",3));
			
			//insert a list (tableName, listName)
			cmd.insertMultipleData("TestTable", data);
			
			//insert an item (tableName, DataFormat)
			cmd.insertData("TestTable", new DataFormat("TestId100",4));
			
			//find an item by id (tableName, id)
			cmd.findId("TestTable", "TestId000");
			
			//remove item by id (tableName, id)
			cmd.removeData("TestTable","TestId001");
			
			//update item (tableName, id, updatedValue)
			cmd.updateData("TestTable", "TestId010", 6);
			
			//find all items (tableName)
			cmd.findAll("TestTable");
			
			//drop table (tableName)
			cmd.dropTable("TestTable");
			
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


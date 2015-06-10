package com.ibm.bluemix.informix;

import java.io.PrintStream;
import java.io.PrintWriter;

public class LogWriter {
	
	private static PrintWriter printWriter = null;
	private static PrintStream printStream = null;
	private static boolean isHttpOn = false;
	private static boolean hasWrittenHeader = false;
	
	private static final String httpItemStart = "<p>";
	private static final String httpItemEnd = "</p>";
	private static final String httpHeader ="<HTML><HEAD><TITLE>Collection Workload</TITLE></HEAD><body bgcolor='#f8f7cd'>";
	private static final String httpFooter = "</body>";
	private static final String httpTagStart = "<div>";
	private static final String httpTagEnd = "</div>";
	
	public static void setWriter(PrintWriter printWriter) {
			LogWriter.printWriter = printWriter;
	}

	public static void setWriter(PrintStream printStream) {
			LogWriter.printStream = printStream;
	}
	
	public static void setHttp(boolean isOn) {
			isHttpOn = isOn;
	}
	
	public static void INFO(String info) {
		printInfo(httpItemStart,info,httpItemEnd);
	}
	
	public static void BEGIN(String info) {
		if(!hasWrittenHeader && isHttpOn) {
			if(printWriter != null )
				printWriter.println(httpHeader);
			if(printStream != null )
				printStream.println(httpHeader);
			
			hasWrittenHeader = true;
		}
		
		printInfo(httpTagStart,info,"");
	}
	
	public static void END() {
		printInfo(httpTagEnd,"","");
	}
	
	public static void END(String info) {
		printInfo("",info,httpFooter);
		hasWrittenHeader = false;
	}

	private static void printInfo(String start, String info, String end) {
		if(printWriter != null) {
			if(isHttpOn) 
				printWriter.println(start + info + end);
			else
				printWriter.println(info);
		}
		
		if(printStream != null) {
			if(isHttpOn) 
				printStream.println(start + info + end);
			else
				printStream.println(info);
		}
	}	
}
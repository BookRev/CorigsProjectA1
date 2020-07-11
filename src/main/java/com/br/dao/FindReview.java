package com.br.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class FindReview {

	 private static final PythonInterpreter intr = new PythonInterpreter();
	    static String isbn1;
	    
	    public static ArrayList<String> FindReviews (String tmp) {
	    	ArrayList<String> ret = new ArrayList<String>(); 
	    	try{
	    		int i = 0;
	    		int bufferSize = 8 * 2048;
	    		System.out.println("start");
	    		String[] args1=new String[]{"python3","findrev.py",tmp};
	    		Process pr=Runtime.getRuntime().exec(args1);
	    		  BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(),"UTF8"),bufferSize);
	    		   String line;
	    			 while ((line = in.readLine()) != null) {
	    				 //line = line.replaceAll("[^\\x00-\\x7F]", "'");
	    				 line = line.replaceAll( "([\\ud800-\\udbff\\udc00-\\udfff])", "'");
	    				 line = line.replaceAll( "''", "'");
	    			 System.out.println(line);
	    			 ret.add(line);
	    			 i++;}
	    			 in.close();
	    			 pr.waitFor();
	    			 System.out.println("end");
	    			 } catch (Exception e) {
	    			e.printStackTrace();}
	    	if(ret.indexOf("review")==3)
	    		ret.add(3, "");
	    	else if(ret.size()<=3)
	    		 ret.clear();
	    	return ret;
	    }
	    
	  

	  
}
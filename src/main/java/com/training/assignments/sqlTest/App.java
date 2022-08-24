package com.training.assignments.sqlTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
	    Connection connection = null;
	    try {
	    	connection = DriverManager
	    			.getConnection("jdbc:mysql://localhost:3306/JDBCDemo", "root", "S2xj8!efD4m37");
	    	
	    	if(connection.isValid(0)) System.out.println("Connection Successful!");
	    	
	    } catch (SQLException e) {
	        System.out.println("Connection Failed!");
	        return;
	    } finally {
	        try
	        {
	          if(connection != null)
	            connection.close();
	          System.out.println("Connection closed !!");
	        } catch (SQLException e) {
	          e.printStackTrace();
	        }
	    }
    }
}
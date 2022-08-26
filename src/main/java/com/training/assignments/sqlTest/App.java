package com.training.assignments.sqlTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 
 *
 */
public class App 
{
	 
	public static void main( String[] args ) throws ClassNotFoundException, SQLException, IOException
    {
		
		configManager sqlConfig = new configManager("sql.cfg",'=','"');
		Map<String,String> sqlConfigMap = sqlConfig.getMap();
		
	    Connection connection = null;
	    Statement stmt = null;
	    try {
	    	connection = DriverManager
	    			.getConnection(sqlConfigMap.get("JDBC_URL"), sqlConfigMap.get("JDBC_USERNAME"), sqlConfigMap.get("JDBC_PASSWORD"));
	    	
	    	if(connection.isValid(0)) System.out.println("Connection Successful!");
	    	
	    	stmt = connection.createStatement();
	    	
	    	stmt.execute("DROP TABLE IF EXISTS `JDBCDemo`.`EMPLOYEE`");
	    	stmt.execute("DROP TABLE IF EXISTS `JDBCDemo`.`DEPARTMENT`");
	    	
	    	stmt.execute("CREATE  TABLE `JDBCDemo`.`EMPLOYEE`\n"
	    			+ "("
	    			+ "  `ID` INT NOT NULL DEFAULT 0 ,\n"
	    			+ "  `FIRST_NAME` VARCHAR(100) NOT NULL ,\n"
	    			+ "  `LAST_NAME` VARCHAR(100) NULL ,\n"
	    			+ "	 `DEPT_ID` INT NOT NULL DEFAULT 0 ,\n"
	    			+ "  `STAT_CD` TINYINT NOT NULL DEFAULT 0 "
	    			+ ");");
	    	
	    	stmt.execute("CREATE  TABLE `JDBCDemo`.`DEPARTMENT`\n"
	    			+ "("
	    			+ "  `ID` INT NOT NULL DEFAULT 0 ,\n"
	    			+ "  `DEPT_NAME` VARCHAR(100) NOT NULL ,\n"
	    			+ "  `STAT_CD` TINYINT NOT NULL DEFAULT 0 "
	    			+ ");");
	    	
	    	
	    	stmt.execute("INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,DEPT_ID,STAT_CD) VALUES (1,'Some','Body',1,5)");
	    	stmt.execute("INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,DEPT_ID,STAT_CD) VALUES (2,'Charles','Boeing',1,5)");
	    	stmt.execute("INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,DEPT_ID,STAT_CD) VALUES (3,'Bob','Bobbington',2,5)");
	    	stmt.execute("INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,DEPT_ID,STAT_CD) VALUES (4,'Fake','Name',1,5)");
	    	
	    	stmt.execute("INSERT INTO DEPARTMENT (ID,DEPT_NAME,STAT_CD) VALUES (1,'Development',5)");
	    	stmt.execute("INSERT INTO DEPARTMENT (ID,DEPT_NAME,STAT_CD) VALUES (2,'Mangement',5)");
	    	stmt.execute("INSERT INTO DEPARTMENT (ID,DEPT_NAME,STAT_CD) VALUES (3,'Public Relations',5)");
	    	
	    	ResultSet rs = stmt.executeQuery("SELECT EMPLOYEE.LAST_NAME, DEPARTMENT.ID\n"
	    			+ "FROM EMPLOYEE\n"
	    			+ "LEFT OUTER JOIN DEPARTMENT ON DEPARTMENT.ID=EMPLOYEE.DEPT_ID\n"
	    			+ "WHERE EMPLOYEE.DEPT_ID=1\n"
	    			+ "UNION\n"
	    			+ "SELECT EMPLOYEE.LAST_NAME, DEPARTMENT.ID\n"
	    			+ "FROM EMPLOYEE\n"
	    			+ "RIGHT OUTER JOIN DEPARTMENT ON DEPARTMENT.ID=EMPLOYEE.DEPT_ID\n"
	    			+ "WHERE EMPLOYEE.DEPT_ID=1;");
	        while(rs.next())
	        {
	          System.out.println(rs.getString(1));  
	          System.out.println(rs.getString(2));
	        }
	    	
	    	
	    } catch (SQLException e) {
	        System.out.println("Connection Failed!");
	        return;
	    } finally {
	        try
	        {
	          if(connection != null)
	        	stmt.close();
	            connection.close();
	          System.out.println("Connection closed !!");
	        } catch (SQLException e) {
	          e.printStackTrace();
	        }
	    }
    }
}
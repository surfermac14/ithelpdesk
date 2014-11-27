package com.ts.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


/**
 * Creates a database connection
 * @author Reena
 *
 */
public class DBConnectionManager {

	 private final static Logger log =
		        Logger.getLogger(DBConnectionManager.class.getName());

	private DBConnectionManager()
	{
		
		try{
			BasicConfigurator.configure();		
		}
		catch(Exception ex){
				
		}
	}

	public static  Connection getJDBCConnection()
	{
		String DATASOURCE_CONTEXT = "jdbc/mysqlds";
	      
	      Connection con = null;
	      try {
	    
	      	// Obtain our environment naming context
	        Context initialContext = new InitialContext();
	        Context envCtx = (Context) initialContext.lookup("java:comp/env");
	        
	        // Look up our data source
	        DataSource datasource = (DataSource)envCtx.lookup(DATASOURCE_CONTEXT);
	        if (datasource != null) {
	          con = datasource.getConnection();
	          log.debug("Got Connection");
	        }
	        else {
	        	con = getSimpleConnection();	
	        	log.debug("Got Connection");
	        }
	      }
	      catch ( NamingException ex ) {
	        log.error("Cannot get connection: " + ex);
	      }
	      catch(SQLException ex){
	        log.error("Cannot get connection: " + ex);
	      }
	      return con;
	}

		
	public static void rollbackJDBCConnection(final Connection conn)
	{
		if (conn != null){
			 try{
				conn.rollback();
			 }
			 catch (SQLException ex){
				log.error(conn, ex);
			 }
		}
	}
	
	public static void commitJDBCConnection(final Connection conn){
		if (conn != null){
			 try{
				conn.commit();
			 }
			 catch (SQLException ex){
				log.error(conn, ex);
			 }
		}
	}

	public static void closeJDBCConnection(final Connection conn){
		if (conn != null){
			 try{
				conn.close();
			 }
			 catch (SQLException ex){
				log.error(conn, ex);
			 }
		}
	}

	public static void closeStatement(final Statement stmt){
		if (stmt != null){
			 try{
				stmt.close();
			 }
			 catch (SQLException ex){
				log.error(stmt, ex);
			 }
		}
	}

	public static void closeResultSet(final ResultSet rs)
	{
		if (rs != null){
			 try{
				rs.close();
			 }
			 catch (SQLException ex){
				log.error(rs, ex);
			 }
		}
	}
	  public static Connection getSimpleConnection(){
	        String dbUrl = "jdbc:mysql://localhost:3306/tsfwdb";
	        Connection con = null;
	        try{
	        Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection (dbUrl, "root", "root");
	        }catch(Exception ex){
	        	log.error("Cannot get connection: " + ex);
	        }
	        return con;
	    }
}
package ithelp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManager {

	
	public DBConnectionManager() {
		// TODO Auto-generated constructor stub
	}
	
	public Connection getJDBCConnection(){
		 Connection con = null;
		return con;
	}
	public static Connection getSimpleConnection(){
		Connection con = null;
		String dbUrl = "jdbc:mysql://localhost:3307/ithelp";
		 try{
		        Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection (dbUrl, "root", "password");
		        }catch(Exception ex){
		        	
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
				//log.error(conn, ex);
			 }
		}
	}
	
	public static void commitJDBCConnection(final Connection conn){
		if (conn != null){
			 try{
				conn.commit();
			 }
			 catch (SQLException ex){
				//log.error(conn, ex);
			 }
		}
	}

	public static void closeJDBCConnection(final Connection conn){
		if (conn != null){
			 try{
				conn.close();
			 }
			 catch (SQLException ex){
				//log.error(conn, ex);
			 }
		}
	}

	public static void closeStatement(final Statement stmt){
		if (stmt != null){
			 try{
				stmt.close();
			 }
			 catch (SQLException ex){
				//log.error(stmt, ex);
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
				//log.error(rs, ex);
			 }
		}
	}
	
}

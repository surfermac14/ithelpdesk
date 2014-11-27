package com.ts.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;

import com.ts.dao.DBConnectionManager;
import com.ts.exception.ProjectApplicationException;
import com.ts.beans.UserBean; 


import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

/**
 *User Data Access Objects
 * Connects to data base and retrieves the user information. 
 * @author Reena
 *
 */
public class UserDAO {
	
		static final String SUCCESS = "success";
		static final String FAILURE = "failure";
		
		static Logger log = Logger.getLogger("com.a");
		
		public UserDAO(){
		
		}
				
		/**
		 * Deletes a user as per the userId
		 * @param email
		 * @return Success or Failure
		 */
		public String deleteUser(String email) throws ProjectApplicationException
		{
			String result = null;
			Connection con = DBConnectionManager.getSimpleConnection();		
			Statement st = null;
						
			try{
				con.setAutoCommit(false);
				st = con.createStatement();
		
				int row = st.executeUpdate("delete from user where email = '"+ email +"'") ;
				if(row != 0){
					log.info("Deleted user  " + email );
					result = SUCCESS;
				}else{
					result = FAILURE;
				}
			}	
			catch (SQLException ex){
				
				DBConnectionManager.rollbackJDBCConnection(con);
				result = FAILURE;
				log.error(ex);
			}
			finally
			{
				DBConnectionManager.commitJDBCConnection(con);
				DBConnectionManager.closeStatement(st);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;
		}

		/**
		 *Returns a collection of Users from the database
		 * @return collection of UserBean
		 */
		public Collection<UserBean> selectUsers()throws ProjectApplicationException{
			
			Collection<UserBean> users = new ArrayList<UserBean> ();
			
			//Create a Database Connection
			Connection con = DBConnectionManager.getSimpleConnection();		
			Statement st = null;
			ResultSet rs = null;
		
			
			try{
				con.setAutoCommit(false);
				st = con.createStatement();
		
				rs = st.executeQuery("select * from user") ;
						
				while (rs.next()){
					UserBean userBean = new UserBean();
					userBean.setName(rs.getString("name"));
					userBean.setAge(Integer.parseInt(rs.getString("age")));
					userBean.setAddress(rs.getString("address"));
					userBean.setPassword(rs.getString("password"));
					userBean.setEmail(rs.getString("email"));
					userBean.setUserId(rs.getString("userId"));		
					users.add(userBean);					
				}
										
			}catch (SQLException ex){
				
				DBConnectionManager.rollbackJDBCConnection(con);
				log.error(ex);
			}
			finally
			{
				DBConnectionManager.commitJDBCConnection(con);
				DBConnectionManager.closeStatement(st);
				DBConnectionManager.closeJDBCConnection(con);
			}
			
					
			return users;
			
		}
		
		/**
		 * Insert a user in the database
		 * @param newUser of type UserBean
		 * @return Success/ Failure
		 */
		public String createUser(UserBean newUser)throws ProjectApplicationException{
			
			String result = null;
			PreparedStatement stmtInsert = null;
			
			//Create a Database Connection
			Connection con = DBConnectionManager.getSimpleConnection();
			try{
				con.setAutoCommit(false);
						
				StringBuffer sbInsert = new StringBuffer();
												
				sbInsert.append("INSERT INTO ");
				//TABLE_NAME
				sbInsert.append("user");
				sbInsert.append(" ( name, email, password, age, address ) ");
				sbInsert.append(" VALUES (");
				sbInsert.append(" ?, ?, ?, ?, ?) ");
				
				stmtInsert = con.prepareStatement(sbInsert.toString());
				
				stmtInsert.setString(1, newUser.getName());
				stmtInsert.setString(2, newUser.getEmail());
				stmtInsert.setString(3, newUser.getPassword());
				stmtInsert.setString(4, Integer.toString(newUser.getAge()));	
				stmtInsert.setString(5, newUser.getAddress());	
				
				int rows = stmtInsert.executeUpdate();
				
				if (rows != 1){
					result = FAILURE;
					throw new SQLException(
						"executeUpdate return value: "
						+ rows);
				}	
				result = SUCCESS;				
			}catch (SQLException ex){
				result = FAILURE;
				DBConnectionManager.rollbackJDBCConnection(con);
				log.error(ex);
			}
			finally
			{
				DBConnectionManager.commitJDBCConnection(con);
				DBConnectionManager.closeStatement(stmtInsert);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;	
			
		}

		/**
		 * Retrieve the user information in the UserBean object for a
		 * user 
		 * @param user
		 * @return - Success or Failure
		 */
		public String getUserInfo(UserBean user)throws ProjectApplicationException {
			String result = null;
				
			//Create a Database Connection
			Connection con = DBConnectionManager.getSimpleConnection();
		
			Statement st = null;
			ResultSet rs = null;
					
			try{
				con.setAutoCommit(false);
				st = con.createStatement();
		
				rs = st.executeQuery("select * from user l where l.email = '"+ user.getEmail()+"'") ;
						
				while (rs.next()){
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setAge(Integer.parseInt(rs.getString("age")));
					user.setUserId(rs.getString("userId"));				
										
				}				
				result = SUCCESS;			
			}catch (SQLException ex){
				result = FAILURE;
				DBConnectionManager.rollbackJDBCConnection(con);
				log.error(ex);
			}
			finally
			{
				DBConnectionManager.commitJDBCConnection(con);
				DBConnectionManager.closeStatement(st);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;	
		}
		
		/**
		 * Checks whether user exists in the database as per EmailId and Password
		 * @param user of type UserBean
		 * @return - Success or Failure
		 */
		public String checkUser(UserBean user)throws ProjectApplicationException{
			String result = null;
							
			//Create a Database Connection
			Connection con = DBConnectionManager.getSimpleConnection();
		
			Statement st = null;
			ResultSet rs = null;
		
			
			try{
				con.setAutoCommit(false);
				st = con.createStatement();
		
				rs = st.executeQuery("select * from user l where l.email = '"+ user.getEmail() +
						"' and l.password = '"+ user.getPassword() + "'") ;
						
				while (rs.next()){						
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setAddress(rs.getString("address"));
					user.setAge(Integer.parseInt(rs.getString("age")));
					user.setPassword(rs.getString("password"));			
					user.setUserId(rs.getString("userId"));
					log.info("Logging for user   " + user.getName());					
					result = SUCCESS;
				}
			}catch (SQLException ex){
				result = FAILURE;
				DBConnectionManager.rollbackJDBCConnection(con);
				log.error(ex);
			}catch (Exception e){
				result = FAILURE;
				e.printStackTrace();
			}
			finally{
				DBConnectionManager.commitJDBCConnection(con);
				DBConnectionManager.closeStatement(st);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;	
		}
		/**
		 * Updates user information in the database 
		 * @param user of type UserBean
		 * @return - Success or Failure
		 */
		public String updateUser(UserBean user)throws ProjectApplicationException{
							
			String result = null;
			PreparedStatement stmtUpdate = null;
			
			//Create a Database Connection
			Connection con = DBConnectionManager.getSimpleConnection();
			try{
				con.setAutoCommit(false);
						
				StringBuffer sbUpdate = new StringBuffer();
				
				sbUpdate.append("Update user SET ");
				
				sbUpdate.append(" name = '" + user.getName() + "', ");
				sbUpdate.append(" age = '" + user.getAge() + "', ");
				sbUpdate.append(" password = '" + user.getPassword() + "' , ");
				sbUpdate.append(" address = '" + user.getAddress() + "' ");
				
				sbUpdate.append(" where email = '" + user.getEmail()  + "'" );
				
				stmtUpdate = con.prepareStatement(sbUpdate.toString());
				
				int rows = stmtUpdate.executeUpdate();
				
				if (rows != 1){
					result = FAILURE;
					log.error("Execute update error for user "+ user.getEmail());
					throw new SQLException(
						"executeUpdate return value: "
						+ rows);
				}	
				log.info("Updating UserInfo  " + user.getEmail());
				result = SUCCESS;
				DBConnectionManager.commitJDBCConnection(con);
			}catch (SQLException ex){
				result = FAILURE;
				DBConnectionManager.rollbackJDBCConnection(con);
				log.error(ex);
			}
			finally{
				DBConnectionManager.closeStatement(stmtUpdate);
				DBConnectionManager.closeJDBCConnection(con);
			}
			return result;	
		}
		
}
		

	
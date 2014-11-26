package ithelp.dao;

import ithelp.beans.EmployeeBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;






public class EmployeeDao {
	
	static final String SUCCESS = "success";
	static final String FAILURE = "failure";
	
	
	public String checkUser(EmployeeBean e)  {
		String result=FAILURE;
		Connection con = DBConnectionManager.getSimpleConnection();
		PreparedStatement st=null;
		try {
			st = con.prepareStatement("select * from employee where empemail=? and password=?");
			st.setString(2,e.getPassword());
			st.setString(1,e.getEmail());
			ResultSet rs=st.executeQuery();
			
			
			if(rs.next()){
				result=SUCCESS;
				
				
			}
			
		} catch (SQLException e1) {
			
			result = FAILURE;
			DBConnectionManager.rollbackJDBCConnection(con);
		}
		finally
		{
			DBConnectionManager.commitJDBCConnection(con);
			DBConnectionManager.closeStatement(st);
			DBConnectionManager.closeJDBCConnection(con);
		}
		
		
		return result;
	}
	
	public String deleteUser(String email) 
	{
		String result = null;
		Connection con = DBConnectionManager.getSimpleConnection();		
		PreparedStatement st2 = null;
					
		try{
			con.setAutoCommit(false);
			
			st2 = con.prepareStatement("delete from employee where email =?");
			
	
			
			int row2 = st2.executeUpdate();
			if( row2!=0){
				//log.info("Deleted user  " + email );
				result = SUCCESS;
			}else{
				result = FAILURE;
			}
		}	
		catch (SQLException ex){
			
			DBConnectionManager.rollbackJDBCConnection(con);
			result = FAILURE;
			//log.error(ex);
		}
		finally
		{
			DBConnectionManager.commitJDBCConnection(con);
			DBConnectionManager.closeStatement(st2);
			DBConnectionManager.closeJDBCConnection(con);
		}
		return result;
	}
	
	public Collection<EmployeeBean> selectUsers(){
		
		Collection<EmployeeBean> users = new ArrayList<EmployeeBean> ();
		
		//Create a Database Connection
		Connection con = DBConnectionManager.getSimpleConnection();		
		Statement st = null;
		ResultSet rs = null;
	
		
		try{
			con.setAutoCommit(false);
			st = con.createStatement();
	
			rs = st.executeQuery("select * from employee") ;
					
			while (rs.next()){
				EmployeeBean userBean = new EmployeeBean();
				userBean.setName(rs.getString("empname"));
				userBean.setPhone(rs.getString("empphone"));
				userBean.setMgr(Boolean.parseBoolean(rs.getString("mgr")));
				userBean.setPassword(rs.getString("password"));
				userBean.setEmail(rs.getString("empemail"));
				userBean.setDept(rs.getString("dept"));		
				users.add(userBean);					
			}
									
		}catch (SQLException ex){
			
			DBConnectionManager.rollbackJDBCConnection(con);
			//log.error(ex);
		}
		finally
		{
			DBConnectionManager.commitJDBCConnection(con);
			DBConnectionManager.closeStatement(st);
			DBConnectionManager.closeJDBCConnection(con);
		}
		
				
		return users;
		
	}
	
public EmployeeBean selectUser(EmployeeBean e){
		
		EmployeeBean user = new EmployeeBean();
		
		//Create a Database Connection
		Connection con = DBConnectionManager.getSimpleConnection();		
		PreparedStatement st = null;
		ResultSet rs = null;
		
	
		
		try{
			con.setAutoCommit(false);
			//System.out.println(e.getEmail());
			st = con.prepareStatement("select * from employee where empemail=?");
			st.setString(1,e.getEmail());
			
			rs = st.executeQuery() ;
					
			while (rs.next()){
				
				user.setName(rs.getString("empname"));
				System.out.println(rs.getString("empname"));
				user.setPhone(rs.getString("empphone"));
				user.setMgr(Boolean.parseBoolean(rs.getString("mgr")));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("empemail"));
				user.setDept(rs.getString("dept"));		
								
			}
			
									
		}catch (SQLException ex){
			
			DBConnectionManager.rollbackJDBCConnection(con);
			//log.error(ex);
		}
		finally
		{
			DBConnectionManager.commitJDBCConnection(con);
			DBConnectionManager.closeStatement(st);
			DBConnectionManager.closeJDBCConnection(con);
		}
		
				
		return user;
		
	}
	
	
	
	public String updateUser(EmployeeBean e){
		Connection con = DBConnectionManager.getSimpleConnection();		
		PreparedStatement st = null;
		String output="failure";
		try {
			st=con.prepareStatement("update employee set empname =?,empphone=?,dept=?,pass=?,mgr=? where empemail=?");
			st.setString(1,e.getName());
			st.setString(2,e.getPhone());
			st.setString(3, e.getDept());
			st.setString(4,e.getPassword());
			st.setBoolean(5,e.isMgr());
			st.setString(6, e.getEmail());
			
			 st.executeUpdate();
			 output="success";
			
		} catch (SQLException e1) {
			output="failure";
			DBConnectionManager.rollbackJDBCConnection(con);
		}
		finally{
			DBConnectionManager.commitJDBCConnection(con);
			DBConnectionManager.closeStatement(st);
			DBConnectionManager.closeJDBCConnection(con);
		}
		return output;
	}
	


}

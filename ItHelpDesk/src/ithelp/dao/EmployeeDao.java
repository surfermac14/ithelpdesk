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
	
	
	
	public String setMgr(String email){
		Connection con = DBConnectionManager.getSimpleConnection();		
		PreparedStatement st = null;
		String output="failure";
		try {
			st=con.prepareStatement("update employee set mgr =? where empemail=?");
			
			
			st.setBoolean(1,true);
			st.setString(2, email);
			
			int n=st.executeUpdate();
			
			if(n==0){ 
			 output="success";
			 return output;
			}
		} catch (SQLException e1) {
			output="failure";
			e1.printStackTrace();
			DBConnectionManager.rollbackJDBCConnection(con);
		}
		finally{
			DBConnectionManager.commitJDBCConnection(con);
			DBConnectionManager.closeStatement(st);
			DBConnectionManager.closeJDBCConnection(con);
		}
		return output;
	}
	
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
			e1.printStackTrace();
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
		System.out.println(email);
					
		try{
			con.setAutoCommit(false);
			
			st2 = con.prepareStatement("delete from employee where empemail =?");
			st2.setString(1,email);
	
			
			int row2 = st2.executeUpdate();
			if( row2!=0){
				//log.info("Deleted user  " + email );
				result = SUCCESS;
			}else{
				result = FAILURE;
			}
		}	
		catch (SQLException ex){
			ex.printStackTrace();
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
				userBean.setEmpid(Integer.parseInt(rs.getString("empid")));
				users.add(userBean);					
			}
									
		}catch (SQLException ex){
			ex.printStackTrace();
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
	
	public String insertUser(EmployeeBean e){
		Connection con = DBConnectionManager.getSimpleConnection();		
		PreparedStatement st = null;
		String output="failure";
		try {
			st=con.prepareStatement("insert into employee(empname,empphone,dept,password,mgr,empemail) values(?,?,?,?,?,?)");
			
			st.setString(1,e.getName());
			st.setString(2,e.getPhone());
			st.setString(3, e.getDept());
			st.setString(4,e.getPassword());
			st.setBoolean(5,e.isMgr());
			st.setString(6, e.getEmail());
			
			int n=st.executeUpdate();
			
			if(n==0){ 
			 output="success";
			 return output;
			}
		} catch (SQLException e1) {
			output="failure";
			e1.printStackTrace();
			DBConnectionManager.rollbackJDBCConnection(con);
		}
		finally{
			DBConnectionManager.commitJDBCConnection(con);
			DBConnectionManager.closeStatement(st);
			DBConnectionManager.closeJDBCConnection(con);
		}
		return output;
	}
	
	public String updateUser(EmployeeBean e){
		Connection con = DBConnectionManager.getSimpleConnection();		
		PreparedStatement st = null;
		String output="failure";
		try {
			st=con.prepareStatement("update employee set empname =?,empphone=?,dept=?,password=?,mgr=? where empemail=?");
			
			st.setString(1,e.getName());
			st.setString(2,e.getPhone());
			st.setString(3, e.getDept());
			st.setString(4,e.getPassword());
			st.setBoolean(5,e.isMgr());
			st.setString(6, e.getEmail());
			
			int n=st.executeUpdate();
			
			if(n==0){ 
			 output="success";
			 return output;
			}
		} catch (SQLException e1) {
			output="failure";
			e1.printStackTrace();
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

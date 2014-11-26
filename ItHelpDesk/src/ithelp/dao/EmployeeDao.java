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
		PreparedStatement st;
		try {
			st = con.prepareStatement("select * from passwords where password=? and empid=(select empid from employee where empemail=?)");
			st.setString(1,e.getPassword());
			st.setString(2,e.getEmail());
			ResultSet rs=st.executeQuery();
			
			if(rs.next()){
				result=SUCCESS;
				
				
			}
			
		} catch (SQLException e1) {
			
			result = FAILURE;
			DBConnectionManager.rollbackJDBCConnection(con);
		}
		
		
		return result;
	}
	
	public String deleteUser(String email) 
	{
		String result = null;
		Connection con = DBConnectionManager.getSimpleConnection();		
		PreparedStatement st = null;
		PreparedStatement st2 = null;
					
		try{
			con.setAutoCommit(false);
			st = con.prepareStatement("delete from passwords where id=(select id from employee where email=?");
			st2 = con.prepareStatement("delete from employee where email =?");
			
	
			int row = st.executeUpdate() ;
			int row2 = st2.executeUpdate();
			if(row != 0 && row2!=0){
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
			DBConnectionManager.closeStatement(st);
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
	
			rs = st.executeQuery("select * from user") ;
					
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
		PreparedStatement pt = null;
	
		
		try{
			con.setAutoCommit(false);
			st = con.prepareStatement("select * from user where empemail=?");
			st.setString(1,e.getEmail());
			pt	=con.prepareStatement("select password from passwords where empid=(select empid from employee where empemail=?)");
			pt.setString(1,e.getEmail());
			rs = st.executeQuery() ;
					
			while (rs.next()){
				
				user.setName(rs.getString("empname"));
				user.setPhone(rs.getString("empphone"));
				user.setMgr(Boolean.parseBoolean(rs.getString("mgr")));
				
				user.setEmail(rs.getString("empemail"));
				user.setDept(rs.getString("dept"));		
								
			}
			rs=pt.executeQuery();
			while(rs.next()){
				user.setPassword(rs.getString("password"));
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
	
	public int insertUser(EmployeeBean e){
		
		Connection con = DBConnectionManager.getSimpleConnection();		
		PreparedStatement st = null;
		
		try {
			con.setAutoCommit(false);
			
			st = con.prepareStatement("insert into employee(empname,empemail,empphone,dept) values(?,?,?,?)");
			st.setString(1, e.getName());
			st.setString(2,e.getEmail());
			st.setString(3,e.getPhone());
			st.setString(4,e.getDept());
			int n=st.executeUpdate();
			
			return n;
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			DBConnectionManager.rollbackJDBCConnection(con);
		}
		finally{
			DBConnectionManager.commitJDBCConnection(con);
			DBConnectionManager.closeStatement(st);
			DBConnectionManager.closeJDBCConnection(con);
		}
		
		
		
		return 0;
	}
	
	public int updateUser(EmployeeBean e){
		Connection con = DBConnectionManager.getSimpleConnection();		
		PreparedStatement st = null;
		int n;
		try {
			st=con.prepareStatement("update employee set empname =?,empphone=?,dept=?");
			st.setString(1,e.getName());
			st.setString(2,e.getPhone());
			st.setString(3, e.getDept());
			
			 n=st.executeUpdate();
			
		} catch (SQLException e1) {
			n=0;
			DBConnectionManager.rollbackJDBCConnection(con);
		}
		finally{
			DBConnectionManager.commitJDBCConnection(con);
			DBConnectionManager.closeStatement(st);
			DBConnectionManager.closeJDBCConnection(con);
		}
		return n;
	}
	
	public int changePass(EmployeeBean e){
		Connection con = DBConnectionManager.getSimpleConnection();		
		PreparedStatement st = null;
		int n;
		try {
			st=con.prepareStatement("update passwords set password = ? where empid=(select empid from employee where empemail=?)");
			st.setString(1,e.getName());
			st.setString(2,e.getEmail());
			 n=st.executeUpdate();
			
		} catch (SQLException e1) {
			n=0;
			DBConnectionManager.rollbackJDBCConnection(con);
		}
		finally{
			DBConnectionManager.commitJDBCConnection(con);
			DBConnectionManager.closeStatement(st);
			DBConnectionManager.closeJDBCConnection(con);
		}
		return n;
	}

}

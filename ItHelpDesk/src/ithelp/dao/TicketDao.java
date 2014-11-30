package ithelp.dao;

import ithelp.beans.EmployeeBean;
import ithelp.beans.TicketBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class TicketDao {

	
	
public Collection<TicketBean> selectTickets(){
		
		Collection<TicketBean> tickets = new ArrayList<TicketBean> ();
		
		//Create a Database Connection
		Connection con = DBConnectionManager.getSimpleConnection();		
		Statement st = null;
		ResultSet rs = null;
	
		
		try{
			con.setAutoCommit(false);
			st = con.createStatement();
	
			rs = st.executeQuery("select * from tickets") ;
					
			while (rs.next()){
				TicketBean ticketBean = new TicketBean();
				ticketBean.setTicketId(rs.getInt("ticketId"));
				ticketBean.setAssignTo(rs.getInt("assignTo"));
				ticketBean.setPriority(rs.getInt("priority"));
				ticketBean.setIssue(rs.getString("issue"));
				ticketBean.setSolution(rs.getString("solution"));
				ticketBean.setStartDate(rs.getDate("startDate"));
				ticketBean.setEndDate(rs.getDate("endDate"));
				tickets.add(ticketBean);
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
		
				
		return tickets;
		
	}
}

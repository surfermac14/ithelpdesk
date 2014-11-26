package ithelp.beans;

import java.util.Date;

public class TicketBean {
	
	String ticketType;
	int priority;
	int ticketId;
	String issue;
	String solution;
	int assignTo;
	Date startDate;
	Date endDate;
	
	
	public String getTicketType() {
		return ticketType;
	}
	public int getPriority() {
		return priority;
	}
	public int getTicketId() {
		return ticketId;
	}
	public String getIssue() {
		return issue;
	}
	public String getSolution() {
		return solution;
	}
	public int getAssignTo() {
		return assignTo;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	
	
	
	

}

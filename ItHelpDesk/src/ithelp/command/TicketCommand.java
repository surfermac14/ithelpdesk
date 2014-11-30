package ithelp.command;

import ithelp.beans.TicketBean;
import ithelp.dao.TicketDao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TicketCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if("raise".equals(request.getParameter("action"))){
			this.raise(request,response);
		}
		else if("update".equals(request.getParameter("action"))){
			this.update(request,response);
		}
		else if("select".equals(request.getParameter("action"))){
			this.show(request, response);
		}
		
		
	}
	private void mapToTicket(HttpServletRequest request, TicketBean t) {
		SimpleDateFormat s = new SimpleDateFormat("dd-mm-yyyy");
		t.setTicketType(request.getParameter("type"));
		t.setPriority(Integer.parseInt(request.getParameter("priority")));
		t.setIssue(request.getParameter("issue"));
		t.setSolution(request.getParameter("solution"));
		t.setAssignTo(Integer.parseInt(request.getParameter("assign")));
		try{
		t.setStartDate(s.parse(request.getParameter("startdate")));
		t.setEndDate(s.parse(request.getParameter("enddate")));
		}catch(ParseException e){
			e.printStackTrace();
		}
	}
	
	
	private void show(HttpServletRequest request, HttpServletResponse response) {
		
		TicketDao d = new TicketDao();
		TicketBean t = new TicketBean();
		mapToTicket (request, t);
		
		
	}

	

	private void update(HttpServletRequest request, HttpServletResponse response) {
		TicketDao d = new TicketDao();
		TicketBean t = new TicketBean();
		mapToTicket (request, t);
		
	}

	private void raise(HttpServletRequest request, HttpServletResponse response) {
		TicketDao d = new TicketDao();
		TicketBean t = new TicketBean();
		mapToTicket (request, t);
		
	}

}

package ithelp.command;

import ithelp.beans.TicketBean;
import ithelp.dao.TicketDao;

import java.io.IOException;

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
		// TODO Auto-generated method stub
		
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

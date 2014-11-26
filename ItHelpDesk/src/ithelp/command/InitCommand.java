package ithelp.command;

import java.io.IOException;

import ithelp.beans.EmployeeBean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InitCommand implements Command {

	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException   {
		// TODO Auto-generated method stub
		request.setAttribute("userBean", new EmployeeBean());
		ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/home.jsp").forward(request, response);
	}

}

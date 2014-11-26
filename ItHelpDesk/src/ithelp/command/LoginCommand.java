package ithelp.command;

import java.io.IOException;

import ithelp.beans.EmployeeBean;
import ithelp.dao.EmployeeDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





public class LoginCommand implements Command {
	private void mapToUserBean(HttpServletRequest request, EmployeeBean userBean) {		
		userBean.setEmail(request.getParameter ("emailId"));
		userBean.setPassword( request.getParameter("password"));
	}


	private String checkUser(EmployeeBean empbean){
		EmployeeDao  e = new EmployeeDao();
		return e.checkUser(empbean);
	}


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeBean userBean = new EmployeeBean();
		mapToUserBean (request, userBean);

		if ("success".equalsIgnoreCase(this.checkUser(userBean))){        
			System.out.println("entered");

			EmployeeDao  e = new EmployeeDao();
			userBean = e.selectUser(userBean);
			HttpSession session = request.getSession();
			session.setAttribute("userBean", userBean);
			
			

			if(userBean.getDept().equals("admin")){
				ServletContext context = request.getSession().getServletContext();
				context.getRequestDispatcher("/WelcomeAdmin.jsp").forward(request, response);
			}
			else if(userBean.isMgr()==true){
				ServletContext context = request.getSession().getServletContext();
				context.getRequestDispatcher("/WelcomeManager.jsp").forward(request, response);
			}
			else if(userBean.getDept().equals("Project")){
				ServletContext context = request.getSession().getServletContext();
				context.getRequestDispatcher("/WelcomeTeam.jsp").forward(request, response);
				
			}
			else if(userBean.getDept().equals("IT")){
				ServletContext context = request.getSession().getServletContext();
				context.getRequestDispatcher("/WelcomeIT.jsp").forward(request, response);
				
			}
			else{
				ServletContext context = request.getSession().getServletContext();
				context.getRequestDispatcher("/home.jsp").forward(request, response);
			}
		}else {
			ServletContext context = request.getSession().getServletContext();
			context.getRequestDispatcher("/home.jsp").forward(request, response);	
		}

	}
}

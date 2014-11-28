package ithelp.command;

import java.io.IOException;

import ithelp.beans.EmployeeBean;
import ithelp.dao.EmployeeDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmployeeCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String e = request.getParameter("action");
		if("update".equals(e)){
			this.updateUser(request,response);
		}
		else if("insert".equals(e)){
			this.insertUser(request,response);
		}
		else if("delete".equals(e)){
			this.deleteUser(request,response);
		}
		else if("viewall".equals(e)){
			this.viewAll(request,response);
		}
		else if("check".equals(e)){
			this.checkUser(request,response);
		}
		else if("mgr".equals(e)){
			this.mgr(request,response);
		}
	}

	private void mgr(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
			EmployeeBean userBean = new EmployeeBean();
			mapToUserBean (request, userBean);
			EmployeeDao e = new EmployeeDao();
			System.out.println(userBean.getEmail());
			if("success".equals(e.setMgr(userBean.getEmail()))){
				
				ServletContext context = request.getSession().getServletContext();
				context.getRequestDispatcher("/ViewAllEmployee.jsp").forward(request, response);	
			}
		}	
	

	private void insertUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EmployeeBean userBean = new EmployeeBean();
		mapToUserBean (request, userBean);
		EmployeeDao e = new EmployeeDao();
		
		if("success".equals(e.insertUser(userBean))){
			
			request.getSession().setAttribute("stats", "inserted");
			ServletContext context = request.getSession().getServletContext();
			context.getRequestDispatcher("/WelcomeAdmin.jsp").forward(request, response);
		}
		else{
			request.getSession().setAttribute("stats","not inserted try again");
			ServletContext context = request.getSession().getServletContext();
			context.getRequestDispatcher("/AddEmployee.jsp").forward(request, response);
		}
		
	}

	private void checkUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EmployeeBean userBean = new EmployeeBean();
		mapToUserBean (request, userBean);

		if ("success".equalsIgnoreCase(this.check(userBean))){        

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

	private void mapToUserBean(HttpServletRequest request, EmployeeBean userBean) {
		userBean.setName(request.getParameter("name"));
		userBean.setEmail(request.getParameter ("mail"));
		userBean.setPassword( request.getParameter("pass"));
		userBean.setPhone(request.getParameter("phno"));
		userBean.setDept(request.getParameter("dept"));
		//userBean.setMgr(request.getParameter("mgr"));
		
	}

	private String check(EmployeeBean userBean) {
		EmployeeDao  e = new EmployeeDao();
		return e.checkUser(userBean);
	}

	private void viewAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getSession().getServletContext();
		context.getRequestDispatcher("/ViewAllEmployee.jsp").forward(request, response);	
		
	}

	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) {

		EmployeeBean userBean = new EmployeeBean();
		mapToUserBean (request, userBean);
		EmployeeDao e = new EmployeeDao();
		
		if("success".equals(e.updateUser(userBean))){
			System.out.println("bisket");
			request.getSession().setAttribute("stats", "inserted");
		}
		else{
			System.out.println(e.updateUser(userBean));
		}
		
	}

	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EmployeeBean userBean = new EmployeeBean();
		mapToUserBean (request, userBean);
		EmployeeDao e = new EmployeeDao();
		System.out.println(userBean.getEmail());
		if("success".equals(e.deleteUser(userBean.getEmail()))){
			
			ServletContext context = request.getSession().getServletContext();
			context.getRequestDispatcher("/ViewAllEmployee.jsp").forward(request, response);	
		}
		
	}
	


}

package ithelp.command;

import ithelp.beans.EmployeeBean;
import ithelp.dao.EmployeeDao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsEmpCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		EmployeeBean userBean = new EmployeeBean();
		mapToUserBean (request, userBean);
		EmployeeDao e = new EmployeeDao();
		
		if("success".equals(e.updateUser(userBean))){
			System.out.println("bisket");
			request.getSession().setAttribute("stats", "inserted");
		}
		
	}
	private void mapToUserBean(HttpServletRequest request, EmployeeBean userBean) {		
		userBean.setName(request.getParameter("name"));
		userBean.setEmail(request.getParameter ("mail"));
		userBean.setPassword( request.getParameter("pass"));
		userBean.setPhone(request.getParameter("phno"));
		userBean.setDept(request.getParameter("dept"));
	}

}

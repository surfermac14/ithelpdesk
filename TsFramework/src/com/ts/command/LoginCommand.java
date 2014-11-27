package com.ts.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import javax.servlet.ServletException;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import java.io.IOException;

import com.ts.dao.UserDAO;
import com.ts.exception.ProjectApplicationException;
import com.ts.beans.UserBean;


/**
 * Checks for User Authentication
 * @author Reena
 *
 */
public class LoginCommand implements Command{
	

	private static final long serialVersionUID = 1L;
	
	// Check log4j.properties in WEB-INF folder
	static Logger log = Logger.getLogger("com.a");
	


	/**
	 * Checks for user authentication
	 * Calls the UserDAO for authentications
	 * If success- control goes to "WelcomeUser.jsp
	 * if failure- control goes to login.jsp with an error message
	 */	
  public void execute(HttpServletRequest request,
                        HttpServletResponse response)
                    throws ServletException, IOException{


	   	UserBean userBean = new UserBean();
        mapToUserBean (request, userBean);
       
                
        if ("success".equalsIgnoreCase(this.checkUser(userBean))){        
        	
         
        	HttpSession session = request.getSession();
        	session.setAttribute("userBean", userBean);
        	ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/welcomeUser.jsp").forward(request, response);
            
        }else {
        	 ServletContext context = request.getSession().getServletContext();
             context.getRequestDispatcher("/login.jsp").forward(request, response);	
        }
               
    }

    /**
     * Reads the request object and creates the user bean object
     * 
     */
	private void mapToUserBean(HttpServletRequest request, UserBean userBean) {		
		userBean.setEmail(request.getParameter ("emailId"));
        userBean.setPassword( request.getParameter("password"));
	}
    
	/**
	 * Calls the DAO Check User
	 */
    private String checkUser(UserBean userBean){
    	
    	try{
    	UserDAO dao = new UserDAO(); 
       	return dao.checkUser(userBean);
    	}catch(ProjectApplicationException ex){
    		log.error("Login Error" );
    		return "FAILURE";
    		
    	}
    }
}

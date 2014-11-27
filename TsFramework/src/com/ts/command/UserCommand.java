package com.ts.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

import com.ts.dao.UserDAO;
import com.ts.exception.ProjectApplicationException;
import com.ts.beans.UserBean;


/**
 * Completes the requests as per the 'action' defined from the jsp class.
 * 
 * @author Reena
 *
 */
public class UserCommand implements Command{
	
	
	static Logger log = Logger.getLogger("com.a");
	
	/**
	 * Completes the request to insert, update, delete or view all user
	 * as per the 'action' defined in the request parameter
	 */
	 public void execute(HttpServletRequest request,
                        HttpServletResponse response)
                    throws ServletException, IOException{
  	  
	  if("insert".equalsIgnoreCase(request.getParameter ("action"))){	      

		  this.addUser(request,response);
	  }
	  else if("update".equalsIgnoreCase(request.getParameter ("action"))){
	  	
		  this.updateUser(request,response);
	  }
	  else if("viewAll".equalsIgnoreCase(request.getParameter ("action"))){
		  	
		  this.viewAllUser(request,response);
		  
	  }  else if("delete".equalsIgnoreCase(request.getParameter ("action"))){
		  	
		  this.deleteUser(request,response);
	  }
  }
  
  	/**
  	 * Retrieves a collection of UserBean object
  	 * @param request
  	 * @param response
  	 * @throws ServletException
  	 * @throws IOException
  	 */
  	private void viewAllUser(HttpServletRequest request,
          HttpServletResponse response)
      throws ServletException, IOException{
	  
  		
    	Collection<UserBean>  userBeans = new ArrayList<UserBean>();
    	try{
    		userBeans = new UserDAO().selectUsers();       
    		
    	}catch(ProjectApplicationException ex){
    		log.error(" Error in Fethcing Data about users" );    		
    	}
 
        if (!userBeans.isEmpty()){
               	request.setAttribute("userBeans", userBeans);
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/viewAllUsers.jsp").forward(request, response);
        }
        else{
        	  ServletContext context = request.getSession().getServletContext();
              context.getRequestDispatcher("/login.jsp").forward(request, response);
        }
  	}
  
  		/**
  		 * Updates the user information
  		 * User information is sent in request parameter from jsp file. 
  		 * @param request
  		 * @param response
  		 * @throws ServletException
  		 * @throws IOException
  		 */
  
      private void updateUser(HttpServletRequest request,
          HttpServletResponse response)
      throws ServletException, IOException{
  	
	  UserBean userBean = new UserBean();
	  mapToUserBean(request, userBean);
	  String result = null;
	  
	   try{
		  result = new UserDAO().updateUser(userBean); 
	   }catch(ProjectApplicationException ex){
	  		log.error(" Error in Updating user data" );    		
	  	}

	   if ("success".equalsIgnoreCase(result)){
		        
       		HttpSession session = request.getSession();
       		session.setAttribute("userBean", userBean);
		   ServletContext context = request.getSession().getServletContext();
           context.getRequestDispatcher("/welcomeUser.jsp").forward(request, response);
	     }
	 
	   else
	     {
		   	  ServletContext context = request.getSession().getServletContext();
	          context.getRequestDispatcher("/login.jsp").forward(request, response);	
	     }
		}
  	 	
      /**
       * Inserts a new User in the database.
       * User information is sent in request parameter from jsp file.  
       * @param request
       * @param response
       * @throws ServletException
       * @throws IOException
       */
      private void addUser(HttpServletRequest request,
              HttpServletResponse response)
          throws ServletException, IOException{
      	
    	  UserBean userBean = new UserBean();
    	  mapToUserBean(request, userBean);
    	  
    	  String result = null;
    	  try{
    	  		result = new UserDAO().createUser(userBean); 
	      	}catch(ProjectApplicationException ex){
	  			log.error(" Error in inserting Data about users" );    		
	  		}
    	   if ("success".equalsIgnoreCase(result)){
    		       
    		    HttpSession session = request.getSession();
           		session.setAttribute("userBean", userBean);
    			ServletContext context = request.getSession().getServletContext();
               context.getRequestDispatcher("/welcomeUser.jsp").forward(request, response);
    	     }
    	 
    	   else{
    	     	 ServletContext context = request.getSession().getServletContext();
    	          context.getRequestDispatcher("/login.jsp").forward(request, response);	
    	     }
    	}
      
      

      /**
       * Deletes a user as per the emailId defined in 'notify' request parameter from jsp file
       * @param request
       * @param response
       * @throws ServletException
       * @throws IOException
       */
      private void deleteUser(HttpServletRequest request,
              HttpServletResponse response)
          throws ServletException, IOException{
      	
    	 String email = request.getParameter("notify");

    	 String result = null;
    	 try{
    	 	result = new UserDAO().deleteUser(email); 
	      }catch(ProjectApplicationException ex){
		  		log.error(" Error in deleting user data" );    		
		  	}
    	   if ("success".equalsIgnoreCase(result)){
    		       
    		   ServletContext context = request.getSession().getServletContext();
               context.getRequestDispatcher("/welcomeUser.jsp").forward(request, response);
    	     }
    	 
    	   else
    	     {
    	     	 ServletContext context = request.getSession().getServletContext();
    	          context.getRequestDispatcher("/login.jsp").forward(request, response);	
    	     }
    	}
      
      /**
       * Reads the request object and creates the user bean object
       * 
       */
  	private void mapToUserBean(HttpServletRequest request, UserBean userBean){
  		userBean.setName(request.getParameter ("name"));
		userBean.setEmail(request.getParameter ("email"));
		userBean.setAddress(request.getParameter ("address"));
        userBean.setPassword( request.getParameter("password"));
        userBean.setAge(Integer.parseInt(request.getParameter("age")));
  	}
  			
}

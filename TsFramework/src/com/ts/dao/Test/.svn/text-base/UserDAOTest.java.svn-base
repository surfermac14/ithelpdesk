package com.ts.dao.Test;

import java.util.ArrayList;
import java.util.Collection;

import com.ts.dao.UserDAO;
import com.ts.beans.UserBean;
import com.ts.exception.*;

import junit.framework.TestCase;


/**
 * Test case class for UserDAO
 * @author Reena
 *
 */
public class UserDAOTest extends  TestCase{

	 UserDAO userDao = new UserDAO();
	 
	 UserBean userBean = new UserBean();
	  
	   public UserDAOTest() {
		   super();
	   }
	 
	   /*
	    * Example for successful test case 
	    */
       public void testCreateUser() {
    	   
    	   String email = "John@email.com";
    	   String pwd = "john";
    	      	    	   
    	   userBean.setEmail(email);
           userBean.setPassword(pwd);           
           try{
        	   assertEquals("success",userDao.checkUser(userBean));
           }catch(ProjectApplicationException ex){}
         
	  }
       
       /*
        * Example for failed test case
        */
	
       public void testSelectUsers(){
    	   
    	   Collection<UserBean> userList =  new ArrayList<UserBean> ();
    	   try{
    		   assertEquals("Result", userList, userDao.selectUsers());
    	   }catch(ProjectApplicationException ex){}
       }
}

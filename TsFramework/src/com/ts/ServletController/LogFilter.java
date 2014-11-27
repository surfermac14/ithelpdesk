package com.ts.ServletController;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;
import java.util.*;

// Implements Filter class
public class LogFilter implements Filter  {
	
	
	// Check log4j.properties in WEB-INF folder
  	static Logger log = Logger.getLogger("com.a");
  	private int count;
  	
   public void  init(FilterConfig config) 
                         throws ServletException{
	   	   
      // Get init parameter 
      String testParam = config.getInitParameter("test-param"); 
      //Print the init parameter 
      System.out.println("Test Param: " + testParam);
      
      count = 0;
      
     }
   public void  doFilter(ServletRequest request, 
                 ServletResponse response,
                 FilterChain chain) 
                 throws java.io.IOException, ServletException {

      // Get the IP address of client machine.   
      String ipAddress = request.getRemoteAddr();

     log.info("IP "+ ipAddress + ", Time "
                                       + new Date().toString());
     count++;     
     log.info("This site has been hit " + count + " time(s)");
     
      // Pass request back down the filter chain
      chain.doFilter(request,response);
   }
   public void destroy( ){
      /* Called before the Filter instance is removed 
      from service by the web container*/
   }
}

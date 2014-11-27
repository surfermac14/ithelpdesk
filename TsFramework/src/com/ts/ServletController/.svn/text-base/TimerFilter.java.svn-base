package com.ts.ServletController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TimerFilter implements Filter
{
   private FilterConfig _filterConfig = null;

   /** 
   * The server calls this method to initialize the Filter and
   * passes in a FilterConfig object.
   */
   public void init (FilterConfig filterConfig)
               throws javax.servlet.ServletException
   {
      _filterConfig = filterConfig;
   }
    
   /**
   * Return the FilterConfig object
   */
   public FilterConfig getFilterConfig()
   {
      return _filterConfig;
   }

   /**
   * EAServer calls this method each time a servlet, JSP or static Web 
   * resource is invoked.
   */
   public void doFilter (ServletRequest request, 
                         ServletResponse response, 
                         FilterChain chain)
               throws java.io.IOException, javax.servlet.ServletException
   {
      // This is executed before the servlet/jsp/static resource is served.
      long startTime = System.currentTimeMillis();

      // Pass control to the next filter in the chain.
      chain.doFilter(request, response);

      // This is executed after the servlet/jsp/static resource has been served.
      long endTime = System.currentTimeMillis();

      // Get the ServletContext from the FilterConfig
      ServletContext context = _filterConfig.getServletContext();

      // Get the type parameter from the filter's initialization
      // paramters.  Return null if the parameter was not set
      String type = (String)_filterConfig.getInitParameter("type");

      // Get the filter’s name to include in the log
      String filterName = _filterConfig.getFilterName();

      HttpServletRequest httprequest = (HttpServletRequest)request;
      String path = httprequest.getRequestURI();

      // By default, record the absolute time
      if ((type == null) || (type.equals("absolute"))) 
      {
         Date date = new Date(endTime);
         context.log(filterName + " - " + path + " finished: " +
                     date.toString());
      } 
      else 
      {
         context.log(filterName + " - time to process " + path + ": " +
                     (endTime - startTime) + "ms");
      }
   }
   /** 
   * Notifies the filter that it is being taken out of service.
   */
   public void destroy()
   {
      // free resources
   }
 
}
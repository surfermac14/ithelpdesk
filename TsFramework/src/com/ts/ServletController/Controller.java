package com.ts.ServletController;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;


import com.ts.command.Command;
import com.ts.command.UserCommand;
import com.ts.command.InitCommand;
import com.ts.command.LoginCommand;
import com.ts.command.PDFCommand;


import java.util.Map;
import java.util.HashMap;

/**
 * Servlet Controller class
 * @author Reena
 *
 */
public class Controller extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")	
	private Map commands = new HashMap();

	    @SuppressWarnings("unchecked")
		@Override
	    public void init(ServletConfig config) throws ServletException{
	        super.init();	            	        
	        this.commands.put("init",  new InitCommand());
	        this.commands.put("user", new UserCommand());
	        this.commands.put("login", new LoginCommand());
	        this.commands.put("pdfCreate", new PDFCommand());
	       
	        
	   }

	   
	    /** 
	     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	     */
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	      processCommand(request, response);

	    } 

	      /** 
	     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	     */
	    public void processCommand(HttpServletRequest  request,
	                               HttpServletResponse response)
	                           throws ServletException, IOException{
	    	
	    	// formAction is set null from fileUpload.jsp for upload
	        String formAction = request.getParameter("form_action");
	       
	        
	        // Retrieve the command object with the key as formAction
	        Command command = (Command)commands.get(formAction);

	        if(null == command){
	            throw new IllegalArgumentException(
	                "No command for form action: " + formAction);
	        }
	        
	        // Calling the command class execute method
	        command.execute(request, response);
	    }
	    
	    
	  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	    /** 
	     * Handles the HTTP <code>GET</code> method.
	     * @param request servlet request
	     * @param response servlet response
	     * @throws ServletException if a servlet-specific error occurs
	     * @throws IOException if an I/O error occurs
	     */
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        processRequest(request, response);
	    } 

	    /** 
	     * Handles the HTTP <code>POST</code> method.
	     * @param request servlet request
	     * @param response servlet response
	     * @throws ServletException if a servlet-specific error occurs
	     * @throws IOException if an I/O error occurs
	     */
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        processRequest(request, response);
	    }

	    /** 
	     * Returns a short description of the servlet.
	     * @return a String containing servlet description
	     */
	    @Override
	    public String getServletInfo() {
	        return "Short description";
	    }// </editor-fold>
}


package ithelp.controller;

import ithelp.command.InitCommand;
import ithelp.command.LoginCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ithelp.command.Command;



public class Controller extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private Map commands = new HashMap();
	
	public void init(ServletConfig config) throws ServletException{
        super.init();	            	        
        this.commands.put("init",  new InitCommand());
        this.commands.put("login", new LoginCommand());
    }
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		      processCommand(request, response);

		    }

	public void processCommand(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
	public void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException
	{
	processRequest(req,resp);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException
	{
		processRequest(req,resp);
	}
}

package com.ts.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/*
 * 	Interface for all the command classes.
 *  Implemented classes has to define the 'execute' method
 */

public interface Command {
	
    public void execute(HttpServletRequest request,
                       HttpServletResponse response)
                   throws ServletException, IOException;
}
package com.ts.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;
import com.ts.beans.UserBean;


public class InitCommand implements Command{
  public void execute(HttpServletRequest request,
                        HttpServletResponse response)
                    throws ServletException, IOException{

        request.setAttribute("userBean", new UserBean());
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}

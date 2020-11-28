package com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBConnection;

/**
 *
 * @author Raul-Andrei Ginj-Groshzart
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Request username and password parameters from index.jsp login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Create connection to database
        DBConnection db = new DBConnection();
        // Call the query function and pass in username and password (string gets returned)
        String s = db.doQuery(username, password);
        
        // Set attribute to returned string 's'
        request.setAttribute("username", s);
        // Dispatch the request to dashboard.jsp
        RequestDispatcher view = request.getRequestDispatcher("dashboard.jsp");
        // Forward the request
        view.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

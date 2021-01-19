/*
 * Removed unused import
 */
package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DBConnection;
import model.User;

/**
 *
 * @author Raul-Andrei Ginj-Groszhart
 */

public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        // Create session
        HttpSession session = request.getSession();
        
        // Create a new connection to the database
        DBConnection dbcon = new DBConnection();
        // Connect to the database - request the connection attribute from the Listener
        dbcon.connect((Connection)request.getServletContext().getAttribute("connection"));
        // Set a session attribute with the connection
        session.setAttribute("dbcon", dbcon);
        
        if(session.getAttribute("dbcon")==null)
        {
            request.getRequestDispatcher("conError.jsp").forward(request, response);
        }
        
        // Array to hold requested parameters
        String[] query = new String[2];
        // Request username and password 
        query[0] = (String)request.getParameter("username");
        query[1] = (String)request.getParameter("password");
        
        String msg = "";
            
        if(request.getParameter("signin") != null)
        {
             
            // If the user is found in the database
            if(dbcon.login(query))
            {   
                // Create user object and set attributes
                User user = dbcon.grabUserByName(query[0]);
                session.setAttribute("user", user);
                session.setAttribute("username", query[0]);

//                // Creating cookie for testing purposes
//                Cookie userName = new Cookie("username", query[0]);
//                // Max Age of cookie 30 minutes
//                userName.setMaxAge(30*60);
//                // Add cookie to response header
//                response.addCookie(userName);

                // Redirect to DashboardServlet
                response.sendRedirect("DashboardServlet.do");
                return;
                
            } else {
                msg = "<span>Make sure your credentials are correct!</span>";
            }

        }
        
        // Set error message attribute
        request.setAttribute("msg", msg);
        // Redirect to index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

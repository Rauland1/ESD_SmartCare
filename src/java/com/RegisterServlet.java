/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DBConnection;
import model.User;

/**
 *
 * @author Raul
 */
public class RegisterServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession(false);
       
        // Create a new connection to the database
        DBConnection dbcon = new DBConnection();
        // Connect to the database - request the connection attribute from the Listener
        dbcon.connect((Connection)request.getServletContext().getAttribute("connection"));
        // Set a session attribute with the connection
        session.setAttribute("dbcon", dbcon);
        
        // Create a new connection to the database
        if(session.getAttribute("dbcon")==null)
        {
            request.getRequestDispatcher("conError.jsp").forward(request, response);
        } 
        else if(request.getParameter("approve_reg") != null) // From admin dashboard
        {
            
            String regUsername = request.getParameter("regUsername");
            
            if(regUsername == null)
            {
                request.setAttribute("msg", "<span>No account has been selected!</span>");
            } else {
                dbcon.confirmReg(regUsername);
                request.setAttribute("msg", "<span>Account registration has been approved!</span>");
            }
            
            request.getRequestDispatcher("DashboardServlet.do").forward(request, response);
        }
        else if(request.getParameter("complete_reg") != null) // From complete_registration.jsp
        {

            // Array to hold requested parameters
            String[] details = new String[5];
            // Request username and password 
            details[0] = (String)request.getParameter("title");
            details[1] = (String)request.getParameter("fName");
            details[2] = (String)request.getParameter("lName");
            details[3] = (String)request.getParameter("house_no");
            details[4] = (String)request.getParameter("post_code");
            
            if(session.getAttribute("user") != null) {
                User user = (User)session.getAttribute("user");
            
                dbcon.completeRegistration(details, user.getUsername(), user.getRole());
                request.setAttribute("msg", "Registration Complete!");
            } else {
                response.sendRedirect("conError.jsp");
            }
            
            request.getRequestDispatcher("DashboardServlet.do").forward(request, response);
            
        }
        else if(request.getParameter("registration_form") != null) // From register.jsp
        {
            
            // Array to hold requested parameters
            String[] query = new String[3];
            // Request username, password and role
            query[0] = (String)request.getParameter("username");
            query[1] = (String)request.getParameter("password");
            query[2] = (String)request.getParameter("role");
        
            if(dbcon.exists(query[0]))
            {
                request.setAttribute("message", "<span>Username '" + query[0] + "' is already taken! Please choose another username.</span>");
            }
            else if(query[2].equals("doctor") || query[2].equals("nurse"))
            {
                dbcon.insert(query);
                request.setAttribute("message", "<span>" + query[2].substring(0, 1).toUpperCase() + query[2].substring(1) + " accounts need to be authorised by an admin! Please wait for further instructions.</span>");
            } 
            else
            {
                dbcon.insert(query);
                request.setAttribute("message", "Account succesfully created! Please <a href='index.jsp'>log in.</a>");
            }
            
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        else if(request.getParameter("completeRegistration").equals("true")){

            request.getRequestDispatcher("complete_registration.jsp").forward(request, response);
            
        } 
        

        
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
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
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

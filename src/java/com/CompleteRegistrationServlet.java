/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DBConnection;
import model.User;

/**
 *
 * @author Alexu
 */
public class CompleteRegistrationServlet extends HttpServlet {

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
            throws ServletException, IOException {
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
        
        
        
        
        if(request.getParameter("complete_reg") != null) // From complete_registration.jsp
        {

            // Array to hold requested parameters
            String[] details = new String[6];
            // Request username and password 
            details[0] = (String)request.getParameter("title");
            details[1] = (String)request.getParameter("fName");
            details[2] = (String)request.getParameter("lName");
            details[3] = (String)request.getParameter("house_no");
            details[4] = (String)request.getParameter("post_code");
            details[5] = (String)request.getParameter("DOB");
            
            if(session.getAttribute("user") != null) {
                User user = (User)session.getAttribute("user");
            
                dbcon.completeRegistration(details, user.getUsername(), user.getRole());
                request.setAttribute("msg", "Registration Complete!");
            } else {
                response.sendRedirect("conError.jsp");
            }
            
            request.getRequestDispatcher("DashboardServlet.do").forward(request, response);
            
        }
        if(request.getParameter("completeRegistration").equals("true")){

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
        processRequest(request, response);
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
        processRequest(request, response);
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

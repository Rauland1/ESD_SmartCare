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
import java.util.ArrayList;
import java.util.List;
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
         HttpSession session = request.getSession(false);
       
        // Create a new connection to the database
        DBConnection dbcon = new DBConnection();
        // Connect to the database - request the connection attribute from the Listener
        dbcon.connect((Connection)request.getServletContext().getAttribute("connection"));
        // Set a session attribute with the connection
        session.setAttribute("dbcon", dbcon);
        
        User user = (User) session.getAttribute("user");
        
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
        
        if(request.getParameter("completeRegistration") != null && request.getParameter("completeRegistration").equals("true")){

            request.getRequestDispatcher("complete_registration.jsp").forward(request, response);
            
        } 
        
        if(request.getParameter("complete_reg") != null) // From complete_registration.jsp
        {
            List<String> detailsList = new ArrayList<>();
            
            detailsList.add(0, (String)request.getParameter("title"));
            detailsList.add(1, (String)request.getParameter("fName"));
            detailsList.add(2, (String)request.getParameter("lName"));
            detailsList.add(3, (String)request.getParameter("house_no"));
            detailsList.add(4, (String)request.getParameter("post_code"));
            detailsList.add(5, (String)request.getParameter("DOB"));
            
            // Array to hold requested parameters
            if(user.getRole().equals("Patient"))
            {
                detailsList.add(6, (String)request.getParameter("patientType"));
            } else {
                detailsList.add(6, (String)request.getParameter("shift_start"));
                detailsList.add(7, (String)request.getParameter("shift_start"));
            }
            
            
            String[] detailsArray = new String[detailsList.size()];
            detailsArray = detailsList.toArray(detailsArray);
            
            
            if(session.getAttribute("user") != null) {
                dbcon.completeRegistration(detailsArray, user.getUsername(), user.getRole());
                User newUser = dbcon.grabUserByName(user.getUsername());
                session.removeAttribute("user");
                session.setAttribute("user", newUser);
                request.setAttribute("msg", "Registration Complete!");
            } else {
                response.sendRedirect("conError.jsp");
            }
            
            request.getRequestDispatcher("DashboardServlet.do").forward(request, response);
            
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
            Logger.getLogger(CompleteRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CompleteRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
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

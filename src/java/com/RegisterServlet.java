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

/**
 *
 * @author ggra9
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
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
       
        // Array to hold requested parameters
        String[] query = new String[3];
        // Request username and password 
        query[0] = (String)request.getParameter("username");
        query[1] = (String)request.getParameter("password");
        query[2] = (String)request.getParameter("role");
        
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
        else
        {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DBConnection;

/**
 *
 * @author Chris
 */
public class AddPatientServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {      
        
        response.setContentType("text/html;charset=UTF-8");
        
        // Get current session and DON'T create one if it doesn't exist already
        HttpSession session = request.getSession(false);
        
        // Get database connection
        DBConnection dbcon = (DBConnection) session.getAttribute("dbcon");  
        
        String title = (String) request.getParameter("title");
        String fname = (String) request.getParameter("fname");
        String lname = (String) request.getParameter("lname");
        String address = (String) request.getParameter("address");        
        String type = (String) request.getParameter("type");
        String referred = (String) request.getParameter("referred");
        String dob = (String) request.getParameter("dob");        

        String[] details = new String[8];
        // Request username and password 
        details[0] = (String)title;
        details[1] = (String)fname;
        details[2] = (String)lname;
        details[3] = (String)address;   
        details[4] = (String)type;
        details[5] = (String)dob; 
        details[6] = (String)referred;       
        StringBuilder uname = new StringBuilder(); 
        uname.append(fname);
        uname.append(lname);
        uname.append("123");
        details[7] = uname.toString();      
        // Show booking confirmation if successful
        if (dbcon.insertPatient(details)){
            request.setAttribute("msg", "New Patient Added!");
        }     
        else{
            request.setAttribute("msg", "Unable to add new patient!");            
        }                
        request.getRequestDispatcher("add_patient.jsp").forward(request, response);
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
        } catch (ParseException ex) {
            Logger.getLogger(AddPatientServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(AddPatientServlet.class.getName()).log(Level.SEVERE, null, ex);
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

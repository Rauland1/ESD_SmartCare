/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DBConnection;
import java.text.SimpleDateFormat;  
import java.util.Date;  

/**
 *
 * @author Nelson Hobday
 */
public class BookAppointmentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException{
        
        response.setContentType("text/html;charset=UTF-8");
        
        // Get current session and DON'T create one if it doesn't exist already
        HttpSession session = request.getSession();
        
        // Get database connection
        DBConnection dbcon = (DBConnection) session.getAttribute("dbcon");     
        
        // Grab Appointment details to show on screen
        int PID = dbcon.grabPatientId((String) session.getAttribute("username"));  
        request.setAttribute("pID", PID);
        if(request.getParameter("date") != null){
            String date = request.getParameter("date");
            Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(date); 
            request.setAttribute("date", date); 
            // Get list of available staff for selected day
            String employees = dbcon.staffList(date1);
            request.setAttribute("staff", employees);
        }
        if (request.getParameter("staff")!= null){
            String name = request.getParameter("staff");
            String[] staffName = name.split(" ");
            String pid = Integer.toString(PID);
            String EID = staffName[0];
            request.setAttribute("EID", EID);
            request.setAttribute("staffName", name);
            String date = (String)request.getParameter("date");
            String time = "00:00";
            
            // Array to hold requested parameters
            String[] details = new String[4];
            // Request username and password 
            details[0] = (String)EID;
            details[1] = (String)pid;
            details[2] = (String)date;
            details[3] = (String)time;           
            
            dbcon.insertBooking(details);
            request.setAttribute("msg", "Booking Complete!");
            request.getRequestDispatcher("confirm_booking.jsp").forward(request, response);
        }
        request.getRequestDispatcher("booking.jsp").forward(request, response);
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
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(BookAppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(BookAppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
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

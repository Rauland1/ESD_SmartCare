/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
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
        HttpSession session = request.getSession(false);
        
        // Get database connection
        DBConnection dbcon = (DBConnection) session.getAttribute("dbcon");     
        
        // Grab selected date and time
        String selectedDate = request.getParameter("date");
        String selectedTime = request.getParameter("time");
        
        if(selectedDate == null && selectedTime == null) {
            request.setAttribute("date", "Not Selected");
            request.setAttribute("time", "Not Selected");
        }
               
        if(request.getParameter("select_appointment") != null && selectedDate != null && selectedTime != null) {
            
            // Format date
            SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = oldDateFormat.parse(selectedDate);
            // Get the day from the full date
            String day = new SimpleDateFormat("EEE").format(date);
            day = day.substring(0,2);
            System.out.println(day);
            
            // Get list of available staff for selected date/time
            String employees = dbcon.staffList(day, selectedTime);
            request.setAttribute("staffList", employees);
            
            oldDateFormat.applyPattern("dd/MM/yyyy");
            request.setAttribute("date", oldDateFormat.format(date));
            request.setAttribute("time", selectedTime);
            
            // Set proceed to booking confirmation message.
            String pr_msg = "Now confirm your booking.";
            request.setAttribute("pr_msg", pr_msg);
        }
        
        // After selecting date & time show available staff and overview of booking details
        if (request.getParameter("staff") != null){
            // Get name of doctor
            String name = request.getParameter("staff");
            String[] staffName = name.split(" ");
            String pid = String.valueOf(request.getParameter("patientID"));
            String EID = staffName[0];
                  
            
            // Format date one more time
            Date oldFormat = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("hiddenDate"));
            DateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = newFormat.format(oldFormat);
            
            request.setAttribute("staffName", name.substring(1)); 
            request.setAttribute("finalDate", request.getParameter("hiddenDate"));
            request.setAttribute("finalTime", request.getParameter("hiddenTime"));
            
            // Array to hold requested parameters
            String[] details = new String[4];
            // Request username and password 
            details[0] = (String)EID;
            details[1] = (String)pid;
            details[2] = (String)date;
            details[3] = (String)request.getParameter("hiddenTime");          
            
            // Show booking confirmation if successful
            if (dbcon.insertBooking(details)){
                request.setAttribute("msg", "Booking Complete!");
                request.getRequestDispatcher("confirm_booking.jsp").forward(request, response);
            }
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

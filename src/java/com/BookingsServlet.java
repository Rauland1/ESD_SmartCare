/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DBConnection;
import model.Patient;
import model.User;
/**
 *
 * @author Ash
 */
public class BookingsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @thrzows java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         // Get current session and DON'T create one if it doesn't exist already
        HttpSession session = request.getSession(false);
        
        // Get database connection
        DBConnection dbcon = (DBConnection) session.getAttribute("dbcon");
        User user = (User) session.getAttribute("user");

    
        if(session.getAttribute("dbcon")==null)
        {
            request.getRequestDispatcher("conError.jsp").forward(request, response);
            
        } 
//        else if(request.getParameter("app_date") == null){
//             
//            request.getRequestDispatcher("view_all_bookings.jsp").forward(request, response);
//
//        }
        else if(request.getParameter("view_bookings") != null && request.getParameter("app_date") != null)
        {
            String selectedDate = request.getParameter("app_date");
            System.out.println(selectedDate);
            String bookingRows = dbcon.viewBookings(user.getId(), selectedDate);
            
            request.setAttribute("bRow", bookingRows);
            request.getRequestDispatcher("timetable.jsp").forward(request, response);
            
        }
    }
        //else if(request.getParameter("date_submit") != null)
        //{
            //String date = request.getParameter("date");
            //List<Patient> patientsList = dbcon.patientsList(date);
            //session.setAttribute("patientsList", patientsList);
            //request.getRequestDispatcher("view_all_bookings.jsp").forward(request, response);
        //}
        //else if(request.getParameter("view_bookings").equals("true"))
        //{
          //  request.getRequestDispatcher("view_all_bookings.jsp").forward(request, response);
        //}
    

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
            Logger.getLogger(BookingsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BookingsServlet.class.getName()).log(Level.SEVERE, null, ex);
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

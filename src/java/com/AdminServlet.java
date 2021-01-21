/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author Alexu
 */
public class AdminServlet extends HttpServlet {

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
         // Get current session and DON'T create one if it doesn't exist already
        HttpSession session = request.getSession(false);
        
        // Get database connection
        DBConnection dbcon = (DBConnection) session.getAttribute("dbcon");
       
        if(session.getAttribute("dbcon")==null)
        {
            request.getRequestDispatcher("conError.jsp").forward(request, response);
        }
        else if(request.getParameter("change_price") != null){
            String surgeryName = request.getParameter("surgery_name");
            
            request.setAttribute("changePriceForm", dbcon.generatePriceForm(surgeryName));
            request.getRequestDispatcher("operations.jsp").forward(request, response);
 
        }
        else if(request.getParameter("submit_price") != null ){
            String surgeryName = request.getParameter("surgery_name");
            int newPrice = Integer.parseInt(request.getParameter("new_price"));
            dbcon.updateSurgeryPrice(newPrice, surgeryName);
            String msg = "Price has been changed successfully!";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("operations.jsp").forward(request, response);
           
        }
        else if(request.getParameter("viewSurgeryPrices") != null){
            String surgeryPrices =  dbcon.viewSurgeryPrices();
            request.setAttribute("surgeryPrices", surgeryPrices);
            request.getRequestDispatcher("operations.jsp").forward(request, response);
        }        
        else if(request.getParameter("viewAppointments") != null){
            
            // Create dropdown list to view employees that have appointments for a given day
            String booking = dbcon.viewAppointments(-1);
            request.setAttribute("bookingRows", booking);

            request.getRequestDispatcher("records.jsp").forward(request, response);
        }
        else if(request.getParameter("delete_booking") != null){
            dbcon.deleteAppointment(Integer.parseInt(request.getParameter("appointmentID")));
            response.sendRedirect("records.jsp");
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
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
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


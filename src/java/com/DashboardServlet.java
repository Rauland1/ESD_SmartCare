package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DBConnection;
import model.User;

/**
 *
 * @author Grant
 */
public class DashboardServlet extends HttpServlet {

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
        
        response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        
        // Get current session and DON'T create one if it doesn't exist already
        HttpSession session = request.getSession();
        
        // Get database connection
        DBConnection dbcon = (DBConnection) session.getAttribute("dbcon");       
        
        dbcon.getTurnoverForDay(new Date(2020 - 1900, 12 - 1, 18));

        // Get the username from the session
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            session.removeAttribute("username");
            response.sendRedirect("index.jsp");
            return;
        }
        
        User user = dbcon.grabUserByName(username);
        session.setAttribute("user", user);
        
        if(user.getRole().equals("Admin"))
        {
            String dashboard_content = dbcon.checkReg();
            request.setAttribute("regTable", dashboard_content);
        }
        else if(user.getRole().equals("Doctor") || user.getRole().equals("Nurse")) 
        {
            
            String dashboard_content = dbcon.checkPrescription(user.getUsername());
            request.setAttribute("presTable", dashboard_content);
        }
        
        String details = dbcon.checkFields(username, user.getRole());
        request.setAttribute("details", details);
        
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
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
            Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
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

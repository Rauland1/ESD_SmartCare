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

/**
 *
 * @author ggra9
 */
public class PrescriptionServlet extends HttpServlet {

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
        
        DBConnection dbcon = (DBConnection) session.getAttribute("dbcon");
        
        if(session.getAttribute("dbcon")==null)
        {
            request.getRequestDispatcher("conError.jsp").forward(request, response);
        } 
        else if(request.getParameter("approve_request") != null) // From nurse/doc dashboard - Approve patient prescription request
        {
            dbcon.confirmPrescription(Integer.parseInt(request.getParameter("presID")));
            
            request.setAttribute("msg", "<span>Prescription has been approved!</span>");
            
            request.getRequestDispatcher("DashboardServlet.do").forward(request, response);
        }
        else if(request.getParameter("issue_prescription") != null && request.getParameter("patientID") != null ) // From patients.jsp -> Redirects to issue_prescription.jsp
        {
            List<Patient> patientsList = dbcon.patientsList("all");
            String patientName = "";
            
            for(Patient patient : patientsList)
            {
                if(patient.getId() == Integer.parseInt(request.getParameter("patientID")))
                {
                    String patientTitle = patient.getTitle();
                    patientName = patientTitle + ' ' + patient.getName();
                    break;
                }
            }
            
            request.setAttribute("patientName", patientName);
            request.getRequestDispatcher("issue_prescription.jsp").forward(request, response);
        }
        else if(request.getParameter("add_prescription") != null) // From issue_prescription.jsp
        {
            // INSERT PRESCRIPTION
            String prescription[] = new String[3];
            
            //get employee id from username
            String eID = dbcon.getEmplyeeIDFromUsername(request.getParameter("eName"));
            
            // Construct string array
            prescription[0] = (String) eID;
            prescription[1] = (String) request.getParameter("pID");
            prescription[2] = (String) request.getParameter("prescription");
            
            dbcon.insertPrescription(prescription);
            
            String msg = "<h1>New prescription has been added!</h1>";
            request.setAttribute("msg", msg);
            
            request.getRequestDispatcher("issue_prescription.jsp").forward(request, response);
        }
        else if(request.getParameter("request_prescription") != null) // From patient dashboard - request prescription
        {
            
            String prescriptionID = request.getParameter("prescriptionID");
            dbcon.updatePrescription(prescriptionID);
            
            request.getRequestDispatcher("view_prescriptions.jsp").forward(request, response);
            
        }
        else if(request.getParameter("viewPrescription").equals("true")){
            String prescriptionRows = dbcon.viewPrescriptions(dbcon.grabPatientId((String) session.getAttribute("username")));
            
            request.setAttribute("pRow", prescriptionRows);
            request.getRequestDispatcher("view_prescriptions.jsp").forward(request, response);
            
        }
        // ELSE NO PATIENT HAS BEEN SELECTED
        // what if more patients selected? - it uses the first one that was selected
     
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
            Logger.getLogger(PrescriptionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PrescriptionServlet.class.getName()).log(Level.SEVERE, null, ex);
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

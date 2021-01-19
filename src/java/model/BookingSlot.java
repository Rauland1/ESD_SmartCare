/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Grant
 */
public class BookingSlot {
    
    private int id;
    private int employeeId;
    private int patientId;
    private Date resultDate;
    private java.sql.Time resultTime;                
    
    public BookingSlot(int id, int employeeId, int patientId, Date resultDate, java.sql.Time resultTime) {
        this.id = id;
        this.employeeId = employeeId;
        this.patientId = patientId;
        this.resultDate = resultDate;
        this.resultTime = resultTime;
    }
    
    public int getId(){
        return id;
    }
    
    public int getEmployeeId(){
        return employeeId;
    }
    
    public int getPatientId(){
        return patientId;
    }
    
    public Date getDate(){
        return resultDate;
    }
    
    public java.sql.Time getTime(){
        return resultTime;
    }
    
    
    
}

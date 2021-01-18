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
    
    int id;
    int employeeId;
    int patientId;
    Date resultDate;
    java.sql.Time resultTime;                
    
    public BookingSlot(int id, int employeeId, int patientId, Date resultDate, java.sql.Time resultTime) {
        this.id = id;
        this.employeeId = employeeId;
        this.patientId = patientId;
        this.resultDate = resultDate;
        this.resultTime = resultTime;
    }
    
}

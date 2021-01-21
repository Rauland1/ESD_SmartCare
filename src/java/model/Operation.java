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
public class Operation {
    
    int id;
    int bookingSlotId;
    int employeeId;
    int patientId;  
    String surgery_name;
    int operation_duration;
    
    public Operation(int id, int bookingSlotId, int employeeId, int patientId, String surgery_name,int operation_duration) {
        this.id = id;
        this.bookingSlotId = bookingSlotId;
        this.employeeId = employeeId;
        this.patientId = patientId;
        this.surgery_name = surgery_name;
        this.operation_duration = operation_duration;
    }
}
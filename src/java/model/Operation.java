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
    float duration;
    float charge;               
    
    public Operation(int id, int bookingSlotId, float duration, float charge) {
        this.id = id;
        this.bookingSlotId = bookingSlotId;
        this.duration = duration;
        this.charge = charge;
    }
}

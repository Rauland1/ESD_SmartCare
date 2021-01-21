/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Grant
 */
public class SurgeryType {
    String surgeryName;
    int minDuration;
    float charges;
    
    
    public SurgeryType(String surgeryName, int minDuration, float charges) {
        this.surgeryName = surgeryName;
        this.minDuration = minDuration;
        this.charges = charges;
    }
  
}

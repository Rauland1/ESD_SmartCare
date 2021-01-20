/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ggra9
 */
public class Patient {
    
    private int id;
    private String title;
    private String name;
    private String type;
    private String address;
    private String referred;
       
    // Constructor to be used with the new DB format
    public Patient(int id, String title, String fName, String lName, String address, String type, String referred){
        this.id = id;
        this.title = title;
        this.name = fName + ' ' + lName;
        this.address = address;
        this.type = type;
        this.referred = referred;
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public String getAddress() {
        return address;
    }
    public String getReferred(){
        return referred;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Grant - modified by Raul
 */
public class User {
    
    private String username;
    private String role;
    private String title;
    private String firstName;
    private String lastName;

    private int id;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }
    
    public User(String username, String role, String title, String firstName, String lastName, int id) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getRole() {
        String role = this.role;
        // capitalize the first letter for aesthetic purposes
        String capitalRole = role.substring(0,1).toUpperCase() + role.substring(1);
        return capitalRole.trim();
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
}
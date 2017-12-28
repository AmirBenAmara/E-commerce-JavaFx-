/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.models;

/**
 *
 * @author Gold Solution
 */
public class Admin {
    int id_admin ;
    String email_admin ; 
    String password ;

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getEmail_admin() {
        return email_admin;
    }

    public void setEmail_admin(String email_admin) {
        this.email_admin = email_admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}

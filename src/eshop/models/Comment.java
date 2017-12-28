/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.models;

import java.sql.Date;

/**
 *
 * @author Karim SNOUSSI
 */
public class Comment {
    
    private int id;
    private Customer customer;
    private String comment;
    private Date dateComment;
    private Product about;
    
    public Comment(){
        
    }

    public Comment(int id, Customer customer, String comment, Date dateComment, Product about) {
        this.id = id;
        this.customer = customer;
        this.comment = comment;
        this.dateComment = dateComment;
        this.about = about;
    }

    
    
    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getComment() {
        return comment;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public Product getAbout() {
        return about;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public void setAbout(Product about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o); //To change body of generated methods, choose Tools | Templates.
    }
    
}

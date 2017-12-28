/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.models;

import java.sql.Date;




/**
 *
 * @author Gold Solution
 */
public class Claim {
    int id ; 
    Date date ; 
    Order order ; 
    int id_order;   
    String text,etat ; 
    
    
    
    public Claim(){
        
    }
    
    public Claim(int id  , Order order , String text ){
        this.id=id ; 
        this.order=order ; 
        this.text=text;
        
 
            
    }
     public Claim(int id  , java.sql.Date date , String text ){
        this.id=id ; 
        this.date=date ; 
        this.text=text;
     }   
    /**
     *
     * @param id
     * @param date
     * @param order
     * @param text
     */
    public Claim(int id  , java.sql.Date date ,Order order, String text ){
        this.id=id ; 
        this.date=date ; 
        this.order = order;
        this.id_order=order.getOrderId();
        this.text=text;    
    }  
      //const for get metth
       public Claim(int id  , java.sql.Date date ,int id_order, String text , String etat ){
        this.id=id ; 
        this.date=date ; 
        this.id_order=id_order;
        this.text=text; 
        this.etat=etat;
        
    }
       public Claim( java.sql.Date date ,int id_order, String text ){
        this.date=date ; 
        this.id_order=id_order;
        this.text=text;    
        
    }
       
     public Claim( java.sql.Date date , String text ){ 
        this.date=date;
        this.text=text;
        
    }

    @Override
    public String toString() {
        return "Claim{" + "id=" + id + ", date=" + date + ", order_id=" + id_order + ", text=" + text + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Claim other = (Claim) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }
    
}

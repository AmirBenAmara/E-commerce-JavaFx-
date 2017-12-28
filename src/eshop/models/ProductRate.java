/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.models;

/**
 *
 * @author ASUS
 */
public class ProductRate {
   
    private int id;

   private int id_customer ;
    //private Customer Customer;
    private int id_produit;
    private double rate;

    public ProductRate() {
    }

    public ProductRate(int id_produit, double rate) {
        this.id_produit = id_produit;
        this.rate = rate;
    }

    public ProductRate(int id_customer, int id_produit, float rate) {
        this.id_customer = id_customer;
        this.id_produit = id_produit;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public double getRate() {
        return  rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final ProductRate other = (ProductRate) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProduitRate{" + "id=" + id + ", id_customer=" + id_customer + ", id_produit=" + id_produit + ", rate=" + rate + '}';
    }

 
    
    

  

    
}

    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.models;

import static java.sql.JDBCType.NULL;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Gold Solution
 */
public class Cart {

    private int idCart;
    private Customer customer;

    public Cart() {
    }

    public Cart(int idCart, Customer customer) {
        this.idCart = idCart;
        this.customer = customer;
    }

    public Cart(int idCart) {
        this.idCart = idCart;
    }

    public Cart(Customer customer) {
        this.customer = customer;
    }
    

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Cart other = (Cart) obj;
        if (this.idCart != other.idCart) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cart{" + "idCart=" + idCart + ", customer=" + customer + '}';
    }

    
}

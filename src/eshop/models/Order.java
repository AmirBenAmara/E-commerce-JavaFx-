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
public class Order {
    //DEVELOPPE PAR AMIR MAJ PAR KARIM POUR DES BESOINS FONCTONNEL.
    //EN CAS DE DISFOCTIONNEMENT DU A prixTotal, priceProduit OU productName JE SERAI LE RESPONSABLE. DEJA TESTE.
    //@KARIM SNOUSSI AUCUNE NECESSITE DE MAJ DE SERVICE OU DE DB.
    private int orderId, orderQuantity;
    private Date orderDate;
    private Customer customer;
    private Product product;
    private String etat;
    private float prixTotal, priceProduit;//@KARIM SNOUSSI ceci a été ajouté par Karim SNOUSSI pour des besoins fonctionnels d'affichage de prix totale de la commande et pour éviter le redondance dans la DB
    private String productName;//@KARIM SNOUSSI ceci a été ajouté par Karim SNOUSSI pour des besoins fonctionnels d'affichage et pour éviter le redondance dans la DB
    //Les trois attributs sont initialisés lors de toute modification de produit ou de quantité, ya pas besoin des les modifier @kARIM SNOUSSI
    public Order() {

    }

    public Order(int orderId, int orderQuantity, Date orderDate, Customer customer, String etat) {
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.customer = customer;
        this.etat = etat;
    }

    public Order(int orderId, int orderQuantity, Date orderDate, Customer customer, Product product, String etat) {
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.customer = customer;
        this.product = product;
        this.etat = etat;
        this.priceProduit = product.getPrix(); //@KARIM SNOUSSI
        this.productName = product.getDescription(); //@KARIM SNOUSSI
        this.prixTotal= orderQuantity*product.getPrix(); //@KARIM SNOUSSI
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
        this.prixTotal= orderQuantity*product.getPrix(); //@KARIM SNOUSSI
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getProductName() {
        return productName;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public float getPriceProduit() {
        return priceProduit;
    }
    
    

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        this.priceProduit = product.getPrix();
        this.productName = product.getDescription();
        this.prixTotal= this.orderQuantity*product.getPrix();
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderQuantity=" + orderQuantity + ", orderDate=" + orderDate + ", customer=" + customer + ", etat=" + etat + '}';
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
        final Order other = (Order) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        return true;
    }

}

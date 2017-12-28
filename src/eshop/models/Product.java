/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.models;

import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Product {
    
    private  int id;
    private String refernce;
    private String description;
    private float prix;
    private String image;
    private int quantite;
    private float promotion;
    private String categorie;
    private int id_customer;
    private boolean enable;
    

    public Product() {
    }

    public Product(int id, String refernce, String description, float prix, String image, int quantite, String categorie, int id_customer, float promotion, boolean enable) {
        this.id = id;
        this.refernce = refernce;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.quantite = quantite;
        this.categorie = categorie;
        this.id_customer = id_customer;
        this.promotion = promotion;
        this.enable = enable;
    }

    
    
    public Product(int id, String refernce, String description, float prix, String image, int quantite, String categorie, int id_customer, float promotion) {
        this.id = id;
        this.refernce = refernce;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.quantite = quantite;
        this.categorie = categorie;
        this.id_customer = id_customer;
        this.promotion = promotion;
    }

    public Product(String refernce, String description, float prix, String image, int quantite, String categorie, int id_customer, float promotion) {
        this.refernce = refernce;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.quantite = quantite;
        this.categorie = categorie;
        this.id_customer = id_customer;
        this.promotion = promotion;
    }

    public Product(String refernce, String description, float prix, String image, int quantite, String categorie, float promotion) {
        this.refernce = refernce;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.quantite = quantite;
        this.categorie = categorie;
        this.promotion = promotion;
    }

    public Product(String refernce, String description, float prix, int quantite, String categorie, float promotion) {
        this.refernce = refernce;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
        this.promotion = promotion;
    }

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getRefernce() {
        return refernce;
    }

    public void setRefernce(String refernce) {
        this.refernce = refernce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public float getPromotion() {
        return promotion;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "Produit{" + " refernce=" + refernce + ", description=" + description + ", prix=" + prix +", quantite=" + quantite + ", categorie=" + categorie +  ", promotion=" + promotion + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.refernce);
        hash = 79 * hash + this.id_customer;
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
        final Product other = (Product) obj;
        if (this.id_customer != other.id_customer) {
            return false;
        }
        if (!Objects.equals(this.refernce, other.refernce)) {
            return false;
        }
        return true;
    }
    

    
    
}

    


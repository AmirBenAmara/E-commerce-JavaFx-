/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Gold Solution
 */
public class Catalog {

    private int id;

    private java.sql.Date dateSortie;

    private java.sql.Date dateFinValidite;

    private String raisonDeSortie;

    private Double promotion;

    private List<Product> listeProduit;

    public Catalog() {
        listeProduit = new ArrayList<>();
    }

    public Catalog(int id, Date dateSortie, Date dateFinValidite, String raisonDeSortie, Double promotion) {
        this.id = id;
        this.dateSortie = dateSortie;
        this.dateFinValidite = dateFinValidite;
        this.raisonDeSortie = raisonDeSortie;
        this.promotion = promotion;
        listeProduit = new ArrayList<>();

    }

    public Catalog(int id, Date dateSortie, Date dateFinValidite, String raisonDeSortie) {//raison autre que promotion
        this.id = id;
        this.dateSortie = dateSortie;
        this.dateFinValidite = dateFinValidite;
        this.raisonDeSortie = raisonDeSortie;
        this.promotion = 0d;

        listeProduit = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public List<Product> getListeProduit() {
        return listeProduit;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public Date getDateFinValidite() {
        return dateFinValidite;
    }

    public String getRaisonDeSortie() {
        return raisonDeSortie;
    }

    public Double getPromotion() {
        return promotion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setListProduit(List<Product> listProduit) {
        this.listeProduit = listProduit;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public void setDateFinValidite(Date dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    public void setRaisonDeSortie(String raisonDeSortie) {
        this.raisonDeSortie = raisonDeSortie;
    }

    public void setPromotion(Double promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "Catalog{" + "id=" + id + ", dateSortie=" + dateSortie + ", dateFinValidite=" + dateFinValidite + ", raisonDeSortie=" + raisonDeSortie + ", promotion=" + promotion + ", listeProduit=" + listeProduit + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof Catalog) && obj != null) {
            return (((Catalog) obj).getDateSortie() == this.dateSortie) && ((Catalog) obj).getListeProduit().equals(this.listeProduit) && (((Catalog) obj).getDateSortie() == this.dateSortie) && (((Catalog) obj).getDateFinValidite() == this.dateFinValidite) && (((Catalog) obj).getRaisonDeSortie() == null ? this.getRaisonDeSortie() == null : ((Catalog) obj).getRaisonDeSortie().equals(this.getRaisonDeSortie())) && (Objects.equals(((Catalog) obj).getPromotion(), this.promotion));

        }
        return false;
    }

}

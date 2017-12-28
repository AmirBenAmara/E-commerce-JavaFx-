/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.services;

import eshop.interfaces.ICatalog;
import eshop.models.Catalog;
import eshop.models.Product;
import eshop.technique.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gold Solution
 */
public class CatalogServices implements ICatalog {

    Connection connection;

    public CatalogServices() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Catalog catalog) {
        String req = "insert into Catalog (date_sortie,date_fin_validite,raison_de_sortie,promotion) values (?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setDate(1, catalog.getDateSortie());
            preparedStatement.setDate(2, catalog.getDateFinValidite());
            preparedStatement.setString(3, catalog.getRaisonDeSortie());
            preparedStatement.setDouble(4, catalog.getPromotion());
            preparedStatement.executeUpdate();

            for (Product produit : catalog.getListeProduit()) {
                System.err.println("Debut : this.addCatalogueProduct(produit, catalog);");
                this.addCatalogueProduct(produit, catalog);
                System.err.println("Fin : this.addCatalogueProduct(produit, catalog);");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void addCatalogueProduct(Product product, Catalog catalog) {

        String req = "insert into catalog_product (id_catalog,id_product) values (?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, catalog.getId());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erreur d'ajour");
            ex.printStackTrace();
            System.err.println("Erreur d'ajour");

        }
    }

    @Override
    public void update(Catalog t) {//c'est vraiùent pas une bonne idée :')
    }

    public void removeProductFromCatalogue(Product product, Catalog catalog) {
        String req = "delete from catalog_product where id_catalog =? and id_product=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, catalog.getId());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void remove(Catalog catalog) {

        for (Product produit : catalog.getListeProduit()) {
            this.removeProductFromCatalogue(produit, catalog);
        }
        String req = "delete from catalog where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, catalog.getId());

            preparedStatement.executeUpdate();//il faut la vider avant de la supprimer pour eviter les erreur SQL
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public Catalog findById(Integer id
    ) { // rechercher le catalogue par la référence = IdCatalog
        Catalog cat = null;
        String req = "select * from catalog where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cat = new Catalog(resultSet.getInt("id"
                ), resultSet.getDate("date_sortie"
                ), resultSet.getDate("date_fin_validite"
                ), resultSet.getString("raison_de_sortie"
                ), resultSet.getDouble("promotion"
                ));
                cat.setListProduit(this.findProduitByCatalog(cat));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cat;
    }

    @Override
    public List<Catalog> getAll() {

        List<Catalog> catS = new ArrayList<>();
        Catalog cat = null;
        String req = "select * from catalog";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cat = new Catalog(resultSet.getInt("id"
                ), resultSet.getDate("date_sortie"
                ), resultSet.getDate("date_fin_validite"
                ), resultSet.getString("raison_de_sortie"
                ), resultSet.getDouble("promotion"
                ));
                cat.setListProduit(this.findProduitByCatalog(cat));
                catS.add(cat);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return catS;

    }

    @Override
    public List<Catalog> findByRaisonSortie(String raisonDeSortie
    ) {
        List<Catalog> catS = new ArrayList<>();
        Catalog cat = null;
        String req = "select * from catalog where raison_de_sortie =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, raisonDeSortie);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cat = new Catalog(resultSet.getInt("id"
                ), resultSet.getDate("date_sortie"
                ), resultSet.getDate("date_fin_validite"
                ), resultSet.getString("raison_de_sortie"
                ), resultSet.getDouble("promotion"
                ));
                cat.setListProduit(this.findProduitByCatalog(cat));
                catS.add(cat);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return catS;

    }

    @Override
    public List<Catalog> findByPeriode(Date dateDebut, Date dateDeFin
    ) {
        List<Catalog> catS = new ArrayList<>();
        Catalog cat = null;
        String req = "select * from catalog where raison_de_sortie =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setDate(1, dateDebut);
            preparedStatement.setDate(2, dateDeFin);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cat = new Catalog(resultSet.getInt("id"
                ), resultSet.getDate("date_sortie"
                ), resultSet.getDate("date_fin_validite"
                ), resultSet.getString("raison_de_sortie"
                ), resultSet.getDouble("promotion"
                ));
                cat.setListProduit(this.findProduitByCatalog(cat));
                catS.add(cat);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return catS;
    }

    @Override
    public List<Catalog> findByValide() {//liste des Catalogues toujours valable
        List<Catalog> catS = new ArrayList<>();
        Catalog cat = null;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date dateDeFin;
        dateDeFin = new java.sql.Date(utilDate.getTime());//date courante   

        String req = "select * from catalog where date_fin_validite > ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setDate(1, dateDeFin);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cat = new Catalog(resultSet.getInt("id"
                ), resultSet.getDate("date_sortie"
                ), resultSet.getDate("date_fin_validite"
                ), resultSet.getString("raison_de_sortie"
                ), resultSet.getDouble("promotion"
                ));
                cat.setListProduit(this.findProduitByCatalog(cat));
                catS.add(cat);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return catS;
    }

    List<Product> findProduitByCatalog(Catalog cat
    ) {
        List<Product> products;
        products = new ArrayList<>();
        ProductServices productServices = new ProductServices();
        String req = "select * from catalog_product where id_catalog =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, cat.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(productServices.findById(resultSet.getInt("id_product")));
            }

        } catch (SQLException ex) {
        }
        return products;

    }

}

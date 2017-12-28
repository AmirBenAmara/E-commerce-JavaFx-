/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.services;

import eshop.interfaces.IProduct;
import eshop.models.Product;
import eshop.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modulesuser.OverviewController;

/**
 *
 * @author ASUS
 */
public class ProductServices implements IProduct {

    Connection connection;

    public ProductServices() {
        this.connection = DataSource.getInsatance().getConnection();//constructeur qui appl la cnxion etabli ds la datasource

    }

    public ProductServices(Connection connection) {
        this.connection = DataSource.getInsatance().getConnection();//constructeur qui appl la cnxion etabli ds la datasource
    }

    @Override
    public Boolean add(Product p) {

        try {
            String req = "INSERT INTO `product` ( reference, description, prix, quantite, image, categorie, promotion , id_customer) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, p.getRefernce());
            preparedStatement.setString(2, p.getDescription());
            preparedStatement.setFloat(3, p.getPrix());
            preparedStatement.setInt(4, p.getQuantite());

            preparedStatement.setString(5, OverviewController.imgurl);
            preparedStatement.setString(6, p.getCategorie());
            preparedStatement.setFloat(7, p.getPromotion());
            preparedStatement.setInt(8, 11);

            preparedStatement.executeUpdate();
            System.out.println("ajout terminé");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("probleme d ajout");
            return false;
        }
    }

    @Override
    public ObservableList<Product> showProduit() {

        ObservableList<Product> Lproduits = FXCollections.observableArrayList();
        String req = "select * from product where enable = 1 ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product produit = new Product(resultSet.getString("reference"), resultSet.getString("description"),
                        resultSet.getFloat("prix"), resultSet.getString("image"), resultSet.getInt("quantite"),
                        resultSet.getString("categorie"), resultSet.getFloat("promotion"));
                Lproduits.add(produit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Lproduits;
    }

    @Override
    public boolean delete(int id) {
        try {
            String req = "DELETE FROM `product` WHERE id=" + id;

            PreparedStatement ps = connection.prepareStatement(req);

            ps.executeUpdate();
            System.out.println("offre supprimé");
            return true;
        } catch (SQLException ex) {
            System.out.println("Problème de suppression");
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public int getIDByReference(String reference) throws SQLException {
        String req = "SELECT * FROM `product` WHERE reference= ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, reference);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Product produit = new Product(resultSet.getInt("id"), resultSet.getString("reference"), resultSet.getString("description"), resultSet.getFloat("prix"), resultSet.getString("image"), resultSet.getInt("quantite"), resultSet.getString("categorie"), resultSet.getInt("id_customer"), resultSet.getFloat("promotion"));
            return produit.getId();
        }

        return 0;

    }

    @Override
    public String getImgById(int id) throws SQLException {
        String req = "SELECT * FROM `product` WHERE id= ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Product produit = new Product(resultSet.getString("reference"), resultSet.getString("description"),
                    resultSet.getFloat("prix"),
                    resultSet.getString("image"), resultSet.getInt("quantite"), resultSet.getString("categorie"), resultSet.getFloat("promotion"));
            return produit.getImage();

        }

        return null;

    }

    @Override
    public boolean update(int id, String description, float prix, int quantite, float promotion) {

        String req = "UPDATE product SET description = ? ,prix = ? ,quantite = ? , promotion = ?  WHERE id =?";

        try {

            PreparedStatement ps = connection.prepareStatement(req);

            ps.setString(1, description);
            ps.setFloat(2, prix);
            ps.setInt(3, quantite);
            ps.setFloat(4, promotion);
            ps.setInt(5, id);

            ps.executeUpdate();
            System.out.println("Modification terminé");
            return true;

        } catch (SQLException ex) {
            System.out.println("Problème de Modification");
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public ObservableList<Product> SearchProd(String entry) throws SQLException {

        ObservableList<Product> liste = FXCollections.observableArrayList();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM product WHERE reference like ? or description like ?  or prix like ?  or quantite like ?  or promotion like ?  or categorie like ?  ");
        ps.setString(1, "%" + entry + "%");
        ps.setString(2, "%" + entry + "%");
        ps.setString(3, "%" + entry + "%");
        ps.setString(4, "%" + entry + "%");
        ps.setString(5, "%" + entry + "%");
        ps.setString(6, "%" + entry + "%");

        ResultSet set = ps.executeQuery();

        while (set.next()) {

            Product e = new Product(set.getString("reference"), set.getString("description"),
                    set.getFloat("prix"), set.getString("image"), set.getInt("quantite"),
                    set.getString("categorie"), set.getFloat("promotion"));
            e.setId(set.getInt("id"));
            liste.add(e);
        }
        return liste;

    }

    @Override
    public ObservableList<Product> getByCustomer(int id_customer) {

        ObservableList<Product> listP = FXCollections.observableArrayList();
        String req = "select * from product where id_customer = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_customer);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product produit = new Product(resultSet.getString("reference"), resultSet.getString("description"),
                        resultSet.getFloat("prix"), resultSet.getString("image"), resultSet.getInt("quantite"),
                        resultSet.getString("categorie"), resultSet.getFloat("promotion"));
                listP.add(produit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listP;

    }

    @Override
    public ObservableList<Product> getByCategorie(String categorie) {
        ObservableList<Product> listP = FXCollections.observableArrayList();
        String req = "select * from product where categorie = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, categorie);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product produit = new Product(resultSet.getString("reference"), resultSet.getString("description"),
                        resultSet.getFloat("prix"), resultSet.getString("image"), resultSet.getInt("quantite"),
                        resultSet.getString("categorie"), resultSet.getFloat("promotion"));
                listP.add(produit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listP;

    }

    @Override
    public int TestProduit(int id_produit) throws SQLException {

        String req = "select id_customer from `product` where  id= ?   ";
        PreparedStatement ps = connection.prepareStatement(req);

        ps.setInt(1, id_produit);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            int id_cust = resultSet.getInt("id_customer");

            return id_cust;
        }

        return 0;
    }

    public void updateEnable(int id) {
         
        String req = "UPDATE product SET enable =? WHERE id =?";
        try {         
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setBoolean(1, true); 
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        String req = "select * from product ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product p = new Product(resultSet.getInt("id"),
                         resultSet.getString("reference"),
                         resultSet.getString("description"),
                         resultSet.getFloat("prix"),
                         resultSet.getString("image"),
                         resultSet.getInt("quantite"),
                         resultSet.getString("categorie"),
                         resultSet.getInt("id_customer"),
                         resultSet.getFloat("promotion"),
                         resultSet.getBoolean("enable"));

                productList.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productList;
    }


    @Override
    public Product findById(int id_produit) {
        Product p = null;
        String req = "select * from product where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id_produit);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                p = new Product(resultSet.getInt("id")
                        , resultSet.getString("reference")
                        , resultSet.getString("description"),
                        resultSet.getFloat("prix")
                        , resultSet.getString("image")
                        , resultSet.getInt("quantite"),
                        resultSet.getString("categorie")
                        , resultSet.getInt("id_customer")
                        , resultSet.getFloat("promotion")
                        , resultSet.getBoolean("enable"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;

    }
}

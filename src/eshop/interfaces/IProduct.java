/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.interfaces;

import eshop.models.Catalog;
import eshop.models.Customer;
import eshop.models.Product;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Gold Solution
 */
public interface IProduct {
   ObservableList<Product> getByCustomer(int id_customer);
   // List<Product> getByCatalog(Catalog catalogue);
        ObservableList<Product> getByCategorie(String categorie);

     Boolean add(Product p);
      ObservableList<Product> showProduit();
      boolean delete(int id);
       int getIDByReference(String reference) throws SQLException ;
        String getImgById(int id) throws SQLException ;
       ObservableList<Product> SearchProd(String entry) throws SQLException;
       boolean update(int id, String description, float prix, int quantite, float promotion);
     int TestProduit(int id_produit) throws SQLException ;
     List<Product> getAll() ;
     Product findById(int  id_produit);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.services;

import eshop.interfaces.ICart;
import eshop.interfaces.ICartProduct;
import eshop.models.Cart;
import eshop.models.CartProduct;
import eshop.models.Customer;
import eshop.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import eshop.models.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author helak
 */
public class CartProductServices implements ICartProduct {

    Connection connection;

    public CartProductServices() {
        connection = DataSource.getInsatance().getConnection();
    }

    
   /* @Override
    public List<CartProduct> findByProduct(Product product) {
    List<CartProduct> cartProducts = new ArrayList<>();
        String req = "select * from product_panier where id_produit=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, product.getProductId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CartProduct cp = new CartProduct(resultSet.getInt("id"), 
                        new ProductServices().findById(resultSet.getBoolean(1)));
                CartProduct.add(cp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cartProducts;
    
    }
*/
    @Override
    public CartProduct findcarteprodById(int id) {
       CartProduct cartProduct = null;
        String req = "select * from product_panier where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cartProduct = new CartProduct(resultSet.getInt("id"), resultSet.getBoolean(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cartProduct;  }

    @Override
    public void add(CartProduct t) {
        String req = "insert into product_panier (id,id_panier,id_produit,quantity,commander) values (?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, t.getIdCartProduit());
            preparedStatement.setInt(2, t.getCart().getIdCart());
            preparedStatement.setInt(3, t.getProduct().getId());
            preparedStatement.setInt(4, t.getQte());
            preparedStatement.setBoolean(5, t.getCommander());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  }

    @Override
    public void update(CartProduct t) {
    String req = "update product_panier set quantity=? where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
      
            preparedStatement.setInt(1, t.getIdCartProduit()); 
            preparedStatement.setInt(2, t.getQte());
            preparedStatement.executeUpdate();
            System.out.println("update active");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  }

    @Override
    public void remove(CartProduct t) {
    String req = "delete from product_panier where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, t.getIdCartProduit());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } }

    @Override
    public CartProduct findById(Integer r) {
       CartProduct cartP = null;
        String req = "select * from product_panier where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cartP = new CartProduct(resultSet.getInt("id"), resultSet.getBoolean(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cartP;
      }

    @Override
    public List<CartProduct> getAll() {
      List<CartProduct> cartP = new ArrayList<>();
        String req = "select * from product_panier";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CartProduct cartPr = new CartProduct(resultSet.getInt("id"), resultSet.getBoolean(2));
                cartP.add(cartPr);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cartP;  }

    @Override
    public List<CartProduct> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CartProduct> findByProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}

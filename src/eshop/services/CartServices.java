/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.services;

import eshop.interfaces.ICart;
import eshop.models.Cart;
import eshop.models.Customer;
import eshop.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gold Solution
 */
public class CartServices implements ICart{
    
    Connection connection;

    public CartServices() {
        connection = DataSource.getInsatance().getConnection();
    }

// l'ajout du panier se fait automatiquement en ajoutant un customer; chaque customer possède un seul panier dédié pour lui
  /*  public void add(Cart t) {
    }
*/

    @Override
    public List<Cart> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cart findcarteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Cart t) {
    
      String req = "INSERT INTO `panier`(`id`, `id_customer`) value(?,?)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, t.getIdCart());
            ps.setInt(2, t.getCustomer().getCustomerId());
            
            ps.executeUpdate();
            System.out.println("ajout succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   }

    @Override
    public void update(Cart t) {
      
        String req = "update panier set id=?,id_customer=? where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
           preparedStatement.setInt(1, t.getIdCart());
            preparedStatement.setInt(2, t.getCustomer().getCustomerId());
         
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } }

    @Override
    public void remove(Cart t) {
       String req = "delete from panier where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, t.getIdCart());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } }

    @Override
    public Cart findById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cart> getAll() {
    List<Cart> cart = new ArrayList<>();
        String req = "select * from panier";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cart cart1 = new Cart(resultSet.getInt("id"));
                cart.add(cart1);
            }
             System.out.println("getAll succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cart;
    }
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.services;

import eshop.interfaces.IOrder;
import eshop.models.Customer;
import eshop.models.Order;
import eshop.models.Product;
import eshop.technique.DataSource;
import eshop.technique.Tools;
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
public class OrderServices implements IOrder {

    Connection connection;
    ProductServices productServices = new ProductServices();

    public OrderServices() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public List<Order> getByCustomer(Customer customer) {
        List<Order> orders = new ArrayList<>();
        String req = "select * from commande where id_customer=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, customer.getCustomerId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order or;
                or = new Order(resultSet.getInt("id"), resultSet.getInt("quantity"), resultSet.getDate("date"),
                        new CustomerServices().findById(resultSet.getInt("id_customer")),
                        productServices.findById(resultSet.getInt("id_product")),
                        resultSet.getString("etat"));
                orders.add(or);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;
    }

    @Override
    public void add(Order t
    ) {
        String req = "insert into commande (id,id_product,date,quantity,id_customer,etat) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, t.getOrderId());
            preparedStatement.setInt(2, 2);
            preparedStatement.setDate(3, t.getOrderDate());
            preparedStatement.setInt(4, t.getOrderQuantity());
            preparedStatement.setInt(5, t.getCustomer().getCustomerId());
            preparedStatement.setString(6, t.getEtat());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Order findById(Integer id
    ) {
        Order or = null;
        String req = "select * from commande where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                or = new Order(resultSet.getInt("id"), resultSet.getInt("quantity"), resultSet.getDate("date"),
                        new CustomerServices().findById(resultSet.getInt("id_customer")),
                        productServices.findById(resultSet.getInt("id_product")),
                        resultSet.getString("etat"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return or;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String req = "select * from commande";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order or = new Order(resultSet.getInt("id"), resultSet.getInt("quantity"), resultSet.getDate("date"),
                        new CustomerServices().findById(resultSet.getInt("id_customer")),
                        resultSet.getString("etat"));
                orders.add(or);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;

    }

    @Override
    public void update(Order t
    ) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Order t
    ) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> findByOrderDate(java.util.Date orderDate
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

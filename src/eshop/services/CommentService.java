/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.services;

import eshop.models.Comment;
import eshop.models.Customer;
import eshop.models.Product;
import eshop.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import eshop.interfaces.IComment;

/**
 *
 * @author Karim SNOUSSI
 */
public class CommentService implements IComment {

    Connection connection;

    public CommentService() {
        connection = DataSource.getInsatance().getConnection();

    }

    @Override
    public void add(Comment comment) {

        String req = "insert into comments (Customer_id,Comment_text,Comment_date,Product_Id) values (?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);

            preparedStatement.setInt(1, comment.getCustomer().getCustomerId());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setDate(3, comment.getDateComment());
            preparedStatement.setInt(4, comment.getAbout().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("erreur d'ajout de commentaire!!");
            ex.printStackTrace();
        }

    }

    @Override
    public void update(Comment comment) {

        String req = "update comments Customer_id=?,Comment_text=?,Comment_date=?,Product_Id=? where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);

            preparedStatement.setInt(1, comment.getCustomer().getCustomerId());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setDate(3, comment.getDateComment());
            preparedStatement.setInt(4, comment.getAbout().getId());
            preparedStatement.setInt(5, comment.getId());
            
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("erreur de de commentaire!!");
            ex.printStackTrace();
        }

    }

    @Override
    public void remove(Comment comment) {

        String req = "delete from comments where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, comment.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("probleme de suppression de commentaire !!");
            ex.printStackTrace();
        }
    }

    @Override
    public Comment findById(Integer id) {

        CustomerServices customerServices = new CustomerServices();
        ProductServices pruductService = new ProductServices();
        Comment comment = null;
        String req = "select * from comments where id =?";
        PreparedStatement preparedStatement;
        try {

            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comment = new Comment(id, customerServices.findById(resultSet.getInt(2)), resultSet.getString(3), resultSet.getDate(4), pruductService.findById(resultSet.getInt(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Probleme de récupération du commentaire");
            ex.printStackTrace();
        }
        return comment;

    }

    @Override
    public List<Comment> getAll() {//inutil de récuperer tous les commentaire
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Comment> findByProduct(Product produit) {

        ArrayList<Comment> liste = new ArrayList<Comment>();
        Comment comment;
        CustomerServices customerServices = new CustomerServices();
        String req = "select * from comments where Product_id =?";
        PreparedStatement preparedStatement;
        try {

            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, produit.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                liste.add(new Comment(resultSet.getInt(1), customerServices.findById(resultSet.getInt(2)), resultSet.getString(3), resultSet.getDate(4), produit));
            }
        } catch (SQLException ex) {
            System.err.println("Probleme de récupération des commentaire du produit");
            ex.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Comment> findByCustomer(Customer customer) {

        ArrayList<Comment> liste = new ArrayList<Comment>();
        Comment comment;
        ProductServices productServices = new ProductServices();
        String req = "select * from comments where Product_id =?";
        PreparedStatement preparedStatement;
        try {

            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, customer.getCustomerId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                liste.add(new Comment(resultSet.getInt(1), customer, resultSet.getString(3), resultSet.getDate(4), productServices.findById(resultSet.getInt(5))));
            }
        } catch (SQLException ex) {
            System.err.println("Probleme de récupération des commentaire du produit");
            ex.printStackTrace();
        }
        return liste;
    }

}

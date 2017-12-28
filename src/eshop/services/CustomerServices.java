/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.services;

import eshop.interfaces.ICustomer;
import eshop.models.Claim;
import eshop.models.Customer;
import eshop.models.Order;
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

public class CustomerServices implements ICustomer{
    
    Connection connection;

    public CustomerServices() {
        connection = DataSource.getInsatance().getConnection();
    }

   @Override
    public Customer findByUserName(String userName) {
        Customer c = null;
        String req = "select * from customer where username =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                c = new Customer(resultSet.getInt("id"),resultSet.getInt("pt_fidelite")
                        , resultSet.getString("prenom"),resultSet.getString("nom")
                        ,resultSet.getString("email"),resultSet.getString("adresse")
                        ,resultSet.getString("cin"),resultSet.getString("code_postal")
                        ,resultSet.getString("image_path"),resultSet.getString("username")
                        ,resultSet.getString("password"),resultSet.getString("code_parrain")
                        ,resultSet.getString("code_parrainage"),resultSet.getDate("date_naissance")
                        ,resultSet.getDate("date_creation_cpt"),resultSet.getInt("phoneNumber")
                        ,resultSet.getInt("claimNumber"));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    @Override
    public void add(Customer c) {
       // String req = "insert into customer (nom,prenom,e_mail,adresse,date_naissance,date_creation_cpt,cin,code_postal,image_path,username,password,pt_fidelite,code_parrain,code_parrainage,phoneNumber,claimNumber) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      String req = "insert into customer (nom,prenom,email,adresse,date_naissance,date_creation_cpt,cin,code_postal,image_path,username,password,pt_fidelite,code_parrain,code_parrainage,phoneNumber,claimNumber,email_canonical,username_canonical) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
       PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, c.getCustomerLastName());
            preparedStatement.setString(2, c.getCustomerFirstName());
            preparedStatement.setString(3, c.getCustomerMail());
            preparedStatement.setString(4, c.getCustomerAddress());
            preparedStatement.setDate(5, c.getCustomerBirthDate());
            preparedStatement.setDate(6, c.getCustomerAccountCreation());
            preparedStatement.setString(7, c.getCustomerCIN());
            preparedStatement.setString(8, c.getCustomerCP());
            preparedStatement.setString(9, c.getCustomerPicturePath());
            preparedStatement.setString(10, c.getCustomerUserName());
            preparedStatement.setString(11, c.getCustomerPassword());
            preparedStatement.setInt(12, c.getPtFidelite());
            preparedStatement.setString(13, c.getParrain());
            preparedStatement.setString(14, c.getParrainage());
            preparedStatement.setInt(15, c.getPhoneNumber());
            preparedStatement.setInt(16, c.getClaimNumber());
            preparedStatement.setString(17, c.getCustomerMail());
            preparedStatement.setString(18, c.getCustomerUserName());
            
            
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Customer c) {
        String req = "update customer set nom=?,prenom=?,email=?,adresse=?,date_naissance=?,date_creation_cpt=?,cin=?,code_postal=?,image_path=?,username=?,password=?,pt_fidelite=?,code_parrain=?,code_parrainage=?,phoneNumber=?,claimNumber=? where id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, c.getCustomerLastName());
            preparedStatement.setString(2, c.getCustomerFirstName());
            preparedStatement.setString(3, c.getCustomerMail());
            preparedStatement.setString(4, c.getCustomerAddress());
            preparedStatement.setDate(5, c.getCustomerBirthDate());
            preparedStatement.setDate(6, c.getCustomerAccountCreation());
            preparedStatement.setString(7, c.getCustomerCIN());
            preparedStatement.setString(8, c.getCustomerCP());
            preparedStatement.setString(9, c.getCustomerPicturePath());
            preparedStatement.setString(10, c.getCustomerUserName());
            preparedStatement.setString(11, c.getCustomerPassword());
            preparedStatement.setInt(12, c.getPtFidelite());
            preparedStatement.setString(13, c.getParrain());
            preparedStatement.setString(14, c.getParrainage());
            preparedStatement.setInt(15, c.getPhoneNumber());
            preparedStatement.setInt(16, c.getClaimNumber());
            preparedStatement.setInt(17, c.getCustomerId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Customer c) {
        String req = "delete from customer where email =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1,c.getCustomerMail());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Customer findById(Integer customerId) {
           Customer c = null;
        String req = "select * from customer where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                c = new Customer(resultSet.getInt("id"),resultSet.getInt("pt_fidelite")
                        , resultSet.getString("prenom"),resultSet.getString("nom")
                        ,resultSet.getString("email"),resultSet.getString("adresse")
                        ,resultSet.getString("cin"),resultSet.getString("code_postal")
                        ,resultSet.getString("image_path"),resultSet.getString("username")
                        ,resultSet.getString("password"),resultSet.getString("code_parrain")
                        ,resultSet.getString("code_parrainage"),resultSet.getDate("date_naissance")
                        ,resultSet.getDate("date_creation_cpt"),resultSet.getInt("phoneNumber")
                        ,resultSet.getInt("claimNumber"));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    @Override
    public Customer findByMail(String mail) {
        Customer c = null;
        String req = "select * from customer where email =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 c = new Customer(resultSet.getInt("id"),resultSet.getInt("pt_fidelite")
                        , resultSet.getString("prenom"),resultSet.getString("nom")
                        ,resultSet.getString("email"),resultSet.getString("adresse")
                        ,resultSet.getString("cin"),resultSet.getString("code_postal")
                        ,resultSet.getString("image_path"),resultSet.getString("username")
                        ,resultSet.getString("password"),resultSet.getString("code_parrain")
                        ,resultSet.getString("code_parrainage"),resultSet.getDate("date_naissance")
                        ,resultSet.getDate("date_creation_cpt"),resultSet.getInt("phoneNumber")
                        ,resultSet.getInt("claimNumber"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    @Override
    public Customer getByOrder(Order o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer getByClaim(Claim c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> findByCodePostal(String codePostal) {
        List<Customer> customerList = new ArrayList<>();
        String req = "select * from customer where code_postal =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, codePostal);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Customer c = new Customer(resultSet.getInt("id"),resultSet.getInt("pt_fidelite")
                        , resultSet.getString("prenom"),resultSet.getString("nom")
                        ,resultSet.getString("email"),resultSet.getString("adresse")
                        ,resultSet.getString("cin"),resultSet.getString("code_postal")
                        ,resultSet.getString("image_path"),resultSet.getString("username")
                        ,resultSet.getString("password"),resultSet.getString("code_parrain")
                        ,resultSet.getString("code_parrainage"),resultSet.getDate("date_naissance")
                        ,resultSet.getDate("date_creation_cpt"),resultSet.getInt("phoneNumber")
                        ,resultSet.getInt("claimNumber"));
                customerList.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customerList;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();
        String req = "select * from customer";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer c = new Customer(resultSet.getInt("id"),resultSet.getInt("pt_fidelite")
                        , resultSet.getString("prenom"),resultSet.getString("nom")
                        ,resultSet.getString("email"),resultSet.getString("adresse")
                        ,resultSet.getString("cin"),resultSet.getString("code_postal")
                        ,resultSet.getString("image_path"),resultSet.getString("username")
                        ,resultSet.getString("password"),resultSet.getString("code_parrain")
                        ,resultSet.getString("code_parrainage"),resultSet.getDate("date_naissance")
                        ,resultSet.getDate("date_creation_cpt"),resultSet.getInt("phoneNumber")
                        ,resultSet.getInt("claimNumber"));
                customerList.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customerList;
    }

    

}

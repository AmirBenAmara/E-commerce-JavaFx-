/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.services;

import eshop.interfaces.IClaim;
import eshop.models.Claim;
import eshop.models.Customer;
import eshop.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Gold Solution
 */
public class ClaimServices implements IClaim {

    Connection connection;

    public ClaimServices() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Claim c) {
        String req = "insert into claim (date,id_commande,text,etat) values (?,?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setDate(1, c.getDate());
            preparedStatement.setInt(2, c.getId_order());
            preparedStatement.setString(3, c.getText());
            preparedStatement.setString(4, "untreated");
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override // à ne pas travailler, on ne peut pas mettre à jour une réclamation
    public void update(Claim c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public void updateState(int id) {
         
        String req = "UPDATE claim SET etat =? WHERE id =?";
        try {         
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, "treated"); 
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Claim c) {
        String req = "delete from claim where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, c.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void removeById(int id) {
        String req = "delete from claim where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void afficher() {
        String req = "select * from claim";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Claim c = new Claim(resultSet.getInt("id"), resultSet.getDate("date"), resultSet.getInt("id_commande"), resultSet.getString("text"),resultSet.getString("etat"));
                System.out.println(c);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Claim findByDateClaim(java.util.Date dateClaim) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Claim findById(Integer id) {
        Claim c = null;
        String req = "select * from claim where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 c = new Claim(resultSet.getInt("id"), resultSet.getDate("date"), resultSet.getInt("id_commande"), resultSet.getString("text"),resultSet.getString("etat"));
             }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return c;
    }

    @Override
    public List<Claim> getAll() {
        List<Claim> claims = new ArrayList<>();
        String req = "select * from claim";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 Claim c = new Claim(resultSet.getInt("id"), resultSet.getDate("date"), resultSet.getInt("id_commande"), resultSet.getString("text"),resultSet.getString("etat"));
               claims.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return claims;
    }

    public List<Claim> getClaimByDate(String d) throws ParseException {
        //String d1 = "01-02-1994";
        java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(d);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        List<Claim> claims = new ArrayList<>();
        String req = "select * from claim where date =? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setDate(1, sqlDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 Claim c = new Claim(resultSet.getInt("id"), resultSet.getDate("date"), resultSet.getInt("id_commande"), resultSet.getString("text"),resultSet.getString("etat"));
               claims.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("exception");
        }
        return claims;
    }

    @Override
    public List<Claim> getByCustomer(Customer customer) {
        List<Claim> claims = new ArrayList<>();
        String req = "select * from claim where id_customer=? ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, customer.getCustomerId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 Claim c = new Claim(resultSet.getInt("id"), resultSet.getDate("date"), resultSet.getInt("id_commande"), resultSet.getString("text"),resultSet.getString("etat"));
                claims.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return claims;
    }

}

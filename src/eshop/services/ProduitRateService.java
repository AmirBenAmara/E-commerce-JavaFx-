/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import eshop.models.ProductRate;
import eshop.technique.DataSource;

/**
 *
 * @author ASUS
 */
public class ProduitRateService {
     Connection connection ;

    public ProduitRateService(Connection connection) {
        this.connection = DataSource.getInsatance().getConnection();    }
    
    
    public void add(ProductRate pr) {

        try {

            String req = "INSERT into `produitsRate`  (id_customer,id_produit,rate) VALUES (?,?,?)";

            PreparedStatement ps = connection.prepareStatement(req);

            ps.setInt(1, 1);
            ps.setInt(2, pr.getId_produit());
            ps.setDouble(3, pr.getRate());
            

            ps.executeUpdate();
            System.out.println("Insertion terminé");
            

        } catch (SQLException ex) {
            System.out.println("Problème d'insertion");
            ex.printStackTrace();
            
        }

    }
    
    public boolean TestRate (int id_cust , int id_prod) throws SQLException {
            
        String req = "select id from `produitsrate` where id_customer= ? && id_produit= ?   ";
         PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id_cust);
        ps.setInt(2, id_prod);
        ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int id1 = resultSet.getInt("id");  
            if (id1 != 0)
            { return true;}
            
                }
        
        
        return false;
    
    
    }

     
    
}

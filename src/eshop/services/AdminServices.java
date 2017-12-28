/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.services;

import eshop.interfaces.IAdmin;
import eshop.models.Admin;
import eshop.technique.DataSource;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Gold Solution
 */
public class AdminServices implements IAdmin{
    
    Connection connection;

    public AdminServices() {
        connection = DataSource.getInsatance().getConnection();
    }

    @Override
    public void add(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override  // à ne pas travailler c'est pas la peine de supprimer un admin
    public void remove(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override // à ne pas travailler
    public Admin findById(Integer r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override // à ne pas travailler
    public List<Admin> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
}

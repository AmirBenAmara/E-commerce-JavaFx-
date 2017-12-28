/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.shop;

import eshop.interfaces.ICustomer;
import eshop.models.Customer;
import eshop.services.CustomerServices;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gold Solution
 */
public class CustomerTest {
    public static void main(String[] args) {
       
        ICustomer customer= new CustomerServices();
       
        /*Date date = Date.valueOf("2017-02-01");
        Date date1 = Date.valueOf(LocalDate.now());
        //Customer c = new Customer(4,0,"Sergio","Ramos","sergio@esprit.tn","Madrid","07187003","2063","null","SR4","123456","15896","1547",date,date1);
        
        //customer.add(c);
        
        for (Customer a : customer.getAll()) {    // affichage total
            System.out.println(a);
        }
        System.out.println("******************************************************");
        System.out.println("Affichage par ID");
        System.out.println(customer.findById(4));
        System.out.println("affichage par mail");
        System.out.println(customer.findByMail("bechir.landolsi@esprit.tn"));
        System.out.println("affichage par username");
        System.out.println(customer.findByUserName("amir"));*/
        
        System.err.println(customer.findByUserName("amir").getCustomerId());
        
        
    }

    }

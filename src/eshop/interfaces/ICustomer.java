/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.interfaces;

import eshop.models.Claim;
import eshop.models.Customer;
import eshop.models.Order;
import eshop.models.Product;
import java.util.List;

/**
 *
 * @author Gold Solution
 */
public interface ICustomer extends IService<Customer,Integer>{
    Customer findByUserName(String userName);
    Customer findByMail(String mail);
    Customer getByOrder(Order o);
    Customer getByClaim(Claim c);
    List<Customer> findByCodePostal(String codePostal);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.interfaces;

import eshop.models.Customer;
import eshop.models.Order;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gold Solution
 */
public interface IOrder extends IService<Order,Integer>{
    List<Order> findByOrderDate(Date orderDate);
    List<Order> getByCustomer(Customer c);
}

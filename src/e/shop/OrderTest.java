/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.shop;

import eshop.models.Order;
import eshop.services.CustomerServices;
import eshop.services.OrderServices;
import eshop.technique.Tools;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gold Solution
 */
public class OrderTest {

    public static void main(String[] args) throws ParseException {
        
        OrderServices orderServices=new OrderServices();
        CustomerServices customerServices=new CustomerServices();
        List<Order> orders=new ArrayList<>();
        
        Tools.account=customerServices.findByUserName("amir");
        orders=orderServices.getByCustomer(Tools.account);
        for (Order order : orders) {
            System.out.println(order);
            
        }
        

    }
}

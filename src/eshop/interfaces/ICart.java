/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.interfaces;

import eshop.models.Cart;
import eshop.models.Customer;
import eshop.models.Product;

import java.util.List;

/**
 *
 * @author Gold Solution
 */
public interface ICart extends IService<Cart,Integer>{
  
  

    List<Cart> findAll();

    Cart findcarteById(int id);

    //Cart findDepotByAdresse(String adresse);

  


}
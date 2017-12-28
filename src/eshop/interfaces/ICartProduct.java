/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.interfaces;

import eshop.models.Cart;
import eshop.models.CartProduct;
import eshop.models.Customer;
import eshop.models.Product;

import java.util.List;

/**
 *
 * @author Gold Solution
 */
public interface ICartProduct extends IService<CartProduct, Integer> {


 List<CartProduct> findByProduct(Product product) ;


    List<CartProduct> findAll();

   CartProduct findcarteprodById(int id);

}

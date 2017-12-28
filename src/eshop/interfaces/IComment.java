/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.interfaces;

import eshop.models.Comment;
import eshop.models.Customer;
import eshop.models.Product;
import java.util.ArrayList;

/**
 *
 * @author Karim SNOUSSI
 */
public interface IComment extends IService<Comment,Integer> {
    
    public ArrayList<Comment> findByProduct(Product produit);
    public ArrayList<Comment> findByCustomer(Customer customer);
    

    
}

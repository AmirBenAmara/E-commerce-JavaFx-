/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.interfaces;

import eshop.models.Category;

/**
 *
 * @author Gold Solution
 */
public interface ICategory extends IService<Category,Integer>{
    Category findByNameCategory(String NameCategory);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.interfaces;

import java.util.List;

/**
 *
 * @author Gold Solution
 * @param <T>
 * @param <R>
 */
public interface IService<T,R> {
    void add(T t);
    void update(T t);
    void remove(T t);
    T findById(R r);
    List<T> getAll();
}

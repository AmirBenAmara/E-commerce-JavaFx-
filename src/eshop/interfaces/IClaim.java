/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.interfaces;

import eshop.models.Claim;
import eshop.models.Customer;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gold Solution
 */
public interface IClaim extends IService<Claim,Integer>{
    Claim findByDateClaim(Date dateClaim);
    List<Claim> getByCustomer(Customer c);
}

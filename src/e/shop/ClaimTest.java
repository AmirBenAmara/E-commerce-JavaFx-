/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.shop;

import eshop.models.Claim;
import eshop.services.ClaimServices;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Gold Solution
 */
public class ClaimTest {
    public static void main(String[] args) throws ParseException {
        
       //Order o1 = new Order();
       //Order o2 = new Order();
        System.out.println("hjbk");

       /* Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d2 = sdf.parse("2017-09-25");*/
       
        String d1 = "01-02-1994";
        java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(d1);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        //Claim c1 = new Claim(sqlDate,"il m'a arnaqué");
        //System.out.println(c1.toString());

        
        ClaimServices claimservice = new ClaimServices();
        //claimservice.add(c1);
        //claimservice.remove(c1);
        //claimservice.afficher();
        //Claim c = claimservice.findByDateClaim( "1994-20-01");
        //System.out.println(c);
        for (Claim c1 : claimservice.getClaimByDate("1994-02-01")){
            if (c1==null){
                System.out.println("null");
            }                
            System.out.println("null");

            System.out.println(c1);
        }
        
        System.out.println("hjbk");

        
    }
}

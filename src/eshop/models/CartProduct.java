/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eshop.models;
import static java.sql.JDBCType.NULL;
import java.util.List;
import eshop.models.Product;
import java.util.Objects;

/**
 *
 * @author helak
 */
public class CartProduct {

    public static void add(CartProduct cp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   private int idCartProduit,qte;
    private Product product;
    private Cart cart;
    private boolean commander;

    public CartProduct() {
    }

    public CartProduct(int idCartProduit, int qte, Product product, Cart cart, boolean commander) {
        this.idCartProduit = idCartProduit;
        this.qte = qte;
        this.product = product;
        this.cart = cart;
        this.commander = commander;
    }

    public CartProduct(int idCartProduit, int qte, Product product) {
        this.idCartProduit = idCartProduit;
        this.qte = qte;
        this.product = product;
    }

    public CartProduct(int qte, boolean commander) {
        this.qte = qte;
        this.commander = commander;
    }

    public int getIdCartProduit() {
        return idCartProduit;
    }

    public void setIdCartProduit(int idCartProduit) {
        this.idCartProduit = idCartProduit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public boolean getCommander() {
        return commander;
    }

    public void setCommander(boolean commander) {
        this.commander = commander;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CartProduct other = (CartProduct) obj;
        if (this.idCartProduit != other.idCartProduit) {
            return false;
        }
        if (this.qte != other.qte) {
            return false;
        }
        if (this.commander != other.commander) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "CartProduct{" + "idCartProduit=" + idCartProduit + ", qte=" + qte + ", product=" + product + ", cart=" + cart + ", commander=" + commander + '}';
    }
    
    
    

}

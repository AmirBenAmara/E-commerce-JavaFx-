/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesuser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import eshop.models.Product;
import eshop.services.ProductServices;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProductViewController implements Initializable {
      Connection connection;
     ProductServices produitservice = new ProductServices(connection);

    /**
     *
     */
    @FXML
     private ListView<Product> llistproduits1;

    @FXML
    private JFXTextField recherche1;

    @FXML
    private AnchorPane formAjout1;

    @FXML
    private ImageView imageproduit1;

      @FXML
    private JFXButton refresh;
      
    @FXML
    private JFXTextField inputref1;

    @FXML
    private JFXTextArea inputdesc1;

    @FXML
    private JFXTextField inputprix1;

    @FXML
    private JFXTextField inputqte1;

    @FXML
    private JFXTextField inputpromo1;

    @FXML
    private JFXTextField inputcat1;

    @FXML
    void onFiltrer(KeyEvent event) {

    }

    @FXML
    void onMouseclicked(MouseEvent event) throws SQLException {

           int id = produitservice.getIDByReference(llistproduits1.getSelectionModel().getSelectedItem().getRefernce());
        
        /*inputLibelle.setVisible(true);
        inputPrix.setVisible(true);
        inputDescription.setVisible(true);
        btnajout.setVisible(true);
        libellefx.setVisible(true);
        descriptionfx.setVisible(true);
        prixfx.setVisible(true);*/
        inputref1.setText(llistproduits1.getSelectionModel().getSelectedItem().getRefernce());
        inputdesc1.setText(llistproduits1.getSelectionModel().getSelectedItem().getDescription());
        String Sprix = String.valueOf(llistproduits1.getSelectionModel().getSelectedItem().getPrix());
        inputprix1.setText(Sprix);
        
        String Sqte = String.valueOf(llistproduits1.getSelectionModel().getSelectedItem().getQuantite());
        inputqte1.setText(Sqte);
        
        String Spromo = String.valueOf(llistproduits1.getSelectionModel().getSelectedItem().getPromotion());
        inputpromo1.setText(Spromo);
        
        inputcat1.setText(llistproduits1.getSelectionModel().getSelectedItem().getCategorie());

        
        
        Image img= new Image("/images/"+produitservice.getImgById(id));
        System.out.println(produitservice.getImgById(id));
        
         imageproduit1.setImage(img);
        
        
        
    }
    
        @FXML
    void onRefresh( ) {
        llistproduits1.setItems(produitservice.getByCustomer(11));

        

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

llistproduits1.setItems(produitservice.getByCustomer(11));
  
  llistproduits1.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
     @Override
     public ListCell<Product> call(ListView<Product> param) {
        return new ProductFactory();
     }
 });
    }

   
}
   

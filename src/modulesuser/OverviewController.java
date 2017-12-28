/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesuser;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import eshop.models.Product;
import eshop.services.ProductServices;
import eshop.services.ProduitRateService;
import java.io.IOException;
import javafx.application.Application.Parameters;
import javafx.scene.input.ContextMenuEvent;

/**
 * FXML Controller class
 *
 * @author Amiir
 */
public class OverviewController implements Initializable {

    public static int id_produit;
    Connection connection;
    ProductServices produitservice = new ProductServices(connection);
    ProduitRateService prs = new ProduitRateService(connection);

    public static String imgurl;

    @FXML
    private ListView<Product> llistproduits;
    @FXML
    private JFXTextField recherche;
    @FXML
    private AnchorPane formAjout;
    @FXML
    private JFXTextField inputref;
    @FXML
    private JFXTextArea inputdesc;
    @FXML
    private JFXTextField inputprix;
    @FXML
    private JFXTextField inputqte;
    @FXML
    private JFXTextField inputpromo;
    @FXML
    private JFXComboBox<String> combobox;
    @FXML
    private ImageView imageproduit;

    @FXML
    private JFXComboBox<String> combocat;

    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button delete;
    @FXML
    private Button rate;
    @FXML
    private Button imageupload;

    Product produit;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private void onMouseclicked(MouseEvent event) throws SQLException {

        int id = produitservice.getIDByReference(llistproduits.getSelectionModel().getSelectedItem().getRefernce());

        /*inputLibelle.setVisible(true);
        inputPrix.setVisible(true);
        inputDescription.setVisible(true);
        btnajout.setVisible(true);
        libellefx.setVisible(true);
        descriptionfx.setVisible(true);
        prixfx.setVisible(true);*/
        inputref.setText(llistproduits.getSelectionModel().getSelectedItem().getRefernce());
        inputdesc.setText(llistproduits.getSelectionModel().getSelectedItem().getDescription());
        String Sprix = String.valueOf(llistproduits.getSelectionModel().getSelectedItem().getPrix());
        inputprix.setText(Sprix);

        String Sqte = String.valueOf(llistproduits.getSelectionModel().getSelectedItem().getQuantite());
        inputqte.setText(Sqte);

        String Spromo = String.valueOf(llistproduits.getSelectionModel().getSelectedItem().getPromotion());
        inputpromo.setText(Spromo);

        combobox.setValue(llistproduits.getSelectionModel().getSelectedItem().getCategorie());

        Image img = new Image("/images/" + produitservice.getImgById(id));
        System.out.println(produitservice.getImgById(id));

        imageproduit.setImage(img);

    }

    @FXML
    private void onFiltrer(KeyEvent event) throws SQLException {
        llistproduits.setItems(produitservice.SearchProd(recherche.getText()));

    }

    @FXML
    void onCatSelected(ActionEvent event) throws SQLException {

        String cat = combocat.getValue();
        if (cat.equals("All categories")) {
            llistproduits.setItems(produitservice.SearchProd(recherche.getText()));
        } else {
            System.out.println(cat);
            llistproduits.setItems(produitservice.getByCategorie(cat));
        }

    }

    @FXML
    private void Onajouter(ActionEvent event) {

        //System.out.println(inputLibelle.getText() + "" + inputPrix.getText() + "" + inputDescription.getText());
        // Product produit = new Product(inputref.getText(), inputdesc.getText(), Float.parseFloat(inputprix.getText()),Integer.parseInt(inputqte.getText()),combobox.getValue(),Float.parseFloat(inputpromo.getText()));
        /* produit.setLibelle(inputLibelle.getText());
        produit.setDescription(inputDescription.getText());
        produit.setPrix(Float.parseFloat(inputPrix.getText()));*/
        // System.out.println(produit);
        if (!inputref.getText().isEmpty() && !inputdesc.getText().isEmpty() && !inputprix.getText().isEmpty() && !inputqte.getText().isEmpty() && !combobox.getValue().isEmpty()) {
            produit = new Product(inputref.getText(), inputdesc.getText(), Float.parseFloat(inputprix.getText()), Integer.parseInt(inputqte.getText()), combobox.getValue(), Float.parseFloat(inputpromo.getText()));

        } else {

            Notifications notificationsBuilder = Notifications.create()
                    .title("Probléme d'ajout")
                    .text("Verifier les coordonnées")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER);
            notificationsBuilder.showError();
            notificationsBuilder.darkStyle();

        }
        if ((produitservice.add(produit))) {

            llistproduits.setItems(produitservice.showProduit());

            /* inputLibelle.setText(null);
        inputPrix.setText(null);
        inputDescription.setText(null);*/
            Image valide = new Image("/icons/valide.png");
            Notifications notificationsBuilder = Notifications.create()
                    .title("Ajout terminé")
                    .text("Produit ajouté avec succes")
                    .graphic(new ImageView(valide))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationsBuilder.show();
            notificationsBuilder.darkStyle();
            //ProductViewController.llistproduits1.setItems(produitservice.getByCustomer(1));

        }
          else 
        {
       Notifications notificationsBuilder = Notifications.create()
               .title("Probléme d'ajout")
               .text("Verifier les coordonnées")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
               .position(Pos.CENTER);
       notificationsBuilder.showError();
       notificationsBuilder.darkStyle();
        }
    }

    @FXML
    private void Onmodifier(ActionEvent event) throws SQLException {
         if(!llistproduits.getSelectionModel().isEmpty())
         { int id = produitservice.getIDByReference(llistproduits.getSelectionModel().getSelectedItem().getRefernce());
        produitservice.update(id, inputdesc.getText(), Float.parseFloat(inputprix.getText()), Integer.parseInt(inputqte.getText()), Float.parseFloat(inputpromo.getText()));
        //llistproduits.setItems(produitservice.showProduit());
        llistproduits.setItems(produitservice.showProduit());

        llistproduits.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new ProductFactory();
            }
        });
        
         Image valide = new Image("/icons/valide.png");
            Notifications notificationsBuilder = Notifications.create()
                    .title("Modification terminé")
                    .text("Produit modifié avec succes")
                    .graphic(new ImageView(valide))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationsBuilder.show();
            notificationsBuilder.darkStyle();
         }
         else {
             Notifications notificationsBuilder = Notifications.create()
               .title("Probléme de modification")
               .text("Selectionner un produit ")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
               .position(Pos.CENTER);
       notificationsBuilder.showError();
       notificationsBuilder.darkStyle();
         }

    }

    @FXML
    private void Ondelete(ActionEvent event) throws SQLException {
        
         if(!llistproduits.getSelectionModel().isEmpty())
         {      
            
             
             int id = produitservice.getIDByReference(llistproduits.getSelectionModel().getSelectedItem().getRefernce());
        System.out.println("" + id);
        
        
         if( produitservice.TestProduit(id)==1)
             
         { produitservice.delete(id);
         
         llistproduits.setItems(produitservice.showProduit());
         inputref.setText(null);
         inputdesc.setText(null);
         inputprix.setText(null);
         inputpromo.setText(null);
         inputqte.setText(null);
         combobox.setValue(null);
         imageproduit.setImage(null);
         
         Image valide = new Image("/icons/valide.png");
         Notifications notificationsBuilder = Notifications.create()
                 .title("Suppression terminé")
                 .text("Produit supprimé avec succes")
                 .graphic(new ImageView(valide))
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.BOTTOM_RIGHT);
         notificationsBuilder.show();
         notificationsBuilder.darkStyle();
         }
         else {
              Notifications notificationsBuilder = Notifications.create()
               .title("Probléme de suppression")
               .text("Vous pouvez seulement supprimer vos produits ")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
               .position(Pos.CENTER);
       notificationsBuilder.showError();
       notificationsBuilder.darkStyle();
             
         }
             
         }
         
         
         else {
         Notifications notificationsBuilder = Notifications.create()
               .title("Probléme de suppression")
               .text("Veuillez Selectionner un produit ")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
               .position(Pos.CENTER);
       notificationsBuilder.showError();
       notificationsBuilder.darkStyle();
         }
    
    }

    @FXML
    private void Onrate(ActionEvent event) throws SQLException, IOException {
        id_produit = produitservice.getIDByReference(llistproduits.getSelectionModel().getSelectedItem().getRefernce());
        if (prs.TestRate(1, id_produit) == false) {
            Stage primaryStage = new Stage();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("RatePopUp.fxml"));

            Scene scene = new Scene(root);// affichage dans un fenetre
            primaryStage.setScene(scene);
            primaryStage.showAndWait();
        } else {
            Notifications notificationsBuilder = Notifications.create()
                    .title("Probléme de Rate")
                    .text("Vous avez deja noter ce produit")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER);
            notificationsBuilder.showError();

        }

    }

    @FXML
    private void Onimage(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif"));

        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            // Image image = new Image(selectedFile.toURI().toString());
            //imageview.setImage(image);
            imgurl = selectedFile.getName();
        } else {
            System.out.println("file invalid ");
        }

    }

    @FXML
    void facebook(ActionEvent event) {

        String accesToken = "EAACEdEose0cBAJ5KhmD0y4ZAq3pZAv7vOWZCOlpZCDrAuAawqZArE3A4tZCi7TEA0ZCu9dQhPTgrtZCwiReT7ZA9OgkZA5kqTNOAl4h3UuOFWVfPERWOBg8ORDFzDWGyBaHoItLsZATsBLB5iF5fAlPGm0sN6rJq3IVdOZAYxqWT7weFxDpV5fhcecZAvltXVSZBiP7FVXU4yBJPoP6AZDZD";
        FacebookClient fbClient = new DefaultFacebookClient(accesToken);
        FacebookType response = fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", "malaa FAZ"));
        System.out.println("fb.com/" + response.getId());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combobox.getItems().addAll(
                "Voitures",
                "Vetements",
                "Electronique");
        combocat.getItems().addAll(
                "Voitures",
                "Vetements",
                "Electronique",
                "All categories");
        llistproduits.setItems(produitservice.showProduit());

        llistproduits.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new ProductFactory();
            }
        });
    }
}


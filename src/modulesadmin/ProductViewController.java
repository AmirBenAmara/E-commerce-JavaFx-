/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesadmin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import eshop.models.Product;
import eshop.services.ProductServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javax.lang.model.element.Element;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Amiir
 */
public class ProductViewController implements Initializable {

    private ImageView id_img;

    private Label id_label;
    private GridPane _grid;

    private AnchorPane element, GridElsPath, GridElsPath1;
    @FXML
    private AnchorPane holderAnchor;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblLocation;
    @FXML
    private Label lblDepartment;
    @FXML
    private Label lblLevel;
    @FXML
    private Label lblCourse;
    @FXML
    private Label lblFee;
    @FXML
    private Label lblPaid;
    @FXML
    private Label lblBalance;
    @FXML
    private ToggleGroup filter;
    @FXML
    private JFXListView<Label> _list;
    @FXML
    private ImageView id_img_product;
    @FXML
    private Label lblName1;
    @FXML
    private JFXRadioButton rbAll;
    @FXML
    private JFXRadioButton rbEnable;
    @FXML
    private JFXRadioButton rbDisable;
    @FXML
    private JFXButton rmbutton;
    @FXML
    private JFXButton enbutton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rmbutton.setVisible(false);
        enbutton.setVisible(false);

        affProduct();

        ///notification code
        /* File file = new File("src/images/validate.png");
            Image img = new Image(file.toURI().toString());
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Congratulations sir");
            tray.setMessage("You've successfully created your first Tray Notification");
            tray.setImage(img);
            tray.showAndWait();*/
        ///api mail
        /*SendMail sm = new SendMail();
            sm.send("adnen.chebaane@esprit.tn", "api mail", "api l mail khdem ", "amir.benamara@esprit.tn", "Amirbenamara007");
         */
    }

    public void showproduct() {
        /* ProductServices productservice = new ProductServices();
        for (Product p : productservice.getAll()){
            System.out.println(p);
        }
         */

    }

    //Set selected node to a content holder
    private void setElement(Node node, AnchorPane element) {
        element.getChildren().clear();
        element.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    private void buildGridProduct() {
        try {

            GridElsPath = FXMLLoader.load(getClass().getResource("/modulesadmin/GridElements.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(ProductViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //GridElementsController gde =  new GridElementsController(id_img);
        //gde.initialize(getClass().getResource("/modulesadmin/GridElements.fxml") ,null);    
        //an1.setVisible(false);
        //an1.setVisible(true);
        // setElement(GridElsPath, element1);  
        // setElement(GridElsPath, element2);
        //GridElsPath.setId("els1");

        /*File file = new File("src/images/validate.png");
        Image img = new Image(file.toURI().toString()); 
        id_img.setImage(img);*/
    }

    @FXML
    void selection() throws SQLException {
        String a = _list.getSelectionModel().getSelectedItem().getId();
        ProductServices ps = new ProductServices();
        Product p = ps.findById(Integer.parseInt(a));
        //lblName.setText("ORDER INFO");
        lblLocation.setText("product id : " + p.getId());
        lblEmail.setText("ref : " + p.getRefernce());
        lblPhone.setText("Description : " + p.getDescription());
        lblLevel.setText("Prix : " + p.getPrix());
        lblDepartment.setText("quantity : " + p.getQuantite());
        lblCourse.setText("category : " + p.getCategorie());
        lblFee.setText("" + p.getId_customer());
        lblPaid.setText("");
        lblBalance.setText("");
        String img_path = ps.getImgById(Integer.parseInt(a));
        File file = new File("src/images/" + img_path);
        Image img = new Image(file.toURI().toString());
        id_img_product.setImage(img);
        rmbutton.setVisible(true);
        enbutton.setVisible(true);

    }

    @FXML
    void affProduct() {
        _list.getItems().clear();
        ProductServices ps = new ProductServices();
        List<Product> list = ps.getAll();

        for (int i = 0; i < list.size(); i++) {
            Label lbl = new Label("Ref : " + list.get(i).getRefernce()
                    + "\n" + "prix=" + list.get(i).getPrix() + "DT" + "\n" + "stok = "
                    + list.get(i).getQuantite() + "\n");
            lbl.setId(String.valueOf(list.get(i).getId()));
            File file = new File("src/images/" + list.get(i).getImage());
            System.out.println(list.get(i).getImage());
            Image img = new Image(file.toURI().toString());
            ImageView im = new ImageView(img);

            im.setFitHeight(100);
            im.setFitWidth(100);
            lbl.setGraphic(im);
            _list.getItems().add(lbl);
        }
        /* Label lbl = new Label("produit");
        lbl.setId("BFH");
        

        File file = new File("src/images/validate.png");
        Image img = new Image(file.toURI().toString()); 
        ImageView i = new ImageView(img);
        i.setFitHeight(100);
        i.setFitWidth(70);               
        lbl.setGraphic(i); 
        _list.getItems().add(lbl);*/

    }

    @FXML
    private void searchProduct(ActionEvent event) {
        _list.getItems().clear();
        ProductServices ps = new ProductServices();
        List<Product> list = ps.getAll();
        if (rbAll.isSelected()) {

            for (int i = 0; i < list.size(); i++) {

                Label lbl = new Label("Ref : " + list.get(i).getRefernce()
                        + "\n" + "prix=" + list.get(i).getPrix() + "DT" + "\n" + "stok = "
                        + list.get(i).getQuantite() + "\n");

                lbl.setId(String.valueOf(list.get(i).getId()));
                File file = new File("src/images/" + list.get(i).getImage());
                System.out.println(list.get(i).getImage());
                Image img = new Image(file.toURI().toString());
                ImageView im = new ImageView(img);

                im.setFitHeight(100);
                im.setFitWidth(100);
                lbl.setGraphic(im);
                _list.getItems().add(lbl);

            }
        } else if (rbEnable.isSelected()) {
            for (int i = 0; i < list.size(); i++) {

                if (list.get(i).isEnable() == true) {

                    Label lbl = new Label("Ref : " + list.get(i).getRefernce()
                            + "\n" + "prix=" + list.get(i).getPrix() + "DT" + "\n" + "stok = "
                            + list.get(i).getQuantite() + "\n");

                    lbl.setId(String.valueOf(list.get(i).getId()));
                    File file = new File("src/images/" + list.get(i).getImage());
                    System.out.println(list.get(i).getImage());
                    Image img = new Image(file.toURI().toString());
                    ImageView im = new ImageView(img);

                    im.setFitHeight(100);
                    im.setFitWidth(100);
                    lbl.setGraphic(im);
                    _list.getItems().add(lbl);
                }
            }

        } else if (rbDisable.isSelected()) {
            for (int i = 0; i < list.size(); i++) {

                if (list.get(i).isEnable() == false) {

                    Label lbl = new Label("Ref : " + list.get(i).getRefernce()
                            + "\n" + "prix=" + list.get(i).getPrix() + "DT" + "\n" + "stok = "
                            + list.get(i).getQuantite() + "\n");

                    lbl.setId(String.valueOf(list.get(i).getId()));
                    File file = new File("src/images/" + list.get(i).getImage());
                    System.out.println(list.get(i).getImage());
                    Image img = new Image(file.toURI().toString());
                    ImageView im = new ImageView(img);

                    im.setFitHeight(100);
                    im.setFitWidth(100);
                    lbl.setGraphic(im);
                    _list.getItems().add(lbl);
                }
            }

        } else {

        }
    }
    
    @FXML
    private void removeProduct(ActionEvent event) {
        ProductServices ps = new ProductServices();
        String a=_list.getSelectionModel().getSelectedItem().getId();
        ps.delete(Integer.parseInt(a));
        searchProduct(event);
        //noification
        File file = new File("src/images/validate.png");
            Image img = new Image(file.toURI().toString());
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Congratulations sir");
            tray.setMessage("You've successfully remove product");
            tray.setImage(img);
            tray.showAndWait();
        //ps.delete(0);
    }

    @FXML
    private void enableProduct(ActionEvent event) {
        ProductServices ps = new ProductServices();
        String a=_list.getSelectionModel().getSelectedItem().getId();
        ps.updateEnable(Integer.parseInt(a));
        searchProduct(event);
        //noification
        File file = new File("src/images/validate.png");
            Image img = new Image(file.toURI().toString());
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Congratulations sir");
            tray.setMessage("You've successfully enable product");
            tray.setImage(img);
            tray.showAndWait();


    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EshopDash;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXToolbar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class DashboardController implements Initializable {

    private Label lblDash;
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane sideAnchor;
    @FXML
    private Label lblMenu;
    @FXML
    private JFXToolbar toolBar;
    @FXML
    private HBox toolBarRight;
    @FXML
    private VBox overflowContainer;
    @FXML
    private ToggleButton menuHome;
    @FXML
    private ToggleButton menuAdd;
    @FXML
    private ToggleButton menuList;
    @FXML
    private ToggleButton menuLogg;

    private AnchorPane home, add, listUsers, listProducts , listClaims;
    @FXML
    private JFXButton btnLogOut;
    @FXML
    private JFXButton btnExit;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnViewUsers;
    @FXML
    private JFXButton btnViewProducts;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXButton btnClose;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXRippler fXRippler = new JFXRippler(lblDash);
        JFXRippler fXRippler2 = new JFXRippler(lblMenu);
        fXRippler2.setMaskType((JFXRippler.RipplerMask.RECT));
        sideAnchor.getChildren().add(fXRippler);
        toolBarRight.getChildren().add(fXRippler2);
        openMenus();
        createPages();

    }

    private void openMenus() {
        JFXPopup popup = new JFXPopup();
        popup.setContent(overflowContainer);
        popup.setPopupContainer(stackPane);
        popup.setSource(lblMenu);
        lblMenu.setOnMouseClicked((MouseEvent e) -> {
            popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -10, 40);
        });

    }

    //Set selected node to a content holder
    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    //Load all fxml files to a cahce for swapping
    private void createPages() {
        try {
            home = FXMLLoader.load(getClass().getResource("/modulesadmin/Overview.fxml"));
            listUsers = FXMLLoader.load(getClass().getResource("/modulesadmin/UsersView.fxml"));
            listProducts = FXMLLoader.load(getClass().getResource("/modulesadmin/ProductView.fxml"));
            listClaims =FXMLLoader.load(getClass().getResource("/modulesadmin/ClaimAdmin.fxml"));
            add = FXMLLoader.load(getClass().getResource("/modulesadmin/Register.fxml"));

            //set up default node on page load
            setNode(home);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void openHome(ActionEvent event) {
        setNode(home);
    }

    @FXML
    private void openAddStudent(ActionEvent event) {
        setNode(add);
    }

    @FXML
    private void openListStudent(ActionEvent event) {
        setNode(listUsers);
    }
    
    @FXML
    private void openListProducts(ActionEvent event) {
        setNode(listProducts);
    }
    
    @FXML
    private void openListClaims(ActionEvent event) {
        setNode(listClaims);
    }

    @FXML
    private void logOff(ActionEvent event) {

    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

}

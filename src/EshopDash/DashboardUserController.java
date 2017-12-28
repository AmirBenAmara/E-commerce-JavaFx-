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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class DashboardUserController implements Initializable {

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

    private AnchorPane home, add, listUsers, listProducts, profileEdit,ClaimCreator,HistoryEcho;
    @FXML
    private JFXButton btnLogOut;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXButton btnViewProduct;
    @FXML
    private JFXButton btnViewProduct1;
    @FXML
    private JFXButton btnSettings;
    @FXML
    private JFXButton ClaimCreatorBtn;
    @FXML
    private JFXButton HistoryEchobtn;

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
            home = FXMLLoader.load(getClass().getResource("/modulesuser/Overview.fxml"));
            listUsers = FXMLLoader.load(getClass().getResource("/modulesuser/Profile.fxml"));
            listProducts = FXMLLoader.load(getClass().getResource("/modulesuser/ProductView.fxml"));
            add = FXMLLoader.load(getClass().getResource("/modulesuser/Register.fxml"));
            profileEdit = FXMLLoader.load(getClass().getResource("/modulesuser/ProfileEdit.fxml"));

            HistoryEcho = FXMLLoader.load(getClass().getResource("/modulesuser/History.fxml"));
            ClaimCreator = FXMLLoader.load(getClass().getResource("/modulesuser/Claim.fxml"));

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

    private void openAddStudent(ActionEvent event) {
        setNode(add);
    }

    private void openListStudent(ActionEvent event) {
        setNode(listUsers);
    }

    @FXML
    private void openListProducts(ActionEvent event) {
        setNode(listProducts);
    }

    @FXML
    private void logOff(ActionEvent event) {

    }

    @FXML
    private void Modifier(ActionEvent event) {
        setNode(profileEdit);

    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void openClaim(ActionEvent event) {
        setNode(ClaimCreator);
    }

    @FXML
    private void openOrderHitory(ActionEvent event) {
        setNode(HistoryEcho);
    }

    @FXML
    private void logout(MouseEvent event) {
        btnLogOut.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}

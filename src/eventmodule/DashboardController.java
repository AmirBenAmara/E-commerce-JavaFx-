/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventmodule;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToolbar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Amir
 */
public class DashboardController implements Initializable {

    @FXML
    private StackPane stackPane;
    @FXML
    private VBox overflowContainer;
    @FXML
    private JFXButton btnLogOut;
    @FXML
    private JFXButton btnExit;
    @FXML
    private JFXToolbar toolBar;
    @FXML
    private HBox toolBarRight;
    @FXML
    private Label lblMenu;
    @FXML
    private AnchorPane sideAnchor;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnViewProduct;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXButton btnViewUsers;
    @FXML
    private JFXButton btnViewProduct1;
    @FXML
    private AnchorPane holderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logOff(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
    }

    @FXML
    private void openHome(ActionEvent event) {
    }

    @FXML
    private void openListProducts(ActionEvent event) {
    }

    @FXML
    private void openListStudent(ActionEvent event) {
    }

    @FXML
    private void openListClaims(ActionEvent event) {
    }
    
}

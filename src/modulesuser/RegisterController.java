/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesuser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Beshir
 */
public class RegisterController implements Initializable {

    @FXML
    private AnchorPane parentPane;
    @FXML
    private JFXTextField txtFname;
    @FXML
    private JFXTextField txtLname;
    @FXML
    private JFXTextField txtMobile;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtLocation;
    @FXML
    private JFXRadioButton rdMale;
    @FXML
    private ToggleGroup gender;
    @FXML
    private JFXRadioButton rdFemale;
    @FXML
    private JFXTextField txtMobile111;
    @FXML
    private ProgressBar progressPersonal;
    @FXML
    private Label lblComplete;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXTextField txtZipCode;
    @FXML
    private JFXTextField txtCIN;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPasswordConfirmation;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXPasswordField txtParrain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clearFields(ActionEvent event) {
    }
    
}

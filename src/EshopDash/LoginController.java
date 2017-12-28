/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EshopDash;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import eshop.interfaces.ICustomer;
import eshop.models.Customer;
import eshop.services.CustomerServices;
import eshop.technique.BCrypt;
import eshop.technique.Tools;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author danml
 */
public class LoginController implements Initializable {

    private Label label;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private StackPane rootPane;
    @FXML
    private ImageView imgProgress;
    @FXML
    private JFXButton btnRegister;
    @FXML
    private Label labelerror;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleValidation();
        imgProgress.setVisible(false);
    }
    ICustomer customerser = new CustomerServices();

    @FXML
    private void login(ActionEvent event) {
        Customer c1 = customerser.findByUserName(txtUsername.getText());
        Customer c2 = customerser.findByMail(txtUsername.getText());
        
        /////////
        
        
        
        ////////
        
        labelerror.setText("");
        imgProgress.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
            if (txtUsername.getText().equals("admin") && txtPassword.getText().equals("admin")) {
                completeLogin();
                //   System.out.println("admin");
            } else if (c1 == null && c2 == null) {
                labelerror.setText("compte n'existe pas");

                imgProgress.setVisible(false);
                // pauseTransition.play();
            } else if (c1 != null) {
                //if (txtPassword.getText().equals(c1.getCustomerPassword())) {
                
                if (BCrypt.checkpw(txtPassword.getText(),c1.getCustomerPassword())) {
                    try {
                        //  System.out.println("compte mawjoud");
                        Tools.account = (Customer) c1.clone();
                        Main.inCustomer = c1.getCustomerId();
                        // System.out.println(Tools.account);
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    completeLoginUser();

                }

            //} else if (txtPassword.getText().equals(c2.getCustomerPassword())) {
            } else if (BCrypt.checkpw(txtPassword.getText(),c2.getCustomerPassword())) {
                try {
                    //  System.out.println("compte mawjoud");
                    Tools.account = (Customer) c1.clone();
                    Main.inCustomer = c1.getCustomerId();
                    // System.out.println(Tools.account);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                completeLoginUser();

            } else {
                System.out.println("mot de pass erroné");
            }

        });
        pauseTransition.play();
    }

    private void handleValidation() {
        RequiredFieldValidator fieldValidator = new RequiredFieldValidator();
        fieldValidator.setMessage("Input required");
        fieldValidator.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtUsername.getValidators().add(fieldValidator);
        txtUsername.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal) -> {
            if (!newVal) {
                txtUsername.validate();

            }
        });
        RequiredFieldValidator fieldValidator2 = new RequiredFieldValidator();
        fieldValidator2.setMessage("Input required");
        fieldValidator2.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtPassword.getValidators().add(fieldValidator2);
        txtPassword.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                txtPassword.validate();
            }
        });

    }

    private void completeLoginUser() {
        btnLogin.getScene().getWindow().hide();
        try {
            imgProgress.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("DashbordUser.fxml"));

            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void completeLogin() {
        btnLogin.getScene().getWindow().hide();
        try {
            imgProgress.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void Register(ActionEvent event) {
        btnRegister.getScene().getWindow().hide();
        try {
            imgProgress.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("FirstRegister.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}

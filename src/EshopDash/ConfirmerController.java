/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EshopDash;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.twilio.Twilio;
import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import eshop.interfaces.ICustomer;
import eshop.services.CustomerServices;
import eshop.technique.Tools;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author adnen
 */
public class ConfirmerController implements Initializable {

    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXTextField txtmessage;
    @FXML
    private Label Label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(MouseEvent event) throws InterruptedException {
        ICustomer customer = new CustomerServices();
        /*  Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
         
        Message message = Message.creator( 
      
            new PhoneNumber("+21626898869") ,new PhoneNumber("+16283000732"),Tools.account.getCustomerCIN()
        ).create(); */
        
        if(txtmessage.getText().equals(Tools.account.getCustomerCIN()))
        { customer.add(Tools.account);
         Label.setText("Validé");
        
            TimeUnit.SECONDS.sleep(3);

            
        btnClear.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }}
        else {Label.setText("code erroné");}
        
    }

    @FXML
    private void cancel(MouseEvent event) {
        btnClear.getScene().getWindow().hide();
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

    @FXML
    private void clearFields(ActionEvent event) {
    }

    
    
}

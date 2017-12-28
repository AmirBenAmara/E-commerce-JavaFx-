/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesadmin;


import EshopDash.SendMail;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import eshop.models.Customer;
import eshop.services.CustomerServices;
import eshop.technique.Tools;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class OverviewController implements Initializable {

    @FXML
    private HBox groupHolder;
    @FXML
    private Group groupRegistered;
    @FXML
    private Group groupTarget;
    @FXML
    private Group groupGents;
    @FXML
    private Group groupLadies;
    private ObservableList<String> departments;

    protected Connection connection;
    
    Statement stmt = null;
    ResultSet rs = null;
    JFXTextField txtDepartment;
    @FXML
    private StackPane deptStackPane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRipples();
        
    }

    private void setRipples() {
        JFXRippler rippler1 = new JFXRippler(groupRegistered);
        JFXRippler rippler2 = new JFXRippler(groupGents);
        JFXRippler rippler3 = new JFXRippler(groupLadies);
        JFXRippler rippler4 = new JFXRippler(groupTarget);
        rippler1.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler2.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler3.setMaskType(JFXRippler.RipplerMask.RECT);
        rippler4.setMaskType(JFXRippler.RipplerMask.RECT);

        rippler1.setRipplerFill(Paint.valueOf("#1564C0"));
        rippler2.setRipplerFill(Paint.valueOf("#00AACF"));
        rippler3.setRipplerFill(Paint.valueOf("#00B3A0"));
        rippler4.setRipplerFill(Paint.valueOf("#F87951"));

        groupHolder.getChildren().addAll(rippler1, rippler2, rippler3, rippler4);
    }

    @FXML
    private void sendNewsletter(ActionEvent event) {
        
        CustomerServices cs =  new CustomerServices();
        SendMail s = new SendMail();
        for(Customer c : cs.getAll()){
            s.send( c.getCustomerMail()
                    , "E-SHOP Newsletter"
                    ,"visit our website https://www.amazon.com/ ,new products are ready for sale"                    
                    ,"goldsolutioneshop@gmail.com"
                    , "goldsolutioneshop1221");
        }

        File file = new File("src/images/validate.png");
        Image img = new Image(file.toURI().toString());
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Succes");
        tray.setMessage("Newsletter has been sended to all users ");
        tray.setImage(img);
        tray.showAndWait();
    }
}
   

   
   

 


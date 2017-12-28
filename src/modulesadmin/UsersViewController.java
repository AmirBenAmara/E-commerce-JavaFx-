/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesadmin;

import EshopDash.SendMail;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import eshop.interfaces.ICustomer;
import eshop.models.Claim;
import eshop.models.Customer;
import eshop.services.CustomerServices;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author Beshir
 */
public class UsersViewController implements Initializable {

    @FXML
    private AnchorPane holderAnchor;
    @FXML
    private Label lblName;
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
    private Label lblBalance;
    @FXML
    private AnchorPane fabPane;
    @FXML
    private Label fabEdit;
    @FXML
    private ToggleGroup filter;
    @FXML
    private TableView<Customer> tableUsers;
    @FXML
    private JFXTextField inputSearch;
    @FXML
    private TableColumn<?, ?> IDUser;
    @FXML
    private TableColumn<?, ?> UserName;
    @FXML
    private TableColumn<?, ?> Mail;
    @FXML
    private TableColumn<?, ?> CreationDate;
    @FXML
    private JFXRadioButton RDUserName;
    @FXML
    private JFXRadioButton RDMail;
    @FXML
    private JFXButton Search;
    @FXML
    private Button BanUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setRipples();
        buildUsersTable();
        
        
        tableUsers.getSelectionModel().selectedItemProperty().addListener(
                (
                        ObservableValue<? extends Customer> observable,
                        Customer oldValue,
                        Customer newValue) -> {
                    if (newValue == null) {
                                
                        return;
                    }
                    getUserInfo(newValue.getCustomerMail()); 
                   

                }); 
        
    
    }    


    @FXML
    private void search() throws ParseException {
        if (RDUserName.isSelected()){
          ObservableList<Customer> customers = FXCollections.observableArrayList();
   
         ICustomer cs=new CustomerServices();
         Customer client = cs.findByUserName(inputSearch.getText());
         customers.add(client);            
         IDUser.setCellValueFactory(new PropertyValueFactory<>("customerId"));
         UserName.setCellValueFactory(new PropertyValueFactory<>("customerUserName"));
         Mail.setCellValueFactory(new PropertyValueFactory<>("customerMail"));
         CreationDate.setCellValueFactory(new PropertyValueFactory<>("customerAccountCreation"));
         
          tableUsers.getItems().clear();
          tableUsers.getItems().addAll(customers);          
       }
        else if(RDMail.isSelected()){
            ObservableList<Customer> customers = FXCollections.observableArrayList();
   
         ICustomer cs=new CustomerServices();
         Customer client = cs.findByMail(inputSearch.getText());
         customers.add(client);            
         IDUser.setCellValueFactory(new PropertyValueFactory<>("customerId"));
         UserName.setCellValueFactory(new PropertyValueFactory<>("customerUserName"));
         Mail.setCellValueFactory(new PropertyValueFactory<>("customerMail"));
         CreationDate.setCellValueFactory(new PropertyValueFactory<>("customerAccountCreation"));
         
          tableUsers.getItems().clear();
          tableUsers.getItems().addAll(customers);          
            
        }
        
        
    }

    @FXML
    private void buildUsersTable() {
        
        ObservableList<Customer> customers = FXCollections.observableArrayList();
   
         ICustomer cs=new CustomerServices();
         cs.getAll().forEach((user) ->{ 
                 customers.add(user);
                         });
         
         IDUser.setCellValueFactory(new PropertyValueFactory<>("customerId"));
         UserName.setCellValueFactory(new PropertyValueFactory<>("customerUserName"));
         Mail.setCellValueFactory(new PropertyValueFactory<>("customerMail"));
         CreationDate.setCellValueFactory(new PropertyValueFactory<>("customerAccountCreation"));

          tableUsers.getItems().clear();
          tableUsers.getItems().addAll(customers);
    }
    
    private void setRipples() {
        JFXRippler fXRippler = new JFXRippler(fabEdit);
        fXRippler.setMaskType(JFXRippler.RipplerMask.CIRCLE);
        fXRippler.setRipplerFill(Paint.valueOf("#FF80AF"));
        fabPane.getChildren().add(fXRippler);

    }
    
    private void getUserInfo(String mail) {
    
            if (mail == null) {
                return;
            }
            ICustomer cs = new CustomerServices();
            Customer client = cs.findByMail(mail) ; 
            
            lblName.setText(client.getCustomerFirstName()+" "+client.getCustomerLastName());
            lblLocation.setText(client.getCustomerAddress());
            lblEmail.setText(client.getCustomerMail());
            lblPhone.setText(client.getCustomerCP());
            lblLevel.setText("Born on : " + client.getCustomerBirthDate().toString());
            lblDepartment.setText("Your user name is : " + client.getCustomerUserName());
            lblCourse.setText("Created on : " + client.getCustomerAccountCreation().toString());
            lblFee.setText(String.valueOf(client.getPtFidelite()));
            lblBalance.setText(client.getParrainage());
                    
    }       

    @FXML
    private void BanUser(ActionEvent event) {
        ICustomer cs= new CustomerServices();
        cs.remove(cs.findByMail(lblEmail.getText()));
        setRipples();
        buildUsersTable();
        SendMail sm = new SendMail();
       sm.send("amir.benamara@esprit.tn", "api mail", "votre compte a été supprimer, tu es un voleur !", "bechir.landolsi@esprit.tn", "07187003");
      
    }
    
}

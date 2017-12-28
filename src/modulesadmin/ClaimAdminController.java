/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesadmin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import eshop.models.Claim;
import eshop.models.Order;
import eshop.services.ClaimServices;
import eshop.services.OrderServices;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Amiir
 */
public class ClaimAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Label fabEdit;
    private AnchorPane fabPane;

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
    private Label lblCourse;
    @FXML
    private Label lblFee;
    @FXML
    private Label lblPaid;
    @FXML
    private Label lblBalance;

    @FXML
    private Label lblLevel;

    @FXML
    private ToggleGroup filter;
    private JFXRadioButton rbId;
    private JFXRadioButton rbDate;
    private JFXTextField inputSearch;
    @FXML
    private TableView<Claim> _tableClaims;
    private TableColumn<?, ?> _idClaim;
    @FXML
    private TableColumn<?, ?> _idDate;
    @FXML
    private TableColumn<?, ?> _idorder;
    @FXML
    private TableColumn<?, ?> _idText;
    @FXML
    private JFXRadioButton rbTreated;
    @FXML
    private JFXRadioButton rbUntreated;
    @FXML
    private TableColumn<?, ?> _idEtat;
    @FXML
    private JFXRadioButton rbAllc;
    @FXML
    private JFXButton rmbutton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //setRipples();
        buildClaimTable();

        _tableClaims.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Claim> observable,
                        Claim oldValue,
                        Claim newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    getOrderInfo(newValue.getId_order());
                });
    }

    private void setRipples() {
        JFXRippler fXRippler = new JFXRippler(fabEdit);
        fXRippler.setMaskType(JFXRippler.RipplerMask.CIRCLE);
        fXRippler.setRipplerFill(Paint.valueOf("#FF80AF"));
        fabPane.getChildren().add(fXRippler);

    }

    @FXML
    private void searchClaim() throws ParseException {
        ObservableList<Claim> claimlist = FXCollections.observableArrayList();
        ClaimServices claimservices = new ClaimServices();

        if (rbTreated.isSelected()) {
            for (Claim c : claimservices.getAll()) {
                if (c.getEtat().equals("treated")) {
                    claimlist.add(c);
                }
            }

        } else if (rbUntreated.isSelected()) {

            for (Claim c : claimservices.getAll()) {
                if (c.getEtat().equals("untreated") ) {
                    claimlist.add(c);
                }
            }
            }else if(rbAllc.isSelected()) {

            for (Claim c : claimservices.getAll()) {
                    claimlist.add(c);
                }
            }           
            _idDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            _idorder.setCellValueFactory(new PropertyValueFactory<>("id_order"));
            _idText.setCellValueFactory(new PropertyValueFactory<>(("text")));
            _idEtat.setCellValueFactory(new PropertyValueFactory<>(("etat")));
            _tableClaims.getItems().clear();
            _tableClaims.getItems().addAll(claimlist);
              
    }

    private void getClaimInfo(Integer id) {
        if (id == null) {
            return;
        }
        ClaimServices claimservices = new ClaimServices();
        for (Claim c : claimservices.getAll()) {
            lblName.setText(c.getDate().toString());

        }
    }

    @FXML
    private void buildClaimTable() {
        ObservableList<Claim> claimlist = FXCollections.observableArrayList();

        ClaimServices claimservices = new ClaimServices();
        claimservices.getAll().forEach((c1) -> {
            claimlist.add(c1);
        });
        _idDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        _idorder.setCellValueFactory(new PropertyValueFactory<>("id_order"));
        _idText.setCellValueFactory(new PropertyValueFactory<>(("text")));
        _idEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));

        _tableClaims.getItems().clear();
        _tableClaims.getItems().addAll(claimlist);

    }

    private void getOrderInfo(Integer id) {

        if (id == null) {
            return;
        }
        OrderServices orderservices = new OrderServices();
        Order o = orderservices.findById(id);

        lblName.setText("ORDER INFO");
        lblLocation.setText("order id : " + o.getOrderId());
        lblEmail.setText("customer : " + o.getCustomer().getCustomerFirstName());
        lblPhone.setText("order date : " + o.getOrderDate());
        lblLevel.setText("Quantity : " + o.getOrderQuantity());
        lblDepartment.setText("state : " + o.getEtat());
        lblCourse.setText("product :" + o.getProduct());
        lblFee.setText("" + o.getCustomer().getCustomerId());
        lblPaid.setText("" + o.getCustomer().getCustomerFirstName());
        lblBalance.setText("" + o.getCustomer().getCustomerLastName());
    }

    @FXML
    private void removeClaim(ActionEvent event) {
        int a=_tableClaims.getSelectionModel().getSelectedItem().getId();
        System.out.println(""+a);
        ClaimServices cs = new ClaimServices();
        cs.removeById(a);
        buildClaimTable();
        File file = new File("src/images/validate.png");
        Image img = new Image(file.toURI().toString());
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Succes");
        tray.setMessage("You've successfully remove claim");
        tray.setImage(img);
        tray.showAndWait();
    }

    @FXML
    private void validClaim(ActionEvent event) {
        int a=_tableClaims.getSelectionModel().getSelectedItem().getId();
        System.out.println(""+a);
        ClaimServices cs = new ClaimServices();
        cs.updateState(a);
        buildClaimTable();
        File file = new File("src/images/validate.png");
        Image img = new Image(file.toURI().toString());
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Succes");
        tray.setMessage("Claim Has been treated succesfully");
        tray.setImage(img);
        tray.showAndWait();
    
    }

}

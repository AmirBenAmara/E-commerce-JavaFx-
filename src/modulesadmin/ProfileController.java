/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesadmin;

import com.jfoenix.controls.JFXRippler;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class ProfileController implements Initializable {

    @FXML
    private Label fabEdit;
    @FXML
    private AnchorPane fabPane;
    @FXML
   
   
    private Connection connection;
    private ResultSet rs;
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
    private ToggleGroup filter;
    @FXML
    private Label lblLevel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        setRipples();
        
      
   }

    private void setRipples() {
        JFXRippler fXRippler = new JFXRippler(fabEdit);
        fXRippler.setMaskType(JFXRippler.RipplerMask.CIRCLE);
        fXRippler.setRipplerFill(Paint.valueOf("#FF80AF"));
        fabPane.getChildren().add(fXRippler);

    }

   

      /*private void getStudentInfo(Integer id) {
        try {
            if (id == null) {
                return;
            }
            String query = "SELECT * FROM students where id='" + id + "';";
            connection = dbHandler.getConnection();
            ResultSet rs1 = connection.createStatement().executeQuery(query);
            while (rs1.next()) {
                lblName.setText(rs1.getString("fname").toUpperCase() + " " + rs1.getString("lname").toUpperCase());
                lblLocation.setText(rs1.getString("location"));
                lblEmail.setText(rs1.getString("email"));
                lblPhone.setText(rs1.getString("phone"));
                lblLevel.setText(rs1.getString("level") + " Programme");
                lblDepartment.setText(rs1.getString("department") + " Department");
                lblCourse.setText(rs1.getString("course"));
                lblPaid.setText(rs1.getString("paid"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EshopDash;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import static com.sun.media.jfxmediaimpl.MediaUtils.warning;
import eshop.interfaces.ICustomer;
import eshop.models.Customer;
import eshop.services.CustomerServices;
import eshop.technique.BCrypt;
import eshop.technique.Tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import static org.controlsfx.validation.ValidationMessage.warning;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class FirstRegisterController implements Initializable {

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
    private static double progress1 = 0;
    private static double progress2 = 0;
    private static double progress3 = 0;
    private static double progress4 = 0;
    private static double progress5 = 0;
    private static double progress6 = 0;
    private static double progress7 = 0;
    private static double progress8 = 0;
    private static double progress9 = 0;
    private static double progress10 = 0;
    @FXML
    private ProgressBar progressPersonal;
    private ObservableList<String> depart_lists;
    @FXML
    private Label lblComplete;
    private Connection connection;
    private PreparedStatement pst;
    
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnSave;
    @FXML
    private AnchorPane parentPane;
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
    private JFXTextField txtParrain;
    @FXML
    private JFXButton btnCancel;

    
    String getImageUrl="src/icons/téléchargé.png";
    @FXML
    private JFXDatePicker birthday;
    @FXML
    private ImageView image;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        updateProgress();
        try {
            image.setImage(new Image(new FileInputStream("src/icons/téléchargé.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FirstRegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

    private void updateProgress() {
         DecimalFormat decimalFormat = new DecimalFormat("###.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        //progressPersonal.setProgress(0.00);       
        double sum_progress = progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9;

        txtFname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress1 = 0.1;

                } else {
                    progress1 = 0.0;

                }

                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

        txtEmail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress2 = 0.1;

                } else {
                    progress2 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

        txtLname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress3 = 0.1;

                } else {
                    progress3 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

        txtMobile.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 1) {
                    progress4 = 0.1;

                } else {
                    progress4 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

        txtLocation.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress5 = 0.1;

                } else {
                    progress5 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        //zipcode
        txtZipCode.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress6 = 0.1;

                } else {
                    progress6 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        // CIN
        txtCIN.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress7 = 0.1;

                } else {
                    progress7 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        //username
        txtUserName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress8 = 0.1;

                } else {
                    progress8 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

       
        txtPasswordConfirmation.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty() && txtPassword.getText().equals(txtPasswordConfirmation.getText())) {
                    progress9 = 0.2;

                } else {
                    progress9 = 0.0;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

        

    }

   

    @FXML
    public void clearFields() {
        
        birthday.setValue(null);
        txtEmail.setText("");
        txtFname.setText("");
        txtLname.setText("");
        txtLocation.setText("");
        txtMobile.setText("");
        txtZipCode.setText("");
        txtCIN.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
        txtPasswordConfirmation.setText("");
        txtParrain.setText("");

    }

    @FXML
    private void choose() {
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        getImageUrl = selectedfile.getAbsolutePath();
        File file = new File(getImageUrl);
        Image ima = new Image(file.toURI().toString());
        image.setImage(ima);
        
    }

    @FXML
    private void backLoginUser() {
        btnCancel.getScene().getWindow().hide();
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
    private void registre() {
          ICustomer customer = new CustomerServices();
        LocalDate localDate1 = birthday.getValue();
        java.sql.Date date = java.sql.Date.valueOf(localDate1);
        java.sql.Date date1 = java.sql.Date.valueOf(LocalDate.now());
        int convertir = Integer.parseInt(txtCIN.getText());
        int convertir1 = Integer.parseInt(txtMobile.getText());
//////////
        long difference = (date1.getTime() - date.getTime()) / 86400000;
        //System.out.println(Math.abs(difference));
        /*6750*/

/////////
        Customer c1 = customer.findByUserName(txtUserName.getText());
        Customer c2 = customer.findByMail(txtEmail.getText());
        String hashed = BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt());
        Customer c = new Customer(convertir, 10, txtFname.getText(), txtLname.getText(), txtEmail.getText(), txtLocation.getText(), txtCIN.getText(), txtZipCode.getText(), getImageUrl, txtUserName.getText(), hashed, txtParrain.getText(), "eshop", date, date1,convertir1,0);

        if (c1 == null && c2 == null) {

            String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(masque);
            //Matcher controler = pattern.matcher(txtEmail.getText());

            if (txtMobile.getText().length() != 8) {
                txtMobile.setText("");
                lblComplete.setText("mobile num erroné");
            } else if (txtCIN.getText().trim().length() != 8) {
                txtCIN.setText("");
                lblComplete.setText("CIN erroné");
            } /*else if (!controler.matches()) {
                 txtEmail.setText("");
                lblComplete.setText("Mail erroné");

            }*/ else if (difference < 6750) {
                birthday.setValue(null);
                lblComplete.setText("vous devez avoir 18 ans");
            } else {
                Tools.account=c;
              //  customer.add(c);
               // clearFields();
               lblComplete.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("confirmer.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
               
               
            }
        } else {
            lblComplete.setText("compte existant");

        }
        
    }

    @FXML
    private void mobiletest(MouseEvent event) {
    }

 
    }

  

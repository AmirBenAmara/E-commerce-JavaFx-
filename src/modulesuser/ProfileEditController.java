/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesuser;

import EshopDash.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import eshop.interfaces.ICustomer;
import eshop.models.Customer;
import eshop.services.CustomerServices;
import eshop.technique.Tools;
import static eshop.technique.Tools.account;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Beshir
 */
public class ProfileEditController implements Initializable {

    @FXML
    private JFXTextField txtFname;
    @FXML
    private JFXTextField txtLname;
    private JFXTextField txtMobile;
    @FXML
    private JFXTextField txtEmail;

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
    @FXML
    private Label lblComplete;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private AnchorPane parentPane;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXTextField txtZipCode;
    @FXML
    private JFXTextField txtAddress;
    private JFXDatePicker txtBirthDate;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXTextField txtNewEmail;
    @FXML
    private JFXTextField txtRetypeNewEmail;
    @FXML
    private JFXPasswordField txtNewPassword;
    @FXML
    private JFXPasswordField txtRetypeNewPassword;
    @FXML
    private TextField CodeParrainage;
    @FXML
    private TextField PTFidelite;
    @FXML
    private Label Warning;
    @FXML
    private ImageView imgChooser;
    @FXML
    private Label label;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Label Warning1;
    @FXML
    private Label Warning2;
    @FXML
    private Label Warning3;
    @FXML
    private Label Warning4;
    @FXML
    private JFXTextField txtLPhoneNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Date date = Date.valueOf("2017-02-01");
        Date date1 = Date.valueOf(LocalDate.now());
        //Tools T = new Tools();
        //T.account=new Customer(150,"Léo","Messi","bechir.landolsi@esprit.tn","ben arous","07187003","2063"
        //     ,"desktop","blandolsi","123456789","564","413",date,date1,58138630,0);

        setTxtFname(Tools.account.getCustomerFirstName());
        setTxtUserName(Tools.account.getCustomerUserName());
        setTxtAddress(Tools.account.getCustomerAddress());
        setPTFidelite(Tools.account.getPtFidelite());
        setTxtLname(Tools.account.getCustomerLastName());
        setCodeParrainage(Tools.account.getParrainage());
        setTxtEmail(Tools.account.getCustomerMail());
        setTxtZipCode(Tools.account.getCustomerCP());
        txtLPhoneNumber.setText(String.valueOf(Tools.account.getPhoneNumber()));

        Image img = new Image("http://127.1.2.3:8080/tarajji.jpg");
        imgChooser.setImage(img);

    }

    @FXML
    private void DeleteAccount(ActionEvent event) {
        ICustomer cs = new CustomerServices();
        cs.remove(cs.findByMail(txtEmail.getText()));

        btnClear.getScene().getWindow().hide();
        try {
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/EshopDash/Login.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void updateAccount(ActionEvent event) {

        boolean verif = verification();

        ICustomer cs = new CustomerServices();
        if (verif) {
            Tools T = new Tools();
            T.account.setCustomerFirstName(txtFname.getText());
            T.account.setCustomerLastName(txtLname.getText());
            T.account.setPhoneNumber(Integer.valueOf(txtLPhoneNumber.getText()));
            if (!txtNewEmail.getText().isEmpty()) {
                Customer client = cs.findByMail(txtNewEmail.getText());
                if (client != null) {
                    Warning.setText("This account is attached to another account");
                } else {
                    Warning.setText("You must confirm your new mail address");
                }
            }

            if (!txtRetypeNewEmail.getText().isEmpty()) {
                if (txtRetypeNewEmail.getText().equals(txtNewEmail.getText())) {
                    Warning.setText("");

                    T.account.setCustomerMail(txtRetypeNewEmail.getText());

                } else {
                    Warning1.setText("You must confirm your new mail address");
                    Warning.setText("");
                    T.account.setCustomerMail(txtEmail.getText());
                }
            } else {
                if (!txtNewEmail.getText().isEmpty()) {
                    Warning.setText("You must confirm your new mail address");
                    T.account.setCustomerMail(txtEmail.getText());
                }

            }

            T.account.setCustomerCP(txtZipCode.getText());
            T.account.setCustomerAddress(txtAddress.getText());
            if (!txtPassword.getText().isEmpty()) {
                Customer client = cs.findByMail(txtEmail.getText());
                if (client.getCustomerPassword().equals(txtPassword.getText()) == true) {
                    Warning2.setText("");
                } else {
                    Warning2.setText("Check your password please");
                }
            }

            if (!txtPassword.getText().isEmpty()) {
                Customer client = cs.findByMail(txtEmail.getText());
                if (client.getCustomerPassword().equals(txtPassword.getText()) == true) {
                    Warning2.setText("");

                } else {
                    Warning2.setText("Check your password please");
                }
            }

            if (!txtNewPassword.getText().isEmpty()) {
                if (txtNewPassword.getText().equals(txtPassword.getText())) {
                    Warning2.setText("");
                } else {
                    Warning3.setText("It could not be the same password");
                    Warning2.setText("");
                }
            } else {
                if (!txtPassword.getText().isEmpty()) {
                    Warning2.setText("You must create a new password");
                }

            }

            if (!txtRetypeNewPassword.getText().isEmpty()) {
                if (txtRetypeNewPassword.getText().equals(txtNewPassword.getText())) {
                    Warning3.setText("");
                    T.account.setCustomerPassword(txtRetypeNewPassword.getText());
                } else {
                    Warning4.setText("You must confirm your new password");
                    Warning3.setText("");
                }
            } else {
                if (!txtNewPassword.getText().isEmpty()) {
                    Warning3.setText("You must confirm your new password");
                }

            }

        }

        cs.update(account);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Your updates are successfully done", ButtonType.OK);
        alert.showAndWait();

    }

    private boolean verification() {

        boolean verif = true;
        if (txtFname.getText().isEmpty() || txtLname.getText().isEmpty()
                || txtLPhoneNumber.getText().isEmpty() || txtZipCode.getText().isEmpty()
                || txtAddress.getText().isEmpty()) {
            Warning.setText("Please check your empty blanks !");
            verif = false;
        } else {
            Warning.setText("");
        }

        return verif;

    }

    @FXML
    private void validerMail(KeyEvent event) {
        /*String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"+"[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(txtNewEmail.getText());
        
        if (controler.matches()){            
            Warning.setText("mrigla");
            //System.out.println ("lol");
            //Signup.setDisable(false);                            
        }
        else{
        Warning.setText("Please check your new mail address");
        //warning1.setText("veuillez entrez une adresse email valide");
        //Signup.setDisable(true);
        }*/

    }

    /*private void updateProgress() {
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
        //Course name
        txtCourseName.textProperty().addListener(new ChangeListener<String>() {
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
        // Amount paid
        txtAmount.textProperty().addListener(new ChangeListener<String>() {
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
        //Gender Radio buttons
        rdMale.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!oldValue) {
                    progress8 = 0.1;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        rdFemale.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!oldValue) {
                    progress8 = 0.1;

                }

                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

        //Levels Radio buttons
        rdDegree.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!oldValue) {
                    progress9 = 0.1;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        rdDiploma.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!oldValue) {
                    progress9 = 0.1;

                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });
        rdCertificate.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!oldValue) {
                    progress9 = 0.1;

                }
                double sum = (progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(String.valueOf(sum * 100) + "% complete");

            }
        });

        comboDepartmenT.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue,
                    String newValue) {
                if (!newValue.isEmpty()) {
                    progress10 = 0.1;
                } else {
                    progress10 = 0.0;
                }
                double sum = (progress10 + progress1 + progress2 + progress3 + progress4 + progress5 + progress6 + progress7 + progress8 + progress9);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% complete");
            }
        });

    }
     */
    public void setTxtFname(String txtFname) {
        this.txtFname.setText(txtFname);
    }

    public void setTxtLname(String txtLname) {
        this.txtLname.setText(txtLname);
    }

    public void setTxtMobile(String txtMobile) {
        this.txtMobile.setText(txtMobile);
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail.setText(txtEmail);
    }

    public void setTxtZipCode(String txtZipCode) {
        this.txtZipCode.setText(txtZipCode);
    }

    public void setTxtAddress(String txtAddress) {
        this.txtAddress.setText(txtAddress);
    }

    public void setTxtBirthDate(String txtBirthDate) {
        this.txtBirthDate.setAccessibleHelp(txtBirthDate);
    }

    public void setTxtUserName(String txtUserName) {
        this.txtUserName.setText(txtUserName);
    }

    public void setCodeParrainage(String CodeParrainage) {
        this.CodeParrainage.setText(CodeParrainage);
    }

    public void setPTFidelite(int PTFidelite) {
        this.PTFidelite.setText(String.valueOf(PTFidelite));
    }

    @FXML
    private void choisirImage(MouseEvent event) throws IOException {
        final JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(fc);
        FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        File selectedFile = fc.getSelectedFile();
        String path = selectedFile.getAbsolutePath();

        String filename = "file:///" + path;
        Image img = new Image(filename);
        imgChooser.setImage(img);

        InputStream in = new URL(path).openStream();
        Files.copy(in, Paths.get("C:\\wamp64\\www\\E-shop\\image.jpg"));
        label.setText(filename);

    }

}

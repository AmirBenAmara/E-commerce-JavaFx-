/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesuser;

import EshopDash.SendMail;
import eshop.models.Claim;
import eshop.models.Order;
import eshop.services.ClaimServices;
import eshop.services.CustomerServices;
import eshop.services.OrderServices;
import eshop.technique.Tools;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Karim SNOUSSI
 */
public class ClaimController implements Initializable {

    ObservableList<Integer> commandeliste = FXCollections.observableArrayList();
    ObservableList<String> motifList = FXCollections.observableArrayList();
    ObservableList<Integer> commandeList = FXCollections.observableArrayList();

    @FXML
    private AnchorPane holderAnchor;
    @FXML
    private TextArea TextClaim;
    @FXML
    private ComboBox commandeListe;
    @FXML
    private ComboBox MotifListe;
    @FXML
    private Button ClaimSave;

    OrderServices orderServices;
    List<Order> orderList;

    List<Integer> idListe;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderServices = new OrderServices();
        CustomerServices customerServices = new CustomerServices();
        orderList = orderServices.getByCustomer(Tools.account);
     
        int i=0;
        for (Order order : orderList) {
            commandeListe.getItems().add(order.getOrderId() + " : " + order.getProductName());
            System.err.println("Order added");
        }
        System.err.println("fin d'ajout");
        motifList.addAll("Commande non reçu", "Commande reçu endommagé", "Commande reçu différente de commandé", "Commande reçu avec des produit défectueux", "Commande reçu non commandé", "Remboursement non reçu", "Debit different du prix");
        MotifListe.setItems(motifList);
    }

    @FXML
    private int getTheFirstInt(String s) {
        StringBuilder result = new StringBuilder();
        for (char a : s.toCharArray()) {
            try {
                Integer.parseInt(a + "");
                result.append(a);
            } catch (NumberFormatException e) {
                break;
            }
        }
        return Integer.parseInt(result.toString());
    }

    @FXML
    private void SaveMyClaimAction(ActionEvent event) {

        ClaimServices claimService = new ClaimServices();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date;
        date = new java.sql.Date(utilDate.getTime());

        ClaimServices claimServices = new ClaimServices();
        Claim c = new Claim(date, this.getTheFirstInt(commandeListe.getValue().toString()), "\nMotif :" + MotifListe.getValue() + "\nCommentaire : " + TextClaim.getText());
        claimServices.add(c);

        /**
         * *****************envoie d'email*******************
         */
        String mailCntnt = "Claim ID : " + c.getId() + "\nClaim Date : " + c.getDate() + "Claim text :" + c.getText();

        SendMail.send("snoukarim@gmail.com", "E-SHOP Reclamation client reçu", mailCntnt, "goldsolutioneshop@gmail.com", "goldsolutioneshop1221");
        SendMail.send(Tools.account.getCustomerMail(), "Votre requete e-shop by GOLD SOLUTION", "Bonjour " + Tools.account.getCustomerLastName() + ",\nsi vous recevez ce mail, c'est qu'on a bien reçu votre réclamation.\nVotre réclamation a été bien prise en compte et vous serez recontacté ultérieurement.\nVeuillez nous excuser pour la gêne occasionnée.\nCordialement\n|***GOLD SOLUTION TEAM***|", "goldsolutioneshop@gmail.com", "goldsolutioneshop1221");

        commandeListe.setValue("");
        MotifListe.setValue("");
        TextClaim.setText("");
        /**
         * *****************envoie d'email*******************
         */
    }
}

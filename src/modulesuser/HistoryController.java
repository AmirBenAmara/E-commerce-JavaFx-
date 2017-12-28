/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesuser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import eshop.models.Comment;
import eshop.models.Order;
import eshop.services.CommentService;
import eshop.services.CustomerServices;
import eshop.services.OrderServices;
import eshop.technique.Tools;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Karim SNOUSSI
 */
public class HistoryController implements Initializable {

    ObservableList<Order> data = FXCollections.observableArrayList();
    ObservableList<Order> dataFound = FXCollections.observableArrayList();

    Tools T = new Tools();
    CommentService commentService = new CommentService();
    @FXML
    private AnchorPane holderAnchor;
    @FXML
    private TableView<Order> tableCommandes;
    @FXML
    private TableColumn<Order, Date> DateCommande;
    @FXML
    private TableColumn<Order, String> ProduitCommande;
    @FXML
    private TableColumn<Order, Integer> QuantiteProduitCommande;
    @FXML
    private TableColumn<Order, Double> PrixProduitCommande;
    @FXML
    private TableColumn<Order, Double> PrixTotalCommande;
    @FXML
    private TableColumn<Order, String> Etat;
    @FXML
    private JFXButton searchCommande;
    OrderServices orderServices;
    List<Order> orderListe;
    List<Order> orderDataListe;
    @FXML
    private JFXTextField searchText;
    @FXML
    private TextArea commentaires;
    @FXML
    private TextField commentaire;
    @FXML
    private TextField quantityForSell;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productName;
    int position;
    Order selectedOrderForComment;
    @FXML
    private Label alertNotSelected;
    CustomerServices customerServices = new CustomerServices();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderServices = new OrderServices();
        //Tools.account = customerServices.findById(2);// a supprimer
        System.err.println(Tools.account.getCustomerFirstName());
        orderListe = orderServices.getByCustomer(Tools.account);
        System.out.println(orderListe);
        orderListe.forEach((order) -> {
            System.err.println(order);
        });

        data.addAll(orderListe);
        DateCommande.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        ProduitCommande.setCellValueFactory(new PropertyValueFactory<>("productName"));
        QuantiteProduitCommande.setCellValueFactory(new PropertyValueFactory<>("orderQuantity"));
        PrixProduitCommande.setCellValueFactory(new PropertyValueFactory<>("priceProduit"));
        PrixTotalCommande.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tableCommandes.setItems(data);
        data.forEach((order) -> {
            dataFound.add(order);
        });
    }

    @FXML
    private void clickSearchCommande(ActionEvent event) {
        if ((!"".equals(searchText.getText()))) {
            dataFound.clear();

            data.stream().filter((order) -> (-1 != order.getProductName().indexOf(searchText.getText()))).forEachOrdered((order) -> {
                dataFound.add(order);
            });
            tableCommandes.setItems(dataFound);
            tableCommandes.refresh();
        } else {
            tableCommandes.setItems(data);
            tableCommandes.refresh();
        }
    }

    @FXML
    private void TextEditEnCours(KeyEvent event) {
        if ((!"".equals(searchText.getText()))) {
            dataFound.clear();

            data.stream().filter((order) -> (-1 != order.getProductName().indexOf(searchText.getText()))).forEachOrdered((order) -> {
                dataFound.add(order);
            });
            tableCommandes.setItems(dataFound);
            tableCommandes.refresh();
        } else {
            tableCommandes.setItems(data);
            tableCommandes.refresh();
        }
    }

    @FXML
    private void CommandeSelected(MouseEvent event) {
        commentaires.setText("");
        List<Comment> commentsList;
        if (tableCommandes.getSelectionModel().getSelectedItem() != null) {
            position = tableCommandes.getSelectionModel().getSelectedCells().get(0).getRow();
            selectedOrderForComment = dataFound.get(position);
            System.err.println("\nPosition : " + position + " : Selected\n");
            productName.setText(dataFound.get(position).getProductName());
            quantityForSell.setText(dataFound.get(position).getProduct().getQuantite() + " exemplaires disponibles");
            productPrice.setText(dataFound.get(position).getPriceProduit() + " Dinars tunisiens");
            commentsList = commentService.findByProduct(dataFound.get(position).getProduct());
            commentsList.forEach((comment) -> {
                commentaires.appendText("----------\n" + comment.getCustomer().getCustomerFirstName() + " :    \nDate : " + comment.getDateComment() + "\nSuite à l'achat de " + comment.getAbout().getDescription() + " j'ai mis ce commentaire !\n" + comment.getComment() + "\n----------\n");
            });
        }
    }

    @FXML
    private void commenter(ActionEvent event) {
        if (tableCommandes.getSelectionModel().getSelectedItem() != null) {
            alertNotSelected.visibleProperty().set(false);
            commentaires.appendText("\n" + Tools.account.getCustomerFirstName() + " :\n" + commentaire.getText());

            Date date;
            date = new Date(new java.util.Date().getTime());

            System.err.println("Objet");            
            Comment comment = new Comment(0, Tools.account, commentaire.getText(), date, selectedOrderForComment.getProduct());
            System.err.println("Objet");

            System.err.println("maj DB");
            commentService.add(comment);
            System.err.println("maj DB");

            System.err.println("suppression de texte");
            commentaire.setText("");
            System.err.println("suppression de texte");

        } else {
            alertNotSelected.visibleProperty().set(true);
        }
    }

}

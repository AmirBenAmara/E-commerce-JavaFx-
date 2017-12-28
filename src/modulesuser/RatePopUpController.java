/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesuser;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import eshop.models.ProductRate;
import eshop.services.ProduitRateService;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RatePopUpController implements Initializable {
        Connection connection;

    ProduitRateService prs =new ProduitRateService(connection);
    OverviewController ovc =new OverviewController();

        @FXML
    private Rating RateProduit;

    @FXML
    private Button ConfirmerRate;
    
       @FXML
    void onConfirmerRate( ) {
        int id_produit = new OverviewController().id_produit;
      /*  RateProduit.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
            
                

    }  
});*/
      double rate=RateProduit.getRating();
      ProductRate pr =new ProductRate(  id_produit, rate);
      prs.add(pr);
 Stage stage = (Stage) ConfirmerRate.getScene().getWindow();
    // do what you have to do
    stage.close();
    
   Image valide = new Image("/icons/valide.png");
            Notifications notificationsBuilder = Notifications.create()
                    .title("Rating")
                    .text("Your rate has been submitted")
                    .graphic(new ImageView(valide))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationsBuilder.show();
            notificationsBuilder.darkStyle();
         
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
     
    


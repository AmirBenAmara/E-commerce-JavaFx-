/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesuser;

import eshop.models.Product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author ASUS
 */
public class ProductFactory extends ListCell<Product> {

    private final GridPane gridPane = new GridPane();

    private final Label refProduit = new Label();

    private final Label descProduit = new Label();
    private final Label prixProduit = new Label();
    private final Label catProduit = new Label();
    private final ImageView imgProduit = new ImageView();
    private final AnchorPane content = new AnchorPane();
    private float economie, PrixFinal;

    public ProductFactory() {

        imgProduit.setFitWidth(150);
        imgProduit.setFitHeight(150);

        imgProduit.setPreserveRatio(true);
        GridPane.setConstraints(imgProduit, 0, 0, 1, 2);
        GridPane.setValignment(imgProduit, VPos.TOP);

        refProduit.setStyle("-fx-font-size: 1.7em;-fx-text-fill: #2c3e50;");
        GridPane.setConstraints(refProduit, 1, 0);

        descProduit.setStyle("-fx-font-size: 1.6em;");
        GridPane.setConstraints(descProduit, 1, 1);

       // catProduit.setStyle("-fx-font-size: 1.6em;");
       // GridPane.setConstraints(catProduit, 1, 2);

         prixProduit.setStyle("-fx-font-size: 1.6em;");
        GridPane.setConstraints(prixProduit, 1, 2);
        
        
        // solde.setStyle("-fx-font: bold 16 System;-fx-text-fill: #c0392b;-fx-font-size: 1.7em;");
        //GridPane.setConstraints(solde, 1, 1);
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true));
        gridPane.setHgap(6);
        gridPane.setVgap(6);
        gridPane.getChildren().setAll(imgProduit,refProduit, descProduit,  prixProduit);
        AnchorPane.setTopAnchor(gridPane, 0d);
        AnchorPane.setLeftAnchor(gridPane, 0d);
        AnchorPane.setBottomAnchor(gridPane, 0d);
        AnchorPane.setRightAnchor(gridPane, 0d);
        content.getChildren().add(gridPane);
    }

    @Override
    protected void updateItem(Product item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);

        setContentDisplay(ContentDisplay.LEFT);
        if (!empty && item != null) {
            refProduit.setText(item.getRefernce());
            descProduit.setText(item.getDescription());
            prixProduit.setText(String.valueOf(item.getPrix()));
            //catProduit.setText(item.getCategorie());

            try {
                imgProduit.setImage(new Image(new FileInputStream("D:\\wamp64\\www\\HighShop_Web\\web\\uploads\\images\\products\\" + item.getImage())));
            } catch (FileNotFoundException ex) {

            }

            setText(null);
            setGraphic(content);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}


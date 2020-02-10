package controllers.ProductosV2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Card implements Initializable {

    @FXML private Label Nombre,Disponibilidad,Precio;
    @FXML private BorderPane Container;
    @FXML private StackPane ContenedorImage;
    @FXML private ImageView Image;

    public void Edit(javafx.event.ActionEvent actionEvent) {
    }

    public void Estatus(javafx.event.ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProductController productController= new ProductController();
        Container.setPrefWidth(productController.getwidthpane());
//        ContenedorImage.setPrefWidth(Container.getHeight()-(Container.getHeight()*0.20));
//        ContenedorImage.setPrefHeight(Container.getHeight()-(Container.getHeight()*0.20));
        Nombre.setText(productController.getNombre());
        Disponibilidad.setText(productController.getCantidad());
        Precio.setText(productController.getPrecioVenta());
        fondo(productController.getPhoto());
    }

    void fondo(String foto){
        double y=ContenedorImage.getPrefHeight()*0.30;
        Image image;
        if(foto.contains("*")){
            foto=foto.replace("*","\\");
            image = new Image("file:/"+foto);
        }else{
            image = new Image("/images/producto.png");
        }
        Image.setImage(image);
        Image.setFitWidth(y);
        Image.setFitHeight(y);

    }
}
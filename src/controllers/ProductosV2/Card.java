package controllers.ProductosV2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Card implements Initializable {

    @FXML private Label Img,Nombre,Disponibilidad,Precio;
    @FXML private BorderPane Container;

    public void Edit(javafx.event.ActionEvent actionEvent) {
    }

    public void Estatus(javafx.event.ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProductController productController= new ProductController();
        Container.setPrefWidth(productController.getwidthpane()*1.5);
        System.out.println(productController.getwidthpane());
        Nombre.setText(productController.getNombre());
        Disponibilidad.setText(productController.getCantidad());
        Precio.setText(productController.getPrecioVenta());
        fondo(productController.getPhoto());
    }

    void fondo(String foto){
        Image image;
        if(foto.contains("*")){
            foto=foto.replace("*","\\");
            image = new Image("file:/"+foto);
        }else{
            image = new Image("/images/producto.png");
        }BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Img.setBackground(new Background(backgroundImage));
    }


}
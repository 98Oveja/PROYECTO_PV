package controllers.ProductosV2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CardCategorias implements Initializable {

    @FXML private ImageView Photo;
    @FXML private Label Nombre,Descripcion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ScrollPaneCateg datos = new ScrollPaneCateg();
        String path=datos.getImage();
        Image image;
        if (path.contains("*")){
            image= new Image("file:/"+path.replace("*","\\"));
        }else {image= new Image("images/categorias.jpg");}
        Photo.setImage(image);
        Nombre.setText(datos.getNombre());
        Descripcion.setText(datos.getDescripcion());
    }
}
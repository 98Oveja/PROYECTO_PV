package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public JFXButton btnViewProd;
    @FXML
    private ImageView NewImageProducts;
    @FXML
    private Label titleProduct, brandProduct, desProduct;
    public void procutosAnimation(){
        Image images = new Image("images/elClubo.jpg");
        Image images1 = new Image("images/ElCluboP.jpg");
        Image images2 = new Image("images/slide_thumb_1.png");
        Image images3 = new Image("images/post_4.png");

        Timeline timeline=new Timeline();
        KeyFrame keyFrame=new KeyFrame(Duration.seconds(0), (event)->{NewImageProducts.setImage(images);
            titleProduct.setText("EL CLUBO");
            brandProduct.setText("Banda");
            desProduct.setText("Banda de Rock Nacional Guatemala sdasoijdsa lala lalal lalal lalal");});
        KeyFrame keyFrame2=new KeyFrame(Duration.seconds(3), (event)->{NewImageProducts.setImage(images1);
            titleProduct.setText("EL Clubo png");
            brandProduct.setText("Banda Chapina");
            desProduct.setText("Banda de Rock Nacional Guatemala fundada en el anio 1996");});
        KeyFrame keyFrame3=new KeyFrame(Duration.seconds(6), (event)->{NewImageProducts.setImage(images2);
            titleProduct.setText("Slide_thumb");
            brandProduct.setText("Fotografia");
            desProduct.setText("Imagen de transicion prueba sddasjdska asd hasjhd asjdh as");});
        KeyFrame keyFrame4=new KeyFrame(Duration.seconds(9), (event)->{NewImageProducts.setImage(images3);
            titleProduct.setText("BasquetBall");
            brandProduct.setText("Deporte..");
            desProduct.setText("Aca habra una pequena descripccion de la imagen para poder ver como va todo lo del programa");});

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.getKeyFrames().add(keyFrame2);
        timeline.getKeyFrames().add(keyFrame3);
        timeline.getKeyFrames().add(keyFrame4);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        procutosAnimation();
    }


}

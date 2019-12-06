package controllers;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private ImageView NewImageProducts;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image images = new Image("images/elClubo.jpg");
        Image images1 = new Image("images/ElCluboP.jpg");
        Image images2 = new Image("images/slide_thumb_1.png");
        Image images3 = new Image("images/post_4.png");

        Timeline timeline=new Timeline();
        KeyFrame keyFrame=new KeyFrame(Duration.seconds(1), (event)->{NewImageProducts.setImage(images);});
        KeyFrame keyFrame2=new KeyFrame(Duration.seconds(2), (event)->{NewImageProducts.setImage(images1);});
        KeyFrame keyFrame3=new KeyFrame(Duration.seconds(3), (event)->{NewImageProducts.setImage(images2);});
        KeyFrame keyFrame4=new KeyFrame(Duration.seconds(4), (event)->{NewImageProducts.setImage(images3);});

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.getKeyFrames().add(keyFrame2);
        timeline.getKeyFrames().add(keyFrame3);
        timeline.getKeyFrames().add(keyFrame4);
        timeline.play();
    }

}

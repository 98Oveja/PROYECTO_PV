package Utils;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Views{
    public void show(Image img){
        final Stage primaryStage = new Stage();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initOwner(primaryStage);
        dialog.setX(400);
        dialog.setY(200);
        Scene dialogScene = null;
        ImageView im = new ImageView(img);
        im.setFitHeight(600);
        im.setFitWidth(800);
        Button bt = new Button();
        bt.setPrefHeight(30);
        bt.setPrefWidth(30);
        bt.setLayoutX(760);
        bt.setLayoutY(10);bt.setText("X");
        bt.setStyle("-fx-background-color: rgb(0,0,0,.5);"+"-fx-text-fill: #fff;-fx-font-size: 15px");
        Pane pane = new Pane(im,bt);

        bt.setOnAction(actionEvent1 -> {
            closeView c = new closeView();
            c.Cerrar(pane);
        });
        pane.setPrefHeight(600);
        pane.setPrefWidth(800);
        pane.setStyle("-fx-background-color: rgba(0,0,0,.3);");
        dialogScene = new Scene(pane );
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
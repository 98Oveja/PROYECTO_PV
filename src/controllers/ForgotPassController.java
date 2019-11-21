package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import navigator.ViewNavigator;
import utils.ViewUtil;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ForgotPassController {
    private ViewUtil view = new ViewUtil();

    @FXML
    Button btnPrev;

    public void handleButtonActionKey(KeyEvent keyEvent) {
    }

    public void handleButtonAction(MouseEvent mouseEvent) throws IOException {
        final Stage primaryStage = new Stage();
        final Stage dialog = new Stage();

        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initOwner(primaryStage);
        dialog.setX(1000);
        dialog.setY(330);
        Scene dialogScene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/ForgotPassEmail.fxml")));
        dialog.setScene(dialogScene);
        dialog.show();

    }

    @FXML
    void previousPane(ActionEvent event) {
        ViewNavigator.loadVista(ViewNavigator.LOGIN_VIEW);
    }

}

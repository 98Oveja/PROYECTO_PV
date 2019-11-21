package controllers;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ForgotPassEmailController {
    public ImageView closeButton;

    public void handleButtonAction(MouseEvent mouseEvent) {
    }

    public void handleActionClose(MouseEvent mouseEvent) {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}

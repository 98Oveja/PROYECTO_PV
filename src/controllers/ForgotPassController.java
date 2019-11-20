package controllers;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import utils.ViewUtil;

public class ForgotPassController {
    private ViewUtil view = new ViewUtil();

    public void handleButtonActionKey(KeyEvent keyEvent) {
    }

    public void handleButtonAction(MouseEvent mouseEvent) {
    }

    public void handleBackButton(MouseEvent event) {
        view.setViewMouseClick("/fxml/Login.fxml", event);
    }

    public void handleBackButtonKey(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.BACK_SPACE)){
            view.setViewKey("/fxml/Login.fxml",keyEvent);
        }
    }

}

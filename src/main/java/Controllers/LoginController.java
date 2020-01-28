package Controllers;


import Controllers.ScreenController.ScreensController;
import Controllers.item.ControllerComponent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    public StackPane vista1;
    public VBox VboxD;

    double height = Toolkit.getDefaultToolkit().getScreenSize().height;
    double width = Toolkit.getDefaultToolkit().getScreenSize().width;

    public static String screen1ID = "screen1";
    public static String screen1File = "/fxml/loginComponent/ComponentLogin.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "/fxml/ForgotPass.fxml";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            initViewLogin();
        }catch (Exception e ){}

        vista1.setPrefHeight(height);
        vista1.setPrefWidth(width);
    }

    void initViewLogin() {
        VboxD.getChildren().addAll(setContainerScreen());
    }

    private ScreensController setContainerScreen() {
        ScreensController mainContainer = new ScreensController();

        mainContainer.loadScreen(LoginController.screen1ID, LoginController.screen1File);
        mainContainer.loadScreen(LoginController.screen2ID, LoginController.screen2File);

        mainContainer.setScreen(LoginController.screen1ID);
        return mainContainer;
    }
}

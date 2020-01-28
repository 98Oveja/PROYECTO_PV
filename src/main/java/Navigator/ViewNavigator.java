package Navigator;

import Controllers.MainController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ViewNavigator {
    
    public static final String MAIN_LOGIN = "/fxml/Login.fxml";
    public static final String LOGIN_VIEW = "/fxml/LoginView.fxml";
    public static final String HOME = "/fxml/Home.fxml";

    private static MainController mainController;


    public static void setMainController(MainController mainController) {
        ViewNavigator.mainController = mainController;
    }

    public static void loadVista(String fxml) {
        try {
            mainController.setVista(
                FXMLLoader.load(
                    ViewNavigator.class.getResource(
                        fxml
                    )
                )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
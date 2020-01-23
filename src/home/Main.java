package home;

import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import navigator.ViewNavigator;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setScene(
            createScene(
                loadMainPane()
            )
        );
        //stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
            getClass().getResourceAsStream(

                    ViewNavigator.MAIN_LOGIN
            )
        );

        MainController mainController = loader.getController();

        ViewNavigator.setMainController(mainController);
        ViewNavigator.loadVista(ViewNavigator.LOGIN_VIEW);

        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
            mainPane
        );

        scene.getStylesheets().setAll(
            getClass().getResource("/css/main.css").toExternalForm()
        );
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package Ferreteria;

import Controllers.MainController;
import Utils.LoadModalesMovibles;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Navigator.ViewNavigator;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Home extends Application {
    private Pane mainPane;
    @Override
    public void start(Stage stage) throws Exception{
        mainPane = loadMainPane();
        stage.setScene(createScene(mainPane));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        Image img = new Image("/images/Proveedor.png",false);
        stageCloseAction(stage);
        stage.getIcons().add(img);
        stage.show();
    }

    private void stageCloseAction(Stage stage) {

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                windowEvent.consume();
                Image image = new Image("/images/info.png");
                LoadModalesMovibles.LoadAlert(getClass().getResource("/fxml/Alertas.fxml"),
                        "INFORMACION",
                        "Esta seguro que desea salir?",
                        image,
                        mainPane
                );
            }
        });
    }

    @Override
    public void init(){
            //Todo
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


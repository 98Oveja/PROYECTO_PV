package Controllers;

import Controllers.employees.DelEmployController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Navigator.ViewNavigator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaneSearch  implements Initializable {
    public StackPane paneSearch;
    public BorderPane imgUser;
    public TextField txtSearch;
    public MenuItem itemHelp;
    public MenuItem itemConfig;
    public MenuItem itemClose;
    public MenuItem itemCloseStage;
    int status = 0;

    public void goToURL(String URL){
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI(URL);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void handleActionHelp(ActionEvent actionEvent) {
        if (actionEvent.getSource() == itemHelp){
            if(status == 0) {
                goToURL("https://www.facebook.com/mmm.n.plo");
                status = 1;
            }
        }
    }

    private double xOffset = 0;
    private double yOffset = 0;

    public void handleActionConf(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == itemConfig){
            final Stage primaryStage = new Stage();
            final Stage dialog = new Stage();

            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.initOwner(primaryStage);
            dialog.setX(600);
            dialog.setY(300);
            Scene dialogScene = null;
            dialogScene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/ConfigUser.fxml")));
            dialogScene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });


            dialogScene.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void handleActionClose(ActionEvent actionEvent) {
        if (actionEvent.getSource() == itemClose) {
           // Stage stage = (Stage) paneSearch.getScene().getWindow();
           // stage.close();
            ViewNavigator.loadVista(ViewNavigator.LOGIN_VIEW);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImgUser("/images/index.jpeg");
    }

    private void setImgUser(String url) {
        Circle circle = new Circle(32,32,16);
        Image image = new Image(url,false);
        circle.setFill(new ImagePattern(image));
        imgUser.setCenter(circle);
    }

    public void handleActionCloseStage(ActionEvent actionEvent) {
        DelEmployController delEmployController = new DelEmployController();

        Stage stage = (Stage) paneSearch.getScene().getWindow();
        stage.close();
    }
}

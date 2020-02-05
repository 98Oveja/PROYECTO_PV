package Controllers;

import Controllers.item.ControllerComponent;

import Models.Employ.validatorImage;
import Models.User;
import Utils.LoadModalesMovibles;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import Navigator.ViewNavigator;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaneSearch  implements Initializable {
    public StackPane paneSearch;
    public BorderPane imgUser;
    public TextField txtSearch;
    public MenuItem itemHelp;
    public MenuItem itemConfig;
    public MenuItem itemClose;
    public MenuItem itemCloseStage;
    User user =  ControllerComponent.user;
    int status = 0;

    public void goToURL(String URL){
        Runnable runnable = () -> {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

                if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    try {
                        java.net.URI uri = new java.net.URI(URL);
                        desktop.browse(uri);
                    } catch (URISyntaxException | IOException ex) {

                    }
                }
            }
        };
        Thread thread = new Thread(runnable, "UPDATE-USER");
        thread.start();

    }

    public void handleActionHelp(ActionEvent actionEvent) {
        if (actionEvent.getSource() == itemHelp){
                goToURL("https://www.escodgt.com/products/help");
        }
    }


    public void handleActionConf(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == itemConfig){
            if (ControllerComponent.user != null) {
                Utils.LoadModalesMovibles.LoadModalMovible(getClass().getResource("/fxml/ConfigUser.fxml"), null);
            }else {
                ControllerComponent.admin = false;
                ViewNavigator.loadVista(ViewNavigator.LOGIN_VIEW);
            }
        }
    }

    public void handleActionClose(ActionEvent actionEvent) {
        if (actionEvent.getSource() == itemClose) {
            ControllerComponent.admin = false;
            ViewNavigator.loadVista(ViewNavigator.LOGIN_VIEW);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImgUser();
        if(ControllerComponent.admin) itemConfig.setDisable(false);
    }



    private void setImgUser() {
        Circle circle = new Circle(32,32,16);
        validatorImage valIma= new validatorImage();
        String auxUser = user.getUrlPhoto();

        if(!auxUser.isEmpty() &&  auxUser.contains("*") ){
            auxUser = "file:/" + user.getUrlPhoto().replace("*","\\");
        }else if(auxUser == null){
           auxUser = "file:/ssc/jsajd/a.png";
        }

        String trueValimg =  valIma.loadImage(auxUser,"images/index.jpeg");
        Image img = new Image(trueValimg);

        circle.setFill(new ImagePattern(img));
        imgUser.setCenter(circle);
    }

    public void handleActionCloseStage(ActionEvent actionEvent) {
        if(actionEvent.getSource() == itemCloseStage) {
            Image image = new Image("/images/info.png");
            LoadModalesMovibles.LoadAlert(getClass().getResource("/fxml/Alertas.fxml"),
                    "INFORMACION",
                    "Esta seguro que desea salir?",
                    image,
                    paneSearch
            );
        }
    }
}

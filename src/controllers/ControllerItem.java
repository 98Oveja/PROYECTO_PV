package controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerItem implements Initializable {
    public ImageView img;
    public Label statusItem;
    public HBox item;
    public Button btn;
    private String URL = "/images/home.png";

    public void setUrlImg(String url){
        this.URL = url;
    }

    public void setNameItem(ToggleButton item) { }

    HomeController homeController = new HomeController();
    ViewImage view = new ViewImage();
    String aux = homeController.item;

    private void setPropertiesItem(){
        img.setImage( new Image(view.getImageItem(aux)));
        btn.setText(aux);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPropertiesItem();
    }

    ControllerItemSelected controllerItemSelected = new ControllerItemSelected();

    public void action() {

    }

}

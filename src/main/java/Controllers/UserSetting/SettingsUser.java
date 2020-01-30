package Controllers.UserSetting;

import Controllers.item.ControllerComponent;
import Models.User;
import Navigator.ViewNavigator;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsUser implements Initializable {

    public BorderPane imgUser;
    public Label nameUser;
    public Label userC;
    public JFXButton btnViewUser;
     User user =  ControllerComponent.user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            nameUser.setText(user.getName()+" "+user.getLast_name());
            userC.setText(user.getAdmin());
            initImgUser();
    }

    private void initImgUser() {

        Circle circle = new Circle(100,100,50);
        Image image = new Image("/images/index.jpeg",false);
        circle.setFill(new ImagePattern(image));
        imgUser.setCenter(circle);
    }
}

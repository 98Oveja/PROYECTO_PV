package Controllers.UserSetting;

import Controllers.employees.viewEmployController;
import Controllers.item.ControllerComponent;
import Models.Employ.validatorImage;
import Models.User;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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

    Circle circle = new Circle(100,100,50);
    validatorImage valIma= new validatorImage();
    Image img;

    private void initImgUser() {

        String auxUser = user.getUrlPhoto();

        System.out.println(auxUser);

        if(!auxUser.isEmpty() &&  auxUser.contains("*")){
            auxUser = "file:/" + user.getUrlPhoto().replace("*","\\");
        }else {
            auxUser = "file:/ssc/jsajd/a.png";
        }

        System.out.println(auxUser);
        String trueValimg =  valIma.loadImage(auxUser,"images/index.jpeg");
        img = new Image(trueValimg);

        circle.setFill(new ImagePattern(img));
        imgUser.setCenter(circle);
    }

    public void viewUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/viewEmloy.fxml"));
        Parent root = Loader.load();
        viewEmployController viewEm = Loader.getController();
        viewEm.initData(user.getName()+" "+" ",user.getLast_name()+" "+" ",""," ",user.getAdmin(),"",img);
        Scene dialogo = new Scene(root);
        Stage stagedialog = new Stage();
        stagedialog.initStyle(StageStyle.UNDECORATED);
        stagedialog.initModality(Modality.APPLICATION_MODAL);
        stagedialog.setScene(dialogo);
        stagedialog.showAndWait();
    }

    public void saveMailServerConfuration(ActionEvent actionEvent) {
    }

    public void handleTestMailAction(ActionEvent actionEvent) {
    }
}

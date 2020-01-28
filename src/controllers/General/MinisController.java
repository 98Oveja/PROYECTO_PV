package controllers.General;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class MinisController implements Initializable {
    @FXML
    private Button mini1;

    @FXML
    private Button mini2;

    @FXML
    private Button mini3;

    @FXML
    private Button mini4;

    @FXML
    private Button mini5;

    @FXML
    private Button mini6;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mini2.setStyle("-fx-background-color: #3B86FF;");
    }

    public void cambio(javafx.event.ActionEvent actionEvent){

    }
}
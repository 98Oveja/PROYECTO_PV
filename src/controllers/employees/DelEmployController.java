package controllers.employees;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DelEmployController {
    public int BtnOk;
    @FXML
    public AnchorPane PanelDelete;
    @FXML
    public JFXButton Okay;
    @FXML
    public JFXButton Cancel;
    @FXML
    public Label TitleModal, contentAlert;
    @FXML
    public ImageView IconModal;

    @FXML
    public void pressedExit(){
        Stage stage = (Stage) PanelDelete.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void pressOK(){
        BtnOk=1;
        pressedExit();
    }
}


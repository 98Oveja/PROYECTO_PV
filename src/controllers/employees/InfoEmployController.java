package controllers.employees;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InfoEmployController {
    @FXML
    public Label label1,label2,label3,label4,label5,label6,TituloInfo;
    @FXML
    public JFXButton infoSi,infoNo;
    @FXML
    public AnchorPane PanelInfo;

    public void closeInfoModal(){
        Stage stage = (Stage) PanelInfo.getScene().getWindow();
        stage.close();
    }

    public boolean ActionHanderYes(){
        return true;
    }
}

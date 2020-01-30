package controllers.employees;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    public void loadDataClose(Image icon, String Title, String messeger, int op){
        if(op==1){
            IconModal.setImage(icon);
            TitleModal.setText(Title);
            contentAlert.setText(messeger);
        } else {
            loadDataInfo(icon,Title,messeger);
        }

    }
    public  void loadDataInfo(Image icon, String Title, String messeger){
        IconModal.setImage(icon);
        TitleModal.setText(Title);
        contentAlert.setText(messeger);
        Okay.setVisible(false);
        Cancel.setStyle("-fx-translate-x: -65px; -fx-translate-y: -10px;");
        Cancel.setText("Ok");
    }
    @FXML
    public void pressOK(){
        BtnOk=1;
        pressedExit();
    }
}


package controllers.employees;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddEmployController {
@FXML
    private AnchorPane PanelAddEmploy;
 @FXML
    private void CloseModal(){
     Stage stage = (Stage) PanelAddEmploy.getScene().getWindow();
     stage.close();
 }
}

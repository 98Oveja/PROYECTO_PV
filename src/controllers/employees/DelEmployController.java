package controllers.employees;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DelEmployController {
    @FXML
    private AnchorPane PanelDelete;
    @FXML
    private void pressedExit(){
        Stage stage = (Stage) PanelDelete.getScene().getWindow();
        stage.close();
 }
}

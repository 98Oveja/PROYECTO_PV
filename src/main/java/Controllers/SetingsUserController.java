package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SetingsUserController {
    public Button btnCerrarModal;
    public Pane pane;

    public void CloseModal(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnCerrarModal){
            Stage stage = (Stage) this.pane.getScene().getWindow();
            stage.close();
        }
    }

}

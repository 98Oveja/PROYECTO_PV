package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SetingsUserController implements Initializable {
    public Button btnCerrarModal;
    public BorderPane pane;


    public void CloseModal(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnCerrarModal){
            Stage stage = (Stage) this.pane.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

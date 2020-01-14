package utils;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class closeView {
    public void Cerrar(Pane pane) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }
}

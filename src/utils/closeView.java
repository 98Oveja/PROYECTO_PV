package utils;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class closeView {
    public void Cerrar(Pane pane) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }
}

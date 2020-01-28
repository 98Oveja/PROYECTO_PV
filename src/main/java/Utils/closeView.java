package Utils;

import javafx.scene.Node;
import javafx.stage.Stage;

public class closeView {
    public void Cerrar(Node pane) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }
}

package Controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController {

    @FXML
    private StackPane vistaHolder;

    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }

}
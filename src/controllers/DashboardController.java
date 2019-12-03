package controllers;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class DashboardController {
    private StackPane pane;

    public void setVista(Node node) {
        pane.getChildren().setAll(node);
    }

}

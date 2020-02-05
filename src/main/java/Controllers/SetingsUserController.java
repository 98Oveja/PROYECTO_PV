package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SetingsUserController implements Initializable {
    public Button btnCerrarModal;
    public BorderPane pane;
    public VBox container;


    public void CloseModal() {
            Stage stage = (Stage) this.pane.getScene().getWindow();
            stage.close();
    }

    Parent root = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pane.setPrefHeight(600);

        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/settingsUserAdmin/UserSetings.fxml"));
        } catch (IOException ignored) {
        }
        container.getChildren().addAll(root);
    }
}

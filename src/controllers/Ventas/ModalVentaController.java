package controllers.Ventas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ModalVentaController {
    @FXML
    AnchorPane panelContenedor;
    @FXML
    Button btnCerrarModal;

    public void CloseModal(ActionEvent actionEvent) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText(null);
//        alert.setTitle("Close");
//        alert.setContentText("Seguro que quieres Cerrar?");
//        alert.showAndWait();

        //MANERA EN CERRA EL MODAL
          Stage stage = (Stage) panelContenedor.getScene().getWindow();
          stage.close();



    }
}

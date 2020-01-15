package controllers.Ventas;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
public class VentasController {
    public void OpenModal_InsertSale(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ventas/ModalRegistrarVenta.fxml"));
            Parent root = loader.load();
            ModalVentaController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setY(30);
            stage.setX(330);
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

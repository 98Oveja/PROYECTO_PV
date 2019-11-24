package controllers.Ventas;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class VentasController {


    public void OpenModal_InsertSale(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ventas/PanelVentas.fxml"));
            Parent root = loader.load();
            VentasController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

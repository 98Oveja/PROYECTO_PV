package controllers.Compras;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utils.LoadModalesMovibles;
import java.net.URL;
import java.util.ResourceBundle;

public class ModalAddCompras implements Initializable {
    @FXML public JFXButton btnNuevaComprra;
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        btnNuevaComprra.setOnAction( actionEvent -> {
            LoadModalesMovibles.LoadModalMovible(getClass().getResource(
                    "/fxml/Compras/PanelCompras.fxml"),null);
        });
    }
}

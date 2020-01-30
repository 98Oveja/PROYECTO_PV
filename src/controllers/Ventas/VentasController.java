package controllers.Ventas;
import javafx.event.ActionEvent;
import utils.LoadModalesMovibles;
public class VentasController {
    public void OpenModal_InsertSale(ActionEvent actionEvent) {
        LoadModalesMovibles.LoadModalMovible(getClass().getResource(
                "/fxml/Ventas/ModalVentas.fxml"),null);
    }

}

package controllers.Ventas;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import models.Ventas_Compras.Ventas;
import utils.LoadModalesMovibles;

import java.net.URL;
import java.util.ResourceBundle;
public class EditProductoController implements Initializable {
    Ventas ventas = new Ventas();
    public StackPane ContendorEdit;
    public JFXTextField newCantidad;
    public JFXTextField newDescuento;
    public JFXButton saveButton;
    public JFXButton cancelButton;
    public Button btnCerraModal;
    public Label productolabel;

    public void setNuevaCantidad(String cantidad){this.newCantidad.setText(cantidad);}
    public void setNuevoDescuento(String descuento){this.newDescuento.setText(descuento);}
    public void setNombreProducto(String producto){this.productolabel.setText(producto);}
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ventas.validarSoloNumerosJfoenix(newCantidad);
        ventas.validarSoloNumerosJfoenix(newDescuento);
        cancelButton.setOnAction(actionEvent -> {LoadModalesMovibles.CerrarModal(ContendorEdit);});
        btnCerraModal.setOnAction(actionEvent -> {LoadModalesMovibles.CerrarModal(ContendorEdit);});

    }
}

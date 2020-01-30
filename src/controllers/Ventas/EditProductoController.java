package controllers.Ventas;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import models.Ventas_Compras.Ventas;
import utils.ConsultasVentasCompras;
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
    public String PRECIOPROD;
    Object productoSeleccionado;
    public TableView tablaAuxiliarUpdate;
    public ObservableList<Ventas> VentasListaAuxUpdate;
    public void setNuevaCantidad(String cantidad){this.newCantidad.setText(cantidad);}
    public void setNuevoDescuento(String descuento){this.newDescuento.setText(descuento);}
    public void setNombreProducto(String producto){this.productolabel.setText(producto);}
    public void setPRECIOPROD(double precio){this.PRECIOPROD = String.valueOf(precio);}
    public String getPRECIOPROD(){return this.PRECIOPROD;}
    public String getNuevaCantidad(){return this.newCantidad.getText();}
    public String getNuevoDescuento(){return this.newDescuento.getText();}
    public void setProductoSeleccionado(Object thisProducto){this.productoSeleccionado = thisProducto;}
    public Object getProductoSeleccionado(){return this.productoSeleccionado;}
    public TableView getTablaAuxiliarUpdate() {return tablaAuxiliarUpdate;}
    public void setTablaAuxiliarUpdate(TableView tablaAuxiliarUpdate) {this.tablaAuxiliarUpdate = tablaAuxiliarUpdate; }
    public ObservableList<Ventas> getVentasListaAuxUpdate() {return VentasListaAuxUpdate;}
    public void setVentasListaAuxUpdate(ObservableList<Ventas> ventasListaAuxUpdate){VentasListaAuxUpdate = ventasListaAuxUpdate;}



    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ventas.validarSoloNumerosJfoenix(newCantidad);
        ventas.validarSoloNumerosJfoenix(newDescuento);
//      ACCIONES DE LOS BOTONES DENTRO DEL MODAL
        cancelButton.setOnAction(actionEvent -> {LoadModalesMovibles.CerrarModal(ContendorEdit);});
        btnCerraModal.setOnAction(actionEvent -> {LoadModalesMovibles.CerrarModal(ContendorEdit);});
        saveButton.setOnAction(actionEvent -> {
//            ConsultasVentasCompras consulta =
//                    new ConsultasVentasCompras();
//            for (int i = 0; i <consulta.listaProductos().size();i++) {
//                System.out.println(consulta.listaProductos().get(i));
//
//            }
            Ventas VentasAux = (Ventas) getProductoSeleccionado();
            String subTotalNuevo = ventas.calculoDeDescuentos(getPRECIOPROD(),getNuevaCantidad(),getNuevoDescuento());
            VentasAux.setDescuento(Double.parseDouble(getNuevoDescuento()));
            VentasAux.setCantidad(Integer.parseInt(getNuevaCantidad()));
            VentasAux.setSubTotal(Double.parseDouble(subTotalNuevo));
//            this.VentasListaAuxUpdate.set(VentasAux.getNumero()-1,VentasAux);
            getVentasListaAuxUpdate().set((VentasAux.getNumero()-1),VentasAux);
            System.out.println("El tamanio del Array es de "+getVentasListaAuxUpdate().size());
            getTablaAuxiliarUpdate().refresh();
        });

    }
}

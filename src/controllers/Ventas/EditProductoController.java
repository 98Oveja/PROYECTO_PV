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
import utils.LoadModalesMovibles;
import utils.ValidacionesGenerales;

import java.net.URL;
import java.util.ResourceBundle;
public class EditProductoController implements Initializable {
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
    Object modalVentas;
    public void setModalVentas(Object objeto){this.modalVentas = objeto;}
    public Object getModalVentas(){return this.modalVentas;}

    public void setNuevaCantidad(String cantidad){this.newCantidad.setText(cantidad);}
    public void setNombreProducto(String producto){this.productolabel.setText(producto);}
    public void setPRECIOPROD(double precio){this.PRECIOPROD = String.valueOf(precio);}
    public String getPRECIOPROD(){return this.PRECIOPROD;}
    public String getNuevaCantidad(){return this.newCantidad.getText();}
    public void setProductoSeleccionado(Object thisProducto){this.productoSeleccionado = thisProducto;}
    public Object getProductoSeleccionado(){return this.productoSeleccionado;}
    public TableView getTablaAuxiliarUpdate() {return tablaAuxiliarUpdate;}
    public void setTablaAuxiliarUpdate(TableView tablaAuxiliarUpdate) {this.tablaAuxiliarUpdate = tablaAuxiliarUpdate; }
    public ObservableList<Ventas> getVentasListaAuxUpdate() {return VentasListaAuxUpdate;}
    public void setVentasListaAuxUpdate(ObservableList<Ventas> ventasListaAuxUpdate){VentasListaAuxUpdate = ventasListaAuxUpdate;}
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        this.newDescuento.setVisible(false);
        ValidacionesGenerales.validarSoloNumerosJfoenix(newCantidad);
        ValidacionesGenerales.validarSoloNumerosJfoenix(newDescuento);
//      ACCIONES DE LOS BOTONES DENTRO DEL MODAL
        cancelButton.setOnAction(actionEvent -> {LoadModalesMovibles.CerrarModal(ContendorEdit);});
        btnCerraModal.setOnAction(actionEvent -> {LoadModalesMovibles.CerrarModal(ContendorEdit);});
        saveButton.setOnAction(actionEvent -> {
            try{
                Ventas VentasAux = (Ventas) getProductoSeleccionado();
                String precio = getPRECIOPROD();
                String nuevaCantidad = getNuevaCantidad();
                double nuevosubtotal = Double.parseDouble(precio)* Double.parseDouble(nuevaCantidad);
                String nuevoTotalstr = String.valueOf(nuevosubtotal);
                VentasAux.setCantidad(Integer.parseInt(getNuevaCantidad()));
                VentasAux.setSubTotal(Double.parseDouble(nuevoTotalstr));
                getVentasListaAuxUpdate().set((VentasAux.getNumero()-1),VentasAux);
                int nuevoContador = 1;
                double elNuevoTotal =0;
                for (Ventas actualizaID: getVentasListaAuxUpdate()) {
                    actualizaID.setNumero(nuevoContador);
                    elNuevoTotal += actualizaID.getSubTotal();
                    getTablaAuxiliarUpdate().refresh();nuevoContador++;
                }
                ModalVentas modalVentas = (ModalVentas) getModalVentas();
                modalVentas.setNuevoTotal(elNuevoTotal);

                LoadModalesMovibles.CerrarModal(ContendorEdit);
            }catch (Exception e){
                System.out.println("Actualizacion no se completo "+e.getMessage()+" "+e.getLocalizedMessage());
            }
        });
    }
}

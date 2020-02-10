package models.Ventas_Compras;
import com.jfoenix.controls.JFXButton;
import controllers.Ventas.EditProductoController;
import controllers.Ventas.ModalVentas;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import utils.ConnectionUtil;
import utils.LoadModalesMovibles;
import java.sql.*;
import java.util.Objects;
public class Ventas{
    Object modalVentas;
    public void setModalVentas(Object objeto){this.modalVentas = objeto;}
    public Object getModalVentas(){return this.modalVentas;}
    private String NOMBREPRODUCTO;
    public void setNOMBREPRODUCTO(String elProducto){this.NOMBREPRODUCTO = elProducto;}
    public String getNOMBREPRODUCTO(){return this.NOMBREPRODUCTO;}
    public ConnectionUtil cone = new ConnectionUtil();
    public Connection connection = cone.getConnection();
    public TableView tableViewAux = new TableView<>();
    public ObservableList<Ventas> ventasObservableListAux;
    private JFXButton Editar;
    private JFXButton Eliminar;
    private int Numero;
    private int Cantidad;
    private String CodigoProducto;
    private String Producto;
    private double PrecioVenta;
    private double SubTotal;
    public Ventas(){}
    public Ventas(int numero, int cantidad,String codigoProducto, String producto,
                  double precioVenta,double subTotal, JFXButton edit, JFXButton del) {
    Numero = numero;
    Cantidad = cantidad;
    CodigoProducto = codigoProducto;
    Producto = producto;
    PrecioVenta = precioVenta;
    SubTotal = subTotal;
    Editar = edit;
    Eliminar = del;
    Editar.setOnAction(actionEvent -> {
        try{
            EditProductoController
            editProductoController = (EditProductoController)
                LoadModalesMovibles.LoadModalMovible(getClass().getResource("/fxml/Ventas/EditProducto.fxml"),null);
            editProductoController.setNuevaCantidad(String.valueOf(getCantidad()));
            editProductoController.setPRECIOPROD(getPrecioVenta());
            editProductoController.setNombreProducto(getNOMBREPRODUCTO());
            editProductoController.setProductoSeleccionado(this);
            editProductoController.setVentasListaAuxUpdate(this.ventasObservableListAux);
            editProductoController.setTablaAuxiliarUpdate(this.tableViewAux);
            editProductoController.setModalVentas(getModalVentas());
        }catch (Exception e){System.out.println("Accion del Btn Editar "+e.getMessage());}
    });
    Eliminar.setOnAction(actionEvent -> {
        try {
            int nuevoContador = 1;
            double elNuevoTotal = 0;
            Ventas prodseleccionado = this;
            ventasObservableListAux.remove(prodseleccionado);
            for (Ventas actualizar:ventasObservableListAux) {
                actualizar.setNumero(nuevoContador);
                elNuevoTotal += actualizar.getSubTotal();
                tableViewAux.refresh();nuevoContador++;}
            ModalVentas modalVentas = (ModalVentas) getModalVentas();
            modalVentas.setNuevoTotal(elNuevoTotal);
        }catch (Exception e){
            System.out.println("No se puede Borrar por: "+e.getMessage());
        }
    });
    }
//  GETTERS
    public int getNumero() {return Numero;}
    public String getCodigoProducto(){return CodigoProducto;}
    public int getCantidad(){return Cantidad; }
    public String getProducto() {return Producto;}
    public double getPrecioVenta(){return PrecioVenta;}
    public double getSubTotal() {return SubTotal; }
    public JFXButton getEliminar() { return Eliminar; }
    public JFXButton getEditar() { return Editar; }
//    SETTERS
    public void setNumero(int numero) {Numero = numero;}
    public void setCantidad(int cantidad) {Cantidad = cantidad;}
    public void setProducto(String producto) {Producto = producto;}
    public void setSubTotal(double subTotal){SubTotal = subTotal;}
    public void setEliminar(JFXButton eliminar){Eliminar = eliminar;}
    public void setTableViewAux(TableView tableView){tableViewAux=tableView;}
    public void setVentasObservableListAux(ObservableList observableList){ventasObservableListAux=observableList;}
//  EQUALS
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ventas)) return false;
        Ventas ventas = (Ventas) o;
        return getCodigoProducto().equals(ventas.getCodigoProducto()) &&
                getProducto().equals(ventas.getProducto());
    }
    @Override public int hashCode() {return Objects.hash(getCodigoProducto(), getProducto());}
}
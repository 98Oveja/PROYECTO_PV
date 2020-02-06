package controllers.Compras;
import com.jfoenix.controls.*;
import controllers.Ventas.BusquedaProductoControllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import utils.ConsultasVentasCompras;
import utils.LoadModalesMovibles;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class PanelCompras implements Initializable {
    @FXML public BorderPane PanelContendorPrincipal;
    @FXML public Button btnCerrarModal;
    @FXML public JFXComboBox <String>comboboxProveedor;
    @FXML public JFXTextField direccionField;
    @FXML public JFXTextField telefonoField;
    @FXML public JFXTextField noCuentaField;
    @FXML public JFXTextField orgField;
    @FXML public JFXDatePicker fechaChoser;
    @FXML public JFXButton brnAddproveedor;
    @FXML public ImageView buscar;
    @FXML public JFXButton btnAddproducto;
    @FXML public  JFXTextField proveedorField;
    @FXML public JFXCheckBox autoRellenarCheck;

    @FXML public JFXTextField descripcionField;
    @FXML public JFXComboBox <String>comboboxProductos;
    @FXML public JFXTextField precioCompraField;

    @FXML public JFXTextField precioVentaField;
    @FXML public JFXTextField disponibilidadField;
    @FXML public JFXTextField cantidadField;
    @FXML public JFXTextField codigoField;
    @FXML public JFXTextField marcaField;

    public String nombrePro, apellidoPro;
    public String Datos_de_las_Querys;
    public ArrayList<String> result_querys;
//GET AND SET
    public void setDescripcionField(String descripcionField) {this.descripcionField.setText(descripcionField);}
    public void setComboboxProductos(String comboboxProductos){this.comboboxProductos.setValue(comboboxProductos);}
    public void setPrecioCompraField(String precioCompraField) {this.precioCompraField.setText(precioCompraField); }
    public void setPrecioVentaField(String precioVentaField) {this.precioVentaField.setText(precioVentaField);}
    public void setDisponibilidadField(String disponibilidadField){this.disponibilidadField.setText(disponibilidadField);}
    public void setCantidadField(String cantidadField) {this.cantidadField.setText(cantidadField);}
    public void setCodigoField(String codigoField) {this.codigoField.setText(codigoField);}
    public void setMarcaField(String marcaField) {this.marcaField.setText(marcaField);}

    public void mostrarFecha(){
        LocalDate localDate = LocalDate.now();
     fechaChoser.setValue(localDate);
    }
    public void cargarProductos(){
        ArrayList<String> arrayProductos = ConsultasVentasCompras.listaProductos();
        ObservableList<String> itemsProd = FXCollections.observableArrayList();
        itemsProd.addAll(arrayProductos);
        comboboxProductos.setItems(itemsProd);
        comboboxProductos.setVisibleRowCount(3);
    }
    public void cargarProveedores(){
        ArrayList<String> arrayListProveedores = ConsultasVentasCompras.listaProveedores();

        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(arrayListProveedores);
        comboboxProveedor.setItems(items);
        comboboxProveedor.setVisibleRowCount(3);
    }
    public void autoRellenarCampos(){
        this.proveedorField.setText("No Especificado");
        this.direccionField.setText("Ciudad");
        this.telefonoField.setText("00000000");
        this.noCuentaField.setText("00000000");
        this.orgField.setText("Desconocida");
    }
    public void vaciarautoRellenarCampos(){
        this.proveedorField.setText("");
        this.direccionField.setText("");
        this.telefonoField.setText("");
        this.noCuentaField.setText("");
        this.orgField.setText("");
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle){
// BOTONES PROPIOS DEL MODAL DE COMPRAS
        btnCerrarModal.setOnAction(actionEvent -> {
            LoadModalesMovibles.CerrarModal(PanelContendorPrincipal);
        });
        comboboxProveedor.setOnAction(actionEvent -> {
            String NombreApellido = comboboxProveedor.getValue();
            String[] separador = NombreApellido.split(" ");
            nombrePro = separador[0];
            apellidoPro =  separador[1];
            result_querys =ConsultasVentasCompras.getDataProveedoreByName(nombrePro,apellidoPro);
            Datos_de_las_Querys = result_querys.get(0);
            String[] getAllDataProveedor = Datos_de_las_Querys.split("#");
            proveedorField.setText(nombrePro+" "+apellidoPro);
            direccionField.setText(getAllDataProveedor[0]);
            telefonoField.setText(getAllDataProveedor[1]);
            noCuentaField.setText(getAllDataProveedor[2]);
            orgField.setText(getAllDataProveedor[3]);
        });
        comboboxProductos.setOnAction(actionEvent -> {
            Datos_de_las_Querys = comboboxProductos.getValue();
            result_querys= ConsultasVentasCompras.seleccionarProductoParaComprar(Datos_de_las_Querys);
            String consultaCompleta = result_querys.get(0);
            String[] contenedorConsultaProducto = consultaCompleta.split("#");
            String maraca = contenedorConsultaProducto[0];
            String la_marca = ConsultasVentasCompras.getNameMarcabyID(maraca);
            marcaField.setText(la_marca);
            descripcionField.setText(contenedorConsultaProducto[1]);
            codigoField.setText(contenedorConsultaProducto[2]);
            disponibilidadField.setText(contenedorConsultaProducto[3]);
            String unodad = contenedorConsultaProducto[4];
            precioVentaField.setText(contenedorConsultaProducto[5]);
            precioCompraField.setText(contenedorConsultaProducto[6]);
            System.out.println(unodad);
            cantidadField.requestFocus();




        });
        autoRellenarCheck.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(autoRellenarCheck.isSelected()){
                autoRellenarCampos();
            } else{
                vaciarautoRellenarCampos();
            }
        });
        buscar.setOnMouseClicked(mouseEvent -> {
            BusquedaProductoControllers busquedas =
                    (BusquedaProductoControllers) LoadModalesMovibles.LoadModalMovible(getClass().getResource("/fxml/Ventas/BusquedaProductos.fxml"),
                            null);
            busquedas.setControllerModalCompras(this);
        });

//  CARGA DE METODOS PROPIOS
        mostrarFecha();
        cargarProductos();
        cargarProveedores();
    }
}

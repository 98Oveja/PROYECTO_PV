package controllers.Ventas;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXProgressBar;
import controllers.AlertaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Ventas_Compras.Ventas;
import utils.LoadModalesMovibles;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class ModalVentas implements Initializable{
    @FXML public Button btnCerrarModal;
    @FXML public Button addNewCustomer;
    @FXML public JFXButton btn_agregarVenta;
    @FXML public JFXButton btn_venderTodo;

    @FXML public TextField direccion_text;
    @FXML public TextField telefono_text;
    @FXML public TextField nit_text;
    @FXML public TextField descripcion_text;
    @FXML public TextField precio_text;
    @FXML public TextField cantidad_text;
    @FXML public TextField disponibilidad_text;
    @FXML public TextField descuento_text;

    @FXML public Label MensajedeAlertaCampos;
    @FXML public JFXProgressBar barradeProgresoAlerta;
    @FXML public JFXDatePicker fecha_text;
    @FXML public BorderPane borderPVentas;
    @FXML public JFXComboBox<String> listadoClientes;
    @FXML public JFXComboBox<String> listadoProductos;

    @FXML public TableView<Ventas> tablaDetalle;
    @FXML public TableColumn colNumero;
    @FXML public TableColumn colCantidad;
    @FXML public TableColumn colCodigo;
    @FXML public TableColumn colProducto;
    @FXML public TableColumn colPrecio;
    @FXML public TableColumn colDescuento;
    @FXML public TableColumn colSubtotal;
    @FXML public TableColumn colEditar;
    @FXML public TableColumn colEliminar;
    Ventas ventas = new Ventas();
    Ventas ventas1,ventasAuxiliar;
    private ObservableList<Ventas> ventasObservableList;
    public URL urlEditar, urlEliminar;
    public Image imgEditar, imgEliminar;
    public int cuantoCamposVacios;
    public String search_id;
    public ArrayList result_querys;
    public String Datos_de_las_Querys;
    public String CODIGOPRODUCTO ="";
    public int numeros = 0;
    public void cargarClientes(){
        ArrayList<String> arrayList = ventas.listadoClientes();
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(arrayList);
        listadoClientes.setItems(items);
        listadoClientes.setVisibleRowCount(3);
    }
    public void cargarProductos(){
        ArrayList<String> arrayProductos = ventas.listaProductos();
        ObservableList<String> itemsProd = FXCollections.observableArrayList();
        itemsProd.addAll(arrayProductos);
        listadoProductos.setItems(itemsProd);
        listadoProductos.setVisibleRowCount(3);
    }
    public void mostrarFecha(){
        LocalDate localDate = LocalDate.now();
        fecha_text.setValue(localDate);
    }
    public int todolosCamposVacios(){
        cuantoCamposVacios = 0;
        if (ventas.camposVacios(direccion_text)) {
            cuantoCamposVacios += 1;
        }
        if (ventas.camposVacios(telefono_text)) {
            cuantoCamposVacios += 1;
        }
        if (ventas.camposVacios(nit_text)) {
            cuantoCamposVacios += 1;
        }
        if (ventas.camposVacios(cantidad_text)) {
            cuantoCamposVacios += 1;
        }
        if (ventas.camposVacios(descuento_text)) {
            cuantoCamposVacios += 1;
        }
        return cuantoCamposVacios;
    }
    public void modeloAsignacionEnTabla(){
        ventasObservableList = FXCollections.observableArrayList();
        this.colNumero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        this.colCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        this.colCodigo.setCellValueFactory(new PropertyValueFactory<>("CodigoProducto"));
        this.colProducto.setCellValueFactory(new PropertyValueFactory<>("Producto"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory<>("PrecioVenta"));
        this.colDescuento.setCellValueFactory(new PropertyValueFactory<>("Descuento"));
        this.colSubtotal.setCellValueFactory(new PropertyValueFactory<>("SubTotal"));
        this.colEditar.setCellValueFactory(new PropertyValueFactory<>("Editar"));
        this.colEliminar.setCellValueFactory(new PropertyValueFactory<>("Eliminar"));
    }
    public void activarNodos(){
        MensajedeAlertaCampos.setVisible(true);
        barradeProgresoAlerta.setVisible(true);
        barradeProgresoAlerta.setStyle("-fx-pref-height:3px;");
        MensajedeAlertaCampos.setText("Verifica los Campos");
    }
    public void desactivarNodos(){
        MensajedeAlertaCampos.setVisible(false);
        barradeProgresoAlerta.setVisible(false);
        addNewCustomer.setDisable(true);
        listadoClientes.setDisable(true);
        telefono_text.setDisable(true);
        direccion_text.setDisable(true);
        nit_text.setDisable(true);
        fecha_text.setDisable(true);
    }
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
//      ACCIONES DE LOS BOTONES Y OTROS COMPONENTES DENTRO DEL MODAL
        tablaDetalle.setOnMouseClicked(mouseEvent -> {
            Ventas vp = tablaDetalle.getSelectionModel().getSelectedItem();
            if (vp != null){
                System.out.println("Producto "+vp.getProducto());
                System.out.println("Precio "+vp.getPrecioVenta());
                System.out.println("Cantidad "+vp.getCantidad());
                System.out.println("Codigo "+vp.getCodigoProducto());
                System.out.println("SubTotal "+vp.getSubTotal());
                System.out.println("Descuento "+vp.getDescuento());
            }
        });
        btnCerrarModal.setOnAction(actionEvent -> {
            Image imageModal = new Image("/images/info.png");
            Image imageClose = new Image("/images/icon_close.png");
            LoadModalesMovibles.LoadAlert(getClass().getResource("/fxml/Alertas.fxml"),
                    "Cerra el Panel",
                    "¿Estas seguro de cerra el Panel?",
                    imageModal,
                    imageClose,
                    borderPVentas);
        });
        addNewCustomer.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Empleados/AddEmployees.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn_agregarVenta.setOnAction(actionEvent -> {
            if (todolosCamposVacios()<5){activarNodos();}
            else {
                desactivarNodos();
                urlEditar = getClass().getResource("/images/edit.png");
                urlEliminar = getClass().getResource("/images/icons8_delete_50px_1.png");
                imgEditar = new Image(urlEditar.toString(),16,16,false,true);
                imgEliminar = new Image(urlEliminar.toString(),16,16,false,true);
                JFXButton editJfxButton = new JFXButton();
                JFXButton deletJfxButton = new JFXButton();
                editJfxButton.setGraphic(new ImageView(imgEditar));
                deletJfxButton.setGraphic(new ImageView(imgEliminar));
                double subTotal = Double.parseDouble(ventas.calculoDeDescuentos(precio_text.getText(), cantidad_text.getText(), descuento_text.getText()));
                ventas1 = new Ventas(
                        (numeros+1),
                        Integer.parseInt(cantidad_text.getText()),
                        CODIGOPRODUCTO,
                        listadoProductos.getValue()+" "+descripcion_text.getText(),
                        Double.parseDouble(precio_text.getText()),
                        Double.parseDouble(descuento_text.getText()),
                        subTotal,
                        editJfxButton,
                        deletJfxButton);
                    ventas1.setNOMBREPRODUCTO(listadoProductos.getValue());
                if(!ventasObservableList.contains(ventas1)){
                    this.ventasObservableList.add(ventas1);
                    this.tablaDetalle.setItems(ventasObservableList);
                    ventas1.setVentasObservableListAux(ventasObservableList);
                    ventas1.setTableViewAux(this.tablaDetalle);
                    numeros++;  ventasAuxiliar = ventas1;
                } else{
                    Ventas aux2 = ventas1;
                    System.out.print("CANTIDAD Y DESCUETO DEL PRODUCTO QUE ESTA EN LA TABLA"+
                            "\nCantidad "+ventasAuxiliar.getCantidad());
                    System.out.print(" Descuento "+ventasAuxiliar.getDescuento()+"\n");
                    System.out.print("CANTIDAD Y DESCUENTO DEL PRODUCTO QUE ESTAN EN LOS LABELS"+
                            "\nCantidad "+aux2.getCantidad());
                    System.out.println(" Descuento "+aux2.getDescuento());
                    ventas1.setCantidad(aux2.getCantidad());
                    ventas1.setDescuento(aux2.getDescuento());
                    tablaDetalle.refresh();
                }
                descuento_text.setText("0");
            }
            if(ventasObservableList.size() == 0){
                System.out.println("Aun no se envia nadad a la tabla y al Modelo");
            }

        });
        listadoClientes.setOnAction(actionEvent -> {
         String Nombre_Apellido_Cliente = listadoClientes.getValue();
            String[] SeparadaCadena = Nombre_Apellido_Cliente.split(" ");
            String nombreCliente = SeparadaCadena[0];
            String apelliCliente = SeparadaCadena[1];
            search_id = ventas.getIdCostumerInDB(nombreCliente,apelliCliente);
            result_querys = ventas.getCustomerDatabyId(search_id);
            Datos_de_las_Querys = result_querys.get(0).toString();
            String[] getAllDataCustomer = Datos_de_las_Querys.split("#");
            telefono_text.setText(getAllDataCustomer[0]);
            direccion_text.setText(getAllDataCustomer[1]);
            nit_text.setText(getAllDataCustomer[2]);
        });
        listadoProductos.setOnAction(actionEvent -> {
            Datos_de_las_Querys = listadoProductos.getValue();
            result_querys= ventas.getProductByName(Datos_de_las_Querys);
            String consultaCompleta = result_querys.get(0).toString();
            String[] contenedorConsultaProducto = consultaCompleta.split("#");
            disponibilidad_text.setText(contenedorConsultaProducto[0]);
            precio_text.setText(contenedorConsultaProducto[1]);
            CODIGOPRODUCTO = contenedorConsultaProducto[2];
            descripcion_text.setText(contenedorConsultaProducto[3]);
            cantidad_text.requestFocus();
        });
//      ASIGNACION DE VALORES INICIALES
        MensajedeAlertaCampos.setText("");
        MensajedeAlertaCampos.setVisible(false);
        barradeProgresoAlerta.setVisible(false);
        descripcion_text.setEditable(false);
        precio_text.setEditable(false);
        disponibilidad_text.setEditable(false);
        descuento_text.setText("0");
//      VALIDACIONES EXTERNAS
        ventas.validarNumTelefono(telefono_text,8);
        ventas.validarNit(nit_text);
        ventas.validarSoloNumeros(cantidad_text);
        ventas.validarSoloNumeros(descuento_text);
//      CARGANDO LOS METODOS PROPIOS
        mostrarFecha();
        cargarClientes();
        cargarProductos();
        modeloAsignacionEnTabla();
    }
}

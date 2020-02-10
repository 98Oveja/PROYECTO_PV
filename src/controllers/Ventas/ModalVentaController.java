package controllers.Ventas;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Ventas_Compras.Ventas;

import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;

public class ModalVentaController implements Initializable {
    @FXML
    private TableView<Ventas> TablaDetalleVenta;
    @FXML
    private TableColumn Col_Cantidad;
    @FXML
    private TableColumn Col_Codigo;
    @FXML
    private TableColumn Col_Producto;
    @FXML
    private TableColumn Col_PrecioUnitario;
    @FXML
    private TableColumn Col_Descuento;
    @FXML
    private TableColumn <Ventas, JFXButton>Col_Opciones;
    @FXML
    private TableColumn Col_SubTotal;


    public Label MensajeAlerta;
    @FXML
    AnchorPane panelContenedor;
    @FXML
    Button btnCerrarModal;
    @FXML
    private JFXTextField cliente_text;
    @FXML
    private JFXComboBox<String> listadoClietes;
    @FXML
    private JFXTextField nit_txt;
    @FXML
    private JFXTextField telefono_txt;
    @FXML
    private JFXTextField direccion_txt;
    @FXML
    private JFXTextField producto_text;
    @FXML
    private JFXComboBox<String> listadoProductos;
    @FXML
    private JFXTextField cantidad_text;
    @FXML
    private JFXTextField descripcion_text;
    @FXML
    private JFXTextField descuento_text;
    @FXML
    private Button btnVenderTodo;
    @FXML
    public JFXTextField txt_fechaVenta;
    @FXML
    private TextField total_txt;
    Ventas ventas = new Ventas();
    ArrayList datosModalVentas = new ArrayList<String>();
    ObservableList<Ventas> ModeloTablaVentas;
    double subtotalCalculado = 0;
    String CODIGOPRODUCTO = "";
    String PRECIOVENTA = "";
    String EXITENCIAPRODUCTO = "";


    public void mensajeDeAlertaLabel(){
        MensajeAlerta.setVisible(true);
        MensajeAlerta.setText("Por favor llena los campos");
        MensajeAlerta.setStyle("-fx-text-fill: red;");
    }

    public void CloseModal(ActionEvent actionEvent) {
        //MANERA EN CERRA EL MODAL
        Stage stage = (Stage) panelContenedor.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Cerra la pantall de Venta");
        alert.setContentText("Seguro que quieres Cerrar?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){stage.close();
        }else{alert.close();}


    }
    public void GuardarVentaEnDB(ActionEvent actionEvent) {
    }
/*    mostrar la fecha y la hora en un label*/
    public void mostrarFecha(){
        Date date = new Date();
        long miliSec = date.getTime();
        java.sql.Date diaSql = new java.sql.Date(miliSec);
//        java.sql.Time timeSql = new java.sql.Time(miliSec);
//        java.sql.Timestamp tiempoCom = new java.sql.Timestamp(miliSec);
//        System.out.println("Dia normal: "+date);
//        System.out.println("Dia sql: "+diaSql);
//        System.out.println("Tiempo sql: "+timeSql);
//        System.out.println("Tiempo Completo: "+tiempoCom);
        txt_fechaVenta.setText(diaSql.toString());
    }
    public void cargarClientes(){
        ArrayList<String> arrayList = ventas.listadoClientes();
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(arrayList);
        listadoClietes.setItems(items);
        listadoClietes.setVisibleRowCount(3);
    }
    public void cargarProductos(){
        ArrayList<String> arrayProductos = ventas.listaProductos();
        ObservableList<String> itemsProd = FXCollections.observableArrayList();
        itemsProd.addAll(arrayProductos);
        listadoProductos.setItems(itemsProd);
        listadoProductos.setVisibleRowCount(3);
    }

    public boolean validarCampos(String campo){ return campo.length()!=0?true:false; }
    public void verificarTodoLosInputs(){
//        boolean vacio = true;
        int camposLlenos = 0;
        if (validarCampos(cliente_text.getText())==false){
            cliente_text.promptTextProperty().setValue("INGRESA O SELECCIONA UN NOMBRE");
            cliente_text.setStyle("-fx-prompt-text-fill: rgba(205, 121, 192, 0.88)");
            mensajeDeAlertaLabel();
        }else{camposLlenos +=1;}
        if (validarCampos(nit_txt.getText())==false){
            nit_txt.promptTextProperty().setValue("C/F");
            nit_txt.setStyle("-fx-prompt-text-fill: rgba(205, 121, 192, 0.88)");
            mensajeDeAlertaLabel();
        }else{camposLlenos +=1;}
        if (validarCampos(telefono_txt.getText())==false){
            telefono_txt.promptTextProperty().setValue("No Phone");
            telefono_txt.setStyle("-fx-prompt-text-fill: rgba(205, 121, 192, 0.88)");
            mensajeDeAlertaLabel();
        }else{camposLlenos +=1;}
        if (validarCampos(direccion_txt.getText())==false){
            direccion_txt.promptTextProperty().setValue("Ciudad");
            direccion_txt.setStyle("-fx-prompt-text-fill: rgba(205, 121, 192, 0.88)");
            mensajeDeAlertaLabel();
        }else{camposLlenos +=1;}
        if (validarCampos(producto_text.getText())==false){
            producto_text.promptTextProperty().setValue("SELECCIONA UN PRODUCTO");
            producto_text.setStyle("-fx-prompt-text-fill: rgba(205, 121, 192, 0.88)");
            mensajeDeAlertaLabel();
        }else{camposLlenos +=1;}
        if (validarCampos(cantidad_text.getText())==false){
            cantidad_text.promptTextProperty().setValue("1");
            cantidad_text.setStyle("-fx-prompt-text-fill: rgba(205, 121, 192, 0.88)");
            mensajeDeAlertaLabel();
        }else{camposLlenos +=1;}
        if (validarCampos(descripcion_text.getText())==false){
            descripcion_text.promptTextProperty().setValue("Descripcion del Producto");
            descripcion_text.setStyle("-fx-prompt-text-fill: rgba(205, 121, 192, 0.88)");
            mensajeDeAlertaLabel();
        }else{camposLlenos +=1;}
        if (validarCampos(descuento_text.getText())==false){
            descuento_text.promptTextProperty().setValue("00");
            descuento_text.setStyle("-fx-prompt-text-fill: rgba(205, 121, 192, 0.88)");
            mensajeDeAlertaLabel();
        }else{
            camposLlenos +=1;
            Double descuento_dbl = Double.parseDouble(descuento_text.getText());

        }
        System.out.println("Campos llenos "+ camposLlenos);
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ModeloTablaVentas = FXCollections.observableArrayList();
        this.Col_Cantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.Col_Codigo.setCellValueFactory(new PropertyValueFactory("codigoProducto"));
        this.Col_Producto.setCellValueFactory(new PropertyValueFactory("producoComprado"));
        this.Col_PrecioUnitario.setCellValueFactory(new PropertyValueFactory("precioUnitario"));
        this.Col_Descuento.setCellValueFactory(new PropertyValueFactory("descuento"));
        Callback<TableColumn<Ventas, JFXButton>,TableCell<Ventas,JFXButton>> cellCallback =
                new Callback<TableColumn<Ventas, JFXButton>, TableCell<Ventas, JFXButton>>() {
                    @Override
                    public TableCell<Ventas, JFXButton> call(TableColumn<Ventas, JFXButton> ventasJFXButtonTableColumn) {
                        JFXButton btn =  new JFXButton("Editar");
                        {
                            btn.setOnAction((ActionEvent event)-> {
                                System.out.println("Mensaje del boton");
                            });
                        }

                        return null;
                    }
                };




        this.Col_SubTotal.setCellValueFactory(new PropertyValueFactory("subtota"));


        MensajeAlerta.setVisible(false);
        mostrarFecha();
        cargarClientes();
        cargarProductos();
//        ventas.validarSoloLetras(cliente_text,nit_txt);
//        ventas.validarSoloLetras(direccion_txt,producto_text);
//        ventas.validarSoloLetras(producto_text,cantidad_text);
//        ventas.validarSoloNumeros(nit_txt,telefono_txt);
//        ventas.validarSoloNumeros(telefono_txt,direccion_txt);
//        ventas.validarSoloNumeros(descuento_text,descripcion_text);
//        ventas.validarSoloNumeros(cantidad_text,descuento_text);
//      Seleccion de los datos para el Cliente
        listadoClietes.setOnAction(actionEvent -> { cliente_text.setText(listadoClietes.getValue());
            MensajeAlerta.setVisible(false);
            String CadenadeClientes = cliente_text.getText();
            String[] SeparadaCadena = CadenadeClientes.split(" ");
            String nombreCliente = SeparadaCadena[0];
            String apelliCliente = SeparadaCadena[1];
            String search_id = ventas.getIdCostumerInDB(nombreCliente,apelliCliente);
            ArrayList queryResultCustomer = ventas.getCustomerDatabyId(search_id);
            String datosClientes= queryResultCustomer.get(0).toString();
            String [] containerDataCustomer = datosClientes.split("#");
                telefono_txt.setText(containerDataCustomer[0]);
                direccion_txt.setText(containerDataCustomer[1]);
                nit_txt.setText(containerDataCustomer[2]);
            System.out.println();
        });
//      Seleccion de los datos para el producto
        listadoProductos.setOnAction(actionEvent -> {producto_text.setText(listadoProductos.getValue());
            ArrayList queryResultProducts = ventas.getProductByName(producto_text.getText());
            String consultaCompleta = queryResultProducts.get(0).toString();
            String [] contenedorConsultaProducto =consultaCompleta.split("#");
            PRECIOVENTA = contenedorConsultaProducto[1];
            CODIGOPRODUCTO = contenedorConsultaProducto[2];
            descripcion_text.setText(contenedorConsultaProducto[3]);

            if (cantidad_text.getLength() != 0){
//                subtotalCalculado = ventas.calcularSubtotal_andUpdateCantidad(
//                        Double.parseDouble(cantidad_text.getText()),
//                        Double.parseDouble(contenedorConsultaProducto[1]),
//                        Double.parseDouble(contenedorConsultaProducto[0])
//                );
                System.out.println("EL subtotal es = "+subtotalCalculado);
//                total_txt.setText(String.valueOf(subtotalCalculado));
            }

        });
    }

    public void btn_ShopingCar(ActionEvent actionEvent) {
//        try {
//        verificarTodoLosInputs();
//        Ventas nuevaVenta = new Ventas();
//        nuevaVenta.setCantidad(Double.parseDouble(cantidad_text.getText()));
//        nuevaVenta.setCodigoProducto(CODIGOPRODUCTO);
//        nuevaVenta.setProducoComprado(producto_text.getText());
//        nuevaVenta.setPrecioUnitario(Double.parseDouble(PRECIOVENTA));
//        nuevaVenta.setDescuento(Double.parseDouble(descuento_text.getText()));
//        nuevaVenta.setButton2(new JFXButton("INFO"));
//        nuevaVenta.setSubtota(subtotalCalculado);
//        this.ModeloTablaVentas.add(nuevaVenta);
//        this.TablaDetalleVenta.setItems(ModeloTablaVentas);
//        }catch (Exception e){
//            System.out.println(e.getCause());
//        }


    }
}

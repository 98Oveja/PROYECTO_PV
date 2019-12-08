package controllers.Ventas;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Ventas_Compras.Ventas;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ModalVentaController implements Initializable {
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
    public void CloseModal(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Close");
        alert.setContentText("Seguro que quieres Cerrar?");
        alert.showAndWait();

        //MANERA EN CERRA EL MODAL
          Stage stage = (Stage) panelContenedor.getScene().getWindow();
          stage.close();

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
        String Cliente = cliente_text.getText();
        String nit = nit_txt.getText();
        String telefono = telefono_txt.getText();
        String direccion = direccion_txt.getText();
        String productos = producto_text.getText();
        String cantidad_str = cantidad_text.getText();
        String descripcion = descripcion_text.getText();
        String descuento = descuento_text.getText();
        if (validarCampos(Cliente)==false){
            cliente_text.setText("INGRESA O SELECCIONA UN NOMBRE");
            cliente_text.setStyle("-fx-text-fill: rgba(0,229,226,0.65);");
        }
        if (validarCampos(nit)==false){
            nit_txt.setText("C/F");
            nit_txt.setStyle("-fx-text-fill: rgba(0,229,226,0.65);");
        }
        if (validarCampos(telefono)==false){
            telefono_txt.setText("No Phone");
            telefono_txt.setStyle("-fx-text-fill: rgba(0,229,226,0.65);");
        }
        if (validarCampos(direccion)==false){
            direccion_txt.setText("Ciudad");
            direccion_txt.setStyle("-fx-text-fill: rgba(0,229,226,0.65);");
        }
        if (validarCampos(productos)==false){
            producto_text.setText("SELECCIONA UN PRODUCTO");
            producto_text.setStyle("-fx-text-fill: rgba(0,229,226,0.65);");
        }
        if (validarCampos(cantidad_str)==false){
            cantidad_text.setText("??");
            cantidad_text.setStyle("-fx-text-fill: rgba(0,229,226,0.65);");
        }else {

//            Double cantidad_dbl = Double.parseDouble(cantidad_str);

//            if (cantidad_dbl<=0){
//                cantidad_text.setText("No. <= 0");
//                cantidad_text.setStyle("-fx-text-fill: rgba(0,229,226,0.65);");
//            }
        }
        if (validarCampos(descripcion)==false){
            descripcion_text.setText("Decripcion del Producto");
            descripcion_text.setStyle("-fx-text-fill: rgba(0,229,226,0.65);");
        }
        if (validarCampos(descuento)==false){
            descuento_text.setText("00");
            descuento_text.setStyle("-fx-text-fill: rgba(0,229,226,0.65);");
        }else{
            Double descuento_dbl = Double.parseDouble(descuento);
            double multi = descuento_dbl* 3;
            System.out.println(multi);
        }






    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarFecha();
        cargarClientes();
        cargarProductos();
        ventas.validarSoloLetras(cliente_text);
        ventas.validarSoloLetras(direccion_txt);
        ventas.validarSoloLetras(producto_text);
        ventas.validarSoloNumeros(nit_txt);
        ventas.validarSoloNumeros(telefono_txt);
        ventas.validarSoloNumeros(descuento_text);
        ventas.validarSoloNumeros(cantidad_text);
//      Seleccion de los datos para el Cliente
        listadoClietes.setOnAction(actionEvent -> { cliente_text.setText(listadoClietes.getValue());
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
            System.out.println(contenedorConsultaProducto[0]);
            System.out.println(contenedorConsultaProducto[1]);
            System.out.println(contenedorConsultaProducto[2]);

        });



    }

    public void btn_ShopingCar(ActionEvent actionEvent) {
        verificarTodoLosInputs();
    }

}

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
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Ventas_Compras.Ventas;
import utils.ConnectionUtil;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class ModalVentaController implements Initializable {
    //    Model para las ventas

    Ventas ventas = new Ventas();
    @FXML
    AnchorPane panelContenedor;
    @FXML
    DatePicker calendarioIn;
    @FXML
    Button btnCerrarModal;
    @FXML
    private JFXTextField cliente_text;
    @FXML
    private JFXComboBox<String> listadoClietes;
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

    Ventas vent = new Ventas();
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


    public void calendaerioUpdate(ActionEvent actionEvent) {
        String cliente = cliente_text.getText();
        String fecha = calendarioIn.getValue().toString();
        String producto = producto_text.getText();
        int cantidad = Integer.parseInt(cantidad_text.getText());
        String descripcion = descripcion_text.getText();
        float descuento = Float.parseFloat(descuento_text.getText());

    }









    public void GuardarVentaEnDB(ActionEvent actionEvent) {
//        validando los campos
        //mostrarFecha();
        String cliente = cliente_text.getText();
        vent.getNombreEmpleadoById(cliente);

    }



//    mostrar la fecha y la hora en un label
    public void mostrarFecha(){
        Date date = new Date();
        long miliSec = date.getTime();
//        java.sql.Date diaSql = new java.sql.Date(miliSec);
//        java.sql.Time timeSql = new java.sql.Time(miliSec);
        java.sql.Timestamp tiempoCom = new java.sql.Timestamp(miliSec);
//        System.out.println("Dia normal: "+date);
//        System.out.println("Dia sql: "+diaSql);
//        System.out.println("Tiempo sql: "+timeSql);
//        System.out.println("Tiempo Completo: "+tiempoCom);
        txt_fechaVenta.setText(tiempoCom.toString());
    }

    public void cargarClientes(){
        ArrayList<String> arrayList = vent.NomrbesPersonas();
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(arrayList);
        listadoClietes.setItems(items);
    }

    public void cargarProductos(){
        ArrayList<String> arrayProductos = vent.allProducts();
        ObservableList<String> itemsProd = FXCollections.observableArrayList();
        itemsProd.addAll(arrayProductos);
        listadoProductos.setItems(itemsProd);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarFecha();
        cargarClientes();
        cargarProductos();
    }
}

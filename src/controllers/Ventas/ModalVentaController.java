package controllers.Ventas;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Ventas_Compras.Ventas;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

public class ModalVentaController implements Initializable {
//  ELEMENTOS DEL FXML
    public ScrollPane PanelScroleable;
    public Button btnCarritoCompra;
    public Label MensajedeAlertaCampos;
    public JFXProgressBar barradeProgresoAlerta;
    public AnchorPane panelContenedor;
    public Button btnCerrarModal;
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
    public VBox CardContainer;
//  VARIABLES GLOBALES Y AUXILIAS
    int contadorDePaneles;
    int cuantoCamposVacios;
    Ventas ventas = new Ventas();
    double subtotalCalculado = 0;
    String CODIGOPRODUCTO = "";
    String PRECIOVENTA = "";
    String EXITENCIAPRODUCTO = "";
    String search_id;
    ArrayList<Ventas> ventasArrayList = new ArrayList<>();
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
    public void cargarTarjetaPrducto() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ventas/cardprod.fxml"));
        Parent parent = loader.load();
        CartaProducto cartaProducto = loader.getController();
        cartaProducto.setEtextCodProd(CODIGOPRODUCTO);
        cartaProducto.setEtextCantidad(cantidad_text.getText());
        cartaProducto.setEtextPrecio(PRECIOVENTA);
        cartaProducto.setEtextProductos(producto_text.getText());
        cartaProducto.setEtextDescuento(descuento_text.getText());
        cartaProducto.setEtextSubTotal(ventas.calculoDeDescuentos(PRECIOVENTA,cantidad_text.getText(),descuento_text.getText()));
        contadorDePaneles++;
        CardContainer.getChildren().addAll(parent);
    }
    public int todolosCamposVacios(){
        cuantoCamposVacios =0;
       if (ventas.camposVacios(cliente_text)){cuantoCamposVacios+=1;}
       if (ventas.camposVacios(nit_txt)){cuantoCamposVacios+=1;}
       if (ventas.camposVacios(telefono_txt)){cuantoCamposVacios+=1;}

       if (ventas.camposVacios(direccion_txt)){cuantoCamposVacios+=1;}
       if (ventas.camposVacios(producto_text)){cuantoCamposVacios+=1;}
       if (ventas.camposVacios(cantidad_text)){cuantoCamposVacios+=1;}

       if (ventas.camposVacios(descuento_text)){cuantoCamposVacios+=1;}
       if (ventas.camposVacios(descripcion_text)){cuantoCamposVacios+=1;}
       return cuantoCamposVacios;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MensajedeAlertaCampos.setText("");
        MensajedeAlertaCampos.setVisible(false);
        barradeProgresoAlerta.setVisible(false);
        PanelScroleable.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); //Oculta el escrol del panel de manera horizontal
        contadorDePaneles = 0;
        mostrarFecha();
        cargarClientes();
        cargarProductos();
        ventas.validarSoloLetras(cliente_text);
        ventas.validarSoloLetras(direccion_txt);
        ventas.validarSoloLetras(producto_text);
        ventas.validarNit(nit_txt);
        ventas.validarSoloNumeros(telefono_txt);
        ventas.validarSoloNumeros(descuento_text);
        ventas.validarSoloNumeros(cantidad_text);
        descuento_text.setText("0");
//      Seleccion de los datos para el Cliente
        listadoClietes.setOnAction(actionEvent -> { cliente_text.setText(listadoClietes.getValue());
            String CadenadeClientes = cliente_text.getText();
            String[] SeparadaCadena = CadenadeClientes.split(" ");
            String nombreCliente = SeparadaCadena[0];
            String apelliCliente = SeparadaCadena[1];
            search_id = ventas.getIdCostumerInDB(nombreCliente,apelliCliente);
            System.out.println("Este es el ID del cliente "+search_id);
            ArrayList queryResultCustomer = ventas.getCustomerDatabyId(search_id);
            String datosClientes= queryResultCustomer.get(0).toString();
            String [] containerDataCustomer = datosClientes.split("#");
                telefono_txt.setText(containerDataCustomer[0]);
                direccion_txt.setText(containerDataCustomer[1]);
                nit_txt.setText(containerDataCustomer[2]);
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
                subtotalCalculado = ventas.calcularSubtotal_andUpdateCantidad(
                        Double.parseDouble(cantidad_text.getText()),
                        Double.parseDouble(contenedorConsultaProducto[1]),
                        Double.parseDouble(contenedorConsultaProducto[0])
                );
                System.out.println("EL subtotal es = "+subtotalCalculado);
            }
        });
    }
    public void btn_ShopingCar(ActionEvent actionEvent) throws IOException {
        if (todolosCamposVacios()<8){
            visualizarVentas();
            MensajedeAlertaCampos.setVisible(true);
            barradeProgresoAlerta.setVisible(true);
            MensajedeAlertaCampos.setText("Llena los Campos");
        }else{
            cliente_text.setDisable(true);
            nit_txt.setDisable(true);
            telefono_txt.setDisable(true);
            direccion_txt.setDisable(true);
            listadoClietes.setDisable(true);
            MensajedeAlertaCampos.setVisible(false);
            barradeProgresoAlerta.setVisible(false);
            cargarTarjetaPrducto();
        }
    }
    public void GuardarVentaEnDB(ActionEvent actionEvent){
/*        1. Al realizar una venta lo primero es obtener el total en el label total
             (Sumar todo los subtotales en los paneles) almacenarlo en el metodo
*
*
*
* */

        Ventas newVentas = new Ventas();
        contadorDePaneles+=2;
        newVentas.setCodigoCLiente((1+contadorDePaneles));
        newVentas.setCodigoEmpleado(3+contadorDePaneles);
        newVentas.setTotal(90+contadorDePaneles);
        contadorDePaneles++;
        ventasArrayList.add(newVentas);
//        JOptionPane.showMessageDialog(null,ventas.almacenarVentasenDB(1,3,90));

    }

    public void visualizarVentas() {
        int contaodir = 0;
        for (Ventas v : ventasArrayList) {
            contaodir++;
            System.out.println("Cod Cliente " + v.getCodigoCLiente() +
                    "  Cod Empleado " + v.getCodigoEmpleado() +
                    "  Total Q " + v.getTotal()+"  Fila #"+contaodir);
        }
    }

}
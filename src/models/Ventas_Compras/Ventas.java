package models.Ventas_Compras;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controllers.Ventas.EditProductoController;
import controllers.employees.DelEmployController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Element;
import utils.ConnectionUtil;
import utils.LoadModalesMovibles;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ventas{
    private String NOMBREPRODUCTO;
    public void setNOMBREPRODUCTO(String elProducto){this.NOMBREPRODUCTO = elProducto;}
    public String getNOMBREPRODUCTO(){return this.NOMBREPRODUCTO;}
    public ConnectionUtil cone = new ConnectionUtil();
    public Connection connection = cone.getConnection();
    public CallableStatement callableStatement = null;
    public ResultSet resultSet = null;
    public Statement statement = null;
//    public int tamanioObservable =0;
    public ArrayList<String> dataContainer = new ArrayList<>();
    public String resultQuery = "";
//
    public TableView tableViewAux = new TableView<>();
    public ObservableList<Ventas> ventasObservableListAux;
    private JFXButton Editar;
    private JFXButton Eliminar;
//
    private int Numero;
    private int Cantidad;
    private String CodigoProducto;
    private String Producto;
    private double PrecioVenta;
//    private double Descuento;
    private double SubTotal;
//
    public Ventas(){}
    public Ventas(int numero, int cantidad,
                  String codigoProducto, String producto,
                  double precioVenta,
                  double subTotal, JFXButton edit, JFXButton del) {
    Numero = numero;
    Cantidad = cantidad;
    CodigoProducto = codigoProducto;
    Producto = producto;
    PrecioVenta = precioVenta;
//    Descuento = descuento;
    SubTotal = subTotal;
    Editar = edit;
    Eliminar = del;
    Editar.setOnAction(actionEvent -> {
        EditProductoController editProductoController = (EditProductoController) LoadModalesMovibles.LoadModalMovible(getClass().getResource("/fxml/Ventas/EditProducto.fxml"),null);
        editProductoController.setNuevaCantidad(String.valueOf(getCantidad()));
//        editProductoController.setNuevoDescuento(String.valueOf(getDescuento()));
        editProductoController.setPRECIOPROD(getPrecioVenta());
        editProductoController.setNombreProducto(getNOMBREPRODUCTO());

//        creacion de un producto auxiliar para que se el envie al Modal de Editar producto
        Ventas productoSeleccionado =
                new Ventas(getNumero(), getCantidad(), getCodigoProducto(),
                        getProducto(), getPrecioVenta(),
                        getSubTotal(), getEditar(), getEliminar());
//        this.ventasObservableListAux.add(productoSeleccionado);
        editProductoController.setProductoSeleccionado(productoSeleccionado);
        editProductoController.setVentasListaAuxUpdate(this.ventasObservableListAux);
        editProductoController.setTablaAuxiliarUpdate(this.tableViewAux);
        System.out.println("EL tamanio del Array desde el BTN Editar\n" +
                "Tabla: "+ getTableViewAux().getItems()+"\n"+
                "Array List "+getventasObservableListAux().size());

    });
    Eliminar.setOnAction(actionEvent -> {
        System.out.println("Bonton de Eliminacion de datos");
        System.out.println("El numero del Producto es: "+getNumero());
        try {
            int nuevoContador = 1;
            Ventas prodseleccionado = new Ventas(getNumero(), getCantidad(), getCodigoProducto(),
                    getProducto(), getPrecioVenta(),
                    getSubTotal(), getEditar(), getEliminar());
            ventasObservableListAux.remove(prodseleccionado);
            for (Ventas actualizar:ventasObservableListAux) {
                actualizar.setNumero(nuevoContador);
                tableViewAux.refresh();nuevoContador++;}
        }catch (Exception e){
            System.out.println("No se puede Borrar por: "+e.getMessage()+" Causado por "+
                    e.getCause());
        }
    });
    }

//
//    //METODOS PARA LAS VALIDADCIONES
//public void validarSoloLetras(TextField campoDeTexto) {
//   campoDeTexto.addEventFilter(KeyEvent.ANY, event -> {
//     char c = event.getCharacter().charAt(0);
//      if (!(Character.isLetter(c)|| Character.isWhitespace(c) || Character.isISOControl(c))){
//          event.consume();
//      }
////       if (event.getCode() == KeyCode.ENTER ){
////           focus.requestFocus();
////       }
//   });
//}
//public void validarSoloNumeros(TextField campo){
//   campo.addEventFilter(KeyEvent.ANY, event ->{
//     char c = event.getCharacter().charAt(0);
//      if (!(Character.isDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c)) && c!='.'){
//          event.consume();
//      }
//      if (c == '.' && campo.getText().contains(".")){
//          event.consume();
//      }
//   });
//}
//public void validarSoloNumerosJfoenix(JFXTextField campo){
//        campo.addEventFilter(KeyEvent.ANY, event ->{
//            char c = event.getCharacter().charAt(0);
//            if (!(Character.isDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c)) && c!='.'){
//                event.consume();
//            }
//            if (c == '.' && campo.getText().contains(".")){
//                event.consume();
//            }
//        });
//    }
//public void validarNit(TextField campo){
//    campo.addEventFilter(KeyEvent.ANY, event ->{
//        char c = event.getCharacter().charAt(0);
//        if (!(Character.isDigit(c) || Character.isWhitespace(c)
//                || Character.isISOControl(c)) && c!='-'){
//            event.consume();
//        }
//        if (c == '-' && campo.getText().contains("-")){
//            event.consume();
//        }
//    });
//}
//public void validarNumTelefono(TextField campo, int tamanio){
//        campo.addEventFilter(KeyEvent.ANY, event ->{
//            char c = event.getCharacter().charAt(0);
//            int tamCampo = campo.getText().length();
//            if (Character.isDigit(c) || Character.isISOControl(c)) {
//                if (tamCampo >= tamanio && !(Character.isISOControl(c))) {event.consume();}
//            }else{event.consume();}
//        });
//    }
//public boolean camposVacios(TextField jfxTextField){
//    return jfxTextField.getLength()!=0?true:false;
//}
////  METODOS Y FUNCIONES QUE EJECUTAN LOS SCRIPTS DE LA BASE DE DATOS PARA OBETENER LOS DATOS NECESARIOS
//public String getIdCostumerInDB(String Nombre, String Apellido) {
//    try {
//        resultQuery = "";
//        String sql = "{?= call getIdCostumerbyName(?,?)}";
//        callableStatement = connection.prepareCall(sql);
//        callableStatement.registerOutParameter(1, Types.INTEGER);
//        callableStatement.setString(2, Nombre);
//        callableStatement.setString(3, Apellido);
//        callableStatement.execute();
//        resultQuery = callableStatement.getString(1);
//    } catch (SQLException e) {
//        System.out.println("I can't get the customer's ID: " + e.getMessage());
//    }
//    return resultQuery;
//}
//public ArrayList listadoClientes() {
//        try {
//            dataContainer.clear();
//            resultQuery = "";
//            String sql = "{call selectAllCostumers()}";
//             statement = connection.createStatement();
//             resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                resultQuery = resultSet.getString("PRIMER_NOMBRE") + " " + resultSet.getString("PRIMER_APELLIDO");
//                dataContainer.add(resultQuery);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error loading all Customers: " + e.getMessage());
//        }
//    return dataContainer;
//}
//public ArrayList listaProductos() {
//   try {
//       dataContainer.clear();
//       resultQuery = "";
//       String sql = "{call selectAllProducts()}";
//       statement = connection.createStatement();
//       resultSet = statement.executeQuery(sql);
//       while (resultSet.next()) {
//           resultQuery = resultSet.getString("NOMBRE");
//           dataContainer.add(resultQuery);
//       }
//   } catch (SQLException e) {
//       System.out.println("Error loading all Products: " + e.getMessage());
//   }
//   return dataContainer;
//}
//public ArrayList getCustomerDatabyId(String idCliente) {
//   try{
//       dataContainer.clear();
//       resultQuery = "";
//       callableStatement = connection.prepareCall("{call consutaNitDirTelCliente (?)}");
//       callableStatement.setInt(1, Integer.parseInt(idCliente));
//       resultSet = callableStatement.executeQuery();
//       while (resultSet.next()) {
//           dataContainer.add(resultSet.getString("TELEFONO")+"#"+
//                             resultSet.getString("DIRECCION")+"#"+
//                             resultSet.getString("NIT"));
//       }
//   } catch (SQLException e) {
//       System.out.println("I can't get the Customer's Data : "+e.getMessage());
//   }
//   return dataContainer;
//}
//public ArrayList getProductByName(String thisProduct){
//    try{
//        dataContainer.clear();
//        resultQuery = "";
//        callableStatement = connection.prepareCall("{call getDataProductsByNameProd(?)}");
//        callableStatement.setString(1, thisProduct);
//        resultSet = callableStatement.executeQuery();
//        while (resultSet.next()) {
//            dataContainer.add(resultSet.getString("DISPONIBILIDAD")+"#"+
//                    resultSet.getString("PRECIO_VENTA")+"#"+
//                    resultSet.getString("CODIGO")+"#"+
//                    resultSet.getString("DESCRIPCION"));
//
//        }
//    } catch (SQLException e) {
//        System.out.println("I can't get the Product's Data : "+e.getMessage());
//    }
//    return dataContainer;
//}
//    public String calculoDeSubtotal(String precioin, String cantidadin){
//    double precioProd = Double.parseDouble(precioin);
//    double cantidad = Double.parseDouble(cantidadin);
//    double subTotalSinDescuento = (precioProd * cantidad);
//    DecimalFormat decimalFormat = new DecimalFormat("#.00");
//    String sub = String.valueOf(decimalFormat.format(subTotalSinDescuento));
//    return sub;
//    }
//
////SUBTOTAL PARA LOS PRODUCTOS
//    public String CalculoTotalConDescuento(String Totales, String descuentoin){
//    double TotalSum = Double.parseDouble(Totales);
//    double descuento = Double.parseDouble(descuentoin)/100;
//    double elDescuento = TotalSum*descuento;
//    double TotalConDescuento = TotalSum-elDescuento;
//    DecimalFormat decimalFormat = new DecimalFormat("#.00");
//    String sub = String.valueOf(decimalFormat.format(TotalConDescuento));
//    return sub;
//    }
//
//
//
//
////  CALULANDO EL SUB TOTAL PARA LOS PRODUCTOS DE ACUERDO AL PRECIO DE VETA Y LA CANTIDAD QUE SE VA A VENDER
////  TAMBIEN HARA LA ACTUALIZACION A LA CANTIDAD DE PRODUCTO EN LA BASE DE DATOS
////Manera en que almacenamos los datos en la base de datos
//public String almacenarVentasenDB(int idClienteG, int idEmpleadoG, double totalVenta){
//    try {
//        String sql = "{call insertVentas(?,?,?)}";
//        callableStatement = connection.prepareCall(sql);
//        callableStatement.setInt(1,idClienteG);
//        callableStatement.setInt(2, idEmpleadoG);
//        callableStatement.setDouble(3, totalVenta);
//        callableStatement.execute();
//    } catch (SQLException e) {
//        return ("Error isert Ventas: " + e.getMessage());
//    }
//    return "Venta Gurdada en la BD";
//}
//    GETERS
    public int getNumero() {return Numero;}
    public String getCodigoProducto(){return CodigoProducto;}
    public int getCantidad(){return Cantidad; }
    public String getProducto() {return Producto;}
    public double getPrecioVenta(){return PrecioVenta;}
//    public double getDescuento(){return Descuento; }
    public double getSubTotal() {return SubTotal; }
    public JFXButton getEliminar() { return Eliminar; }
    public JFXButton getEditar() { return Editar; }
    public TableView getTableViewAux(){return tableViewAux;}
    public ObservableList getventasObservableListAux(){return ventasObservableListAux;}
//    SETERS
    public void setNumero(int numero) {Numero = numero;}
    public void setCodigoProducto(String codigoProducto){CodigoProducto = codigoProducto;}
    public void setCantidad(int cantidad) {Cantidad = cantidad;}
    public void setProducto(String producto) {Producto = producto;}
    public void setPrecioVenta(double precioVenta){PrecioVenta = precioVenta;}
//    public void setDescuento(double descuento){Descuento = descuento;}
    public void setSubTotal(double subTotal){SubTotal = subTotal;}
    public void setEliminar(JFXButton eliminar){Eliminar = eliminar;}
    public void setEditar(JFXButton editar){Editar = editar;}
    public void setTableViewAux(TableView tableView){tableViewAux=tableView;}
    public void setVentasObservableListAux(ObservableList observableList){ventasObservableListAux=observableList;}
//  EQUALS
@Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Ventas)) return false;
    Ventas ventas = (Ventas) o;
    return  Double.compare(ventas.getPrecioVenta(), getPrecioVenta()) == 0 &&
            getCodigoProducto().equals(ventas.getCodigoProducto()) &&
            getProducto().equals(ventas.getProducto());
}
@Override public int hashCode() { return Objects.hash(getCodigoProducto(), getProducto(), getPrecioVenta()); }



}
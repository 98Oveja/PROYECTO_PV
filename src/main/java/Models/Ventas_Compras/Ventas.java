package Models.Ventas_Compras;
import com.jfoenix.controls.JFXButton;
import Controllers.employees.DelEmployController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Utils.ConnectionUtil;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class Ventas{
    public ConnectionUtil cone = new ConnectionUtil();
    public Connection connection = cone.getConnection();
    public CallableStatement callableStatement = null;
    public ResultSet resultSet = null;
    public Statement statement = null;
    public ArrayList<String> dataContainer = new ArrayList<>();
    public String resultQuery = "";
//
    public TableView tableViewAux;
    public ObservableList<Ventas> ventasObservableListAux;
    private JFXButton Editar;
    private JFXButton Eliminar;
//
    private int Numero;
    private int Cantidad;
    private String CodigoProducto;
    private String Producto;
    private double PrecioVenta;
    private double Descuento;
    private double SubTotal;
//
    public Ventas(){}
    public Ventas(int numero, int cantidad,
                  String codigoProducto, String producto,
                  double precioVenta, double descuento,
                  double subTotal, JFXButton edit, JFXButton del) {
    Numero = numero;
    Cantidad = cantidad;
    CodigoProducto = codigoProducto;
    Producto = producto;
    PrecioVenta = precioVenta;
    Descuento = descuento;
    SubTotal = subTotal;
    Editar = edit;
    Eliminar = del;
//    Editar.setOnAction(actionEvent -> {
//
//    });
    Eliminar.setOnAction(actionEvent -> {
//     Ventas v = (Ventas) tableViewAux.getSelectionModel().getSelectedItem();
//     ventasObservableListAux.remove(v);
        for (Ventas v:ventasObservableListAux) {
            System.out.println(v.getNumero());
        }
    });

    }


//METODOS PARA LAS VALIDADCIONES
public void validarSoloLetras(TextField campoDeTexto) {
   campoDeTexto.addEventFilter(KeyEvent.ANY, event -> {
     char c = event.getCharacter().charAt(0);
      if (!(Character.isLetter(c)|| Character.isWhitespace(c) || Character.isISOControl(c))){
          event.consume();
      }
//       if (event.getCode() == KeyCode.ENTER ){
//           focus.requestFocus();
//       }
   });
}
public void validarSoloNumeros(TextField campo){
   campo.addEventFilter(KeyEvent.ANY, event ->{
     char c = event.getCharacter().charAt(0);
      if (!(Character.isDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c)) && c!='.'){
          event.consume();
      }
      if (c == '.' && campo.getText().contains(".")){
          event.consume();
      }
//      if (event.getCode() == KeyCode.ENTER){
//          newFocus.requestFocus();
//      }
   });
}
public void validarNit(TextField campo){
    campo.addEventFilter(KeyEvent.ANY, event ->{
        char c = event.getCharacter().charAt(0);
        if (!(Character.isDigit(c) || Character.isWhitespace(c)
                || Character.isISOControl(c)) && c!='-'){
            event.consume();
        }
        if (c == '-' && campo.getText().contains("-")){
            event.consume();
        }
    });
}
public void validarNumTelefono(TextField campo, int tamanio){
        campo.addEventFilter(KeyEvent.ANY, event ->{
            char c = event.getCharacter().charAt(0);
            int tamCampo = campo.getText().length();
            if (Character.isDigit(c) || Character.isISOControl(c)) {
                if (tamCampo >= tamanio && !(Character.isISOControl(c))) {event.consume();}
            }else{event.consume();}
        });
    }
public boolean camposVacios(TextField jfxTextField){
    return jfxTextField.getLength()!=0?true:false;
}
//  METODOS Y FUNCIONES QUE EJECUTAN LOS SCRIPTS DE LA BASE DE DATOS PARA OBETENER LOS DATOS NECESARIOS
public String getIdCostumerInDB(String Nombre, String Apellido) {
    try {
        resultQuery = "";
        String sql = "{?= call getIdCostumerbyName(?,?)}";
        callableStatement = connection.prepareCall(sql);
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.setString(2, Nombre);
        callableStatement.setString(3, Apellido);
        callableStatement.execute();
        resultQuery = callableStatement.getString(1);
    } catch (SQLException e) {
        System.out.println("I can't get the customer's ID: " + e.getMessage());
    }
    return resultQuery;
}
public ArrayList listadoClientes() {
        try {
            dataContainer.clear();
            resultQuery = "";
            String sql = "{call selectAllCostumers()}";
             statement = connection.createStatement();
             resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                resultQuery = resultSet.getString("PRIMER_NOMBRE") + " " + resultSet.getString("PRIMER_APELLIDO");
                dataContainer.add(resultQuery);
            }
        } catch (SQLException e) {
            System.out.println("Error loading all Customers: " + e.getMessage());
        }
    return dataContainer;
}
public ArrayList listaProductos() {
   try {
       dataContainer.clear();
       resultQuery = "";
       String sql = "{call selectAllProducts()}";
       statement = connection.createStatement();
       resultSet = statement.executeQuery(sql);
       while (resultSet.next()) {
           resultQuery = resultSet.getString("NOMBRE");
           dataContainer.add(resultQuery);
       }
   } catch (SQLException e) {
       System.out.println("Error loading all Products: " + e.getMessage());
   }
   return dataContainer;
}
public ArrayList getCustomerDatabyId(String idCliente) {
   try{
       dataContainer.clear();
       resultQuery = "";
       callableStatement = connection.prepareCall("{call consutaNitDirTelCliente (?)}");
       callableStatement.setInt(1, Integer.parseInt(idCliente));
       resultSet = callableStatement.executeQuery();
       while (resultSet.next()) {
           dataContainer.add(resultSet.getString("TELEFONO")+"#"+
                             resultSet.getString("DIRECCION")+"#"+
                             resultSet.getString("NIT"));
       }
   } catch (SQLException e) {
       System.out.println("I can't get the Customer's Data : "+e.getMessage());
   }
   return dataContainer;
}
public ArrayList getProductByName(String thisProduct){
    try{
        dataContainer.clear();
        resultQuery = "";
        callableStatement = connection.prepareCall("{call getDataProductsByNameProd(?)}");
        callableStatement.setString(1, thisProduct);
        resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            dataContainer.add(resultSet.getString("CANTIDAD")+"#"+
                    resultSet.getString("PRECIO_VENTA")+"#"+
                    resultSet.getString("CODIGO")+"#"+
                    resultSet.getString("DESCRIPCION"));

        }
    } catch (SQLException e) {
        System.out.println("I can't get the Product's Data : "+e.getMessage());
    }
    return dataContainer;
}
public String calculoDeDescuentos(String precioin, String cantidadin, String descuentoin){
    double precioProd = Double.parseDouble(precioin);
    double cantidad = Double.parseDouble(cantidadin);
    double descuento = Double.parseDouble(descuentoin)/100;
    double subTotalSinDescuento = (precioProd * cantidad);
    double elDescuento = subTotalSinDescuento*descuento;
    double subConDescuento = subTotalSinDescuento-elDescuento;
    DecimalFormat decimalFormat = new DecimalFormat("#.00");
//    String sub = String.valueOf(Math.round(Float.parseFloat(decimalFormat.format(subTotal-subConDescuento))));
    String sub = String.valueOf(decimalFormat.format(subConDescuento));
    return sub;
}
//  CALULANDO EL SUB TOTAL PARA LOS PRODUCTOS DE ACUERDO AL PRECIO DE VETA Y LA CANTIDAD QUE SE VA A VENDER
//  TAMBIEN HARA LA ACTUALIZACION A LA CANTIDAD DE PRODUCTO EN LA BASE DE DATOS
//Manera en que almacenamos los datos en la base de datos
public String almacenarVentasenDB(int idClienteG, int idEmpleadoG, double totalVenta){
    try {
        String sql = "{call insertVentas(?,?,?)}";
        callableStatement = connection.prepareCall(sql);
        callableStatement.setInt(1,idClienteG);
        callableStatement.setInt(2, idEmpleadoG);
        callableStatement.setDouble(3, totalVenta);
        callableStatement.execute();
    } catch (SQLException e) {
        return ("Error isert Ventas: " + e.getMessage());
    }
    return "Venta Gurdada en la BD";
}
public void cerrarModal(BorderPane panel){
    Image image = new Image("/images/info.png");
    alertasPersonalizados("CANCELAR", "Esta seguro que desea salir y cancelar la Venta",image,1,panel);
}
public void alertasPersonalizados(String TITULO, String Cuerpo,
                                  Image image,int opcion,
                                  BorderPane borderPane){
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Empleados/DeleteEmploy.fxml"));
        Parent parent = fxmlLoader.load();
        DelEmployController delEmployController = fxmlLoader.getController();
        delEmployController.TitleModal.setText(TITULO);
        delEmployController.contentAlert.setText(Cuerpo);
        delEmployController.IconModal.setImage(image);
        if(opcion == 0) {
            delEmployController.Cancel.setVisible(false);
            delEmployController.Okay.setStyle("-fx-translate-x: 65px; -fx-translate-y: -10px;");
            delEmployController.Okay.setText("Ok");}
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        if(delEmployController.BtnOk==1 && opcion ==1){
          stage = (Stage) borderPane.getScene().getWindow();
          stage.close();
        }
    }catch (Exception e){ System.out.println(e.getMessage());}
}
//    GETERS
    public int getNumero() {
    return Numero;
}
    public String getCodigoProducto() {
        return CodigoProducto;
    }
    public int getCantidad() {
        return Cantidad;
    }
    public String getProducto() {
        return Producto;
    }
    public double getPrecioVenta() {
        return PrecioVenta;
    }
    public double getDescuento() {
        return Descuento;
    }
    public double getSubTotal() {
        return SubTotal;
    }
    public JFXButton getEliminar() {
        return Eliminar;
    }
    public JFXButton getEditar() {
        return Editar;
    }
    public TableView getTableViewAux(){return tableViewAux;}
    public ObservableList getventasObservableListAux(){return ventasObservableListAux;}
//    SETERS
    public void setNumero(int numero) {
        Numero = numero;
    }
    public void setCodigoProducto(String codigoProducto) {
        CodigoProducto = codigoProducto;
    }
    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }
    public void setProducto(String producto) {
        Producto = producto;
    }
    public void setPrecioVenta(double precioVenta) {
        PrecioVenta = precioVenta;
    }
    public void setDescuento(double descuento) {
        Descuento = descuento;
    }
    public void setSubTotal(double subTotal) {
        SubTotal = subTotal;
    }
    public void setEliminar(JFXButton eliminar) {
        Eliminar = eliminar;
    }
    public void setEditar(JFXButton editar) {
        Editar = editar;
    }
    public void setTableViewAux(TableView tableView){tableViewAux=tableView;}
    public void setVentasObservableListAux(ObservableList observableList){ventasObservableListAux=observableList;}
//  EQUALS
@Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Ventas)) return false;
    Ventas ventas = (Ventas) o;
    return getCantidad() == ventas.getCantidad() &&
            Double.compare(ventas.getPrecioVenta(), getPrecioVenta()) == 0 &&
            Double.compare(ventas.getDescuento(), getDescuento()) == 0 &&
            Double.compare(ventas.getSubTotal(), getSubTotal()) == 0 &&
            getCodigoProducto().equals(ventas.getCodigoProducto()) &&
            getProducto().equals(ventas.getProducto());
}
@Override public int hashCode() { return Objects.hash(getCantidad(), getCodigoProducto(), getProducto(), getPrecioVenta(), getDescuento(), getSubTotal()); }
}
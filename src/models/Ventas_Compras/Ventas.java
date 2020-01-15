package models.Ventas_Compras;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import utils.ConnectionUtil;

import java.net.Socket;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
public class Ventas {
    ConnectionUtil cone = new ConnectionUtil();
    Connection connection = cone.getConnection();
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ArrayList<String> dataContainer = new ArrayList<>();
    String resultQuery = "";
    //  Modelo de la Venta
    String descripcionProducto;
    double subtotal, total;
    int id_Venta,cantidad, id_producto,codigoCLiente, codigoEmpleado;
public Ventas() {
    }
//METODOS PARA LAS VALIDADCIONES
public void validarSoloLetras(JFXTextField campoDeTexto) {
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
public void validarSoloNumeros(JFXTextField campo){
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
public void validarNit(JFXTextField campo){
    campo.addEventFilter(KeyEvent.ANY, event ->{
        char c = event.getCharacter().charAt(0);
        if (!(Character.isDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c)) && c!='.'){
            event.consume();
        }
        if (c == '-' && campo.getText().contains("-")){
            event.consume();
        }
    });
}
public boolean camposVacios(JFXTextField jfxTextField){
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
public double calcularSubtotal_andUpdateCantidad(double cantidadProduct, double precioProducto, double stock){
    if (stock <= cantidadProduct){
        return total = 0;
    }
        return this.total = cantidadProduct * precioProducto;
}
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
//SET Venta
public void setCodigoCLiente(int id_cliente){this.codigoCLiente=id_cliente;}
public void setCodigoEmpleado(int id_empleado){this.codigoEmpleado=id_empleado;}
public void setTotal(double elTotal){this.total=elTotal;}
//Set Detalle Venta
public void setIdVenta(int id_Venta){this.id_Venta=id_Venta;}
public void setIdProducto(int id_producto){this.id_producto=id_producto;}
public void setDescripcionProducto(String descipcion){this.descripcionProducto = descipcion;}
public void setCantidad(int cantidad){this.cantidad=cantidad;}
public void setSubtotal(double subtotal){this.subtotal=subtotal;}
//GET Venta
public int getCodigoCLiente(){return this.codigoCLiente;}
public int getCodigoEmpleado(){return this.codigoEmpleado;}
public double getTotal(){return this.total;}
//get Detalle Venta
public int getIdVenta(){return this.id_Venta;}
public int getIdProducto(){return  this.id_producto;}
public String getDescripcionProducto(){return this.descripcionProducto;}
public int getCantidad(){return this.cantidad;}
public double getSubtotal(){return this.subtotal;}
}
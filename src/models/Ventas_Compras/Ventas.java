package models.Ventas_Compras;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import utils.ConnectionUtil;

import java.net.Socket;
import java.sql.*;
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
    String nombreCliente, nitCliente, telefonoCliente, direccionCliente, producoComprado,descripcionProducto,codigoProducto;
    double cantidad, descuento, subtota, total,precioUnitario;
//  Botones para colocarlo en la tabla para la columna Opciones
    JFXButton btnEditar, btnCancelar;

public Ventas() {
    }
//METODOS PARA VALIDAR SOLO LETRAS
public void validarSoloLetras(JFXTextField campoDeTexto,JFXTextField focus) {
   campoDeTexto.addEventFilter(KeyEvent.ANY, event -> {
     char c = event.getCharacter().charAt(0);
      if (!(Character.isLetter(c)|| Character.isWhitespace(c) || Character.isISOControl(c))){
          event.consume();
      }
       if (event.getCode() == KeyCode.ENTER ){
           focus.requestFocus();
       }
   });
}
public void validarSoloNumeros(JFXTextField campo, JFXTextField newFocus){
   campo.addEventFilter(KeyEvent.ANY, event ->{
     char c = event.getCharacter().charAt(0);
      if (!(Character.isDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c)) && c!='.'){
          event.consume();
      }
      if (c == '.' && campo.getText().contains(".")){
          event.consume();
      }
      if (event.getCode() == KeyCode.ENTER){
          newFocus.requestFocus();
      }
   });
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
                    resultSet.getString("CODDIGO")+"#"+
                    resultSet.getString("DESCRIPCION"));

        }
    } catch (SQLException e) {
        System.out.println("I can't get the Product's Data : "+e.getMessage());
    }
    return dataContainer;
}
//  CALULANDO EL SUB TOTAL PARA LOS PRODUCTOS DE ACUERDO AL PRECIO DE VETA Y LA CANTIDAD QUE SE VA A VENDER
//  TAMBIEN HARA LA ACTUALIZACION A LA CANTIDAD DE PRODUCTO EN LA BASE DE DATOS
public double calcularSubtotal_andUpdateCantidad(double cantidadProduct, double precioProducto, double stock){
    if (stock <= cantidadProduct){
        return total = 0;
    }
        return this.total = cantidadProduct * precioProducto;
}

//Metodos GETERS y SETERS
public void setNombreCliente(String nombre){
this.nombreCliente = nombre;
}
public String getNombreCliente(){
    return this.nombreCliente;
}
public void setNitCliente(String nit){
    this.nitCliente = nit;
}
public String getNitCliente(){
    return this.nitCliente;
}
public void setTelefonoClienteCliente(String telefono){
    this.telefonoCliente = telefono;
}
public String getTelefonoClienteCliente(){
    return this.telefonoCliente;
}
public void setDireccionClienteCliente(String direccion){
    this.direccionCliente = direccion;
}
public String getDireccionClienteCliente(){
    return this.direccionCliente;
}
public void setProducoComprado(String producto){ this.producoComprado = producto; }
public String getProducoComprado(){
        return this.producoComprado;
    }
public void setDescripcionProducto(String descipcion){
    this.descripcionProducto = descipcion;
}
public String getDescripcionProducto(){return this.descripcionProducto;}
public void setCantidad(double cantidad){this.cantidad = cantidad;}
public double getCantidad(){return this.cantidad;}
public void setDescuento(double descuento){this.descuento = descuento;}
public double getDescuento(){return this.descuento;}
public void setSubtota (double subtota){this.subtota = subtota;}
public double getSubtota(){return this.subtota;}
public void setTotal(double total){this.total = total;}
public double getTotal(){return this.total;}
public void setCodigoProducto(String codigoProducto){this.codigoProducto = codigoProducto;}
public String getCodigoProducto(){return this.codigoProducto;}
public void setPrecioUnitario(double precioUnitario){this.precioUnitario = precioUnitario;}
public double getPrecioUnitario(){return this.precioUnitario;}
public void  setButton1(JFXButton button){this.btnCancelar = button;
btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Boton de OPcion1");
    }
});
}

public JFXButton getOpcion1Button(){
    return this.btnCancelar;
}
public void setButton2(JFXButton button1){this.btnEditar = button1;
    btnEditar.setOnMouseClicked(mouseEvent -> {
        System.out.println("Has precionado opcion 2");
    });
}
public JFXButton getOpcion2Button(){
    return this.btnEditar;
}

}
package models.Ventas_Compras;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.input.KeyEvent;
import utils.ConnectionUtil;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
public class Ventas {
    ConnectionUtil cone = new ConnectionUtil();
    Connection connection = cone.getConnection();
    CallableStatement callableStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ArrayList<String> dataContainer = new ArrayList<>();
    String resultQuery = "";

    //  VARIABLES PARA LA TUPLA DE VENTAS
    private int id_Cliente, id_Empleado;
    private double total;
    private Date fecha;
    //  VARIABLES PARA EL DETALLE DE LA VENTA
    private int id_Venta, id_Poducto;
    private String descripcion;
    private double subTotal;
    private int canidad;
    //  VARIABLES COMPLEMENTARIAS PARA LA CLASE
    private String nombre_Empleado;
    private String nombre_Cliente;
    private double calculoTotal;
    private float descuento;

    public Ventas() {
    }
    //METODOS PARA VALIDAR SOLO LETRAS
public void validarSoloLetras(JFXTextField campoDeTexto) {
   campoDeTexto.addEventFilter(KeyEvent.ANY, event -> {
     char c = event.getCharacter().charAt(0);
      if (!(Character.isLetter(c)|| Character.isWhitespace(c) || Character.isISOControl(c))){
          event.consume();
      }
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
                    resultSet.getString("CODIGO"));
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










}
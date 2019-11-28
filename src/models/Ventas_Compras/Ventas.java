package models.Ventas_Compras;
import utils.ConnectionUtil;

import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Ventas {
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
//    ConnectionUtil con = new ConnectionUtil();
    ConnectionUtil con3 = null;
    Connection conn = null;
/*
Procedimiento: Es un conjunto de instrucciones que cumplen una tarea
Función: Como un procedimiento pero retorna un valor
Clase: Concepto de programación orientada a objetos, es una forma de encapsular funcionalidad, contiene campos y métodos.
Método: Puede ser un procedimiento o una función, la diferencia es que le pertenece a una clase.
 */
//        return con!=null?true:false;
//    Constructor
    public Ventas(){
        //  CONEXION A LA BASE DE DATOS
//        Connection con = ConnectionUtil.conDB();
        con3 = new ConnectionUtil();
        System.out.println(con3.toString());
    }
//  METODO PARA BUSCAR A AUN EMPLEADO POR NOMRE
    public void getNombreEmpleadoById(String NombreEmpleado_In){
        String esteEmpleado = NombreEmpleado_In.length()!=0?NombreEmpleado_In:"";
        System.out.println("El empleado "+esteEmpleado);
        try {
            conn = con3.getConnection();
            String query = "SELECT PRIMER_NOMBRE, PRIMER_APELLIDO from personas where  id_persona = 10";
            Statement sql = conn.createStatement();
            ResultSet resultSet = sql.executeQuery(query);
            if (resultSet !=null){
                if (resultSet.next() == true){
                    System.out.println(resultSet.getString("PRIMER_NOMBRE"));
                    System.out.println(resultSet.getString("PRIMER_APELLIDO"));
                }
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }





//        return  id_Empleado = 3;
    }
//  MEDODO PARA BUSCAR EL CLIENTE POR NOMBRE
    public int getNombreClienteById(String NombreCliente_In){

        return  id_Cliente;
    }
//  METODO PARA MOSTRAR LAS VENTAS
    public void listaDeVentas(){

    }
//  METODO PARA INSERTAR UNA VENTA
    public void registrarVenta(){

    }
//  METODO PARA CANCELAR UNA VENTA
//  METODO PARA VER EL DATALLE DE VENTA
//  METODO PARA INSERTAR EL DETALLE DE VENTA
//  METODO PARA BUSCAR UNA VENTA


    public ArrayList NomrbesPersonas(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            conn = con3.getConnection();
            String query = "SELECT PERSONAS.PRIMER_NOMBRE, PERSONAS.PRIMER_APELLIDO, CLIENTES.NIT from (personas inner join clientes on personas.ID_PERSONA = clientes.ID_PERSONA);";
            Statement sql = conn.createStatement();
            ResultSet resultSet = sql.executeQuery(query);
            if (resultSet !=null){
                while (resultSet.next()){
                    String nombre_cliente =resultSet.getString("PERSONAS.PRIMER_NOMBRE");
                    String apellido_cliente =resultSet.getString("PERSONAS.PRIMER_APELLIDO");
                    String nit_cliente =resultSet.getString("CLIENTES.NIT");
                    list.add(nombre_cliente+" "+apellido_cliente+" "+nit_cliente);
                }
            }
//            conn.close();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

//METODO PARA RETORNAR LOS PRODUCTOS EN EL COMBOBOX
    public ArrayList allProducts(){
        ArrayList<String> listaProd = new ArrayList<String>();
        try {
            conn = con3.getConnection();
            String query = "SELECT productos.NOMBRE, productos.PRECIO_VENTA, productos.CANTIDAD from  productos;";
            Statement sql = conn.createStatement();
            ResultSet resultSet = sql.executeQuery(query);
            if (resultSet !=null){
                while (resultSet.next()){
                    String nombre_prod = resultSet.getString("productos.NOMBRE");
                    Double precio_prod = Double.parseDouble(resultSet.getString("PRECIO_VENTA"));
                    int cantidad_prod = Integer.parseInt(resultSet.getString("CANTIDAD"));
                    listaProd.add(nombre_prod);
                }
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listaProd;
    }






}

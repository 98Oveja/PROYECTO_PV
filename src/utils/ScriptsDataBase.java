package utils;

import java.sql.*;
import java.util.ArrayList;

public class ScriptsDataBase {
    //COMPLEMENTARIOS PARA LA CONEXION A LA BASE DE DATOS
    ConnectionUtil cone = new ConnectionUtil();
    Connection connection = cone.getConnection();
    public String getIdCostumerInDB(String Nombre, String Apellido) {
        String result = "";
        try {
            String sql = "{?= call getIdCostumerbyName(?,?)}";
            CallableStatement procedure = connection.prepareCall(sql);
            procedure.registerOutParameter(1, Types.INTEGER);
            procedure.setString(2,Nombre);
            procedure.setString(3,Apellido);
            procedure.execute();
            result = procedure.getString(1);
        } catch (SQLException e) {
            System.out.println("Resultado del Procedure "+e.getMessage());
        }
        return result;
    }

    public ArrayList listadoClientes(){
        ArrayList<String> clietes = new ArrayList<>();
        try{
            String sql = "{call selectAllCostumers()}";
            String a = "";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                a = resultSet.getString("PRIMER_NOMBRE") +" "+ resultSet.getString("PRIMER_APELLIDO");
//                System.out.println(a);
                clietes.add(a);
            }
        } catch (SQLException e){
            System.out.println("Erro al cargar los Clientes" +e.getMessage());
        }
        return clietes;
    }


    public ArrayList listaProductos(){
        ArrayList<String> productos = new ArrayList<>();
        try{
            String sql = "{call selectAllProducts()}";
            String a = "";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                a = resultSet.getString("NOMBRE");
//                System.out.println(a);
                productos.add(a);
            }
        } catch (SQLException e){
            System.out.println("Erro al cargar los Productos" +e.getMessage());
        }
        return productos;
    }



}

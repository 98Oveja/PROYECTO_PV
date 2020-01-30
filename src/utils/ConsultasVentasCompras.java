package utils;

import java.sql.*;
import java.util.ArrayList;

public class ConsultasVentasCompras {
    public ConnectionUtil cone = new ConnectionUtil();
    public Connection connection = cone.getConnection();
    public CallableStatement callableStatement = null;
    public ResultSet resultSet = null;
    public Statement statement = null;
    //    public int tamanioObservable =0;
    public ArrayList<String> dataContainer = new ArrayList<>();
    public String resultQuery = "";
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



}

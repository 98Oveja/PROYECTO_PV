package utils;
import java.sql.*;
import java.util.ArrayList;

public class ConsultasVentasCompras {
    public static String name_and_lastname;
    public static ConnectionUtil cone = new ConnectionUtil();
    public static Connection connection = cone.getConnection();
    public static CallableStatement callableStatement = null;
    public static ResultSet resultSet = null;
    public static Statement statement = null;
    public static ArrayList<String> dataContainer = new ArrayList<>();
    public static String resultQuery = "";
//    GETERS AND SETERS
//    public static String getName_and_lastname(){return name_and_lastname;}
//    public void setName_and_lastname(String name_and_lastname){this.name_and_lastname = name_and_lastname;}
    //  METODOS Y FUNCIONES QUE EJECUTAN LOS SCRIPTS DE LA BASE DE DATOS PARA OBETENER LOS DATOS NECESARIOS
    public static String getIdCostumerInDB(String Nombre, String Apellido) {
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
    public static ArrayList listadoClientes() {
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
    public static ArrayList listaProductos() {
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
    public static ArrayList getCustomerDatabyId(String idCliente) {
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
    public static ArrayList getProductByName(String thisProduct){
        try{
            dataContainer.clear();
            resultQuery = "";
            callableStatement = connection.prepareCall("{call getDataProductsByNameProd(?)}");
            callableStatement.setString(1, thisProduct);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                dataContainer.add(resultSet.getString("DISPONIBILIDAD")+"#"+
                        resultSet.getDouble("PRECIO_VENTA")+"#"+
                        resultSet.getString("CODIGO_PRODUCTO")+"#"+
                        resultSet.getString("DESCRIPCION")+"#"+
                        resultSet.getInt("ID_MARCA"));

            }
        } catch (SQLException e) {
            System.out.println("I can't get the Product's Data : "+e.getMessage());
        }
        return dataContainer;
    }
//
    public static ArrayList listaProveedores(){
        try {
            dataContainer.clear();
            resultQuery = "";
            String sql = "{call getAllProveedores()}";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                resultQuery = resultSet.getString("PRIMER_NOMBRE")+" "
                        +resultSet.getString("PRIMER_APELLIDO");
                dataContainer.add(resultQuery);}
        } catch (SQLException e) {
            System.out.println("Error loading all Proveedores: " + e.getMessage());
        }
        return dataContainer;
    }
    public static ArrayList getDataProveedoreByName(String Nombre, String Apellido) {
        try {
            dataContainer.clear();
            resultQuery = "";
            callableStatement = connection.prepareCall("{call getDataProveedorbyNameAndLasname(?,?)}");
            callableStatement.setString(1, Nombre);
            callableStatement.setString(2, Apellido);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                dataContainer.add(resultSet.getString("DIRECCION")+"#"+
                        resultSet.getString("TELEFONO")+"#"+
                        resultSet.getString("NO_CUENTA")+"#"+
                        resultSet.getString("ORG"));
            }
        } catch (SQLException e) {
            System.out.println("I can't get the customer's ID: " + e.getMessage());
        }
        return dataContainer;
    }
    public static String getNameMarcabyID(String idMarcaSTR){
        String Marca = null;
        try{
            callableStatement = connection.prepareCall("{call getNameMarcbyID(?)}");
            callableStatement.setInt(1, Integer.parseInt(idMarcaSTR));
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Marca = resultSet.getString("NOMBRE");
            }
        } catch (SQLException e) {
            System.out.println("I can't get the Marca Name becaus : "+e.getMessage());
        }
        return Marca;
    }
    public static ArrayList ListadeMarcas() {
        try {
            dataContainer.clear();
            resultQuery = "";
            String sql = "{call getAllMarcars()}";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                resultQuery = resultSet.getString("NOMBRE");
                dataContainer.add(resultQuery);
            }
        } catch (SQLException e) {
            System.out.println("Error loading all Marcs Because: " + e.getMessage());
        }
        return dataContainer;
    }
    public static ArrayList ListadeCategorias() {
        try {
            dataContainer.clear();
            resultQuery = "";
            String sql = "{call getAllCategory()}";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                resultQuery = resultSet.getString("NOMBRE");
                dataContainer.add(resultQuery);
            }
        } catch (SQLException e) {
            System.out.println("Error loading all Category Because: " + e.getMessage());
        }
        return dataContainer;
    }
    public static ArrayList NombresProductoporNombreMarca(String nombreMarca){
        try{
            dataContainer.clear();
            resultQuery = "";
            callableStatement = connection.prepareCall("{call getProductNameByMarcName (?)}");
            callableStatement.setString(1, nombreMarca);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                dataContainer.add(resultSet.getString("PRODUCTOS.NOMBRE")+": "+resultSet.getString("PRODUCTOS.DESCRIPCION")
                );
            }
        } catch (SQLException e) {
            System.out.println("I can't get the Customer's Data : "+e.getMessage());
        }
        return dataContainer;

    }
    public static ArrayList returnNameandDescriptionByCategory(String nombreCategoria){
        try{
            dataContainer.clear();
            resultQuery = "";
            callableStatement = connection.prepareCall("{call getProductsByCategoryName(?)}");
            callableStatement.setString(1, nombreCategoria);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                dataContainer.add(resultSet.getString("PRODUCTOS.NOMBRE")+": "+
                        resultSet.getString("PRODUCTOS.DESCRIPCION")
                );
            }

        } catch (SQLException e) {
            System.out.println("I can't get the Customer's Data : "+e.getMessage());
        }
        return dataContainer;
    }
    public static ArrayList selectProductoByCodeinLikeQuery(String codigo) {
        try{
            dataContainer.clear();
            resultQuery = "";
            callableStatement = connection.prepareCall("{CALL getProductsByLikeCode(?)}");
            callableStatement.setString(1, codigo);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                dataContainer.add(resultSet.getString("PRODUCTOS.NOMBRE")+": "+
                        resultSet.getString("PRODUCTOS.DESCRIPCION")
                );
            }

        } catch (SQLException e) {
            System.out.println("I can't get the product data by code : "+e.getMessage());
        }
        return dataContainer;
    }
    public static ArrayList selectProductoByNameinLikeQuery(String nombre) {
        try{
            dataContainer.clear();
            resultQuery = "";
            callableStatement = connection.prepareCall("{call getProductbyLikeName(?)}");
            callableStatement.setString(1, nombre);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                dataContainer.add(resultSet.getString("PRODUCTOS.NOMBRE")+": "+
                        resultSet.getString("PRODUCTOS.DESCRIPCION")
                );
            }

        } catch (SQLException e) {
            System.out.println("I can't get the product data by code : "+e.getMessage());
        }
        return dataContainer;
    }
//
    public static ArrayList seleccionarProductoParaComprar(String nombre) {
    try{
        dataContainer.clear();
        resultQuery = "";
        callableStatement = connection.prepareCall("{call getProducByNameforBuy(?)}");
        callableStatement.setString(1, nombre);
        resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            dataContainer.add(
                    resultSet.getString("ID_MARCA")+"#"+
                    resultSet.getString("DESCRIPCION")+"#"+
                    resultSet.getString("CODIGO_PRODUCTO")+"#"+
                    resultSet.getString("DISPONIBILIDAD")+"#"+
                    resultSet.getString("UNIDAD_DE_MEDICION")+"#"+
                    resultSet.getString("PRECIO_VENTA")+"#"+
                    resultSet.getString("PRECIO_COMPRA")
            );
        }
    } catch (SQLException e) {
        System.out.println("I can't get the product data by code : "+e.getMessage());
    }
    return dataContainer;
}
}

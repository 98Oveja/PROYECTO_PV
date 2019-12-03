package controllers.Productos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.scene.image.*;
import jdk.jfr.Category;
import utils.ConnectionUtil;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class newproducto implements Initializable {
    @FXML
    private TextField Nombre;

    @FXML
    private TextField Marca;

    @FXML
    private ComboBox<String> Categoria;

    @FXML
    private ComboBox<String> Proveedores;

    @FXML
    private TextField Cantidad;

    @FXML
    private TextField PVenta;

    @FXML
    private TextField Pcompra;

    @FXML
    private TextField CodigoP;

    @FXML
    private Label imagelabe;

    @FXML
    private ImageView imageview;

    @FXML
    private Button close;

    private Connection conexion = null;
    ConnectionUtil conn = null;

    public int consultasID(String name, String tabla) {
        int id = 1;
        String sql;
        if (tabla=="PROVEEDORES"){
            sql = "SELECT ID_PROVEEDOR FROM PROVEEDORES Where ORG = " + "\'" + name + "\' ;";
        }
        else{
        sql = "SELECT ID_CATEGORIA FROM CATEGORIAS Where NOMBRE = " + "\'" + name + "\' ;";
        }
        try {
            conexion= conn.getConnection();
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
//            preparedStatement.setString(1,tabla);
  //          preparedStatement.setString(2,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("NO FUNCIONA :*)");
            } else {
                if(tabla=="CATEGORIAS")
                {
                    id = resultSet.getInt("ID_CATEGORIA");
                }
                else{
                    id = resultSet.getInt("ID_PROVEEDORES");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    String direccion, codigo;

    public void agregarProducto(ActionEvent actionEvent) throws SQLException {
        int idCateg, idProv;
        int estado=1;
        String query = null;
        String name = Nombre.getText();
        String marca = Marca.getText();
        String cantidad = Cantidad.getText();
        String venta = PVenta.getText();
        String compra = Pcompra.getText();
        String codigo = CodigoP.getText();
        String categoria = Categoria.getValue();
        String proveedores = Proveedores.getValue();
        System.out.println(categoria + proveedores);
        if(!name.isEmpty()&&!marca.isEmpty()&&!cantidad.isEmpty()&&!cantidad.isEmpty()&&!venta.isEmpty()&&!compra.isEmpty()&&!codigo.isEmpty()) {
            idCateg =  consultasID(categoria, "CATEGORIAS");
            idProv = consultasID(proveedores, "PROVEEDORES");
            if (cantidad.equals(0)){
                estado=0;
            }
            query= "INSERT INTO PRODUCTOS (ID_PRODUCTO,ID_CATEGORIAS,ID_PROVEEDORES,ID_COMPRA,MARCA,NOMBRE," +
                    "CANTIDAD,PRECIO_COMPRA,PRECIO_VENTA,IMG,ESTADO, CODIGO)" +
                    "VALUES (14," + idCateg +"," + idProv +"," + 0 +"," +  "\'" + marca + "\'" + "," + "\'" + name + "\'" + "," + cantidad +","
                    + compra +"," + venta +"," + "\'" + direccion + "\'" +"," + estado +"," + codigo + ");";
            System.out.println(query);
            conexion = conn.getConnection();
            PreparedStatement preparedStatement = conexion.prepareStatement(query);            //insert.execute(query);
            preparedStatement.execute();
        }
        else {
            Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
            dialogoAlerta.setTitle("Aviso");
            dialogoAlerta.setHeaderText(null);
            dialogoAlerta.setContentText("Debe llenar los siguientes campos de manera obligatoria: Nombre   Codigo  Categoria");
            dialogoAlerta.initStyle(StageStyle.UTILITY);
            dialogoAlerta.showAndWait();
        }
        //    conexion.cerrarConexion();
    }

    public void handleActionClose(MouseEvent mouseEvent) {

        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public ArrayList Categorias()
    {
        ArrayList<String> list = new ArrayList<String>();
        try {
            String Query = "SELECT (ID_CATEGORIA),(NOMBRE) FROM CATEGORIAS";
            conexion = conn.getConnection();
            Statement instruccion= conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while(resultado.next()) {
                    String nombre= resultado.getString("NOMBRE");
                    list.add(nombre);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public ArrayList Proveedores()
    {
        ArrayList<String> list = new ArrayList<String>();
        try {
            String Query = "SELECT (ID_PROVEEDOR),(ORG) FROM PROVEEDORES";
            conexion = conn.getConnection();
            Statement instruccion= conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while(resultado.next()) {
                    String nombre= resultado.getString("ORG");
                    list.add(nombre);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void Search(ActionEvent actionEvent) throws IOException {

        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null)
        {
            direccion= selectedFile.getPath();
          //  System.out.println(direccion);
          // System.out.println(direccion.substring(2,direccion.length()));
            Image image;
            image = new Image("file:/"+direccion);
            imageview.setImage(image);
        } else{
            System.out.println("file is not valid");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> lista = Categorias();
          ObservableList<String> categ = FXCollections.observableArrayList();
          categ.addAll(lista);
          Categoria.setItems(categ);

        ArrayList<String> lista1 = Proveedores();
        ObservableList<String> provee = FXCollections.observableArrayList();
        provee.addAll(lista1);
        Proveedores.setItems(provee);
    }

}
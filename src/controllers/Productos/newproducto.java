package controllers.Productos;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.scene.image.*;
import utils.ConnectionUtil;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class newproducto implements Initializable {
    public Pane PanelContenedor;                    @FXML private TextField Nombre;
    @FXML private TextField Marca;                  @FXML private ComboBox<String> Categoria;
    @FXML private ComboBox<String> Proveedores;     @FXML private TextField Cantidad;
    @FXML private TextField PVenta;                 @FXML private TextField Pcompra;
    @FXML private Label codigo;                     @FXML private ImageView imageview;
    @FXML private TextField Descripcion;

    ArrayList<String> NOMBRE = new ArrayList<String>();
    ArrayList<String> ID = new ArrayList<String>();
    Connection conexion = null;
    ConnectionUtil conn = new ConnectionUtil();
    eventos event = new eventos();

    public int consultasID(String name, String tabla) {
        int id = 1;
        String sql;
        if (tabla=="PROVEEDORES"){
            sql = "SELECT ID_PROVEEDORES FROM PROVEEDORES Where ORG = " + "\'" + name + "\' ;";
        }
        else{
        sql = "SELECT ID_CATEGORIA FROM CATEGORIAS Where NOMBRE = " + "\'" + name + "\' ;";
        }
        try {
            conexion= conn.getConnection();
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
               // System.out.println("NO FUNCIONA");
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
           // e.printStackTrace();

        }
        return id;
    }

    public void obtenernombres(){
        NOMBRE.clear();
        String nombre;
        String sql = "SELECT NOMBRE FROM PRODUCTOS";
        try {
            conexion = conn.getConnection();
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                    nombre = resultSet.getString("NOMBRE");
                    NOMBRE.add(nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String direccion;

    public void limipiar()
    {
        ids();
        Nombre.clear();
        Marca.clear();
        Cantidad.clear();
        PVenta.clear();
        Pcompra.clear();
        codigo.setText("");
        Descripcion.clear();
        imageview.setImage(null);
    }

    boolean validar(){
        String name;
        name=Nombre.getText();
        boolean result = NOMBRE.contains(name);
        return result;
    }

    public void agregarProducto(ActionEvent actionEvent) throws SQLException {
        int idCateg, idProv;
        int estado=1;
        String query = null;
        String name = Nombre.getText();
        String marca = Marca.getText();
        String cantidad = Cantidad.getText();
        String venta = PVenta.getText();
        String compra = Pcompra.getText();
        String codig = codigo.getText();
        String categoria = Categoria.getValue();
        String proveedores = Proveedores.getValue();
        String desc= Descripcion.getText();
        //System.out.println(categoria + proveedores);
        if(direccion!=null){
            direccion=direccion.replace("\\","*");
        }
        if(!name.isEmpty()&&!marca.isEmpty()&&!cantidad.isEmpty()&&!venta.isEmpty()&&!compra.isEmpty()&&!codig.isEmpty()&&validar()!=true) {
            idCateg =  consultasID(categoria, "CATEGORIAS");
            idProv = consultasID(proveedores, "PROVEEDORES");
            if (cantidad.equals(0)){
                estado=0;
            }
            query= "INSERT INTO PRODUCTOS (ID_CATEGORIA,ID_PROVEEDORES,ID_COMPRA,MARCA,NOMBRE," +
                    "CANTIDAD,PRECIO_COMPRA,PRECIO_VENTA,IMG,ESTADO,DESCRIPCION,CODDIGO)" +
                    "VALUES ("+idCateg +","+idProv +"," + 1 +"," +  "\'"+marca+"\'" + "," + "\'"+name+"\'" + "," + cantidad +","
                    + compra +"," + venta +"," + "\'"+direccion+"\'" +"," + estado + "," + "\'"+desc +"\'" + "," +  "\'"+codig +"\'" +");";
            conexion = conn.getConnection();
            PreparedStatement preparedStatement = conexion.prepareStatement(query);            //insert.execute(query);
            preparedStatement.execute();
            limipiar();
        }
        else if(validar()==true){
            Alert dialogo= new Alert(Alert.AlertType.INFORMATION);
            dialogo.setHeaderText(null);
            dialogo.initStyle(StageStyle.UNDECORATED);
            dialogo.setContentText("El nombre del producto ya existe si desea puede editarlo en el modulo de productos");
            dialogo.showAndWait();
        }
        else {
            Alert dialogo= new Alert(Alert.AlertType.INFORMATION);
            dialogo.setHeaderText(null);
            dialogo.initStyle(StageStyle.UNDECORATED);
            dialogo.setContentText("Algunos campos no han sido rellenados por favor verifiquelos");
            dialogo.showAndWait();
        }
    }

    public ArrayList Categorias()
    {
        ArrayList<String> list = new ArrayList<String>();
        try {
            conexion = conn.getConnection();
            String Query = "SELECT NOMBRE FROM CATEGORIAS;";
            Statement instruccion= conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while(resultado.next()) {
                    String nombre= resultado.getString("NOMBRE");
                    list.add(nombre);
                }
            }
        } catch (SQLException e) {
            //System.out.println(e.getErrorCode());;
        }
        return list;
    }

    public ArrayList<String> Proveedores()
    {
        ArrayList<String> list = new ArrayList<String>();
        try {
            String Query = "SELECT ORG FROM PROVEEDORES;";

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
           // System.out.println("ERRRRRRRRRRROOOOOOOOOOOOR!!!!!! :(");
        }
        return list;
    }

    public void Search(ActionEvent actionEvent) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null)
        {
            direccion= selectedFile.getPath();
            Image image;
            image = new Image("file:/"+direccion);
            imageview.setImage(image);
        } else{
                //System.out.println("file is not valid");
        }
    }
    void imit(){
        ArrayList<String> lista = Categorias();
        ObservableList<String> categ = FXCollections.observableArrayList();
        categ.addAll(lista);
        Categoria.setItems(categ);
        ArrayList<String> lista1 = Proveedores();
        ObservableList<String> provee = FXCollections.observableArrayList();
        provee.addAll(lista1);
        Proveedores.setItems(provee);
        event.validarSoloNumeros(Cantidad);
        event.validarSoloNumeros(PVenta);
        event.validarSoloNumeros(Pcompra);
    }
    void ids(){
        ID.clear();
        try {
            String nombre;
            conexion = conn.getConnection();
            String Query = "SELECT ID_PRODUCTO FROM PRODUCTOS;";
            Statement instruccion= conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while(resultado.next()) {
                    nombre= resultado.getString("ID_PRODUCTO");
                    ID.add(nombre);
                }
            }
        } catch (SQLException e) {
            //System.out.println(e.getErrorCode());;
        }
    }

    void generarCodigo(){
        int l=ID.size();
        if (l==0){
            codigo.setText("00"+l);
        }
        else{
            l=l+1;
            codigo.setText("00"+l);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    imit();
    ids();
    obtenernombres();
    }

    public void Cerrar(ActionEvent actionEvent) {
        Stage stage = (Stage) this.PanelContenedor.getScene().getWindow();
        stage.close();
    }

    public void Generador(KeyEvent keyEvent) {
        if(Nombre.getText()!=null){
            generarCodigo();
        }
    }
}
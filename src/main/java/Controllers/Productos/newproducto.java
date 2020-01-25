package Controllers.Productos;

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
import Models.products.marcas;
import Utils.ConnectionUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class newproducto implements Initializable {
    public Pane PanelContenedor;                    @FXML private TextField Nombre;
    @FXML private ComboBox<String> Categoria;
    @FXML private ComboBox<String> Proveedores,Marca;     @FXML private TextField Cantidad;
    @FXML private TextField PVenta;                 @FXML private TextField Pcompra;
    @FXML private Label codigo;                     @FXML private ImageView imageview;
    @FXML private TextField Descripcion;


    ArrayList<String> NOMBRE = new ArrayList<String>();
    List<String> PROVEEDOR= new ArrayList<String>();
    List<String> CATEGORIA= new ArrayList<String>();
    Connection conexion = null;
    ConnectionUtil conn = new ConnectionUtil();
    eventos event = new eventos(); int cantidad;

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
        int estado=1;
        String query = null;
        String name = Nombre.getText();
        String marca = Marca.getValue();
        String cantidad = Cantidad.getText();
        String venta = PVenta.getText();
        String compra = Pcompra.getText();
        String codig = codigo.getText();
        String categoria = Categoria.getValue();
        String proveedor = Proveedores.getValue();
        String desc= Descripcion.getText();
        //System.out.println(categoria + proveedores);
        if(direccion!=null){
            direccion=direccion.replace("\\","*");
        }
        if(!name.isEmpty()&&!marca.isEmpty()&&!cantidad.isEmpty()&&!venta.isEmpty()&&!compra.isEmpty()&&!codig.isEmpty()&&validar()!=true) {
            if (cantidad.equals(0)){
                estado=0;
            }
            query= "INSERT INTO PRODUCTOS (ID_CATEGORIA,ID_PROVEEDORES,ID_COMPRA,MARCA,NOMBRE," +
                    "CANTIDAD,PRECIO_COMPRA,PRECIO_VENTA,IMG,ESTADO,DESCRIPCION,CODIGO)" +
                    "VALUES ((SELECT ID_CATEGORIA FROM CATEGORIAS WHERE NOMBRE=\'"+Categoria +"\'),"+
                    "(SELECT ID_PROVEEDOR FROM PROVEEDORES WHERE ORG=\'"+proveedor+"\')," + 1 +",\'"+marca+"\',\'"+name+"\'," + cantidad +","
                    + compra +"," + venta +",\'"+direccion+"\'," + estado + ",\'"+desc +"\',\'"+codig +"\');";
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

    public void Categorias()
    {
     try {
            conexion = conn.getConnection();
            String Query = "SELECT NOMBRE FROM CATEGORIAS ORDER BY NOMBRE ASC;";
            Statement instruccion= conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while(resultado.next()) {
                    String nombre= resultado.getString("NOMBRE");
                    CATEGORIA.add(nombre);
                }
            }
        } catch (SQLException e) {
        }
    }

    public void Proveedores()
    {
        try {
            String Query = "SELECT ORG FROM PROVEEDORES;";
            conexion = conn.getConnection();
            Statement instruccion= conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while(resultado.next()) {
                    String nombre= resultado.getString("ORG");
                    PROVEEDOR.add(nombre);
                }
            }
        } catch (SQLException e) {
        }
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
        }
    }
    void imit(){
        Categorias();Proveedores();
        ObservableList<String> observableList = FXCollections.observableList(CATEGORIA);
        ObservableList<String> observableList1 = FXCollections.observableList(PROVEEDOR);
        Categoria.setItems(observableList);
        Proveedores.setItems(observableList1);
        event.validarSoloNumeros(Cantidad);
        event.validarSoloNumeros(PVenta);
        event.validarSoloNumeros(Pcompra);
    }
    void ids(){
        try {
            conexion = conn.getConnection();
            String Query = "SELECT COUNT(*) AS CANTIDAD FROM PRODUCTOS;";
            Statement instruccion= conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while(resultado.next()) {
                    cantidad=resultado.getInt("CANTIDAD");
                }
            }
        } catch (SQLException e) {
        }
    }

    void generarCodigo(){
        if (cantidad==0){
            codigo.setText("001");
        }
        else{
            cantidad=+1;
            codigo.setText("00"+cantidad);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        marcas marc = new marcas();
        ObservableList<String> Marc = FXCollections.observableArrayList();
        Marc.addAll(marc.Marcas());
        Marca.setItems(Marc);
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
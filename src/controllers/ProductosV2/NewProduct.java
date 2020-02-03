package controllers.ProductosV2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import models.products.UnidadesDeMedida;
import utils.ConnectionUtil;
import utils.ImageChooser;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewProduct implements Initializable {
    @FXML private TextField Nombre,Disponibilidad,PrecioCompra,PrecioVenta,CodigoProducto,Descripcion;
    @FXML private ComboBox<String> Marca,UnidadMedida;
    @FXML private VBox VBoxCategoria;
    @FXML private ImageView Photo;
    ArrayList<CheckBox> arrayListCategoria = new ArrayList<CheckBox>();
    ArrayList<TextField> textFields= new ArrayList<TextField>();
    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;
    ArrayList<String> nombresProductos = new ArrayList<String>();
    String imagenphoto;

    public void AgregarImagen(ActionEvent actionEvent) {
        ImageChooser imageChooser = new ImageChooser();
        imagenphoto=imageChooser.getImage();
        Image image = new Image("file:/"+imagenphoto);
        Photo.setImage(image);
    }

    public void TextfieldsArray(){
        textFields.add(Nombre);
        textFields.add(Disponibilidad);
        textFields.add(PrecioCompra);
        textFields.add(PrecioVenta);
    }

    public void insertproducto(){
        String Query= "INSERT INTO PRODUCTOS(ID_MARCA, NOMBRE, DESCRIPCION, CODIGO, DISPONIBILIDAD, UNIDAD_DE_MEDICION, PRECIO_COMPRA, PRECIO_VENTA, IMG)\n" +
                "VALUES ((SELECT ID_MARCA FROM MARCAS WHERE MARCA='"+Marca.getValue()+"'),'"+Nombre.getText()+"','"+Descripcion.getText()+"','"+CodigoProducto.getText()+"',"+Disponibilidad.getText()
                +" ,'"+UnidadMedida.getValue()+"',"+PrecioCompra.getText()+","+PrecioVenta.getText()+",'"+imagenphoto+"');";
        try {
            conexion = conn.getConnection();
            PreparedStatement preparedStatement = conexion.prepareStatement(Query);
            preparedStatement.execute();
        }catch (Exception e){
            System.out.println(Query);
            System.out.println("Insertar producto"+e);}
    }

    public void insertCategoria(){
        String Query= "INSERT INTO DETALLE_CATEGORIA_PRODUCTOS(ID_PRODUCTO, ID_CATEGORIA) VALUES ";
        int aux=0;
        for (int i = 0; i < arrayListCategoria.size(); i++) {
            if (arrayListCategoria.get(i).isSelected()){
                if (aux>0){Query=Query+",";}
                Query=Query+"((SELECT ID_PRODUCTO FROM PRODUCTOS WHERE NOMBRE ='"+Nombre.getText()+"'),(SELECT ID_CATEGORIA FROM CATEGORIAS WHERE NOMBRE='"+arrayListCategoria.get(i).getText()+"'))";
                aux=1;
            }
        }
        if (aux==1){
            Query=Query+";";
            try{
                conexion = conn.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(Query);
                preparedStatement.execute();
                System.out.println(Query);}
            catch (Exception e){
                System.out.println("Insertar categoria"+e);
                System.out.println(Query);
            }
        }
    }

    public void GuardarProducto(ActionEvent actionEvent) {
        int aux = 0;
        if (!nombresProductos.contains(Nombre.getText().toUpperCase())) {
            for (int i = 0; i < arrayListCategoria.size(); i++) {
                if (arrayListCategoria.get(i).isSelected()) {
                    System.out.println(arrayListCategoria.get(i).getText());
                }
            }
            for (int i = 0; i < textFields.size(); i++) {
                if (textFields.get(i).getText().isEmpty()) {
                    Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
                    dialogo.setHeaderText(null);
                    dialogo.initStyle(StageStyle.UNDECORATED);
                    dialogo.setContentText("El campo " + textFields.get(i).getId() + " aun no se ha llenado \n por favor verifique");
                    dialogo.showAndWait();aux=0;
                    break;
                } else {
                    aux=1;
                }
            }
            if (aux==1){
                insertproducto();
                insertCategoria();
            }
        } else if (nombresProductos.contains(Nombre.getText().toUpperCase())) {
            Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
            dialogo.setHeaderText(null);
            dialogo.initStyle(StageStyle.UNDECORATED);
            dialogo.setContentText("El Producto ya existe puede modificarlo en el modulo de productos");
            dialogo.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nombresProductosexistentes();
        TextfieldsArray();
        Unidades();
        categ();
        Marcas();
    }

    public void Unidades(){
        UnidadesDeMedida unidadesDeMedida= new UnidadesDeMedida();
        List<String> list = unidadesDeMedida.Unidades();
        ObservableList<String> observableList = FXCollections.observableList(list);
        UnidadMedida.setItems(observableList);
    }

    public void Marcas() {
        List<String> listmarcas = new ArrayList<String>();
        try {
            ResultSet resultados = consultas("SELECT MARCA FROM MARCAS ORDER BY MARCA DESC;");
            if (resultados != null) {
                while (resultados.next()) {
                    listmarcas.add(resultados.getString("MARCA"));
                }
                ObservableList<String> observableList = FXCollections.observableList(listmarcas);
                Marca.setItems(observableList);
            }
        } catch (SQLException e) {}
    }

    public void categ(){
        try {
            ResultSet resultados = consultas("SELECT NOMBRE FROM CATEGORIAS order by NOMBRE desc;");
            if (resultados != null) {
                while (resultados.next()) {
                    CheckBox checkBox = new CheckBox();
                    checkBox.setText(resultados.getString("NOMBRE"));
                    checkBox.setStyle("-fx-text-fill: rgba(0,0,0,.8);-fx-text-alignment: center;" +
                            "-fx-background-color: white;-fx-pref-width: 238px");
                    arrayListCategoria.add(checkBox);
                }
                if (arrayListCategoria.size() != 0) {
                    for (int i = 0; i < arrayListCategoria.size(); i++) {
                        VBoxCategoria.getChildren().add(arrayListCategoria.get(i));
                    }
                }
            }
        }catch(SQLException e){}
    }

    public ResultSet consultas(String query){
        try {
            conexion = conn.getConnection();
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(query);
            if (resultado != null) {
                return resultado;
                }else {return null;}
            } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
            }
        }

    public void nombresProductosexistentes(){
        NewProduct newProduct = new NewProduct();
        try {
            ResultSet resultados = newProduct.consultas("SELECT NOMBRE FROM PRODUCTOS;");
            if (resultados != null) {
                while (resultados.next()) {
                    nombresProductos.add(resultados.getString("NOMBRE").toUpperCase());
                }
            }
        }catch(SQLException e){}
    }
    }
package controllers.ProductosV2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.products.UnidadesDeMedida;
import utils.ConnectionUtil;
import utils.Consultas;
import utils.ImageChooser;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewProduct implements Initializable {
    @FXML private TextField Nombre,Disponibilidad,PrecioCompra,PrecioVenta,CodigoProducto,Descripcion;
    @FXML private ComboBox<String> Marca,UnidadMedida;
    @FXML private VBox VBoxCategoria;
    @FXML private ImageView Photo;
    ArrayList<CheckBox> arrayList = new ArrayList<CheckBox>();
    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;

    public void AgregarImagen(ActionEvent actionEvent) {
        ImageChooser imageChooser = new ImageChooser();
        imageChooser.getImage();
    }

    public void GuardarProducto(ActionEvent actionEvent) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).isSelected()){
                System.out.println(arrayList.get(i).getText());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                    arrayList.add(checkBox);
                    if (arrayList.size() != 0) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            VBoxCategoria.getChildren().add(arrayList.get(i));
                        }
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
    }
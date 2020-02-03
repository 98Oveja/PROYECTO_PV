package controllers.ProductosV2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import utils.ConnectionUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScrollPaneCateg implements Initializable {

    @FXML private Pane ConteinerMain;
    @FXML private GridPane GridContainer;

    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;

    ArrayList<String> Categorias = new ArrayList<String>();
    static String nombre,descripcion,image;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datos();
    }

    public void datos(){
        Categorias.clear();String dato;
        String Query="SELECT NOMBRE,DESCRIPCION,IMG_URL FROM CATEGORIAS";
        try{
            conexion = conn.getConnection();
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while (resultado.next()) {
                    dato= resultado.getString("NOMBRE")+"#"+resultado.getString("DESCRIPCION")+"#"+resultado.getString("IMG_URL");
                    Categorias.add(dato); }
                rellenar();
            }
        }catch (Exception e){}
    }

    private void rellenar() {
        int contador = 0;
        for (int i = 0; i <=Categorias.size()/2; i++) {
            for (int j = 0; j < 2; j++) {
                if (contador<Categorias.size()) {
                    String[] texto =Categorias.get(contador).split("#");
                    nombre=texto[0];    descripcion=texto[1];   image=texto[2];
                    System.out.println(i+" "+j);
                    card(i,j);
                }
                contador+=1;
            }
        }
    }

    private void card(int row, int column) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ProductosV2/CardCateg.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {e.printStackTrace();}
        GridContainer.add(parent, column, row);
    }

    public String getNombre(){
        return nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getImage(){
        return image;
    }

    public void Close(ActionEvent actionEvent) {

    }

    public void Nuevo(ActionEvent actionEvent) throws IOException {
        String fxmlcarga="/fxml/ProductosV2/newCategoria.fxml";
        cargar(fxmlcarga);
    }

    public void cargar(String fxml){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {e.printStackTrace();}
        ConteinerMain.getChildren().add(parent);
    }
}
package controllers.ProductosV2;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import utils.ConnectionUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML private VBox Container;

    ArrayList<String> Productos = new ArrayList<String>();
    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;

    static String nombre,cantidad,descripcion,precioCompra,precioVenta,photo;
    static int estado,position;static double width;
    String Query1 = "SELECT NOMBRE,CANTIDAD,PRECIO_COMPRA,PRECIO_VENTA,IMG,DESCRIPCION FROM PRODUCTOS WHERE ESTADO=1;";
    String Query0 = "SELECT NOMBRE,CANTIDAD,PRECIO_COMPRA,PRECIO_VENTA,IMG,DESCRIPCION FROM PRODUCTOS WHERE ESTADO=0;";

    public void consulta(String Query){
        Productos.clear();String dato;
        try{
            conexion = conn.getConnection();
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while (resultado.next()) {
                    dato= resultado.getString("NOMBRE")+"#"+resultado.getString("CANTIDAD")+"#"+resultado.getString("PRECIO_VENTA")
                            +"#"+resultado.getString("IMG")+"#"+resultado.getString("PRECIO_COMPRA")+"#"+resultado.getString("DESCRIPCION");
                    Productos.add(dato); }
            rellenar(position);}
        }catch (Exception e){}
    }

    public String getNombre(){return nombre;}
    public String getCantidad(){return cantidad;}
    public String getDescripcion(){return descripcion;}
    public String getPrecioCompra(){return precioCompra;}
    public String getPrecioVenta(){return precioVenta;}
    public String getPhoto(){return photo;}
    public int getEstado(){return estado;}
    public double getwidthpane(){return width;}

    public void rellenar(int posicion){
        int size=Productos.size();int pos=posicion*5;int max=pos+5;
        for (int i = pos; i < max; i++) {
            if (i<size){
                String[] texto=Productos.get(i).split("#");
                nombre=texto[0];            cantidad=texto[1];
                precioVenta=texto[2];       photo=texto[3]; width=Container.getPrefWidth();
                card();
            }
        }
    }

    public void card(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ProductosV2/CardProduct.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
            Container.getChildren().addAll(parent);
        } catch (IOException e) {}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { estado=1;consulta(Query1);position=1;}
}
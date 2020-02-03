package controllers.ProductosV2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    String Query1 = "SELECT NOMBRE,DISPONIBILIDAD,PRECIO_COMPRA,PRECIO_VENTA,IMG,DESCRIPCION FROM PRODUCTOS WHERE ESTADO=1;";
    String Query0 = "SELECT NOMBRE,DISPONIBILIDAD,PRECIO_COMPRA,PRECIO_VENTA,IMG,DESCRIPCION FROM PRODUCTOS WHERE ESTADO=0;";

    public void consulta(String Query){
        Productos.clear();String dato;
        try{
            conexion = conn.getConnection();
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while (resultado.next()) {
                    dato= resultado.getString("NOMBRE")+"#"+resultado.getString("DISPONIBILIDAD")+"#"+resultado.getString("PRECIO_VENTA")
                            +"#"+resultado.getString("IMG")+"#"+resultado.getString("PRECIO_COMPRA")+"#"+resultado.getString("DESCRIPCION");
                    Productos.add(dato); }
            rellenar(position);}
        }catch (Exception e){
            System.out.println(e);
        }
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
        int size=Productos.size();int pos=posicion*5;
        System.out.println(size);
        for (int i = pos; i < pos+5; i++) {
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
    public void initialize(URL url, ResourceBundle resourceBundle) { estado=1;consulta(Query1);}

    public void AbrirCategoria(ActionEvent actionEvent){
        String fxml="/fxml/ProductosV2/ScrollPaneCateg.fxml";
        try {
            Abrirmodal(fxml,772.00,515.00);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Abrirmodal(String fxml,Double x,Double y) throws IOException {
        Double height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        Double width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        final Stage primaryStage = new Stage();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initOwner(primaryStage);
        dialog.setX((width/2)-(x/2));
        dialog.setY((height/2)-(y/2));
        Scene dialogScene = null;
        dialogScene = new Scene(FXMLLoader.load(getClass().getResource(fxml)));
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void AgregarProdcuto(ActionEvent actionEvent) {
        try {
            Abrirmodal("/fxml/ProductosV2/NewProduct.fxml",800.00,600.00);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
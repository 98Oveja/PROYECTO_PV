package controllers.Productos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditProduct implements Initializable {
    @FXML    private TextField Marca;           @FXML    private Label codigo1;
    @FXML    private TextField Cantidad;        @FXML    private TextField PVenta;
    @FXML    private TextField Pcompra;         @FXML    private ImageView imagen;
    @FXML    private Label codigo;              @FXML    private TextArea Descripcion;
    @FXML    private Pane PanelContenedor;

    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;
    String IMG= null,Nombre = null;

    public void Datos(String nombre) {
        String marca = null, DESCRIPCION= null;
        int idProducto = 0;
        double PrecioCompra =0, PrecioVenta=0,cantidad=0;
        try {
            String Query = "SELECT ID_PRODUCTO,MARCA,NOMBRE,CANTIDAD,PRECIO_COMPRA,PRECIO_VENTA,IMG,DESCRIPCION FROM PRODUCTOS WHERE NOMBRE='" + nombre + "';";
            //String Query = "SELECT * FROM PRODUCTOS WHERE NOMBRE=\"Martillo\";";
            conexion = conn.getConnection();
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while (resultado.next()) {
                    idProducto = resultado.getInt("ID_PRODUCTO");
                    marca = resultado.getString("MARCA");
                    Nombre = resultado.getString("NOMBRE");
                    cantidad = resultado.getDouble("CANTIDAD");
                    PrecioCompra = resultado.getDouble("PRECIO_COMPRA");
                    PrecioVenta = resultado.getDouble("PRECIO_VENTA");
                    IMG = resultado.getString("IMG");
                    DESCRIPCION = resultado.getString("DESCRIPCION");

                }
            }
            codigo1.setText(Nombre);
            Marca.setText(marca);
            Cantidad.setText(cantidad+"");
            PVenta.setText(PrecioVenta+"");
            Pcompra.setText(PrecioCompra+"");
            codigo.setText("00"+idProducto);
            Descripcion.setText(DESCRIPCION);
            String img = IMG.replace('*','\\');
            Image image = new Image("file:/" + img);
            imagen.setImage(image);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void Search(ActionEvent actionEvent) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null)
        {
            IMG= selectedFile.getPath();
            Image image;
            image = new Image("file:/"+IMG);
            imagen.setImage(image);
        } else{
            //System.out.println("file is not valid");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventos event = new eventos();
        Datos(Controller.name);
        event.validarSoloLetras(Marca);
        event.validarSoloNumeros(Cantidad);
        event.validarSoloNumeros(PVenta);
        event.validarSoloNumeros(Pcompra);
    }

    public void Update(ActionEvent actionEvent) {
        String marca, cantidad, pventa, pcompra, desc;
        marca = Marca.getText(); cantidad= Cantidad.getText();
        pventa = PVenta.getText(); pcompra = Pcompra.getText();
        desc = Descripcion.getText();
        if(!marca.isEmpty()&&!cantidad.isEmpty()&&!pventa.isEmpty()&&!pcompra.isEmpty()&&!desc.isEmpty()){
            IMG=IMG.replace('\\','*');
            String query = "UPDATE PRODUCTOS SET MARCA = '" + marca+"',CANTIDAD='"+cantidad+"',"+
                    "PRECIO_COMPRA='"+pcompra+"',PRECIO_VENTA='"+pventa+"',DESCRIPCION='"+desc+"',IMG='"+IMG+"' WHERE NOMBRE='"+codigo1.getText()+"';";
            Alert dialogo = new Alert(Alert.AlertType.CONFIRMATION);
            dialogo.setTitle("Actualizar Producto");
            dialogo.setHeaderText(null);
            dialogo.initStyle(StageStyle.UNDECORATED);
            dialogo.setContentText("Actualizaras el siguiente producto:\n  -" + Nombre +
                    "\n\n!No podras regresarlo a sus datos anteriores");
            Optional<ButtonType> result = dialogo.showAndWait();
            if (result.get() == ButtonType.OK) {
                conexion = conn.getConnection();
                PreparedStatement preparedStatement = null;//insert.execute(query);
                try {
                    preparedStatement = conexion.prepareStatement(query);
                    preparedStatement.execute();
                    Stage stage = (Stage) this.PanelContenedor.getScene().getWindow();
                    stage.close();
                } catch (SQLException e) {
                }
            }
        }else{
            Alert dialogo= new Alert(Alert.AlertType.INFORMATION);
            dialogo.setHeaderText(null);
            dialogo.initStyle(StageStyle.UNDECORATED);
            dialogo.setContentText("Algunos campos no han sido rellenados por favor verifiquelos");
            dialogo.showAndWait();
    }
    }

    public void Cerrar(ActionEvent actionEvent) {
        Stage stage = (Stage) this.PanelContenedor.getScene().getWindow();
        stage.close();
    }
}
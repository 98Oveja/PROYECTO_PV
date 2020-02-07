package controllers.ProductosV2;

import controllers.AlertaController;
import controllers.employees.DelEmployController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;
import utils.ImageChooser;
import utils.closeView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MarkController implements Initializable {
    public Pane ContenedorMarca;
    public ImageView Imagen;
    public TextField Nombre;
    public TextArea Descripcion;

    ArrayList<String> nombresMarcas = new ArrayList<String>();
    String path, direccion;
    NewProduct newProduct = new NewProduct();
    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;
    Image imag = new Image("images/icons8_box_important_50px_1.png");
    Image exit = new Image("images/icon_close.png");
    AlertaController alertaController = new AlertaController();

    public void Addphoto() {
        ImageChooser imageChooser = new ImageChooser();
        path = imageChooser.getImage();
        Image image = new Image("file:/"+path);
        Imagen.setImage(image);
    }

    public void validateTextField() throws IOException {
        if(Nombre.getText().length() < 1 || Descripcion.getText().isEmpty() || Descripcion.getText().length() < 1){
            modalAlert("Alerta","Debe llenar todos los campos",2);
        }
    }
    public void modalAlert(String title,String messeger,int op) throws IOException {
        FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/DeleteEmploy.fxml"));
        Parent root = Loader.load();
        DelEmployController controller = Loader.getController();
        controller.loadDataClose(imag,title,messeger,op);
        Scene dialogo = new Scene(root);
        Stage stagedialog = new Stage();
        stagedialog.initStyle(StageStyle.UNDECORATED);
        stagedialog.initModality(Modality.APPLICATION_MODAL);
        stagedialog.setScene(dialogo);
        stagedialog.showAndWait();
    }
    public void insertMark() throws IOException {
        if (nombresMarcas.size() > 2 && nombresMarcas.contains(Nombre.getText().toUpperCase())){
            modalAlert("Alerta","Datos duplicados",2);
        }
        if(nombresMarcas.size() > 2 && !nombresMarcas.contains(Nombre.getText().toUpperCase())){
            String queryInsert= "INSERT INTO MARCAS (`NOMBRE`,`IMG_URL`) VALUES('"+Nombre.getText()+"','"+direccion+"')";
            try{
                conexion = conn.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(queryInsert);
                preparedStatement.execute();
            }catch(Exception ex){
                System.err.println("error ingresando una marca Linea 49 " + ex);
            }
            clearTextField();
        }

    }

    public void Guardar() throws IOException {
        validateTextField();
        if(path != null && path.contains("\\")) {
            direccion=path.replace("\\","*");
        }else{
            direccion=null;
        }
        insertMark();
    }

    public void Volver() {
        closeView close = new closeView();
        close.Cerrar(ContenedorMarca);
    }

    public void clearTextField(){
        Nombre.clear();
        Descripcion.clear();
        Imagen.setImage(null);
    }

    public void loadMarks(){
        String querySelect= "SELECT NOMBRE FROM MARCAS ORDER BY NOMBRE DESc";
        try{
            ResultSet resultados = newProduct.consultas(querySelect);
            if (resultados != null) {
                while (resultados.next()) {
                    nombresMarcas.add(resultados.getString("NOMBRE").toUpperCase());
                }
            }
        }catch(Exception ex){
            System.err.println("Error en select linea 78 " + ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMarks();
    }
}

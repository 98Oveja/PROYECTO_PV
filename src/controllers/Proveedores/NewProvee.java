package controllers.Proveedores;

import controllers.Productos.eventos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;
import utils.closeView;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class NewProvee implements Initializable {
    @FXML private TextField Nombre,Apellidos,Direccion,Telefono,Correo,Organizacion,Cuenta;
    @FXML private ImageView Photo;
    @FXML private Pane Container;

    eventos event = new eventos();String path;
    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;

    public void Agregar(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null)
        {
            path= selectedFile.getPath();
            Image image;
            image = new Image("file:/"+path);
            Photo.setImage(image);
        } else{
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        event.validarSoloLetras(Nombre);
        event.validarSoloLetras(Apellidos);
        event.validarSoloNumeros(Telefono);
        event.validarSoloLetras(Organizacion);
        event.validarSoloNumeros(Cuenta);
    }

    public void Guardar(ActionEvent actionEvent) throws SQLException {
        String nombre,n1,n2, apellido,a1,a2, direccion, tel, correo, organizacion, cuenta, photo, query;
        nombre=Nombre.getText();
        apellido=Apellidos.getText();
        direccion=Direccion.getText();
        tel=Telefono.getText();
        correo=Correo.getText();
        organizacion=Organizacion.getText();
        cuenta=Cuenta.getText();int id = 0;
        if (!nombre.isEmpty()&&!apellido.isEmpty()&&!direccion.isEmpty()&&!tel.isEmpty()){
            if(path!=null&&path.contains("\\")){
                photo=path.replace("\\","*");
            }else{photo=path;}
            if(nombre.contains(" ")){
                String[] textElements=nombre.split(" ");
                n1=textElements[0];n2=textElements[1];
            }else{n1=nombre;n2="";}
            if(apellido.contains(" ")){
                String[]ape=apellido.split(" ");
                a1=ape[0];a2=ape[1];
            }else{a1=apellido;a2="";}
            query= "INSERT INTO PERSONAS (PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,DIRECCION,TELEFONO,CORREO,url_foto)" +
                    "VALUES('"+n1+"','"+n2+"','"+a1+"','"+a2+"','"+direccion+"','"+tel+"','"+correo+"','"+photo+"');";
            System.out.println(query);
            conexion = conn.getConnection();
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.execute();
            query= "SELECT COUNT(*) AS a FROM PERSONAS;";
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(query);
            while(resultado.next())
            {
                id=resultado.getInt("a");
            }
            query= "INSERT INTO PROVEEDORES (ID_PERSONA,ORG,NO_CUENTA) VALUES("+id+",'"+organizacion+"','"+cuenta+"');";
            preparedStatement = conexion.prepareStatement(query);
            preparedStatement.execute();
            limipiar();
        }
        else{
            Alert dialogo= new Alert(Alert.AlertType.INFORMATION);
            dialogo.setHeaderText(null);
            dialogo.initStyle(StageStyle.UNDECORATED);
            dialogo.setContentText("Debe llenar los siguientes campos:\n-Nombres\n-Apellidos\n-Direccion\n-Telefono");
            dialogo.showAndWait();
        }
    }

    private void limipiar() {
        Nombre.clear();
        Apellidos.clear();
        Direccion.clear();
        Telefono.clear();
        Correo.clear();
        Organizacion.clear();
        Cuenta.clear();
        Photo.setImage(null);
    }

    public void Close(ActionEvent actionEvent) {
        closeView close= new closeView();
        close.Cerrar(Container);
    }
}

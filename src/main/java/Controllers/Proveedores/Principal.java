package Controllers.Proveedores;

import Controllers.ScreenController.ImplementsU.ControlledScreen;
import Controllers.ScreenController.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Utils.ConnectionUtil;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Principal implements Initializable, ControlledScreen {

    public StackPane mainContainer;
    ArrayList<String> Proveedores = new ArrayList<String>();
    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;

    ScreensController myController;

    @FXML private TextField labelSearch;
    @FXML private GridPane Pane;
    @FXML private Button activo, inactivo;
    static String nombre,descripcion,movil,org,path;
    String Query1 = "SELECT PRIMER_NOMBRE, PRIMER_APELLIDO, DIRECCION, TELEFONO, url_foto, ORG, NO_CUENTA FROM PERSONAS INNER JOIN PROVEEDORES " +
            "WHERE PERSONAS.ID_PERSONA=PROVEEDORES.ID_PERSONA AND PROVEEDORES.ESTADO=1;";
    String Query2 = "SELECT PRIMER_NOMBRE, PRIMER_APELLIDO, DIRECCION, TELEFONO, url_foto, ORG, NO_CUENTA FROM PERSONAS INNER JOIN PROVEEDORES " +
            "WHERE PERSONAS.ID_PERSONA=PROVEEDORES.ID_PERSONA AND PROVEEDORES.ESTADO=0;";

    double height = Toolkit.getDefaultToolkit().getScreenSize().height;
    double width = Toolkit.getDefaultToolkit().getScreenSize().width;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datos(Query1);
        mainContainer.setPrefWidth(width-260);
        mainContainer.setPrefHeight(height-110);
    }

    String getNombre(){return nombre;}
    String getDescripcion(){return descripcion;}
    String getMovil(){return movil;}
    String getOrg(){return org;}
    String getPath(){return path; }


    public void rellenar(int row,int column){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Proveedores/Card.fxml"));
        Parent parent = null;

        try {
            parent = fxmlLoader.load();

        } catch (IOException e) {}

        Pane.add(parent, column, row);
    }

    public void Agregar() {
        int tamanio=Proveedores.size();
        int a=tamanio/2;int i=0;
        for (int x = 0; x <= a; x++) {
            for(int j=0;j<2;j++) {
                if (i != tamanio) {
                    String[] cadena = Proveedores.get(i).split("#");
                    nombre=cadena[0];descripcion=cadena[1];
                    movil=cadena[2];org=cadena[3];
                    path=cadena[4];
                    rellenar(x,j);
                    i++;
                }
            }
        }
    }

    public void datos(String Query) {
        activo.setStyle("-fx-font-size: 16px;    -fx-background-color: #3B86FF;");
        inactivo.setStyle(".boton-mini");
        Proveedores.clear();
        String nombre, apellido, direccion, org, tel, dato, photo, cuenta;
        try {
            conexion = conn.getConnection();
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while (resultado.next()) {
                    nombre = resultado.getString("PRIMER_NOMBRE");
                    apellido = resultado.getString("PRIMER_APELLIDO");
                    direccion = resultado.getString("DIRECCION");
                    tel = resultado.getString("TELEFONO");
                    org = resultado.getString("ORG");
                    photo = resultado.getString("url_foto");
                    cuenta= resultado.getString("NO_CUENTA");
                    dato = nombre + " " + apellido + "#" + direccion + "#" + tel + "#" + org + "#" + photo + "#" + cuenta ;
                    Proveedores.add(dato);
                }
                if (Proveedores.size() != 0) {
                    Agregar();
                }
            }
        } catch (SQLException e) {
        }
    }

    public void Abrir(ActionEvent actionEvent) throws IOException {
        Double height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        Double width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        final Stage primaryStage = new Stage();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initOwner(primaryStage);
        dialog.setX((width/2)-(570/2));
        dialog.setY((height/2)-(779/2));
        Scene dialogScene = null;
        dialogScene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Proveedores/NuevoProveedor.fxml")));
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void Inactivos(ActionEvent actionEvent) {
        Pane.getChildren().clear();
        datos(Query2);
        inactivo.setStyle("-fx-font-size: 16px;    -fx-background-color: #3B86FF;");
        activo.setStyle(".boton-mini");
    }

    public void Activos(ActionEvent actionEvent) {
        Pane.getChildren().clear();
        datos(Query1);
        activo.setStyle("-fx-font-size: 16px;    -fx-background-color: #3B86FF;");
        inactivo.setStyle(".boton-mini");
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
}
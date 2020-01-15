package controllers.Proveedores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;
import utils.Consultas;
import utils.Views;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Principal implements Initializable {
    @FXML private Pane Pane1;   @FXML private Pane Pane2;   @FXML private Pane Pane3;
    @FXML private Pane Pane4;   @FXML private Pane Pane5;   @FXML private Pane Pane6;
    @FXML private ImageView Photo1; @FXML private ImageView Photo2; @FXML private ImageView Photo3;
    @FXML private ImageView Photo4; @FXML private ImageView Photo5; @FXML private ImageView Photo6;
    @FXML private Label Nombre1;    @FXML private Label Nombre2;    @FXML private Label Nombre3;
    @FXML private Label Nombre4;    @FXML private Label Nombre5;    @FXML private Label Nombre6;
    @FXML private Label Descripcion1;   @FXML private Label Descripcion2;   @FXML private Label Descripcion3;
    @FXML private Label Descripcion4;   @FXML private Label Descripcion5;   @FXML private Label Descripcion6;
    @FXML private Label Movil1; @FXML private Label Movil2; @FXML private Label Movil3;
    @FXML private Label Movil4; @FXML private Label Movil5; @FXML private Label Movil6;
    @FXML private Button mini1; @FXML private Button mini2; @FXML private Button mini3;
    @FXML private Button mini4; @FXML private Button mini5; @FXML private Button mini6;
    @FXML private Label Org1;   @FXML private Label Org2;   @FXML private Label Org3;
    @FXML private Label Org4;   @FXML private Label Org5;   @FXML private Label Org6;
    @FXML private Button imgUser;   @FXML private Button imgUser2;   @FXML private Button imgUser3;
    @FXML private Button imgUser4;   @FXML private Button imgUser5;   @FXML private Button imgUser6;
    @FXML private Button delete1;   @FXML private Button delete2;   @FXML private Button delete3;
    @FXML private Button delete4;   @FXML private Button delete5;   @FXML private Button delete6;

    int pos,posicionmini, tamaniomini;Circle circle = new Circle(36);
    ArrayList<String> Proveedores = new ArrayList<String>();
    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;
    public static String path=null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pos=1;
        datos();
        mini2.setStyle("-fx-background-color: #3B86FF;" + "-fx-text-fill: #fff;");
    }

    public void datos() {
        Proveedores.clear(); String nombre, apellido, direccion, org, tel, dato, photo;
        try {
            String Query = "SELECT PRIMER_NOMBRE, PRIMER_APELLIDO, DIRECCION, TELEFONO, url_foto, ORG FROM PERSONAS INNER JOIN PROVEEDORES " +
                    "WHERE PERSONAS.ID_PERSONA=PROVEEDORES.ID_PERSONA AND PROVEEDORES.ESTADO=1;";
            conexion = conn.getConnection();
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while (resultado.next()) {
                    nombre= resultado.getString("PRIMER_NOMBRE");
                    apellido= resultado.getString("PRIMER_APELLIDO");
                    direccion= resultado.getString("DIRECCION");
                    tel= resultado.getString("TELEFONO");
                    org= resultado.getString("ORG");
                    photo= resultado.getString("url_foto");
                    dato = nombre +" "+ apellido + "#" + direccion + "#" + tel+ "#" + org + "#" +photo;
                    Proveedores.add(dato);
                }
                if (Proveedores.size() != 0) {
                    mostrar();
                }}} catch (SQLException e) {}
    }

    public void mostrar() {
        limpiar();
        String datos, direccion, nom, apel, tel,org,photo;
        int tamanio = Proveedores.size();int asig, panel = 0;
        tamaniomini = tamanio / 6;
        asig = (posicionmini * 6);
        if (asig <= tamanio) {
            int limit = asig + 6;
            if (tamanio <= 6) {
                for (int x = 0; x < tamanio; x++) {
                    if (x < tamanio) {
                        panel = panel + 1;
                        datos = Proveedores.get(x);
                        String[] textElements = datos.split("#");
                        nom = textElements[0];
                        direccion = textElements[1];
                        tel = textElements[2];
                        org = textElements[3];
                        photo = textElements[4];
                        photo= photo.replace("*", "\\");
                        paneles(panel, nom,direccion,tel,org,photo);
                    }
                }
            } else {
                for (int x = asig; x <= limit; x++) {
                    if (x < tamanio) {
                        panel = panel + 1;
                        datos = Proveedores.get(x).toString();
                        String[] textElements = datos.split("#");
                        nom = textElements[0];
                        direccion = textElements[1];
                        tel = textElements[2];
                        org = textElements[3];
                        photo = textElements[4];
                        photo= photo.replace("*", "\\");
                        paneles(panel, nom,direccion,tel,org,photo);
                    }
                }
                mostrarminis();
            }
        }
    }

    public void paneles(int panel, String a, String b, String c, String d, String e) {
        Image img;
        if(e.equals("null")||e.isEmpty()){
            img = new Image("/images/Proveedor.png");
        }
        else if (e.contains("\\")){
            img = new Image("file:/" + e,false);
            circle.setFill(new ImagePattern(img));
        }else{
            img = new Image("/images/Proveedor.png");
        }
        switch (panel) {
            case 1:
                Pane1.setVisible(true);
                Nombre1.setText(a);
                Descripcion1.setText("direccion: "+b);
                Movil1.setText(c);
                Org1.setText(d);
                Photo1.setImage(img);
                break;
            case 2:
                Pane2.setVisible(true);
                Nombre2.setText(a);
                Descripcion2.setText("direccion: "+b);
                Movil2.setText(c);
                Org2.setText(d);
                Photo2.setImage(img);
                break;
            case 3:
                Pane3.setVisible(true);
                Nombre3.setText(a);
                Descripcion3.setText("direccion: "+b);
                Movil3.setText(c);
                Org3.setText(d);
                Photo3.setImage(img);
                break;
            case 4:
                Pane4.setVisible(true);
                Nombre4.setText(a);
                Descripcion4.setText("direccion: "+b);
                Movil4.setText(c);
                Org4.setText(d);
                Photo4.setImage(img);
                break;
            case 5:
                Pane5.setVisible(true);
                Nombre5.setText(a);
                Descripcion5.setText("direccion: "+b);
                Movil5.setText(c);
                Org5.setText(d);
                Photo5.setImage(img);
                break;
            case 6:
                Pane6.setVisible(true);
                Nombre6.setText(a);
                Descripcion6.setText("direccion: "+b);
                Movil6.setText(c);
                Org6.setText(d);
                Photo6.setImage(img);
                break;
        }
    }

    void limpiar() {
        Pane1.setVisible(false);
        Pane2.setVisible(false);
        Pane3.setVisible(false);
        Pane4.setVisible(false);
        Pane5.setVisible(false);
        Pane6.setVisible(false);
        mini1.setVisible(false);
        mini2.setVisible(false);
        mini3.setVisible(false);
        mini4.setVisible(false);
        mini5.setVisible(false);
        mini6.setVisible(false);
    }

    void mostrarminis() {
        switch (tamaniomini) {
            case 0:
                mini1.setVisible(false);
                mini2.setVisible(false);
                mini3.setVisible(false);
                mini4.setVisible(false);
                mini5.setVisible(false);
                mini6.setVisible(false);
                break;
            case 1:
                mini1.setVisible(false);
                mini2.setVisible(true);
                mini3.setVisible(true);
                mini4.setVisible(false);
                mini5.setVisible(false);
                mini6.setVisible(false);
                break;
            case 2:
                mini1.setVisible(false);
                mini2.setVisible(true);
                mini3.setVisible(true);
                mini4.setVisible(true);
                mini5.setVisible(false);
                mini6.setVisible(false);
                break;
            case 3:
                mini1.setVisible(false);
                mini2.setVisible(true);
                mini3.setVisible(true);
                mini4.setVisible(true);
                mini5.setVisible(true);
                mini6.setVisible(false);
                break;
            default:
                if (tamaniomini >= 4) {
                    if (posicionmini == 0) {
                        mini1.setVisible(false);
                        mini2.setVisible(true);
                        mini3.setVisible(true);
                        mini4.setVisible(true);
                        mini5.setVisible(true);
                        mini6.setVisible(true);
                    } else if (posicionmini == tamaniomini) {
                        mini1.setVisible(true);
                        mini2.setVisible(true);
                        mini3.setVisible(true);
                        mini4.setVisible(true);
                        mini5.setVisible(true);
                        mini6.setVisible(false);
                    } else {
                        mini1.setVisible(true);
                        mini2.setVisible(true);
                        mini3.setVisible(true);
                        mini4.setVisible(true);
                        mini5.setVisible(true);
                        mini6.setVisible(true);
                    }
                }break;
        }
    }

    public void cambio(ActionEvent actionEvent) {
        if (actionEvent.getSource() == mini2) {
            mini1.setStyle("-fx-background-color: #BCBCCB; -fx-text-fill: #fff;");
            mini2.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini3.setStyle(".boton-mini");
            mini4.setStyle(".boton-mini");
            mini5.setStyle(".boton-mini");
            mini6.setStyle(".boton-mini");
            posicionmini = Integer.parseInt(mini2.getText());
            posicionmini = posicionmini - 1;
            pos = 1;
            mostrar();
        } else if (actionEvent.getSource() == mini3) {
            mini3.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini2.setStyle(".boton-mini");
            mini4.setStyle(".boton-mini");
            mini5.setStyle(".boton-mini");
            mini6.setStyle(".boton-mini");
            mini1.setStyle(".boton-mini");
            posicionmini = Integer.parseInt(mini3.getText());
            posicionmini = posicionmini - 1;
            pos = 2;
            mostrar();
        } else if (actionEvent.getSource() == mini4) {
            mini4.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini5.setStyle(".boton-mini");
            mini2.setStyle(".boton-mini");
            mini3.setStyle(".boton-mini");
            mini6.setStyle(".boton-mini");
            mini1.setStyle(".boton-mini");
            posicionmini = Integer.parseInt(mini4.getText());
            posicionmini = posicionmini - 1;
            pos = 3;
            mostrar();
        } else if (actionEvent.getSource() == mini5) {
            mini1.setStyle(".boton-mini");
            mini5.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini2.setStyle(".boton-mini");
            mini3.setStyle(".boton-mini");
            mini4.setStyle(".boton-mini");
            mini6.setStyle(".boton-mini");
            posicionmini = Integer.parseInt(mini5.getText());
            posicionmini = posicionmini - 1;
            pos = 4;
            mostrar();
        } else if (actionEvent.getSource() == mini1) {
            switch (pos) {
                case 1:
                    mini2.setStyle("-fx-background-color: #3B86FF;" +
                            "-fx-text-fill: #fff;");
                    if (Integer.parseInt(mini2.getText()) != 1) {
                        mini2.setText(String.valueOf(Integer.parseInt(mini2.getText()) - 1));
                        mini3.setText(String.valueOf(Integer.parseInt(mini3.getText()) - 1));
                        mini4.setText(String.valueOf(Integer.parseInt(mini4.getText()) - 1));
                        mini5.setText(String.valueOf(Integer.parseInt(mini5.getText()) - 1));
                    }
                    posicionmini = Integer.parseInt(mini2.getText());
                    if (posicionmini != 0) {
                        posicionmini = posicionmini - 1;
                    } else {
                        mini1.setVisible(false);
                        posicionmini = 0;
                    }
                    pos = 1;
                    mostrar();
                    break;
                case 2:
                    posicionmini = Integer.parseInt(mini2.getText()) - 1;
                    pos = 1;
                    mostrar();
                    mini2.setStyle("-fx-background-color: #3B86FF;" +
                            "-fx-text-fill: #fff;");
                    mini3.setStyle(".boton-mini");
                    break;
                case 3:
                    posicionmini = Integer.parseInt(mini3.getText()) - 1;
                    pos = 2;
                    mostrar();
                    mini3.setStyle("-fx-background-color: #3B86FF;" +
                            "-fx-text-fill: #fff;");
                    mini4.setStyle(".boton-mini");
                    break;
                case 4:
                    posicionmini = Integer.parseInt(mini4.getText()) - 1;
                    pos = 3;
                    mostrar();
                    mini4.setStyle("-fx-background-color: #3B86FF;" +
                            "-fx-text-fill: #fff;");
                    mini5.setStyle(".boton-mini");
                    break;
            }
        } else if (actionEvent.getSource() == mini6) {
            switch (pos) {
                case 1:
                    mini3.setStyle("-fx-background-color: #3B86FF;" +
                            "-fx-text-fill: #fff;");
                    mini2.setStyle(".boton-mini");
                    posicionmini = Integer.parseInt(mini3.getText()) - 1;
                    pos = 2;
                    mostrar();
                    break;
                case 2:
                    mini4.setStyle("-fx-background-color: #3B86FF;" +
                            "-fx-text-fill: #fff;");
                    mini3.setStyle(".boton-mini");
                    posicionmini = Integer.parseInt(mini4.getText()) - 1;
                    pos = 3;
                    mostrar();
                    break;
                case 3:
                    mini5.setStyle("-fx-background-color: #3B86FF;" +
                            "-fx-text-fill: #fff;");
                    mini4.setStyle(".boton-mini");
                    posicionmini = Integer.parseInt(mini5.getText()) - 1;
                    pos = 4;
                    mostrar();
                    break;
                case 4:
                    mini5.setStyle("-fx-background-color: #3B86FF;" +
                            "-fx-text-fill: #fff;");
                    pos = 4;
                    if (Integer.parseInt(mini2.getText()) != tamaniomini) {
                        mini2.setText(String.valueOf(1 + Integer.parseInt(mini2.getText())));
                        mini3.setText(String.valueOf(1 + Integer.parseInt(mini3.getText())));
                        mini4.setText(String.valueOf(1 + Integer.parseInt(mini4.getText())));
                        mini5.setText(String.valueOf(1 + Integer.parseInt(mini5.getText())));
                        posicionmini = Integer.parseInt(mini5.getText()) - 1;
                        mostrar();
                    } else if (tamaniomini == Integer.parseInt(mini5.getText())) {
                        mini6.setVisible(false);
                    }
                    break;
            }
        }
    }

    public void Abrir(ActionEvent actionEvent) throws IOException {
        final Stage primaryStage = new Stage();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initOwner(primaryStage);
        dialog.setX(500);
        dialog.setY(100);
        Scene dialogScene = null;
        dialogScene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Proveedores/NuevoProveedor.fxml")));
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void Ver(ActionEvent actionEvent) throws IOException {
        Image img = null;
        Views views= new Views();
        if(actionEvent.getSource() == imgUser ){
            img=Photo1.getImage();
        }
        else if(actionEvent.getSource() == imgUser2 ){
            img=Photo2.getImage();
        }
        else if(actionEvent.getSource() == imgUser3){
            img=Photo2.getImage();
        }
        else if(actionEvent.getSource() == imgUser4){
            img=Photo4.getImage();
        }
        else if(actionEvent.getSource() == imgUser5){
            img=Photo5.getImage();
        }
        else if(actionEvent.getSource() == imgUser6){
            img=Photo6.getImage();
        }
        views.show(img);
    }

    public void Eliminar(ActionEvent actionEvent) {
        Consultas consultas = new Consultas();
        if(actionEvent.getSource() == delete1 ){
            consultas.eliminarProveedor(Nombre1.getText(),Movil1.getText(),Org1.getText());
            datos();
        }
        else if(actionEvent.getSource() == delete2 ){
            consultas.eliminarProveedor(Nombre2.getText(),Movil2.getText(),Org2.getText());
            datos();
        }
        else if(actionEvent.getSource() == delete3){
            consultas.eliminarProveedor(Nombre3.getText(),Movil3.getText(),Org3.getText());
            datos();
        }
        else if(actionEvent.getSource() == delete4){
            consultas.eliminarProveedor(Nombre4.getText(),Movil4.getText(),Org4.getText());
            datos();
        }
        else if(actionEvent.getSource() == delete5){
            consultas.eliminarProveedor(Nombre5.getText(),Movil5.getText(),Org5.getText());
            datos();
        }
        else if(actionEvent.getSource() == delete6){
            consultas.eliminarProveedor(Nombre6.getText(),Movil6.getText(),Org6.getText());
            datos();
        }
    }

    public void Activos(ActionEvent actionEvent) {

        mostrar();
        inactivos();
    }

    private void inactivos() {
        Proveedores.clear(); String nombre, apellido, direccion, org, tel, dato, photo;
        try {
            String Query = "SELECT PRIMER_NOMBRE, PRIMER_APELLIDO, DIRECCION, TELEFONO, url_foto, ORG FROM PERSONAS INNER JOIN PROVEEDORES " +
                    "WHERE PERSONAS.ID_PERSONA=PROVEEDORES.ID_PERSONA AND PROVEEDORES.ESTADO=0;";
            conexion = conn.getConnection();
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while (resultado.next()) {
                    nombre= resultado.getString("PRIMER_NOMBRE");
                    apellido= resultado.getString("PRIMER_APELLIDO");
                    direccion= resultado.getString("DIRECCION");
                    tel= resultado.getString("TELEFONO");
                    org= resultado.getString("ORG");
                    photo= resultado.getString("url_foto");
                    dato = nombre +" "+ apellido + "#" + direccion + "#" + tel+ "#" + org + "#" +photo;
                    Proveedores.add(dato);
                }
                if (Proveedores.size() != 0) {
                    mostrar();
                }}} catch (SQLException e) {}
    }
}
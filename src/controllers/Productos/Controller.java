package controllers.Productos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private ImageView imageview1;
    @FXML private Label Nombre1;
    @FXML private Label disp1;
    @FXML private Label precio1;
    @FXML private ImageView imageview2;
    @FXML private Label Nombre2;
    @FXML private Label disp2;
    @FXML private Label precio2;
    @FXML private ImageView imageview3;
    @FXML private Label Nombre3;
    @FXML private Label disp3;
    @FXML private Label precio3;
    @FXML private ImageView imageview4;
    @FXML private Label Nombre4;
    @FXML private Label disp4;
    @FXML private Label precio4;
    @FXML private ImageView imageview5;
    @FXML private Label Nombre5;
    @FXML private Label disp5;
    @FXML private Label precio5;
    @FXML private Button mini2;
    @FXML private Button mini3;
    @FXML private Button mini4;
    @FXML private Button mini5;
    @FXML private Button mini6;
    @FXML private Button mini1;
    @FXML private Pane P1;
    @FXML private Pane P2;
    @FXML private Pane P3;
    @FXML private Pane P4;
    @FXML private Pane P5;


    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;
    String direccion;
    int posicionmini=0;
    ArrayList<String> a = new ArrayList<String>();
    ArrayList<String> b = new ArrayList<String>();
    ArrayList<Double> c = new ArrayList<>();
    ArrayList<Double> d = new ArrayList<>();
    @FXML
    public void Abrir(ActionEvent actionEvent) throws IOException {
        final Stage primaryStage = new Stage();
        final Stage dialog = new Stage();


        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initOwner(primaryStage);
        dialog.setX(300);
        dialog.setY(100);

        Scene dialogScene = null;
        dialogScene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Productos/nuevoproducto.fxml")));

        dialog.setScene(dialogScene);
        dialog.show();
    }

    void limpiar(){
            P1.setVisible(false);
            P2.setVisible(false);
            P3.setVisible(false);
            P4.setVisible(false);
            P5.setVisible(false);
    }

    public void paneles(int panel,String a, String b, String c, String d){
        Image img = new Image("file:/"+a);
        System.out.println(a+" "+b+" "+" "+c+" "+d);
        if (a!=null||a=="")
        {
            switch (panel)
            {
                case 1:
                    P1.setVisible(true);
                    imageview1.setImage(img);
                    Nombre1.setText(b);
                    disp1.setText("en Stock "+c);
                    precio1.setText("Q "+d);
                    break;
                case 2:
                    P2.setVisible(true);
                    imageview2.setImage(img);
                    Nombre2.setText(b);
                    disp2.setText("en Stock "+c);
                    precio2.setText("Q "+d);
                    break;
                case 3:
                    P3.setVisible(true);
                    imageview3.setImage(img);
                    Nombre3.setText(b);
                    disp3.setText("en Stock "+c);
                    precio3.setText("Q "+d);
                    break;
                case 4:
                    P4.setVisible(true);
                    imageview4.setImage(img);
                    Nombre4.setText(b);
                    disp4.setText("en Stock "+c);
                    precio4.setText("Q "+d);
                    break;
                case 5:
                    P5.setVisible(true);
                    imageview5.setImage(img);
                    Nombre5.setText(b);
                    disp5.setText("en Stock "+c);
                    precio5.setText("Q "+d);
                    break;
            }
        }


    }

    public  void mostrar(){
        limpiar();
        String direccion, nom, cant, prec;
        int tamanio = a.size();
        int asig, panel=0;
        asig=(posicionmini*5);
        if (asig<=tamanio)
        {
            int limit = asig+5;
            if(tamanio<=5){
                for(int x = 0; x<tamanio; x++)
                {
                    panel=panel+1;
                    direccion= a.get(x).toString();
                    nom=b.get(x).toString();
                    cant=c.get(x).toString();
                    prec=d.get(x).toString();
                    direccion= direccion.replace("*","\\");
                    paneles(panel,direccion,nom,cant,prec);
                }
            }else
            {
                for(int x = asig; x<=limit; x++)
                {
                    panel=panel+1;
                    direccion= a.get(x).toString();
                    nom=b.get(x).toString();
                    cant=c.get(x).toString();
                    prec=d.get(x).toString();
                    direccion= direccion.replace("*","\\");
                    paneles(panel,direccion,nom,cant,prec);
                }
            }
        }
    }

    public void datos(){
        a.clear();b.clear();c.clear();d.clear();
        ArrayList<String> URL = new ArrayList<String>();
        ArrayList<String> NAME = new ArrayList<String>();
        ArrayList<Double>  DISPONIBILIDAD = new ArrayList<Double>();
        ArrayList<Double> PRECIO = new ArrayList<Double>();
        try {
            String Query = "SELECT IMG,NOMBRE,CANTIDAD,PRECIO_VENTA FROM PRODUCTOS ORDER BY ID_PRODUCTO DESC;";
            conexion = conn.getConnection();
            Statement instruccion= conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while(resultado.next()) {
                    String url= resultado.getString("IMG");
                    String nombre= resultado.getString("NOMBRE");
                    Double disp = resultado.getDouble("CANTIDAD");
                    Double precio = resultado.getDouble("PRECIO_VENTA");
                    URL.add(url);
                    NAME.add(nombre);
                    DISPONIBILIDAD.add(disp);
                    PRECIO.add(precio);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR "+e.getErrorCode());;
        }
        a=URL; b=NAME; c=DISPONIBILIDAD; d=PRECIO;
        if(a.size()!=0){
            mostrar();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        datos();
    }
    public void cambio(ActionEvent actionEvent) {
        if (actionEvent.getSource()==mini2){
            mini1.setStyle("-fx-background-color: #BCBCCB; -fx-text-fill: #fff;");
            mini2.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini3.setStyle(".PanelLateralOpciones");
            mini4.setStyle(".PanelLateralOpciones");
            mini5.setStyle(".PanelLateralOpciones");
            mini6.setStyle(".Cambio");
            posicionmini=0;
            mostrar();
        }
        else if (actionEvent.getSource()==mini3){
            mini3.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini2.setStyle(".PanelLateralOpciones");
            mini4.setStyle(".PanelLateralOpciones");
            mini5.setStyle(".PanelLateralOpciones");
            mini6.setStyle(".Cambio");
            mini1.setStyle(".Cambio");
            posicionmini=1;
            mostrar();
        }
        else if (actionEvent.getSource()==mini4){
            mini4.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini5.setStyle(".PanelLateralOpciones");
            mini2.setStyle(".PanelLateralOpciones");
            mini3.setStyle(".PanelLateralOpciones");
            mini6.setStyle(".Cambio");
            mini1.setStyle(".Cambio");
            posicionmini=2;
            mostrar();
        }
        else if (actionEvent.getSource()==mini5){
            mini1.setStyle(".Cambio");
            mini5.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini2.setStyle(".PanelLateralOpciones");
            mini3.setStyle(".PanelLateralOpciones");
            mini4.setStyle(".PanelLateralOpciones");
            mini6.setStyle("-fx-background-color: #BCBCCB; -fx-text-fill: #fff;");
            posicionmini=3;
            mostrar();
        }
    }

    public void Actualizar(ActionEvent actionEvent) {
       datos();
    }
}
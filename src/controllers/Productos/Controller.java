package controllers.Productos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class Controller implements Initializable {
    @FXML private Button mini2;    @FXML private Button mini3;
    @FXML private Button mini4;    @FXML private Button mini5;
    @FXML private Button mini6;    @FXML private Button mini1;
    @FXML private Button Activos;
    @FXML private Pane P1;@FXML private Pane P2;@FXML private Pane P3;@FXML private Pane P4;@FXML private Pane P5;
    @FXML private Button Inactivos;
    @FXML private ImageView imageview1;     @FXML private Label Nombre1;
    @FXML private Label disp1;              @FXML private Label precio1;
    @FXML private ImageView imageview2;     @FXML private Label Nombre2;
    @FXML private Label disp2;              @FXML private Label precio2;
    @FXML private ImageView imageview3;     @FXML private Label Nombre3;
    @FXML private Label disp3;              @FXML private Label precio3;
    @FXML private ImageView imageview4;     @FXML private Label Nombre4;
    @FXML private Label disp4;              @FXML private Label precio4;
    @FXML private ImageView imageview5;     @FXML private Label Nombre5;
    @FXML private Label disp5;              @FXML private Label precio5;
    @FXML private Button Delete1;   @FXML private Button Delete2;   @FXML private Button Delete3;   @FXML private Button Delete4;   @FXML private Button Delete5;

    int act=1;
    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;
    int posicionmini=0,tamaniomini,pos;
    ArrayList<String> Datosproductos = new ArrayList<String>();
    String url,nombre; String disp,precio,dato;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pos=1;
        limpiar();
        datos();
        Activos.setStyle("-fx-background-color: #3B86FF;" +"-fx-text-fill: #fff;");
        mini2.setStyle("-fx-background-color: #3B86FF;" +"-fx-text-fill: #fff;");
    }



    public void datos(){
        Datosproductos.clear();
        try {
            String Query = "SELECT IMG,NOMBRE,CANTIDAD,PRECIO_VENTA FROM PRODUCTOS WHERE ESTADO=1 ORDER BY ID_PRODUCTO DESC;";
            conexion = conn.getConnection();
            Statement instruccion= conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while(resultado.next()) {
                    url= resultado.getString("IMG");
                    nombre= resultado.getString("NOMBRE");
                    disp = resultado.getString("CANTIDAD");
                    precio = resultado.getString("PRECIO_VENTA");
                    dato=url+"#"+nombre+"#"+disp+"#"+precio;
                    Datosproductos.add(dato);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR "+e.getErrorCode());;
        }
        if(Datosproductos.size()!=0){
            mostrar();
        }
    }

    void limpiar() {
        P1.setVisible(false);
        P2.setVisible(false);
        P3.setVisible(false);
        P4.setVisible(false);
        P5.setVisible(false);
        mini1.setVisible(false);
        mini2.setVisible(false);
        mini3.setVisible(false);
        mini4.setVisible(false);
        mini5.setVisible(false);
        mini6.setVisible(false);
    }

    public void paneles(int panel, String a, String b, String c, String d) {
        Image img = new Image("file:/" + a);
        //System.out.println(a + " " + b + " " + " " + c + " " + d);
        if (a != null || a == "") {
            switch (panel) {
                case 1:
                    P1.setVisible(true);
                    imageview1.setImage(img);
                    Nombre1.setText(b);
                    disp1.setText("en Stock " + c);
                    precio1.setText("Q " + d);
                    break;
                case 2:
                    P2.setVisible(true);
                    imageview2.setImage(img);
                    Nombre2.setText(b);
                    disp2.setText("en Stock " + c);
                    precio2.setText("Q " + d);
                    break;
                case 3:
                    P3.setVisible(true);
                    imageview3.setImage(img);
                    Nombre3.setText(b);
                    disp3.setText("en Stock " + c);
                    precio3.setText("Q " + d);
                    break;
                case 4:
                    P4.setVisible(true);
                    imageview4.setImage(img);
                    Nombre4.setText(b);
                    disp4.setText("en Stock " + c);
                    precio4.setText("Q " + d);
                    break;
                case 5:
                    P5.setVisible(true);
                    imageview5.setImage(img);
                    Nombre5.setText(b);
                    disp5.setText("en Stock " + c);
                    precio5.setText("Q " + d);
                    break;
            }
        }
    }

    public  void mostrar(){
        limpiar();
        String datos, direccion, nom, cant, prec;
        int tamanio = Datosproductos.size();
        int asig, panel=0;
        tamaniomini=tamanio/5;
        asig=(posicionmini*5);
        if (asig<=tamanio)
        {
            int limit = asig+5;
            if(tamanio<=5){
                for(int x = 0; x<tamanio; x++)
                {
                    if(x<tamanio){
                        panel=panel+1;
                        datos = Datosproductos.get(x).toString();
                        String[] textElements = datos.split("#");
                        direccion= textElements[0];
                        nom=textElements[1];
                        cant=textElements[2];
                        prec=textElements[3];
                        direccion= direccion.replace("*","\\");
                        paneles(panel,direccion,nom,cant,prec);
                    }
                }
            }else
            {
                for(int x = asig; x<=limit; x++)
                {
                    if(x<tamanio){
                        panel=panel+1;
                        datos = Datosproductos.get(x).toString();
                        String[] textElements = datos.split("#");
                        direccion= textElements[0];
                        nom=textElements[1];
                        cant=textElements[2];
                        prec=textElements[3];
                        direccion= direccion.replace("*","\\");
                        paneles(panel,direccion,nom,cant,prec);
                    }
                }
                mostrarminis();
            }
        }
    }

    void mostrarminis() {
        if (tamaniomini == 1) {
            mini1.setVisible(false);
            mini2.setVisible(true);
            mini3.setVisible(true);
            mini4.setVisible(false);
            mini5.setVisible(false);
            mini6.setVisible(false);
        }else if (tamaniomini == 2) {
            mini1.setVisible(false);
            mini2.setVisible(true);
            mini3.setVisible(true);
            mini4.setVisible(true);
            mini5.setVisible(false);
            mini6.setVisible(false);
        }
        else if (tamaniomini == 3) {
            mini1.setVisible(false);
            mini2.setVisible(true);
            mini3.setVisible(true);
            mini4.setVisible(true);
            mini5.setVisible(true);
            mini6.setVisible(false);
        }
        else if (tamaniomini >= 4) {
            if(posicionmini==0){
                mini1.setVisible(false);
                mini2.setVisible(true);
                mini3.setVisible(true);
                mini4.setVisible(true);
                mini5.setVisible(true);
                mini6.setVisible(true);
            }else if(posicionmini==tamaniomini)
            {
                mini1.setVisible(true);
                mini2.setVisible(true);
                mini3.setVisible(true);
                mini4.setVisible(true);
                mini5.setVisible(true);
                mini6.setVisible(false);
            }
            else{
                mini1.setVisible(true);
                mini2.setVisible(true);
                mini3.setVisible(true);
                mini4.setVisible(true);
                mini5.setVisible(true);
                mini6.setVisible(true);
            }
            }
            }

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

    public void VEliminar(String nombre) throws SQLException {
        String query=null;
        if (act==1){
            query="UPDATE PRODUCTOS SET ESTADO = 0 WHERE NOMBRE="+'"'+nombre+'"';
        }else if (act==0){
            query="UPDATE PRODUCTOS SET ESTADO = 1 WHERE NOMBRE="+'"'+nombre+'"';
        }
        Alert dialogo= new Alert(Alert.AlertType.CONFIRMATION);
        dialogo.setTitle("Dar de baja Producto");
        dialogo.setHeaderText(null);
        dialogo.initStyle(StageStyle.UNDECORATED);
        dialogo.setContentText("Seguro que quieres dar de baja el siguiente producto:\n  -"+ Nombre1.getText() +
                                "\n\nPodras buscar y cambiar su estado en PRODUCTOS DE BAJA");
        Optional<ButtonType> result = dialogo.showAndWait();
        if (result.get()==ButtonType.OK){
            conexion = conn.getConnection();
            PreparedStatement preparedStatement = conexion.prepareStatement(query);//insert.execute(query);
            preparedStatement.execute();
        }
    }

    public void Eliminar(ActionEvent actionEvent) throws SQLException {
        if (actionEvent.getSource()==Delete1)
        {
            VEliminar(Nombre1.getText());
        }
        else if (actionEvent.getSource()==Delete2)
        {
            VEliminar(Nombre2.getText());
        }
        else if (actionEvent.getSource()==Delete3)
        {
            VEliminar(Nombre3.getText());
        }
        else if (actionEvent.getSource()==Delete4)
        {
            VEliminar(Nombre4.getText());
        }
        else if (actionEvent.getSource()==Delete5)
        {
            VEliminar(Nombre5.getText());
        }
    }

    public void bajas(){
        String url,nombre; String disp,precio,dato;
        Datosproductos.clear();
        try {
            String Query = "SELECT IMG,NOMBRE,CANTIDAD,PRECIO_VENTA FROM PRODUCTOS WHERE ESTADO=0 ORDER BY ID_PRODUCTO DESC;";
            conexion = conn.getConnection();
            Statement instruccion= conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while(resultado.next()) {
                    url= resultado.getString("IMG");
                    nombre= resultado.getString("NOMBRE");
                    disp = resultado.getString("CANTIDAD");
                    precio = resultado.getString("PRECIO_VENTA");
                    dato=url+"#"+nombre+"#"+disp+"#"+precio;
                    Datosproductos.add(dato);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR "+e.getErrorCode());;
        }
        if(Datosproductos.size()!=0){
            mostrar();
        }
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
            posicionmini= Integer.parseInt(mini2.getText());
            posicionmini=posicionmini-1;
            pos=1;
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
            posicionmini= Integer.parseInt(mini3.getText());
            posicionmini=posicionmini-1;
            pos=2;
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
            posicionmini= Integer.parseInt(mini4.getText());
            posicionmini=posicionmini-1;
            pos=3;
            mostrar();
        }
        else if (actionEvent.getSource()==mini5){
            mini1.setStyle(".Cambio");
            mini5.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini2.setStyle(".PanelLateralOpciones");
            mini3.setStyle(".PanelLateralOpciones");
            mini4.setStyle(".PanelLateralOpciones");
            mini6.setStyle(".PanelLateralOpciones");
            posicionmini= Integer.parseInt(mini5.getText());
            posicionmini=posicionmini-1;
            pos=4;
            mostrar();
        }
        else if (actionEvent.getSource()==mini1){
           switch (pos){
                case 1:
                        mini2.setStyle("-fx-background-color: #3B86FF;" +
                                "-fx-text-fill: #fff;");
                        if(Integer.parseInt(mini2.getText())!=1){
                            mini2.setText(String.valueOf(Integer.parseInt(mini2.getText())-1));
                            mini3.setText(String.valueOf(Integer.parseInt(mini3.getText())-1));
                            mini4.setText(String.valueOf(Integer.parseInt(mini4.getText())-1));
                            mini5.setText(String.valueOf(Integer.parseInt(mini5.getText())-1));
                        }
                        posicionmini= Integer.parseInt(mini2.getText());
                        if(posicionmini!=0){
                            posicionmini=posicionmini-1;
                        }else {mini1.setVisible(false);posicionmini=0;}
                        pos=1;
                        mostrar();
                    break;
                case 2:
                    posicionmini= Integer.parseInt(mini2.getText())-1;
                    pos=1;
                    mostrar();
                    mini2.setStyle("-fx-background-color: #3B86FF;" +
                                "-fx-text-fill: #fff;");
                        mini3.setStyle(".PanelLateralOpciones");
                    break;
                case 3:
                        posicionmini= Integer.parseInt(mini3.getText())-1;
                        pos=2;
                        mostrar();
                    mini3.setStyle("-fx-background-color: #3B86FF;" +
                            "-fx-text-fill: #fff;");
                    mini4.setStyle(".PanelLateralOpciones");
                    break;
                case 4:
                        posicionmini= Integer.parseInt(mini4.getText())-1;
                        pos=3;
                        mostrar();
                    mini4.setStyle("-fx-background-color: #3B86FF;" +
                            "-fx-text-fill: #fff;");
                    mini5.setStyle(".PanelLateralOpciones");
                    break;
            }
        }
        else if (actionEvent.getSource()==mini6){
            switch (pos){
                case 1:
                        mini3.setStyle("-fx-background-color: #3B86FF;" +
                                "-fx-text-fill: #fff;");
                        mini2.setStyle(".PanelLateralOpciones");
                        posicionmini= Integer.parseInt(mini3.getText())-1;
                        pos=2;
                        mostrar();
                    break;
                case 2:
                        mini4.setStyle("-fx-background-color: #3B86FF;" +
                                "-fx-text-fill: #fff;");
                        mini3.setStyle(".PanelLateralOpciones");
                        posicionmini= Integer.parseInt(mini4.getText())-1;
                        pos=3;
                        mostrar();
                    break;
                case 3:
                        mini5.setStyle("-fx-background-color: #3B86FF;" +
                                "-fx-text-fill: #fff;");
                        mini4.setStyle(".PanelLateralOpciones");
                        posicionmini= Integer.parseInt(mini5.getText())-1;
                        pos=4;
                        mostrar();
                    break;
                case 4:
                        mini5.setStyle("-fx-background-color: #3B86FF;" +
                                "-fx-text-fill: #fff;");
                        pos=4;
                    if(Integer.parseInt(mini2.getText())!=tamaniomini){
                            mini2.setText(String.valueOf(1+Integer.parseInt(mini2.getText())));
                            mini3.setText(String.valueOf(1+Integer.parseInt(mini3.getText())));
                            mini4.setText(String.valueOf(1+Integer.parseInt(mini4.getText())));
                            mini5.setText(String.valueOf(1+Integer.parseInt(mini5.getText())));
                            posicionmini= Integer.parseInt(mini5.getText())-1;
                            mostrar();
                        }
                        else if (tamaniomini==Integer.parseInt(mini5.getText())){
                            mini6.setVisible(false);
                        }
                    break;
            }
        }
    }

    public void presionando(int num,int lado){
        switch (num){

        }
    }

    public void botonesvista(ActionEvent actionEvent){
        if (actionEvent.getSource()==Activos){
            limpiar();
            Activos.setStyle("-fx-background-color: #3B86FF;" +"-fx-text-fill: #fff;");
            Inactivos.setStyle(".PanelLateralOpciones");
            datos();
            act=1;
        }else if(actionEvent.getSource()==Inactivos){
            limpiar();
            Inactivos.setStyle("-fx-background-color: #3B86FF;" +"-fx-text-fill: #fff;");
            Activos.setStyle(".PanelLateralOpciones");
            bajas();
            act=0;
        }
    }


    public void Actualizar(ActionEvent actionEvent) {
        datos();
    }
}
package controllers.Productos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
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
    @FXML private ImageView imageview;
    @FXML private Button agregarV;

    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;
    String direccion;
    int posicionmini=0;
    ArrayList a; ArrayList b; ArrayList c; ArrayList d;
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

    public void paneles(int panel,Image a, String b, String c, String d){
        switch (panel)
        {
            case 1:
                imageview1.setImage(a);
                Nombre1.setText(b);
                disp1.setText("en Stock "+c);
                precio1.setText("Q "+d);
                break;
            case 2:
                imageview2.setImage(a);
                Nombre2.setText(b);
                disp2.setText("en Stock "+c);
                precio2.setText("Q "+d);
                break;
            case 3:
                imageview3.setImage(a);
                Nombre3.setText(b);
                disp3.setText("en Stock "+c);
                precio3.setText("Q "+d);
                break;
            case 4:
                imageview4.setImage(a);
                Nombre4.setText(b);
                disp4.setText("en Stock "+c);
                precio4.setText("Q "+d);
                break;
            case 5:
                imageview5.setImage(a);
                Nombre5.setText(b);
                disp5.setText("en Stock "+c);
                precio5.setText("Q "+d);
                break;
        }

    }

    public  void mostrar(){
        String direccion, nom, cant, prec;
        int tamanio = a.size();
        int asig, panel=0;
        int alcance = tamanio/5;
        asig=(posicionmini*5);
        if (asig<=tamanio)
        {
            int limit = asig+5;
            for(int x = asig; x<=limit; x++)
            {
                panel=panel+1;
                if (b.get(x).toString()!=null)
                {
                    direccion= a.get(x).toString();
                    nom=b.get(x).toString();
                    cant=c.get(x).toString();
                    prec=d.get(x).toString();
                    direccion= direccion.replace("*","\\");
                    Image img= new Image("file:/"+direccion);
                    paneles(panel,img,nom,cant,prec);
                }
                else{
                    direccion=null;
                    nom= " ";
                    cant= null;
                    prec= null;
                    direccion=null;
                    Image n = new Image("");
                    paneles(panel,n,nom,cant,prec);
                }
            }

        }
    }

    public void datos(){
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
        //mostrar();
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
}

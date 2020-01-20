package controllers.Proveedores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import utils.ConnectionUtil;
import utils.closeView;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
public class View implements Initializable {

    @FXML private StackPane PaneConteiner;
    @FXML private ImageView photo;
    @FXML private Label Nombre,Tel,Organ,Direc,Cuenta;

    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;
    static Double x,y;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Card card = new Card();String name=card.getNombre();
        Nombre.setText("Nombre: "+name);
        Tel.setText(card.getMovil());
        Organ.setText(card.getOrg());
        Direc.setText(card.getDirecc());
        Image img=card.getImagen();
        x=img.getWidth();
        y=img.getHeight();
        Ajuste(x,y);
        PaneConteiner.setPrefWidth(x);PaneConteiner.setPrefHeight(y);
        photo.setFitWidth(x);
        photo.setFitHeight(y);
        photo.setImage(img);
        try {
            Buscar(name);
        } catch (SQLException e) {
        }
    }

    public void Buscar(String name) throws SQLException {
        String text[] = name.split(" ");String cuenta;
        String Query = "SELECT NO_CUENTA FROM PERSONAS INNER JOIN PROVEEDORES " +
                "WHERE PERSONAS.ID_PERSONA=PROVEEDORES.ID_PERSONA AND PERSONAS.PRIMER_NOMBRE='" + text[0] +
                "' AND PERSONAS.PRIMER_APELLIDO='" + text[1] + "';";
        conexion = conn.getConnection();
        Statement instruccion = conexion.createStatement();
        ResultSet resultado = instruccion.executeQuery(Query);
        if (resultado != null) {
            while (resultado.next()) {
                cuenta=resultado.getString("NO_CUENTA");
                Cuenta.setText("No. Cuenta: "+cuenta);
            }
        }

    }

    public void Cerrar(javafx.event.ActionEvent actionEvent) {
        closeView closeView= new closeView();
        closeView.Cerrar(PaneConteiner);
    }

    public void Ajuste(double m, double n){
        if(m>720){      x = x*0.80;      y = y*0.80;Ajuste(x,y);}
        else if(m<500){ x = x*1.20;      y = y*1.20;Ajuste(x,y);}
        else{x=m;y=n;}
    }
}
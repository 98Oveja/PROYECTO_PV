package controllers.Proveedores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utils.ConnectionUtil;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Edit implements Initializable {
    @FXML private Pane Container;
    @FXML private TextField Direccion, Telefono, Correo, Organizacion, Cuenta;
    @FXML private ImageView Photo;
    @FXML private Label NombreC;

    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;
    Card card = new Card();

    public void Agregar(ActionEvent actionEvent) {
    }

    public void Guardar(ActionEvent actionEvent) {
    }

    public void Close(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try { Buscar(card.getNombre());
        } catch (SQLException e) {}
    }

    public void Buscar(String name) throws SQLException {
        String text[] = name.split(" ");
        String cuenta = null,nombre = null,correo=null;
        String Query = "SELECT SEGUNDO_NOMBRE,SEGUNDO_APELLIDO,CORREO,NO_CUENTA FROM PERSONAS" +
                "    INNER JOIN PROVEEDORES P on PERSONAS.ID_PERSONA = P.ID_PERSONA " +
                "WHERE PRIMER_NOMBRE='"+text[0]+"' AND PRIMER_APELLIDO='"+text[1]+"' AND ORG='"+card.convertir(card.getOrg())+"' AND TELEFONO='"+card.convertir(card.getMovil())+"';";
        conexion = conn.getConnection();
        Statement instruccion = conexion.createStatement();
        ResultSet resultado = instruccion.executeQuery(Query);
        if (resultado != null) {
            while (resultado.next()) {
                cuenta = resultado.getString("NO_CUENTA");
                nombre= text[0]+" "+resultado.getString("SEGUNDO_NOMBRE")+" "+text[1]+" "+resultado.getString("SEGUNDO_APELLIDO");
                correo= resultado.getString("CORREO");
            }
            Cuenta.setText(cuenta);
            NombreC.setText(nombre);
            Direccion.setText(card.convertir(card.getDirecc()));
            Telefono.setText(card.convertir(card.getMovil()));
            Correo.setText(correo);
            Organizacion.setText(card.convertir(card.getOrg()));
            Image img = card.getImagen();
            Photo.setImage(img);
        }
    }
}
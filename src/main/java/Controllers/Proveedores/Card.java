package Controllers.Proveedores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Utils.ConnectionUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Card implements Initializable {
    @FXML private Label Nombre,Direccion,Movil,Org;Double x,y;
    @FXML private ImageView Photo;
    public static String nombre,direcc,movil,org,prueba;
    public static Image imagen;
    Double alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    Double largo = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    Principal principal = new Principal();
    Nombre.setText(principal.getNombre());
    Direccion.setText("Direccion: "+principal.getDescripcion());
    Movil.setText("Movil: "+principal.getMovil());
    Org.setText("Organización: "+principal.getOrg());
    fondo(principal.getPath());
    }

    Image getImagen(){return imagen;}
    String getNombre(){return nombre;}
    String getDirecc(){return direcc;}
    String getMovil(){return movil;}
    String getOrg(){return org;}

    void fondo(String foto){
        javafx.scene.image.Image image;
        if(foto.contains("*")){
            foto=foto.replace("*","\\");
            image = new Image("file:/"+foto);
        }else{
            image = new Image("/images/Proveedor.png");
        }Photo.setImage(image);
    }

    public void Visualizar(ActionEvent actionEvent) throws IOException {
        String search=Photo.getImage().getUrl();
        Image im = new Image(search);   imagen=im;
        Ajuste(im.getWidth(),im.getHeight());
        cargar("/fxml/Proveedores/View.fxml");
    }

    public void Ajuste(double m, double n){
        if(m>720){      x = m*0.80;      y = n*0.80;Ajuste(x,y);}
        else if(m<500){ x = m*1.20;      y = n*1.20;Ajuste(x,y);}
        else{x=m;y=n;}
    }

    public void Editar(ActionEvent actionEvent) throws IOException {
        x=570.00;y=675.00;
        cargar("/fxml/Proveedores/Edit.fxml");
    }

    public void cargar(String fxml) throws IOException {
        nombre=Nombre.getText();
        direcc=Direccion.getText();     movil=Movil.getText();
        org=Org.getText();
        final Stage primaryStage = new Stage();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initOwner(primaryStage);
        dialog.setX((largo/2)-x/2);
        dialog.setY((alto/2)-y/2);
        Scene dialogScene = null;
        dialogScene = new Scene(FXMLLoader.load(getClass().getResource(fxml)));
        dialog.setScene(dialogScene);
        dialog.show();
    }

    String convertir(String texto){
        String[] Mobil=texto.split(" ");
        String mov = "";
        int i=Mobil.length;int leng=i-1;
        for (int x=1;x<i;x++){
            mov=mov+Mobil[x];
            if(x!=leng){mov=mov+" ";}
        }
        return mov;
    }

    public void Delete(ActionEvent actionEvent) throws SQLException {
        String[] Name=Nombre.getText().split(" ");
        String mov,org;
        mov=convertir(Movil.getText());
        org=convertir(Org.getText());
        String query="UPDATE PROVEEDORES as p " +
                "inner join PERSONAS P2 on p.ID_PERSONA = P2.ID_PERSONA " +
                "SET ESTADO = if(ESTADO=1,0,1) " +
                "WHERE ORG='"+org+"' and PRIMER_NOMBRE='"+Name[0]+"' and PRIMER_APELLIDO = '"+Name[1]+"';";
        System.out.println(query);
        Alert dialogo = new Alert(Alert.AlertType.CONFIRMATION);
        dialogo.setTitle("Cambiar estado proveedor");
        dialogo.setHeaderText(null);
        dialogo.initStyle(StageStyle.UNDECORATED);
        dialogo.setContentText("Seguro que quieres cambiar el estado del proveedor:\n  -" + Name[0] + Name[1] +
                "\n\nPodras buscar y cambiar su estado al navegar en proveedores activos e inactivos");
        Optional<ButtonType> result = dialogo.showAndWait();
        if (result.get() == ButtonType.OK) {
            conexion = conn.getConnection();
            PreparedStatement preparedStatement = conexion.prepareStatement(query);//insert.execute(query);
            preparedStatement.execute();
        }
    }
}
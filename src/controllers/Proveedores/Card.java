package controllers.Proveedores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Card implements Initializable {
    @FXML private Label Nombre,Direccion,Movil,Org;Double x,y;
    @FXML private ImageView Photo;
    public static String nombre,direcc,movil,org;
    public static Image imagen;
    Double alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    Double largo = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
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
        nombre=Nombre.getText();        String search=Photo.getImage().getUrl();
        Image im = new Image(search);   imagen=im;
        direcc=Direccion.getText();     movil=Movil.getText();
        org=Org.getText();
        final Stage primaryStage = new Stage();
        final Stage dialog = new Stage();
        Ajuste(im.getWidth(),im.getHeight());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initOwner(primaryStage);
        dialog.setX((largo/2)-x/2);
        dialog.setY((alto/2)-y/2);
        Scene dialogScene = null;
        dialogScene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Proveedores/View.fxml")));
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void Ajuste(double m, double n){
        if(m>720){      x = m*0.80;      y = n*0.80;Ajuste(x,y);}
        else if(m<500){ x = m*1.20;      y = n*1.20;Ajuste(x,y);}
        else{x=m;y=n;}
    }
}
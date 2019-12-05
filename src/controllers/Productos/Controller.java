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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Controller implements Initializable
{



    @FXML
    private ImageView imageview;
    @FXML
    private Button agregarV;

    Connection conexion = null;
    ConnectionUtil conn = new ConnectionUtil();
    String direccion;
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

    public  String consulta(){
        String sql = null;
        sql = "SELECT IMG FROM PRODUCTOS";
        try {
            conexion= conn.getConnection();
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("NO FUNCIONA");
            } else {
                    direccion = resultSet.getString("IMG");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return direccion;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        direccion="C:*Users*Wilian*Downloads*mart.jpg";
        String nueva = direccion.replace("*","\\");
        System.out.println(nueva);
        Image image;
        image = new Image("file:/"+nueva);
        imageview.setImage(image);
    }
}
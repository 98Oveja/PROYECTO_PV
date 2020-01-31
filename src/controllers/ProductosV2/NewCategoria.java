package controllers.ProductosV2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.controlsfx.control.CheckComboBox;
import utils.ConnectionUtil;
import utils.ImageChooser;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewCategoria implements Initializable {

    @FXML private TextField Nombre;
    @FXML private TextArea Descripcion;
    @FXML private ImageView Imagen;
    @FXML private Pane Contenedor;

    String path;
    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;

    public void Addphoto(ActionEvent actionEvent){
        ImageChooser imageChooser = new ImageChooser();
        path = imageChooser.getImage();
        Image image = new Image("file:/"+path);
        Imagen.setImage(image);
    }

    public void Guardar(ActionEvent actionEvent) throws SQLException {
        String direccion = null;
        if (!Nombre.getText().isEmpty()&&!Descripcion.getText().isEmpty()){
            if(path!=null&&path.contains("\\")){
                direccion=path.replace("\\","*");
            }else{direccion=path;}
        }
        String Query=("INSERT INTO CATEGORIAS(NOMBRE, DESCRIPCION, IMG_URL) " +
                "VALUES ('"+Nombre.getText()+"','"+Descripcion.getText()+"','"+direccion+"',);");
        conexion = conn.getConnection();
        PreparedStatement preparedStatement = conexion.prepareStatement(Query);
        preparedStatement.execute();
    }
    
    

    public void Volver(ActionEvent actionEvent) {
        Contenedor.setVisible(false);
    }

    public void combo(){
        ObservableList<String> strings = FXCollections.observableArrayList();
        for (int i = 0; i <10 ; i++) {
            strings.add("Product "+i);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combo();
    }
}
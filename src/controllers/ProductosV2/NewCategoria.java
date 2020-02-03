package controllers.ProductosV2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;
import utils.ImageChooser;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewCategoria implements Initializable {

    @FXML private TextField Nombre;
    @FXML private TextArea Descripcion;
    @FXML private ImageView Imagen;
    @FXML private Pane Contenedor;
    @FXML private VBox ConteinerProducto;

    ArrayList<String> nombresCategorias = new ArrayList<String>();
    ArrayList<CheckBox> arrayListProducto = new ArrayList<CheckBox>();
    String path;
    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;

    public void nombrescategoriasexistentes(){
        NewProduct newProduct = new NewProduct();
        try {
            ResultSet resultados = newProduct.consultas("SELECT NOMBRE FROM CATEGORIAS;");
            if (resultados != null) {
                while (resultados.next()) {
                    nombresCategorias.add(resultados.getString("NOMBRE").toUpperCase());
                }
            }
        }catch(SQLException e){}
    }

    public void Addphoto(ActionEvent actionEvent){
        ImageChooser imageChooser = new ImageChooser();
        path = imageChooser.getImage();
        Image image = new Image("file:/"+path);
        Imagen.setImage(image);
    }

    public void Guardar(ActionEvent actionEvent) throws SQLException {
        if (!nombresCategorias.contains(Nombre.getText().toUpperCase())){
            String direccion = null;
            if (!Nombre.getText().isEmpty()&&!Descripcion.getText().isEmpty()){
                if(path!=null&&path.contains("\\")){
                    direccion=path.replace("\\","*");
                }else{direccion=path;}
                String Query=("INSERT INTO CATEGORIAS(NOMBRE, DESCRIPCION, IMG_URL) " +
                        "VALUES ('"+Nombre.getText()+"','"+Descripcion.getText()+"','"+direccion+"');");
                conexion = conn.getConnection();
                PreparedStatement preparedStatement = conexion.prepareStatement(Query);
                preparedStatement.execute();
            }
            insertproductosCategorias();
            limpiar();
        }else if(Nombre.getText().isEmpty()){
            Alert dialogo= new Alert(Alert.AlertType.INFORMATION);
            dialogo.setHeaderText(null);
            dialogo.initStyle(StageStyle.UNDECORATED);
            dialogo.setContentText("Debe llenar el campo nombre");
            dialogo.showAndWait();
        }else if (nombresCategorias.contains(Nombre.getText().toUpperCase())){
            Alert dialogo= new Alert(Alert.AlertType.INFORMATION);
            dialogo.setHeaderText(null);
            dialogo.initStyle(StageStyle.UNDECORATED);
            dialogo.setContentText("El producto ya existe");
            dialogo.showAndWait();
        }
    }

    private void limpiar() {
        Nombre.clear();
        Descripcion.clear();
        for (int i = 0; i < arrayListProducto.size(); i++) {
            arrayListProducto.get(i).setSelected(false);
        }
        Imagen.setImage(null);
    }

    public void insertproductosCategorias(){
        String Query= "INSERT INTO DETALLE_CATEGORIA_PRODUCTOS(ID_PRODUCTO, ID_CATEGORIA) VALUES ";
        int aux=0;
        for (int i = 0; i < arrayListProducto.size(); i++) {
            if (arrayListProducto.get(i).isSelected()){
                if (aux>0){Query=Query+",";}
                Query=Query+"((SELECT ID_PRODUCTO FROM PRODUCTOS WHERE NOMBRE ='"+arrayListProducto.get(i).getText()+"'),(SELECT ID_CATEGORIA FROM CATEGORIAS WHERE NOMBRE='"+Nombre.getText()+"'))";
                aux=1;
            }
        }
        if (aux==1){
            Query=Query+";";
            try{
            conexion = conn.getConnection();
            PreparedStatement preparedStatement = conexion.prepareStatement(Query);
            preparedStatement.execute();}
            catch (Exception e){
                System.out.println(e);
                System.out.println(Query);
            }
        }
    }

    public void Volver(ActionEvent actionEvent) {
        Contenedor.setVisible(false);
    }

    public void combo(){
        NewProduct newProduct = new NewProduct();
        try {
            ResultSet resultados = newProduct.consultas("SELECT NOMBRE FROM PRODUCTOS order by NOMBRE asc;");
            if (resultados != null) {
                while (resultados.next()) {
                    CheckBox checkBox = new CheckBox();
                    checkBox.setText(resultados.getString("NOMBRE"));
                    checkBox.setStyle("-fx-text-fill: rgba(0,0,0,.8);-fx-text-alignment: center;" +
                            "-fx-background-color: white;-fx-pref-width: 383px");
                    arrayListProducto.add(checkBox);
                    }
                if (arrayListProducto.size() != 0) {
                    for (int i = 0; i < arrayListProducto.size(); i++) {
                        ConteinerProducto.getChildren().add(arrayListProducto.get(i));
                    }
                }
            }
        }catch(SQLException e){}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nombrescategoriasexistentes();
        combo();
    }
}
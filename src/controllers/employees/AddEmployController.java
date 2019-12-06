package controllers.employees;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.ConnectionUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddEmployController implements Initializable {
    public BorderPane imgUser;
    Date date =new Date();//varaiables para obtener la fecha actual del system
    long milsec = date.getTime();
    java.sql.Date dia = new java.sql.Date(milsec);
    ObservableList<String> list= FXCollections.observableArrayList("Administrador","Bodeguero","Vendedor","Secretaria");

@FXML
    private AnchorPane PanelAddEmploy;
@FXML
    private TextField EmployNameOne, EmployNameTwo, EmployLasteNameOne, EmployLasteNameTwo, EmployDir, EmployPhone, EmployDate, EmployPlace, EmployEmail;
@FXML
    private ImageView photoEmploy;
@FXML
    private JFXComboBox<String> placeList;


private int idpersona;
    private String directionImage;

 @FXML
    private void CloseModal(){
     Stage stage = (Stage) PanelAddEmploy.getScene().getWindow();
     stage.close();
 }


 @FXML
    public void AddEmploy(ActionEvent event) throws SQLException {
     String firstName= EmployNameOne.getText();
     String secondName= EmployNameTwo.getText();
     String firstLastName= EmployLasteNameOne.getText();
     String secondLastName= EmployLasteNameTwo.getText();
     String direction= EmployDir.getText();
     String numberPhone= EmployPhone.getText();
     EmployPlace.setText(placeList.getValue());
     String email= EmployEmail.getText();

     Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
     alert.setTitle("Confirmar Informacion...");
     alert.setContentText("Verifica los datos: Nombres: "+firstName +" "+ secondName +" "+firstLastName+" "+secondLastName+ "\nDireccion: "+direction+" Telefono: "+numberPhone+" Puesto: "+placeList.getValue());
     //agregamos los botones al dialogo
     ButtonType yes= new ButtonType("Si...");
     ButtonType no= new ButtonType("No...");
     alert.getButtonTypes().setAll(yes,no);
     Optional<ButtonType> optional= alert.showAndWait();

        if(optional.get() == yes) {
            querySql(firstName, secondName, firstLastName, secondLastName, direction, numberPhone,placeList.getValue(), email);//metodo insertar
            ClearTextField();//limipiar los textfield
        }else{
            System.out.println("Cancelo el ingreso");
        }

 }

     //METODO PARA REALIZAR EL INGRESO DE UN CLIENTE
    public void querySql(String firstName,String secondName,String firstLastName,String secondLastName,String direction, String numberPhone,String place, String email) throws SQLException {
        String Nueva = "";
            String separador = Pattern.quote("\\");
            String[] dir = directionImage.split(separador);

            for (int i = 0; i <= (dir.length - 1); i++) {
                Nueva = Nueva + dir[i] + "*";
            }
            System.out.println(Nueva);



        ConnectionUtil connectionClass= new ConnectionUtil();
        Connection connection= connectionClass.getConnection();  /*coneccion establecida*/
        String sqlinsert= "INSERT INTO `PERSONAS` (`ID_PERSONA`, `PRIMER_NOMBRE`, `SEGUNDO_NOMBRE`, `PRIMER_APELLIDO`, `SEGUNDO_APELLIDO`, `DIRECCION`, `TELEFONO`, `CORREO`, `url_foto`) VALUES (NULL, '"+firstName+"', '"+secondName+"', '"+firstLastName+"', '"+secondLastName+"', '"+direction+"', '"+numberPhone+"', '"+email+"','"+Nueva+"')";
        Statement statement= connection.createStatement();
        statement.executeUpdate(sqlinsert); //aca insertamos los dato

        //aca buscaremos el id de la persona ingresada--
        String searchId = "SELECT `ID_PERSONA` FROM `PERSONAS` WHERE `PRIMER_NOMBRE` = '"+firstName+"' AND `SEGUNDO_NOMBRE` = '"+secondName+"' AND `PRIMER_APELLIDO` = '"+firstLastName+"'";
        ResultSet result = statement.executeQuery(searchId);
        if (result.first()){
            idpersona = result.getInt("ID_PERSONA");
        }

        //esteremos realizando el segundo insert para la tabla empleados
        String sql2= "INSERT INTO `EMPLEADOS` (`ID_EMPLEADO`, `ID_PERSONA`, `ESTADO`, `FECHA_CONTRATACION`, `FECHA_RETIRO`, `CARGO`) VALUES (NULL,'"+idpersona+"', '1', '"+dia.toString()+"', NULL, '"+place+"')";
        statement.executeUpdate(sql2);
    }

    //metodo para limpiar las variales
     public void ClearTextField(){
         Image ima = new Image("/images/male_user_.png");
         EmployNameOne.setText("");
         EmployNameTwo.setText("");
         EmployLasteNameOne.setText("");
         EmployLasteNameTwo.setText("");
         EmployDir.setText("");
         EmployPhone.setText("");
         EmployPlace.setText("");
         EmployEmail.setText("");
         photoEmploy.setImage(ima);
         directionImage="NULL";
     }

     public void validatorString(){

     }

     //URL de la imagen
    public String searchEmploy(ActionEvent actionEvent) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            directionImage= selectedFile.getPath();
            System.out.println(directionImage);
            return setImgUser("/images/empleados.png");
            //image = new Image("file:/"+directionImage);/root/IdeaProjects/appJavaFX/src/images/empleados.png

        } else{
            System.out.println("file is not valid");
            return "NULL";
        }
    }
    private String setImgUser(String url) {
        Circle circle = new Circle(86,86,43);
        Image image = new Image(url,false);
        circle.setFill(new ImagePattern(image));
        imgUser.setCenter(circle);
        return url;
    }

    @FXML
    private void selectItem(ActionEvent event){
        EmployPlace.setText(placeList.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImgUser("/images/male_user_.png");
        EmployDate.setText(dia.toString());
        placeList.setItems(list);
        EmployPlace.setEditable(false);
    }
}


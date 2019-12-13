package controllers.employees;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    public Button btnCerrarModal;
    Date date =new Date();//varaiables para obtener la fecha actual del system
    long milsec = date.getTime();
    java.sql.Date dia = new java.sql.Date(milsec);
    ObservableList<String> list= FXCollections.observableArrayList("Administrador","Bodeguero","Vendedor","Secretaria");

@FXML
    private AnchorPane PanelAddEmploy;
@FXML
    private TextField EmployNameOne, EmployNameTwo, EmployLasteNameOne, EmployLasteNameTwo, EmployDir, EmployPhone, EmployDate, EmployPlace, EmployEmail;
@FXML
    private ImageView warningOne, warningTwo, warningThree, warningFour, warningFive, warningSix;

@FXML
    private JFXComboBox<String> placeList;


private int idpersona;
    private String directionImage;

 @FXML
    private void CloseModal(){
     Stage stage = (Stage) PanelAddEmploy.getScene().getWindow();
     stage.close();
 }

    //METODOS PARA VALIDAR SOLO LETRAS
    public void validateLetter(TextField campoDeTexto) {
        campoDeTexto.addEventFilter(KeyEvent.ANY, event -> {
            char c = event.getCharacter().charAt(0);
            if (!(Character.isLetter(c)|| Character.isWhitespace(c) || Character.isISOControl(c))){
                event.consume();
            }
        });
    }
    public void validateNumber(TextField campo){
        campo.addEventFilter(KeyEvent.ANY, event ->{
            char c = event.getCharacter().charAt(0);
            System.out.println(c);
            if (!(Character.isDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c)) && c!='.'){
                event.consume();
//                            }
            if (c == '.' && campo.getText().contains(".")){
                event.consume();
            }
        }
        }
        );
    }
 @FXML
    public void AddEmploy(ActionEvent event) throws SQLException {
     validateLetter(EmployNameOne);
     validateLetter(EmployNameTwo);
     validateLetter(EmployLasteNameOne);
     validateLetter(EmployLasteNameTwo);
     validateNumber(EmployPhone);

     String firstName= EmployNameOne.getText();
     String secondName= EmployNameTwo.getText();
     String firstLastName= EmployLasteNameOne.getText();
     String secondLastName= EmployLasteNameTwo.getText();
     String direction= EmployDir.getText();
     String numberPhone= EmployPhone.getText();
     EmployPlace.setText(placeList.getValue());
     String email= EmployEmail.getText();


     if (placeList.getValue()==null){
         EmployPlace.setPromptText("Elige un puesto");
         EmployPlace.setStyle("-fx-prompt-text-fill: rgba(255,180,13,0.65);");
         warningFive.setVisible(true);
     }

     Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
     alert.setTitle("Confirmar Informacion...");
     alert.setContentText("Verifica los datos: Nombres: "+firstName +" "+ secondName +" "+firstLastName+" "+secondLastName+ "\nDireccion: "+direction+" Telefono: "+numberPhone+" Puesto: "+placeList.getValue());
     //agregamos los botones al dialogo
     ButtonType yes= new ButtonType("Si!");
     ButtonType no= new ButtonType("No!");
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
         setImgUser("/images/male_user_.png");
         directionImage="NULL";
     }

     //URL de la imagen
    @FXML
    public String searchEmploy(MouseEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            directionImage= selectedFile.getPath();
            System.out.println(directionImage);
            return setImgUser("file:/"+directionImage);

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
    private void EventKeyEnterNameOne(KeyEvent event){
        if(event.getCode()==KeyCode.ENTER){ EmployNameTwo.requestFocus(); }
            if(EmployNameOne.getLength() < 3){
                EmployNameOne.setPromptText("Ingresa un nombre valido");
                EmployNameOne.setStyle("-fx-prompt-text-fill:  rgba(255,180,13,0.65);");
                warningOne.setVisible(true);
             }else{
         warningOne.setVisible(false);
        }
        }
    @FXML
    private void EventKeyEnteNameTwo(KeyEvent event){
     if(event.getCode()==KeyCode.ENTER){
         EmployLasteNameOne.requestFocus();
     }
    }

    @FXML
    private void EventKeyEnterLastNameOne(KeyEvent event){
     if(event.getCode()==KeyCode.ENTER){ EmployLasteNameTwo.requestFocus();
         if (EmployLasteNameOne.getLength() < 3){
             EmployLasteNameOne.setPromptText("Ingresa un apellido valida");
             EmployLasteNameOne.setStyle("-fx-prompt-text-fill:  rgba(255,180,13,0.65)");
             warningTwo.setVisible(true);
         }else{
             warningTwo.setVisible(false);
         }
     }}
    @FXML
    private void EventKeyEnterLasteNameTwo(KeyEvent event){
        if(event.getCode()==KeyCode.ENTER) {EmployDir.requestFocus();}
     }

    @FXML
    private void EventKeyEnteDir(KeyEvent event){
     if(event.getCode()==KeyCode.ENTER){ EmployPhone.requestFocus(); }
        if (EmployDir.getLength() <= 4){
            EmployDir.setPromptText("Ingresa una direccion valida");
            EmployDir.setStyle("-fx-prompt-text-fill:  rgba(255,180,13,0.65);");
            warningThree.setVisible(true);
        }else{warningThree.setVisible(false);}
     }
    @FXML
    private void EventKeyEnterPhone(KeyEvent event){
    if(event.getCode()==KeyCode.ENTER){ EmployEmail.requestFocus();}
            if (EmployPhone.getLength() < 8) {
                EmployPhone.setPromptText("Ingresa un numero valido");
                EmployPhone.setStyle("-fx-prompt-text-fill: rgba(255,180,13,0.65);");
                warningFour.setVisible(true);
            }else{warningFour.setVisible(false);}
 }


    @FXML
    private void EventKeyEnterEmail(KeyEvent event){
     if(event.getCode()==KeyCode.ENTER){ placeList.requestFocus();}
        if (EmployEmail.getLength() <= 8){
            EmployEmail.setPromptText("Ingresa un correo valida");
            EmployEmail.setStyle("-fx-prompt-text-fill: rgba(255,180,13,0.65);");
            warningSix.setVisible(true);
        }else{  warningSix.setVisible(false);}
 }

    @FXML
    private void selectItem(ActionEvent event){EmployPlace.setText(placeList.getValue()); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImgUser("/images/male_user_.png");
        EmployDate.setText(dia.toString());
        placeList.setItems(list);
        EmployPlace.setEditable(false);
    }
}


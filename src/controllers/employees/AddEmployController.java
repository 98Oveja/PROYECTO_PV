package controllers.employees;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Employ.dataEmploy;
import models.Employ.sqlEmploy;
import utils.ConnectionUtil;
import utils.ParseEmail;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;


public class AddEmployController implements Initializable {
    public BorderPane imgUser;
    public Button btnCerrarModal;
    public JFXButton BtnSaveEmploy, BtnUpdateEmploy;
    @FXML
    public AnchorPane PanelAddEmploy;
@FXML
    public TextField EmployNameOne, EmployNameTwo, EmployLasteNameOne, EmployLasteNameTwo, EmployDir, EmployPhone, EmployDate, EmployPlace, EmployEmail;
@FXML
    public ImageView   warningOne,warningTwo, warningThree, warningFour, warningFive, warningSix;

@FXML
    public JFXComboBox<String> placeList;
@FXML
    public Label TitleModal;
    sqlEmploy sqlGeneralEmploy = new sqlEmploy();

    Date date =new Date();//varaiables para obtener la fecha actual del system
    long milsec = date.getTime();
    java.sql.Date dia = new java.sql.Date(milsec);
    ObservableList<String> list= FXCollections.observableArrayList("Administrador","Bodeguero","Vendedor","Secretaria");

public int idpersona,infoStatus;
public dataEmploy emDb=new dataEmploy();
@FXML
public void CloseModal(){
    Image image = new Image("/images/info.png");
    CloseModalMethod("Salir","Esta seguro que desea salir del Modal??",image,1);
}
@FXML
    public void CloseModalMethod(String title,String contenido,Image image,int op){
     try {
         FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/DeleteEmploy.fxml"));
         Parent root = Loader.load();
         DelEmployController controller = Loader.getController();
         controller.TitleModal.setText(title);
         controller.contentAlert.setText(contenido);
         controller.IconModal.setImage(image);
         if(op==0){
             controller.Cancel.setVisible(false);
             controller.Okay.setStyle("-fx-translate-x: 65px; -fx-translate-y: -10px;");
             controller.Okay.setText("Ok");
         }
         Scene dialogo = new Scene(root);
         Stage stagedialog = new Stage();
         stagedialog.initStyle(StageStyle.UNDECORATED);
         stagedialog.initModality(Modality.APPLICATION_MODAL);
         stagedialog.setScene(dialogo);
         stagedialog.showAndWait();
         if(controller.BtnOk==1 && op==1){
             exitAddEmployModal();
         }
     }catch (Exception ex){ System.out.println(ex);}
 }

 @FXML
 public void initDatos(String place){
     emDb.name1= EmployNameOne.getText();
     emDb.name2= EmployNameTwo.getText();
     emDb.lastname1= EmployLasteNameOne.getText();
     emDb.lastname2= EmployLasteNameTwo.getText();
     emDb.dir= EmployDir.getText();
     emDb.tel= EmployPhone.getText();
     emDb.cargo = place;
     emDb.correo= EmployEmail.getText();
 }

    @FXML
    public void AddEmploy(ActionEvent event) throws IOException {
        initDatos(placeList.getValue());
        validatorPlace();
          int vl = validatorWarnings();
            if(vl == 0) {
                loaderModalInfo();
                if (infoStatus == 1) {
                    sqlGeneralEmploy.insertEmploy(idpersona ,emDb.name1, emDb.name2, emDb.lastname1, emDb.lastname2, emDb.dir, emDb.tel, placeList.getValue(), emDb.correo,emDb.img);
                    ClearTextField();//limipiar los textfield
                    System.out.println("Insertado... AddEmployController line 123");
                } else {
                    System.out.println("Cancelo el ingreso");
                }
            }
    }

    public int validatorWarnings(){
        if(warningOne.isVisible() || warningTwo.isVisible() || warningThree.isVisible() || warningFour.isVisible() || warningFive.isVisible() || warningSix.isVisible() ||
            EmployPlace.getLength() == 0 || EmployNameOne.getLength() == 0 || EmployLasteNameOne.getLength() == 0 || EmployDir.getLength() == 0 || EmployEmail.getLength() == 0 || EmployPhone.getLength() == 0){
            Image image = new Image("/images/info.png");
            CloseModalMethod("Informacion","Debe llenar los datos requeridos",image,0);
            return 1;
        }else {
            return 0;
        }

    }

    public void loaderModalInfo() throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/fxml/Empleados/InfoEmploy.fxml"));
        Parent root = Loader.load();
        InfoEmployController controller = Loader.getController();
        controller.label1.setText("Nombres:  " + EmployNameOne.getText() + " " + EmployNameTwo.getText());
        controller.label2.setText("Apellidos:  " + EmployLasteNameOne .getText()+ " " + EmployLasteNameTwo.getText());
        controller.label3.setText("Dirección:  " + EmployDir.getText());
        controller.label4.setText("Teléfono:  " + EmployPhone.getText());
        controller.label5.setText("Correo:  " + EmployEmail.getText());
        controller.label6.setText("Puesto:  " + EmployPlace.getText());
        Scene dialogo = new Scene(root);
        Stage stagedialog = new Stage();
        stagedialog.initStyle(StageStyle.UNDECORATED);
        stagedialog.initModality(Modality.APPLICATION_MODAL);
        stagedialog.setScene(dialogo);
        stagedialog.showAndWait();
        infoStatus = controller.status;
    }

    //metodo para limpiar las variales
    public void ClearTextField(){
        EmployNameOne.setText("");
        EmployNameTwo.setText("");
        EmployLasteNameOne.setText("");
        EmployLasteNameTwo.setText("");
        EmployDir.setText("");
        EmployPhone.setText("");
        EmployPlace.setText("");
        EmployEmail.setText("");
        setImgUser("/images/male_user_.png");
        emDb.img="NULL";
    }

    @FXML
    public String searchEmploy(MouseEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
           emDb.img= selectedFile.getPath();
            return setImgUser("file:/"+emDb.img);
        } else{
            return "/images/male_user_.png";
        }
    }

    public String setImgUser(String url) {
        Circle circle = new Circle(86,86,43);
        Image image = new Image(url,false);
        circle.setFill(new ImagePattern(image));
        imgUser.setCenter(circle);
        return url;
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
                    if (!(Character.isDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c)) && c!='.'){
                        event.consume();
                        if (c == '.' && campo.getText().contains(".")){event.consume();}
                    }
                });
    }

    @FXML
    public void validorGeneral(){
        validateLetter(EmployNameOne);
        validateLetter(EmployNameTwo);
        validateLetter(EmployLasteNameOne);
        validateLetter(EmployLasteNameTwo);
        validateNumber(EmployPhone);
    }

    @FXML
    private void EventKeyEnteNameOne(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            EmployNameTwo.requestFocus();
            if (EmployNameOne.getLength() <= 1) {
                warningOne.setVisible(true);
            } else {
                warningOne.setVisible(false);
            }
        }
    }

    @FXML
    private void EventKeyEnteNameTwo(KeyEvent event){
     if(event.getCode()==KeyCode.ENTER){
         EmployLasteNameOne.requestFocus();
         if (EmployNameOne.getLength() <=2){
             warningOne.setVisible(true);
         }else{
             warningOne.setVisible(false);
         }
     }
    }

    @FXML
    private void EventKeyEnterLastNameOne(KeyEvent event){
     if(event.getCode()==KeyCode.ENTER){ EmployLasteNameTwo.requestFocus();
         if (EmployLasteNameOne.getLength() < 3){
             warningTwo.setVisible(true);
         }else{
             warningTwo.setVisible(false);
         }
     }}

     @FXML
     private void validatorPlace(){
         if ( EmployPlace.getText() == null ){
             warningFive.setVisible(true);
         }
     }

    @FXML
    private void EventKeyEnterLasteNameTwo(KeyEvent event){
        if(event.getCode()==KeyCode.ENTER) {EmployDir.requestFocus();
            if (EmployLasteNameTwo.getLength() < 3){
                warningTwo.setVisible(true);
            }else{
                warningTwo.setVisible(false);
            }
        }
     }

    @FXML
    private void EventKeyEnteDir(KeyEvent event){
     if(event.getCode()==KeyCode.ENTER){ EmployPhone.requestFocus(); }
        if (EmployDir.getLength() <= 3){
            warningThree.setVisible(true);
        }else{warningThree.setVisible(false);}
     }

    @FXML
    private void EventKeyEnterPhone(KeyEvent event){
    if(event.getCode()==KeyCode.ENTER){ EmployEmail.requestFocus();}
            if (EmployPhone.getLength() <=6) {
                warningFour.setVisible(true);
            }else{warningFour.setVisible(false);}
    }

    @FXML
    private void EventKeyEnterEmail(KeyEvent event){
        ParseEmail valEmali= new ParseEmail();
        if (!valEmali.isValid(EmployEmail.getText())){
            warningSix.setVisible(true);
        }else{  warningSix.setVisible(false);}
    }

    @FXML
    public void selectItem(ActionEvent event){
     EmployPlace.setText(placeList.getValue());
     warningFive.setVisible(false);
    }
    @FXML
    public void btnUpdate() throws IOException {
       int n = validatorWarnings();
        if( n == 0){
            loaderModalInfo();
            if(infoStatus == 1){
                exitAddEmployModal();
            }
        }
    }
    public void exitAddEmployModal(){
        Stage stage = (Stage) PanelAddEmploy.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImgUser("/images/male_user_.png");
        EmployDate.setText(dia.toString());
        placeList.setItems(list);
        validorGeneral();
    }
}
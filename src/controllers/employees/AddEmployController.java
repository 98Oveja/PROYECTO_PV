package controllers.employees;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;

public class AddEmployController {
@FXML
    private AnchorPane PanelAddEmploy;
@FXML
    private TextField EmployNameOne, EmployNameTwo, EmployLasteNameOne, EmployLasteNameTwo, EmployDir, EmployPhone, EmployDate, EmployPlace, EmployEmail;
@FXML
    private JFXButton BtnSaveEmploy;
    int idpersona;

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
     String place= EmployPlace.getText();
     String email= EmployEmail.getText();
     String dateTime= "2019-02-12";


     EmployDate.setText(dateTime);

     ConnectionClass connectionClass= new ConnectionClass();
        Connection connection= connectionClass.conecctiondb();  /*coneccion establecida*/
        String sqlinsert= "INSERT INTO `personas` (`ID_PERSONA`, `PRIMER_NOMBRE`, `SEGUNDO_NOMBRE`, `PRIMER_APELLIDO`, `SEGUNDO_APELLIDO`, `DIRECCION`, `TELEFONO`, `CORREO`) VALUES (NULL, '"+firstName+"', '"+secondName+"', '"+firstLastName+"', '"+secondLastName+"', '"+direction+"', '"+numberPhone+"', '"+email+"')";
        Statement statement= connection.createStatement();
        statement.executeUpdate(sqlinsert); //aca insertamos los dato


        //aca buscaremos el id de la persona ingresada--
    String searchId = "SELECT `ID_PERSONA` FROM `personas` WHERE `PRIMER_NOMBRE` = '"+firstName+"' AND `SEGUNDO_NOMBRE` = '"+secondName+"' AND `PRIMER_APELLIDO` = '"+firstLastName+"'";
    ResultSet result = statement.executeQuery(searchId);
    if (result.first()){
      idpersona = result.getInt("ID_PERSONA");
    }

        //esteremos realizando el segundo insert para la tabla empleados
     String sql2= "INSERT INTO `empleados` (`ID_EMPLEADO`, `ID_PERSONA`, `ESTADO`, `FECHA_CONTRATACION`, `FECHA_RETIRO`, `CARGO`) VALUES (NULL,'"+idpersona+"', '1', '"+dateTime+"', NULL, '"+place+"')";
     statement.executeUpdate(sql2);
     Alert alert= new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Confirmar Informacion...");
     alert.setContentText("Verifica los datos: Nombres: "+firstName +" "+ secondName +" "+firstLastName+" "+secondLastName+
             "\nDireccion: "+direction+" Telefono: "+numberPhone+" Puesto: "+place);
     alert.showAndWait();
     ClearTextField();
     }

     public void ClearTextField(){
         EmployNameOne.setText("");
         EmployNameTwo.setText("");
         EmployLasteNameOne.setText("");
         EmployLasteNameTwo.setText("");
         EmployDir.setText("");
         EmployPhone.setText("");
         EmployPlace.setText("");
         EmployEmail.setText("");
         EmployDate.setText("");
     }

}


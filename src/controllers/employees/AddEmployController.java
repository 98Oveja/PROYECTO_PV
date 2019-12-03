package controllers.employees;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class AddEmployController {
@FXML
    private AnchorPane PanelAddEmploy;
@FXML
    private TextField EmployName, EmployLasteName, EmployDir, EmployPhone, EmployDate, EmployPlace, EmployEmail;
@FXML
    private JFXButton BtnSaveEmploy;


 @FXML
    private void CloseModal(){
     Stage stage = (Stage) PanelAddEmploy.getScene().getWindow();
     stage.close();

 }
 @FXML
    public void AddEmploy(ActionEvent event) throws SQLException {

     String name= EmployName.getText();
     String LastName= EmployLasteName.getText();
     String Direction= EmployDir.getText();
     String NumberPhone= EmployPhone.getText();
     String Place= EmployPlace.getText();
     String Email= EmployEmail.getText();
     String DateTime= "02/12/2019";
     EmployDate.setText(DateTime);
     ConnectionClass connectionClass= new ConnectionClass();
        Connection connection= connectionClass.conecctiondb();
       // String sql= "INSERT INTO PERSONAS VALUES(21,'"+name+"','"+name+"','"+LastName+"','"+LastName+"','"+Direction+"','"+NumberPhone+"','"+Email+"')";
        String sql2= "INSERT INTO empleados VALUES (6,20,TRUE,STR_TO_DATE('04.10.2003' ,GET_FORMAT(date,'USA')),STR_TO_DATE('04.10.2003' ,GET_FORMAT(date,'USA')),'"+Place+"')";
        Statement statement= connection.createStatement();
        statement.executeUpdate(sql2);
       // statement.executeUpdate(sql2);

     }
}


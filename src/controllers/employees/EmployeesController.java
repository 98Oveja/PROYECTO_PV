package controllers.employees;

import com.mysql.jdbc.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.Principal;
import java.sql.DriverManager;

public class EmployeesController {
public Connection connection;
    @FXML
    private void DeleteEmploy() throws IOException {

        try {
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/DeleteEmploy.fxml"));
            Parent root = Loader.load();
            DelEmployController controller = Loader.getController();
            Scene dialogo = new Scene(root);
            //abrimos un nuevo escenario
            Stage stagedialog = new Stage();
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();

        }catch (Exception ex){ ex.printStackTrace();}
    }

    @FXML
    private void pressedAddModal() throws IOException {

        try {
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/AddEmployees.fxml"));
            Parent root = Loader.load();
            AddEmployController controller = Loader.getController();
            Scene dialogo = new Scene(root);
            //abrimos un nuevo escenario
            Stage stagedialog = new Stage();
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();

        }catch (Exception ex){ ex.printStackTrace();}
    }

    @FXML
    public Connection conecctiondb() {

        String nameDB="Ferreteria";
        String nameUser="root";
        String pass="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+nameDB + nameUser + pass);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return connection;
    }
}

package controllers.employees;

import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.sql.DriverManager;
import java.time.LocalDate;

public class EmployeesController {

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
    public void verPro() {
        final DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setOnAction(event -> {
            LocalDate date = datePicker.getValue();
            System.out.println("Selected date: " + date);
        });

    }
  }

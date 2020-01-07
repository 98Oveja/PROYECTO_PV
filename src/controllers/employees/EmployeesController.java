package controllers.employees;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.time.LocalDate;

public class EmployeesController {

    public Button mini1;
    public Button mini2;
    public Button mini3;
    public Button mini4;
    public Button mini5;
    public Button mini6;

    @FXML
    private void DeleteEmploy() throws IOException {
    int optionPress;
        try {
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/DeleteEmploy.fxml"));
            Parent root = Loader.load();
            DelEmployController controller = Loader.getController();
            optionPress = controller.status;
            Scene dialogo = new Scene(root);
            //abrimos un nuevo escenario
            Stage stagedialog = new Stage();
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();

            if(optionPress==1){
               System.out.println("ya dio");
               controller.pressedExit();
            }

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
    private void pressedEditModal() throws IOException {
        try {
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/AddEmployees.fxml"));
            Parent root = Loader.load();
            AddEmployController controller = Loader.getController();
            controller.TitleModal.setText("Editar Empleados");
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
    public void cambio(ActionEvent actionEvent) {
        if (actionEvent.getSource()==mini2){
            mini2.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini3.setStyle(".PanelLateralOpciones");
            mini4.setStyle(".PanelLateralOpciones");
            mini5.setStyle(".PanelLateralOpciones");
        }
        else if (actionEvent.getSource()==mini3){
            mini3.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini2.setStyle(".PanelLateralOpciones");
            mini4.setStyle(".PanelLateralOpciones");
            mini5.setStyle(".PanelLateralOpciones");
        }
        else if (actionEvent.getSource()==mini4){
            mini4.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini5.setStyle(".PanelLateralOpciones");
            mini2.setStyle(".PanelLateralOpciones");
            mini3.setStyle(".PanelLateralOpciones");
        }
        else if (actionEvent.getSource()==mini5){
            mini5.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini2.setStyle(".PanelLateralOpciones");
            mini3.setStyle(".PanelLateralOpciones");
            mini4.setStyle(".PanelLateralOpciones");
        }
    }
  }

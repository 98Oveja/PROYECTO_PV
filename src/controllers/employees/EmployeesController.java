package controllers.employees;

import com.mysql.cj.protocol.SocksProxySocketFactory;
import com.mysql.cj.protocol.StandardSocketFactory;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Employ.EditEmploy;
import models.Employ.dataEmploy;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.font.LayoutPath;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;

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
            dataEmploy res= new dataEmploy();
            EditEmploy editEmploy = new EditEmploy();
            res = editEmploy.searchData(2);
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/AddEmployees.fxml"));
            Parent root = Loader.load();
            AddEmployController controller = Loader.getController();
            controller.TitleModal.setText("Editar Empleados");
            controller.EmployNameOne.setText(res.name1);
            controller.EmployNameTwo.setText(res.name2);
            controller.EmployLasteNameOne.setText(res.lastname1);
            controller.EmployLasteNameTwo.setText(res.lastname2);
            controller.EmployDir.setText(res.dir);
            controller.EmployPhone.setText(res.tel+"");
            controller.EmployDate.setText(res.fechaInicio);
            controller.EmployPlace.setText(res.cargo);
            controller.EmployEmail.setText(res.correo);
            controller.BtnSaveEmploy.setVisible(false);
            controller.BtnUpdateEmploy.setVisible(true);
            String traslate= res.img.replace("*","\\");
            controller.setImgUser("file:/"+traslate);
            if(controller.BtnUpdateEmploy.getOnMouseClicked() != null) {
                editEmploy.updateEmploy(res.idper, res.idemp, res.name1, res.name2, res.lastname1, res.lastname2, res.dir, res.tel, res.correo, res.img, res.fechaInicio, res.cargo);
                System.out.println("Actualizacion exitosa");
            }

            Scene dialogo = new Scene(root);
            //abrimos un nuevo escenario
            Stage stagedialog = new Stage();
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();


        }catch (Exception ex){ System.out.println(ex);}
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

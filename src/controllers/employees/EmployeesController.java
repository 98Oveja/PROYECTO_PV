package controllers.employees;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Employ.EditEmploy;
import models.Employ.dataEmploy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesController implements Initializable {
    @FXML
    public Pane containerFather;
    @FXML
    public VBox contenedor;
    public Button mini1;
    public Button mini2;
    public Button mini3;
    public Button mini4;
    public Button mini5;
    public Button mini6;

    @FXML
    private void DeleteEmploy() {
        
        try {
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/DeleteEmploy.fxml"));
            Parent root = Loader.load();
            DelEmployController controller = Loader.getController();
            Scene dialogo = new Scene(root);
            Stage stagedialog = new Stage();
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();

            if(controller.BtnOk==1){
                EditEmploy del = new EditEmploy();
                del.deleteEmploy(2);
               System.out.println("ya dio LA ELIMINACION");
               controller.pressedExit();
            }else{
                System.out.println("NO SE DIO DE BAJA");
            }

        }catch (Exception ex){ ex.printStackTrace();}
    }

    @FXML
    private void pressedAddModal() {
        try {
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/AddEmployees.fxml"));
            Parent root = Loader.load();
            Scene dialogo = new Scene(root);
            Stage stagedialog = new Stage();
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();

        }catch (Exception ex){ ex.printStackTrace();}
    }

    @FXML
    private void pressedEditModal() {

        try {
            dataEmploy res;
            EditEmploy editEmploy = new EditEmploy();
            res = editEmploy.searchData(3);
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
            controller.initDatos(controller.EmployPlace.getText());

            if(res.img!="NULL"){
                String traslate= res.img.replace("*","\\");
                controller.setImgUser("file:/"+traslate);
            }else {
                controller.setImgUser("/images/male_user_.png");
            }
            Scene dialogo = new Scene(root);
            Stage stagedialog = new Stage();
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();

            if(controller.status == 1){

                controller.loaderModalInfo();
                if(controller.infoStatus==1) {
                    editEmploy.updateEmploy(res.idper, res.idemp, controller.EmployNameOne.getText(),controller.EmployNameTwo.getText(),controller.EmployLasteNameOne.getText(), res.lastname2, res.dir, res.tel, res.correo, res.img, res.cargo);
                    System.out.println("Actualizacion exitosa");
                }else{
                    pressedEditModal();
                }
            }



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
    public void card() throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/fxml/Empleados/cardEmpoy.fxml"));
        Parent parent= fxmlLoader.load();
        contenedor.setTranslateX(0);
        contenedor.getChildren().addAll(parent);

        //        Parent parent = new FXMLLoader().load(getClass().getResource("/fxml/Empleados/cardEmpoy.fxml"));
//        Image n= new Image("images/male_user_.png");
//        CardEmployController con= new CardEmployController(1,2,"Juan","ciudad","8711979",n);
//        contenedor.setTranslateX(276);
//        contenedor.getChildren().addAll(parent);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            card();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

  }


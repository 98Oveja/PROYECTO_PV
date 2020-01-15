package controllers.employees;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Employ.dataEmploy;
import models.Employ.sqlEmploy;

public class CardEmployController {
    @FXML
    public Button delet,edit;
    @FXML
    public Label city,name,phone;
    @FXML
    public BorderPane photoEmploy;
    public dataEmploy employ;
    String imgTemporal;

    public void addData(dataEmploy empData){
        employ = empData;
    }
    public void initDatoCard(){
        name.setText(employ.name1+" "+employ.lastname1);
        city.setText("De: "+employ.dir);
        phone.setText("Movil: " +employ.tel);
        imaConverter();
        setImgUser(imgTemporal);
    }

    public void imaConverter(){
        if(employ.img.contains("*")){
            imgTemporal ="file:/" + employ.img.replace("*","\\");
            employ.img =  employ.img.replace("*","\\");
        }else{
            imgTemporal = "images/male_user_.png";
        }
    }

    public void setImgUser(String url) {
        Circle circle = new Circle(72,72,36);

        try{
            Image image = new Image(url,false);
            circle.setFill(new ImagePattern(image));
            photoEmploy.setCenter(circle);
        }catch (Exception ex){
            System.out.println("No se encontro la imagen");
            Image image = new Image("images/male_user_.png",false);
            circle.setFill(new ImagePattern(image));
            photoEmploy.setCenter(circle);
        }


    }
    @FXML
    private void DeleteEmploy() {

        try {
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/DeleteEmploy.fxml"));
            Parent root = Loader.load();
            DelEmployController controller = Loader.getController();
            controller.contentAlert.setText("Esta seguro que desea eliminar al Empleado: "+employ.name1+" "+ employ.lastname1);
            Scene dialogo = new Scene(root);
            Stage stagedialog = new Stage();
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();

            if(controller.BtnOk==1){
                sqlEmploy del = new sqlEmploy();
                del.deleteEmploy(employ.idemp);
                System.out.println("ya dio LA ELIMINACION");
                controller.pressedExit();
            }else{
                System.out.println("NO SE DIO DE BAJA");
            }

        }catch (Exception ex){ ex.printStackTrace();}
    }

    @FXML
    private void pressedEditModal() {

        try {
            dataEmploy res;
            sqlEmploy editEmploy = new sqlEmploy();
            res = editEmploy.searchData(employ.idemp);
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/AddEmployees.fxml"));
            Parent root = Loader.load();
            AddEmployController controller = Loader.getController();
            controller.TitleModal.setText("Editar Empleados");
            controller.EmployNameOne.setText(res.name1);
            controller.EmployNameOne.setDisable(true);
            controller.EmployNameTwo.setText(res.name2);
            controller.EmployNameTwo.setDisable(true);
            controller.EmployLasteNameOne.setText(res.lastname1);
            controller.EmployLasteNameOne.setDisable(true);
            controller.EmployLasteNameTwo.setText(res.lastname2);
            controller.EmployLasteNameTwo.setDisable(true);
            controller.EmployDir.setText(res.dir);
            controller.EmployDir.setDisable(true);
            controller.EmployPhone.setText(res.tel+"");
            controller.EmployDate.setText(res.fechaInicio);
            controller.EmployPlace.setText(res.cargo);
            controller.EmployEmail.setText(res.correo);
            controller.BtnSaveEmploy.setVisible(false);
            controller.BtnUpdateEmploy.setVisible(true);
            controller.initDatos(controller.EmployPlace.getText());

            if(res.img.contains("\\")){
                controller.setImgUser("file:/"+res.img);
            }else{
            controller.setImgUser("images/male_user_.png");
            }
            Scene dialogo = new Scene(root);
            Stage stagedialog = new Stage();
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();

            if(controller.emDb.img!=null){
                res.img = controller.emDb.img;
            }else {

            }
            if(controller.status == 1){
                controller.loaderModalInfo();
                if(controller.infoStatus==1) {
                    editEmploy.updateEmploy(res.idper, res.idemp, controller.EmployPhone.getText(), res.img, controller.EmployPlace.getText());
                    System.out.println("Actualizacion exitosa");
                }else{
                    pressedEditModal();
                }
            }
        }catch (Exception ex){
            System.out.println("linea 146 ------- "+ex);}
    }
}

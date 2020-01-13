package controllers.employees;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public ImageView photo;
    public dataEmploy employ;

    public void addData(dataEmploy empData){
        employ = empData;
    }
    public void initDatoCard(){
        name.setText(employ.name1+" "+employ.name2);
        city.setText(employ.dir);
        phone.setText("Movil: " +employ.tel);
        Image ima=new Image("images/male_user_.png");
        System.out.println("-------"+employ.img);
        photo.setImage(ima);
    }

    public Image validorImage(){
        Image img;
        if(employ.img.length()<6){
            String replaceImge= employ.img.replace("*","\\");
            img= new Image("file"+replaceImge);
        }else{
            img= new Image("images/male_user_.png");
        }
        return img;
    }

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
}

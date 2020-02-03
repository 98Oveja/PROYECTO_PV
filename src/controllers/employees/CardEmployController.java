package controllers.employees;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import models.Employ.validatorImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CardEmployController implements Initializable {
    @FXML
    public Button delet,edit;
    @FXML
    public Label city,name,phone,Organitation;
    @FXML
    public BorderPane photoEmploy;
    public dataEmploy employ;
    public BorderPane container;
    String imgTemporal;
    validatorImage valImage = new validatorImage();
    static double whidtgrid=EmployeesController.widthGrid;
    static double heightgrid=EmployeesController.heightGrid;
    int valueStatus;
    public void addData(dataEmploy empData){
        employ = empData;
    }

    public void initDatoCard(){
        name.setText(employ.name1+" "+employ.lastname1);
        city.setText("De: "+employ.dir);
        phone.setText("Movil: " +employ.tel);
        Organitation.setText(employ.cargo.toUpperCase());
        imaConverter();
        setImgUser(imgTemporal);
        if(employ.estado){
            valueStatus = 0;
        }else{
            valueStatus = 1;
            edit.setVisible(false);
        }

    }

    public void imaConverter(){
        if(employ.img.contains("*")){
            imgTemporal ="file:/" + employ.img.replace("*","\\");
        }else{
            imgTemporal = "images/male_user_.png";
        }
    }

    public void setImgUser(String url) {
        Circle circle = new Circle(80,80,40);
        String urlImage= valImage.loadImage(url,"images/male_user_.png");
        Image image = new Image(urlImage);
        circle.setFill( new ImagePattern(image));
        photoEmploy.setCenter(circle);
    }

    @FXML
    private void DeleteEmploy() {
        try {
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/DeleteEmploy.fxml"));
            Parent root = Loader.load();
            DelEmployController controller = Loader.getController();
            if(employ.estado){
                controller.contentAlert.setText("Esta seguro que desea eliminar al Empleado: "+employ.name1+" "+ employ.lastname1);
            }else {
                Image info = new Image("images/info.png");
                controller.loadDataClose(info,"Confirmar","Esta seguro que desea habilitar el empleado: "+employ.name1+" "+ employ.lastname1,1);
            }
            Scene dialogo = new Scene(root);
            Stage stagedialog = new Stage();
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();
            if(controller.BtnOk==1){
                sqlEmploy del = new sqlEmploy();
                del.deleteEmploy(employ.idemp,valueStatus);
                controller.pressedExit();
            }
        }catch (Exception ex){ ex.printStackTrace();}
    }

    @FXML
    private void pressedEditModal() {

        try {
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/AddEmployees.fxml"));
            Parent root = Loader.load();
            AddEmployController controller = Loader.getController();
            controller.TitleModal.setText("Editar Empleados");
            controller.EmployNameOne.setText(employ.name1);
            controller.EmployNameOne.setDisable(true);
            controller.EmployNameTwo.setText(employ.name2);
            controller.EmployNameTwo.setDisable(true);
            controller.EmployLasteNameOne.setText(employ.lastname1);
            controller.EmployLasteNameOne.setDisable(true);
            controller.EmployLasteNameTwo.setText(employ.lastname2);
            controller.EmployLasteNameTwo.setDisable(true);
            controller.EmployDir.setText(employ.dir);
            controller.EmployDir.setDisable(true);
            controller.EmployPhone.setText(employ.tel+"");
            controller.EmployDate.setText(employ.fechaInicio);
            controller.EmployPlace.setText(employ.cargo);
            controller.EmployEmail.setText(employ.correo);
            controller.BtnSaveEmploy.setVisible(false);
            controller.BtnUpdateEmploy.setVisible(true);
            controller.initDatos(controller.EmployPlace.getText());
            String imaAux = valImage.loadImage(imgTemporal , "images/male_user_.png");
            controller.setImgUser(imaAux);

            Scene dialogo = new Scene(root);
            Stage stagedialog = new Stage();
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();
            if(controller.emDb.img!=null){
                employ.img = controller.emDb.img;
            }
                if(controller.infoStatus==1) {
                    sqlEmploy editEmploy = new sqlEmploy();
                    editEmploy.updateEmploy(employ.idper, employ.idemp, controller.EmployPhone.getText(), employ.img, controller.EmployPlace.getText());
                }

        }catch (Exception ex){
            System.out.println("linea 146 ------- "+ex);}
    }

    public void viewAction(ActionEvent event) throws IOException {
            String imaAux = valImage.loadImage(imgTemporal , "images/male_user_.png");
            Image im =new Image(imaAux);
            loadView(im);
    }

    public void loadView(Image ima) throws IOException {
        FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/viewEmloy.fxml"));
        Parent root = Loader.load();
        viewEmployController viewEm = Loader.getController();
        viewEm.initData(employ.name1+" "+employ.name2,employ.lastname1+" "+employ.lastname2,employ.dir,employ.tel,employ.cargo,employ.correo,ima);
        Scene dialogo = new Scene(root);
        Stage stagedialog = new Stage();
              stagedialog.initStyle(StageStyle.UNDECORATED);
        stagedialog.initModality(Modality.APPLICATION_MODAL);
        stagedialog.setScene(dialogo);
        stagedialog.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        container.setPrefWidth(whidtgrid);
        container.setPrefHeight(heightgrid);
    }
}

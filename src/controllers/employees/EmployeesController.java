package controllers.employees;

import controllers.ScreenController.ImplementsU.ControlledScreen;
import controllers.ScreenController.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Employ.dataEmploy;
import models.Employ.sqlEmploy;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeesController implements Initializable, ControlledScreen {
    @FXML
    public StackPane containerEmploy;
    @FXML
    public GridPane containercard;
//    public Button mini1;
//    public Button mini2;
//    public Button mini3;
//    public Button mini4;
//    public Button mini5;
//    public Button mini6;
    public int posx, posy;
    public int idcard,cantEmploy;
    public sqlEmploy dataEmp = new sqlEmploy();
    public List<dataEmploy> arrayEmploy= new ArrayList<>();
    public Button activo;
    public Button Inactivos;
    double widthMenu = Toolkit.getDefaultToolkit().getScreenSize().width;
    double heightMenu = Toolkit.getDefaultToolkit().getScreenSize().height;
    static double  widthGrid, heightGrid;
    ScreensController myController;
    @FXML
    private void pressedAddModal() {
        try {
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/AddEmployees.fxml"));
            Parent root = Loader.load();
            Scene dialogo = new Scene(root);
            Stage stagedialog = new Stage();
            stagedialog.setX((widthMenu/2) - (570/2));
            stagedialog.setY((heightMenu/2) - (779/2));
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();
        }catch (Exception ex){ ex.printStackTrace();}
    }

//    public void cambio(ActionEvent actionEvent) {
//        if (actionEvent.getSource()==mini2){
//            mini2.setStyle("-fx-background-color: #3B86FF; -fx-text-fill: #fff;");
//            mini3.setStyle(".PanelLateralOpciones");
//            mini4.setStyle(".PanelLateralOpciones");
//            mini5.setStyle(".PanelLateralOpciones");
//        }
//        else if (actionEvent.getSource()==mini3){
//            mini3.setStyle("-fx-background-color: #3B86FF; -fx-text-fill: #fff;");
//            mini2.setStyle(".PanelLateralOpciones");
//            mini4.setStyle(".PanelLateralOpciones");
//            mini5.setStyle(".PanelLateralOpciones");
//        }
//        else if (actionEvent.getSource()==mini4){
//            mini4.setStyle("-fx-background-color: #3B86FF;" +
//                    "-fx-text-fill: #fff;");
//            mini5.setStyle(".PanelLateralOpciones");
//            mini2.setStyle(".PanelLateralOpciones");
//            mini3.setStyle(".PanelLateralOpciones");
//        }
//        else if (actionEvent.getSource()==mini5){
//            mini5.setStyle("-fx-background-color: #3B86FF;" +
//                    "-fx-text-fill: #fff;");
//            mini2.setStyle(".PanelLateralOpciones");
//            mini3.setStyle(".PanelLateralOpciones");
//            mini4.setStyle(".PanelLateralOpciones");
//        }
//    }

    public void card(List<dataEmploy> arrayEmploys) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/fxml/Empleados/CardEmploy.fxml"));
        Parent parent= fxmlLoader.load();
        CardEmployController cn =fxmlLoader.getController();
        cn.addData(arrayEmploys.get(idcard));
        cn.initDatoCard();
        containercard.setRowIndex(parent,posx);
        containercard.setColumnIndex(parent,posy);
        containercard.getChildren().addAll(parent);
            posx=posx+1;
        if(posx==3){
            posy=1;
            posx=0;
        }
     idcard =idcard+1;
    }
    public void loaderArrayData(){
        posx = 0;
        posy = 0;
        idcard=0;
    }

    public void initShowCard() throws IOException {
        if(cantEmploy != 0){
        while (idcard < cantEmploy && idcard < 6 ){
                card(arrayEmploy);
        }
        }else{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/fxml/Empleados/cardEmpoy.fxml"));
            Parent parent= fxmlLoader.load();
            CardEmployController cn =fxmlLoader.getController();
            cn.setImgUser("images/male_user_.png");
            containercard.getChildren().addAll(parent);
        }
    }

    public void loadActivos() {
        btnpressedEfect(activo,Inactivos);
        arrayEmploy.clear();
        arrayEmploy = dataEmp.employDB(1);
        cantEmploy = arrayEmploy.size();
        containercard.getChildren().clear();
        loaderArrayData();
        try {
            initShowCard();
        } catch (IOException e) {
            System.err.println("Line 148 EmployeesController " + e);;
        }
    }

    public void loadInactivos() {
        btnpressedEfect(Inactivos,activo);
        arrayEmploy.clear();
        arrayEmploy = dataEmp.employDB(0);
        cantEmploy = arrayEmploy.size();
        containercard.getChildren().clear();
        loaderArrayData();
        try {
            initShowCard();
        } catch (IOException e) {
            System.err.println("Line 163 EmployeesController " + e);;
        }
    }
    public void btnpressedEfect(Button Active,Button Inactive){
        Active.setStyle("-fx-background-color: #1C4C84;");
        Inactive.setStyle("-fx-background-color: #3B86FF;");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        containerEmploy.setPrefWidth(widthMenu - 260);
        containerEmploy.setPrefHeight(heightMenu - 110);
        widthGrid = (containercard.getWidth()/2) - containercard.getHgap();
        heightGrid = (containercard.getHeight()/3) - containercard.getVgap();
        loadActivos();
    }
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
}


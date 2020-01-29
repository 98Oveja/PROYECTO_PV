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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
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
    public int posx, posy;
    public int idcard,cantEmploy,btnNumber;
    public sqlEmploy dataEmp = new sqlEmploy();
    public List<dataEmploy> arrayEmploy= new ArrayList<>();
    public Button activo;
    public Button Inactivos;
    public TextField textFielSearch;
    public Button mini1,mini2,mini3,mini4,mini5,mini6;
    ArrayList<Button> Botones = new ArrayList<Button>();
    public int posicion;
    public int tamanio=20;
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
                stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();
        }catch (Exception ex){ ex.printStackTrace();}
    }

    public void card(List<dataEmploy> arrayEmploys) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/fxml/Empleados/CardEmploy.fxml"));
        Parent parent= fxmlLoader.load();
        CardEmployController cn =fxmlLoader.getController();
        cn.addData(arrayEmploys.get(idcard));
        cn.initDatoCard();
        containercard.setRowIndex(parent,posy);
        containercard.setColumnIndex(parent,posx);
        containercard.getChildren().addAll(parent);
            posx=posx+1;
        if(posx==2){
            posx=0;
            posy+=1;
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
        }
    }
    public void loadActivos() {
        btnpressedEfect(activo,Inactivos);
        arrayEmploy.clear();
        arrayEmploy = dataEmp.employDB(1);
        auxAddCard();
    }
    public void auxAddCard(){
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
        auxAddCard();
    }
    public void btnpressedEfect(Button Active,Button Inactive){
        Active.setStyle("-fx-background-color: #3B86FF;");
        Inactive.setStyle("-fx-background-color: #BDBDBD;");
    }

    public void actionSearch(KeyEvent event) {
        if((event.getCode() == KeyCode.ENTER)) {
            String letterSearch= textFielSearch.getText();
            if (letterSearch.length() > 0){
                arrayEmploy.clear();
                arrayEmploy =dataEmp.searchData(letterSearch);
                if(arrayEmploy != null){
                    auxAddCard();
                }

            }

        }
    }

    public void cambio(ActionEvent actionEvent) {
        if (actionEvent.getSource()==mini1){
            if (posicion>0){
                posicion-=1;
                Botones.get(posicion).setStyle("-fx-background-color: #3B86FF");
                Botones.get(posicion+1).setStyle(".botones-minis");
            }else{setmiminis(0);}
            System.out.println("line 26---*-*-*-");
        }
        else if (actionEvent.getSource()==mini6){
            if (posicion<3){
                posicion+=1;
                Botones.get(posicion).setStyle("-fx-background-color: #3B86FF");
                Botones.get(posicion-1).setStyle(".botones-minis");
            }else{setmiminis(1);}
            System.out.println("line 34---*-*-*-");
        }
        if (Integer.parseInt(mini2.getText())==1){mini1.setVisible(false);}else {mini1.setVisible(true);}
        if (tamanio==Integer.parseInt(mini5.getText())){mini6.setVisible(false);}else {mini6.setVisible(true);}
    }

   public void Asignando(String mini){
        for (int i = 0; i < Botones.size(); i++) {
            if(mini==Botones.get(i).getId()){
                System.out.println("line 48 ----");
                Botones.get(i).setStyle("-fx-background-color: #3B86FF");
            }else {Botones.get(i).setStyle(".botones-mini");}
        }
    }

    public void Numero(ActionEvent actionEvent) {
        for (int i = 0; i < Botones.size(); i++) {
            if (actionEvent.getSource()==Botones.get(i)){
                Asignando(Botones.get(i).getId());
                posicion=i;
            }
        }
    }

    public void derecha(){
        if (posicion<3&&Integer.parseInt(Botones.get(posicion).getText())<tamanio){
            posicion+=1;
            Botones.get(posicion).setStyle("-fx-background-color: #3B86FF");
            Botones.get(posicion-1).setStyle(".botones-minis");
        }else if(Integer.parseInt(mini5.getText())!=tamanio){setmiminis(1);}
        if (Integer.parseInt(mini2.getText())==1){mini1.setVisible(false);}else {mini1.setVisible(true);}
        if (tamanio==Integer.parseInt(mini5.getText())){mini6.setVisible(false);}else {mini6.setVisible(true);}
        System.out.println("line 71 -----");
    }

    public void izquierda(){
        if (posicion>0&&Integer.parseInt(Botones.get(posicion).getText())>1){
            posicion-=1;
            Botones.get(posicion).setStyle("-fx-background-color: #3B86FF");
            Botones.get(posicion+1).setStyle(".botones-minis");
        }else if (Integer.parseInt(mini2.getText())!=1){setmiminis(0);}
        if (Integer.parseInt(mini2.getText())==1){mini1.setVisible(false);}else {mini1.setVisible(true);}
        if (tamanio==Integer.parseInt(mini5.getText())){mini6.setVisible(false);}else {mini6.setVisible(true);}
        System.out.println("line 82 -----");
    }

    public void keypress(KeyEvent keyEvent) {
        KeyCode key =keyEvent.getCode();int aux=tamanio-1;
        if (key==KeyCode.RIGHT){
            derecha();
        }else if(key==KeyCode.LEFT){
            izquierda();
        }
    }

    public void setmiminis(int val){
        int a;
        for (int i = 0; i < Botones.size(); i++) {
            Button boton=Botones.get(i);
            if (val==0){a=Integer.parseInt(boton.getText())-1;}
            else{a=Integer.parseInt(boton.getText())+1;}
            boton.setText(String.valueOf(a));
            boton.setEllipsisString(String.valueOf(a));
        }
    }

    public void Scroll(ScrollEvent scrollEvent) {
        double y=scrollEvent.getTextDeltaY();
        if (y>0){
            derecha();
        }else if(y<0){
            izquierda();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        containerEmploy.setPrefWidth(widthMenu - 260);
        containerEmploy.setPrefHeight(heightMenu - 110);
        mini1.setVisible(false);
        mini2.setStyle("-fx-background-color: #3B86FF");
        Botones.add(mini2);Botones.add(mini3);Botones.add(mini4);Botones.add(mini5);
        widthGrid = (containercard.getWidth()/2) - containercard.getHgap();
        heightGrid = (containercard.getHeight()/3) - containercard.getVgap();
        loadActivos();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
}


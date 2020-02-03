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
    public int posx, posy,idcard,cantEmploy,tamanio,posicion,estadoShow;
    public sqlEmploy dataEmp = new sqlEmploy();
    public List<dataEmploy> arrayEmploy= new ArrayList<>();
    public Button activo;
    public Button Inactivos;
    public TextField textFielSearch;
    public Button mini1,mini2,mini3,mini4,mini5,mini6;
    ArrayList<Button> Botones = new ArrayList<Button>();
    double widthMenu = Toolkit.getDefaultToolkit().getScreenSize().width;
    double heightMenu = Toolkit.getDefaultToolkit().getScreenSize().height;
    static double  widthGrid, heightGrid;
    ScreensController myController;
    List<dataEmploy> auxEmploy= new ArrayList<>();


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
    public void loaderAuxData(){
        posx = 0;
        posy = 0;
        idcard=0;
    }

    public void loadActivos() throws IOException {
        estadoShow= 1;
        btnpressedEfect(activo,Inactivos);
        arrayEmploy.clear();
        arrayEmploy = dataEmp.employDB(1);
        cantEmploy = arrayEmploy.size();
        initbtn();
        auxAddCard();
    }

    public void auxAddCard() throws IOException {
        initbtn();
        Cambiar(1);
    }

    public void loadInactivos() throws IOException {
        estadoShow=2;
        btnpressedEfect(Inactivos,activo);
        arrayEmploy.clear();
        arrayEmploy = dataEmp.employDB(0);
        cantEmploy =arrayEmploy.size();
        auxAddCard();
    }

    public void refreshGrid() throws IOException {
        if(estadoShow == 1){
            loadActivos();
        }
        if(estadoShow == 2){
            loadInactivos();
        }

    }
    public void btnpressedEfect(Button Active,Button Inactive){
        Active.setStyle("-fx-background-color: #3B86FF;");
        Inactive.setStyle("-fx-background-color: #BDBDBD;");
    }

    public void actionSearch(KeyEvent event) throws IOException {
        if((event.getCode() == KeyCode.ENTER)) {
            String letterSearch= textFielSearch.getText();
            if (letterSearch.length() > 0){
                arrayEmploy.clear();
                arrayEmploy =dataEmp.searchData(letterSearch);
                cantEmploy = arrayEmploy.size();
                if(arrayEmploy != null){
                    auxAddCard();
                }
            }
        }
    }

    public void cambio(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource()==mini1){
            if (posicion>0){
                posicion-=1;
                Botones.get(posicion).setStyle("-fx-background-color: #3B86FF");
                Botones.get(posicion+1).setStyle(".botones-minis");
            }else{setmiminis(0);}
        }
        else if (actionEvent.getSource()==mini6){
            if (posicion<3){
                posicion+=1;
                Botones.get(posicion).setStyle("-fx-background-color: #3B86FF");
                Botones.get(posicion-1).setStyle(".botones-minis");
            }else{setmiminis(1);}
        }
        if (Integer.parseInt(mini2.getText())==1){mini1.setVisible(false);}else {mini1.setVisible(true);}
        if (tamanio==Integer.parseInt(mini5.getText())){mini6.setVisible(false);}else {mini6.setVisible(true);}
    }

    public void Visibles(){
        int a=tamanio;
        for(int i= 0; i<Botones.size();i++){
            Botones.get(i).setDisable(false);
            Botones.get(i).setVisible(true);
        }
        for (int i = a; i < 4; i++) {
            Botones.get(i).setDisable(true);
            Botones.get(i).setVisible(false);
        }
        mini6.setDisable(true);
        mini6.setVisible(false);
    }


    public void Asignando(String mini,int m) throws IOException {
        Cambiar(m);
        for (int i = 0; i < Botones.size(); i++) {
            if(mini==Botones.get(i).getId()){
                Botones.get(i).setStyle("-fx-background-color: #3B86FF");
            }else {Botones.get(i).setStyle(".botones-mini");}
        }
    }

    public void Numero(ActionEvent actionEvent) throws IOException {
        for (int i = 0; i < Botones.size(); i++) {
            if (actionEvent.getSource()==Botones.get(i)){
                Asignando(Botones.get(i).getId(),Integer.parseInt(Botones.get(i).getText()));
                posicion=i;
            }
        }
    }

    public void derecha() throws IOException {
        if (posicion<3&&Integer.parseInt(Botones.get(posicion).getText())<tamanio){
            posicion+=1;
            Cambiar(Integer.parseInt(Botones.get(posicion).getText()));
            Botones.get(posicion).setStyle("-fx-background-color: #3B86FF");
            Botones.get(posicion-1).setStyle(".botones-minis");
        }else if(Integer.parseInt(mini5.getText())!=tamanio&&tamanio>4){setmiminis(1);}
        if (Integer.parseInt(mini2.getText())==1){mini1.setVisible(false);}else {mini1.setVisible(true);}
        if (tamanio==Integer.parseInt(mini5.getText())){mini6.setVisible(false);}else if (tamanio>4){mini6.setVisible(true);}
    }

    public void izquierda() throws IOException {
        if (posicion>0&&Integer.parseInt(Botones.get(posicion).getText())>1){
            posicion-=1;
            Cambiar(Integer.parseInt(Botones.get(posicion).getText()));
            Botones.get(posicion).setStyle("-fx-background-color: #3B86FF");
            Botones.get(posicion+1).setStyle(".botones-minis");
        }else if (Integer.parseInt(mini2.getText())!=1){setmiminis(0);}
        if (Integer.parseInt(mini2.getText())==1){mini1.setVisible(false);}else {mini1.setVisible(true);}
        if (tamanio==Integer.parseInt(mini5.getText())){mini6.setVisible(false);}else if (tamanio>4){mini6.setVisible(true);}
    }

    public void keypress(KeyEvent keyEvent) throws IOException {
        KeyCode key =keyEvent.getCode();
        if (key==KeyCode.RIGHT){
            derecha();
        }else if(key==KeyCode.LEFT){
            izquierda();
        }
    }

    public void setmiminis(int val) throws IOException {
        int a=0;
        for (int i = 0; i < Botones.size(); i++) {
            Button boton=Botones.get(i);
            if (val==0){a=Integer.parseInt(boton.getText())-1;}
            else{a=Integer.parseInt(boton.getText())+1;}
            boton.setText(String.valueOf(a));
            boton.setEllipsisString(String.valueOf(a));
        }
        if (val==0){
            Cambiar(Integer.parseInt(Botones.get(0).getText()));
        }else {Cambiar(a*val);}

    }

    public void Scroll(ScrollEvent scrollEvent) throws IOException {
        double y=scrollEvent.getTextDeltaY();
        if (y>0){
            derecha();
        }else if(y<0){
            izquierda();
        }
    }

    void Cambiar(int a) throws IOException {
        loaderAuxData();
        containercard.getChildren().clear();
        auxEmploy.clear();
        for (int i = (a-1)*6; i < (a*6); i++) {
            if (i<cantEmploy){
                auxEmploy.add(arrayEmploy.get(i));
                card(auxEmploy);
            }
        }
    }

    public void initbtn(){
        if (cantEmploy%6 != 0){
            tamanio=(cantEmploy/6)+1;
        }else {tamanio=cantEmploy/6;}
        if (cantEmploy==6){
            tamanio=1;
        }
        if (tamanio<=4){
            Visibles();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        containerEmploy.setPrefWidth(widthMenu - 260);
        containerEmploy.setPrefHeight(heightMenu - 110);
        widthGrid = (containercard.getWidth()/2) - containercard.getHgap();
        heightGrid = (containercard.getHeight()/3) - containercard.getVgap();
        mini1.setVisible(false);
        mini2.setStyle("-fx-background-color: #3B86FF");
        Botones.add(mini2);Botones.add(mini3);Botones.add(mini4);Botones.add(mini5);
        try {
            loadActivos();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cantEmploy= arrayEmploy.size();
        initbtn();
        try {
            Cambiar(1);
        } catch (IOException e) {
            System.err.println("LIne 283 "+ e);
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }


}


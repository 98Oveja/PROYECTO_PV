package controllers.ProductosV2;
import controllers.ScreenController.ImplementsU.ControlledScreen;
import controllers.ScreenController.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ConnectionUtil;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductController implements Initializable, ControlledScreen {

    public StackPane containerProductos;
    @FXML private VBox Container;
    ScreensController myController;
    ArrayList<String> Productos = new ArrayList<String>();
    ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;
    int tamanio,posicion,cantCard;
    public String getNombre(){return nombre;}
    public String getCantidad(){return cantidad;}
    public String getDescripcion(){return descripcion;}
    public String getPrecioCompra(){return precioCompra;}
    public String getPrecioVenta(){return precioVenta;}
    public String getPhoto(){return photo;}
    public int getEstado(){return estado;}
    public double getwidthpane(){return width;}
    ArrayList<Button> Botones = new ArrayList<Button>();
    public Button mini1,mini2,mini3,mini4,mini5,mini6;
    double widthMenu = Toolkit.getDefaultToolkit().getScreenSize().width;
    double heightMenu = Toolkit.getDefaultToolkit().getScreenSize().height;

    static String nombre,cantidad,descripcion,precioCompra,precioVenta,photo;
    static int estado,position;static double width;
    String Query1 = "SELECT NOMBRE,DISPONIBILIDAD,PRECIO_VENTA,IMG,DESCRIPCION FROM productos WHERE ESTADO=1;";
    String Query0 = "SELECT NOMBRE,DISPONIBILIDAD,PRECIO_VENTA,IMG,DESCRIPCION FROM productos WHERE ESTADO=0;";

    public void consulta(String Query){

        Productos.clear();String dato;
        try{
            conexion = conn.getConnection();
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(Query);
            if (resultado != null) {
                while (resultado.next()) {
                    dato= resultado.getString("NOMBRE")+"#"+resultado.getString("DISPONIBILIDAD")+"#"+resultado.getString("PRECIO_VENTA")
                            +"#"+resultado.getString("IMG")+"#"+resultado.getString("PRECIO_COMPRA")+"#"+resultado.getString("DESCRIPCION");
                    Productos.add(dato); }
//            rellenar(position);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        cantCard = Productos.size();
    }
    public void rellenar(int posicion){
      posicion-=1;
        cantCard =Productos.size();
        int pos=posicion*5;
        for (int i = pos; i < pos+5; i++) {
            if (i < cantCard ){
                String[] texto=Productos.get(i).split("#");
                nombre=texto[0];            cantidad=texto[1];
                precioVenta=texto[2];       photo=texto[3]; width=Container.getPrefWidth();
                card();
            }
        }
    }

    public void card(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ProductosV2/CardProduct.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
            Container.getChildren().addAll(parent);
        } catch (IOException e) {}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        estado=1;
        consulta(Query1);
        containerProductos.setPrefWidth(widthMenu - 260);
        containerProductos.setPrefHeight(heightMenu - 110);
        mini1.setVisible(false);
        mini2.setStyle("-fx-background-color: #3B86FF");
        Botones.add(mini2);Botones.add(mini3);Botones.add(mini4);Botones.add(mini5);
        try {
            Cambiar(1);
            System.out.println("cambio   " + cantCard);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void AbrirCategoria(ActionEvent actionEvent){
        String fxml="/fxml/ProductosV2/ScrollPaneCateg.fxml";
        try {
            Abrirmodal(fxml,772.00,515.00);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Abrirmodal(String fxml,Double x,Double y) throws IOException {
        Double height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        Double width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        final Stage primaryStage = new Stage();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initOwner(primaryStage);
        dialog.setX((width/2)-(x/2));
        dialog.setY((height/2)-(y/2));
        Scene dialogScene = null;
        dialogScene = new Scene(FXMLLoader.load(getClass().getResource(fxml)));
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void AgregarProdcuto(ActionEvent actionEvent) {
        try {
            Abrirmodal("/fxml/ProductosV2/NewProduct.fxml",800.00,660.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //INICIO DE LOS BOTONES MINIS-----*-**-**-*-**-*-
    //-----------------------------------------------------------
    //----------------------------------------------------------
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

    void Cambiar(int a)  {
        Container.getChildren().clear();
        initbtn();
        rellenar(a);
    }

    public void initbtn(){
        if (cantCard%5 != 0){
            tamanio=(cantCard/5)+1;
        }else {tamanio=cantCard/5;}
        if (cantCard==5){
            tamanio=1;
        }
        if (tamanio<=4){
            Visibles();
        }
    }
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    public void showModalMarK() throws IOException {
        Abrirmodal("/fxml/ProductosV2/Mark.fxml",664.00,484.00);
    }
}
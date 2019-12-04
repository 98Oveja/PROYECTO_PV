package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HomeController implements Initializable {

    public Label line1;
    public Label line2;
    public Label line3;
    public Label line4;
    public Label line5;
    public Label line6;
    public Label line7;
    public Label line8;
    public Label line9;
    public Label line10;
    public ToggleButton btninicio;
    public ToggleButton btnestadistica;
    public ToggleButton btnempleados;
    public ToggleButton btnreportes;
    public ToggleButton btnproductos;
    public ToggleButton btnclientes;
    public ToggleButton btncompras;
    public ToggleButton btncalendario;
    public ToggleButton btnventas;
    public ToggleButton btnproveedores;

    public StackPane pane;
    public BorderPane imgUser;
    public MenuItem itemClose;
    public MenuItem itemConfig;
    public MenuItem itemHelp;
    int status = 0;
    ArrayList<Label> list = new ArrayList<>();
    ArrayList<ToggleButton> listB = new ArrayList<>();

    public static int getCode() {
        return LoginController.code;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setVista("/fxml/Empleados/menu.fxml");
            setImgUser("/images/index.jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        avalibleLabel(getCode());
        avalibleButton(getCode());
    }

    private void setImgUser(String url) {
        Circle circle = new Circle(32,32,16);
        //circle.setStroke(Color.valueOf("#E8E9EC"));
        Image image = new Image(url,false);
        circle.setFill(new ImagePattern(image));
        //circle.setEffect(new DropShadow(+10d, 0d, +2d, Color.valueOf("#E8E9EC")));
        imgUser.setCenter(circle);
    }

    public void setVista(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        pane.getChildren().setAll(root.getChildrenUnmodifiable());
    }

    void addButtons(){

        listB.add(btninicio);
        listB.add(btnestadistica);
        listB.add(btnempleados);
        listB.add(btnproductos);
        listB.add(btnproveedores);
        listB.add(btnclientes);
        listB.add(btncompras);
        listB.add(btncalendario);
        listB.add(btnventas);
        listB.add(btnreportes);
        for (ToggleButton b : listB) {
            b.setSelected(false);
        }
    }

    void addlist(){
        list.add(line1);
        list.add(line2);
        list.add(line3);
        list.add(line4);
        list.add(line5);
        list.add(line6);
        list.add(line7);
        list.add(line8);
        list.add(line9);
        list.add(line10);
        for (Label a : list) {
            a.setVisible(false);
        }
    }

    public  void avalibleButton(int date){
        addButtons();
        for (ToggleButton b: listB) {
            if(b.equals(listB.get(date))){
                //System.out.println("son iguales ");
                b.setSelected(true);
            }
        }
    }

    public  void avalibleLabel(int date){
        addlist();
        for (Label a: list) {
            if(a.equals(list.get(date))){
                //System.out.println("son iguales ");
                a.setVisible(true);
            }
        }
    }

    public void handleHome(ActionEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == btninicio){
            setVista("/fxml/Empleados/menu.fxml");
            avalibleLabel(0);
            avalibleButton(0);

        }
    }

    public void handleEst(ActionEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == btnestadistica){
            setVista("/fxml/Stadistic.fxml");
            avalibleLabel(1);
            avalibleButton(1);
        }
    }

    public void handleEmpl(ActionEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == btnempleados){
            setVista("/fxml/Empleados/Employees.fxml");
            avalibleLabel(2);
            avalibleButton(2);
        }
    }

    public void handleProd(ActionEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == btnproductos){
            setVista("/fxml/Productos/Productos.fxml");
            avalibleLabel(3);
            avalibleButton(3);
        }
    }

    public void handleProve(ActionEvent mouseEvent) {
        if(mouseEvent.getSource() == btnproveedores){
            avalibleLabel(4);
            avalibleButton(4);
        }
    }

    public void handleCli(ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == btnclientes) {
            avalibleLabel(5);
            avalibleButton(5);
        }
    }

    public void handleComp(ActionEvent mouseEvent) {
        if(mouseEvent.getSource() == btncompras){
            avalibleLabel(6);
            avalibleButton(6);
        }
    }

    public void handleCal(ActionEvent mouseEvent) {
        if(mouseEvent.getSource() == btncalendario){
            avalibleLabel(7);
            avalibleButton(7);
        }
    }

    public void handleVen(ActionEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == btnventas){
            setVista("/fxml/Ventas/PanelVentas.fxml");
            avalibleLabel(8);
            avalibleButton(8);
        }
    }

    public void handleRep(ActionEvent mouseEvent) {
        if(mouseEvent.getSource() == btnreportes){
            avalibleLabel(9);
            avalibleButton(9);
        }
    }

    public void handleActionHelp(ActionEvent actionEvent) {
        if (actionEvent.getSource() == itemHelp){
            if(status == 0) {
                goToURL("https://www.facebook.com/mmm.n.plo");
                status = 1;
            }
        }
    }

    public void handleActionConf(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == itemConfig){
            final Stage primaryStage = new Stage();
            final Stage dialog = new Stage();

            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.initOwner(primaryStage);
            dialog.setX(600);
            dialog.setY(300);
            Scene dialogScene = null;
            dialogScene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/ConfigUser.fxml")));

            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void handleActionClose(ActionEvent actionEvent) {
        if (actionEvent.getSource() == itemClose) {
            Stage stage = (Stage) this.pane.getScene().getWindow();
            stage.close();
        }
    }

    public void goToURL(String URL){
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI(URL);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}


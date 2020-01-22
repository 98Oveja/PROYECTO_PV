package controllers;

import controllers.ScreenController.ScreensController;
import controllers.item.ControllerComponent;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public StackPane pane;
    public VBox paneItemRoot;
    public StackPane paneSearch;
    static String item = null;

    public static String screen0ID = "screen0";
    public static String screen0File = "/fxml/Empleados/menu.fxml";
    public static String screen1ID = "screen1";
    public static String screen1File = "/fxml/Stadistic.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "/fxml/Empleados/Employees.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "/fxml/Productos/Productos.fxml";
    public static String screen4ID = "screen4";
    public static String screen4File = "/fxml/ForgotPass";
    public static String screen5ID = "screen5";
    public static String screen5File = "/fxml/ForgotPass";
    public static String screen6ID = "screen6";
    public static String screen6File = "/fxml/ForgotPass";
    public static String screen7ID = "screen7";
    public static String screen7File = "/fxml/Calendar/CalendarPane.fxml";
    public static String screen8ID = "screen8";
    public static String screen8File = "/fxml/Ventas/PanelVentas.fxml";
    public static String screen9ID = "screen9";
    public static String screen9File = "/fxml/ForgotPass";


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String items = getItemForIsAdmin();
        String[] itemsX = items.split(",");
        try {

            for (String x: itemsX) {
                item = x;
                loadPane();
            }

            ArrayList<HBox> arrayListAuxBoxItem = new ArrayList<>();
            for (int i = 0; i < paneItemRoot.getChildren().size() ; i++) {
                HBox hBox = (HBox) paneItemRoot.getChildren().get(i);
                arrayListAuxBoxItem.add(hBox);
            }

            arrayListAuxBoxItem.get(0).getChildren().get(0).setVisible(true);
            Pane paneInit = (Pane) arrayListAuxBoxItem.get(0).getChildren().get(1);
            paneInit.getChildren().get(0).setStyle("-fx-background-color:#3C3B54; ");

            for (HBox hBox : arrayListAuxBoxItem) {

                Pane paneAux = (Pane) hBox.getChildren().get(1);
                Label label = (Label) hBox.getChildren().get(0);
                Button button = (Button) paneAux.getChildren().get(0);

                button.setOnMouseClicked(mouseEvent ->{
                    for (HBox hBoxD : arrayListAuxBoxItem) {

                        Pane paneAuxD = (Pane) hBoxD.getChildren().get(1);
                        Label labelD = (Label) hBoxD.getChildren().get(0);
                        Button buttonD = (Button) paneAuxD.getChildren().get(0);
                        labelD.setVisible(false);
                        buttonD.setStyle(null);
                    }
                    button.setStyle("-fx-background-color:#3C3B54; ");
                    label.setVisible(true);
                    System.out.println(button.getText());
                });
            }

            loadSearchPane();
            pane.getChildren().addAll(setContainerScreen());

        } catch (IOException e) {
            System.out.println("Error in HomeController: "+e);
        }

    }

    private void loadPane() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/components/itemDashboard.fxml"));
        paneItemRoot.getChildren().addAll(root);
    }

    private void loadSearchPane() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/components/paneSearch.fxml"));
        paneSearch.getChildren().addAll(root);
        double width= Toolkit.getDefaultToolkit().getScreenSize().width;
        paneSearch.setPrefWidth(width-260);
    }

    private String getItemForIsAdmin (){
        if(ControllerComponent.admin) {
            return "Inicio,Estadisiticas,Empleados,Productos,Proveedores,Clientes,Compras,Calendario,Ventas,Reportes";
        }else {
            return"Inicio,Clientes,Compras,Calendario,Ventas,Reportes";
        }
    }

    private ScreensController setContainerScreen() {
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(HomeController.screen0ID, HomeController.screen0File);
        mainContainer.loadScreen(HomeController.screen1ID, HomeController.screen1File);
        mainContainer.loadScreen(HomeController.screen2ID, HomeController.screen2File);
        mainContainer.loadScreen(HomeController.screen3ID, HomeController.screen3File);
        mainContainer.loadScreen(HomeController.screen4ID, HomeController.screen4File);
        mainContainer.loadScreen(HomeController.screen5ID, HomeController.screen5File);
        mainContainer.loadScreen(HomeController.screen6ID, HomeController.screen6File);
        mainContainer.loadScreen(HomeController.screen7ID, HomeController.screen7File);
        mainContainer.loadScreen(HomeController.screen8ID, HomeController.screen8File);
        mainContainer.loadScreen(HomeController.screen9ID, HomeController.screen9File);

        mainContainer.setScreen(HomeController.screen0ID);
        return mainContainer;
    }
}


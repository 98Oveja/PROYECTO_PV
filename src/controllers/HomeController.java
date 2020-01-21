package controllers;

import controllers.ScreenController.ImplementsU.ControlledScreen;
import controllers.ScreenController.ScreensController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable , ControlledScreen {

    public StackPane pane;
    public MenuButton paneResSearch;
    public VBox paneItemRoot;
    public StackPane paneSearch;
    ScreensController myController;


    public static String items; public static String[] itemsX;
    public static String item = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items = "Inicio,Estadisiticas,Empleados,Productos,Proveedores,Clientes,Compras,Calendario,Ventas,Reportes";
        itemsX = items.split(",");
        ControllerItemSelected controllerItemSelected =  new ControllerItemSelected();
        String aux = controllerItemSelected.getItem();
        try {
            for (String x: itemsX) {
                item = x;
                loadPane();
            }
            loadSearchPane();
            //setVista("/fxml/Empleados/menu.fxml");

        } catch (IOException e) {
            e.printStackTrace();
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

    public void setVista(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        pane.getChildren().setAll(root.getChildrenUnmodifiable());
    }

    public void handleActionText(String value, String newValue) {
        if (!newValue.isEmpty()) {
            paneResSearch.getItems().add(new MenuItem(newValue));
            System.out.println(newValue);
            //paneResSearch.show();
        }else {
            System.out.println("eliminado");
            paneResSearch.getItems().removeAll();
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

}


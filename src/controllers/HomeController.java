package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {

    public StackPane pane;
    public MenuButton paneResSearch;
    public VBox paneItemRoot;
    public Pane paneSearch;

    public static int getCode() {
        return LoginController.code;
    }
    public static String items; public static String[] itemsX;
    public static String item = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items = "inicio,estadisiticas,empleados,productos,proveedores,clientes,compras,calendario,ventas,reportes";
        itemsX = items.split(",");
        ControllerItemSelected controllerItemSelected =  new ControllerItemSelected();
        String aux = controllerItemSelected.getItem();
        try {
            for (String x: itemsX) {
                item = x;
                loadPane();
            }
            loadSearchPane();
            setVista("/fxml/Empleados/menu.fxml");

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
}


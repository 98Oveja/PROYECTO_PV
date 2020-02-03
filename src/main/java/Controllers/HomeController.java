package Controllers;

import Controllers.ScreenController.ImplementsU.ControlledScreen;
import Controllers.item.ControllerComponent;
import Controllers.ScreenController.ScreensController;
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

public class HomeController implements Initializable, ControlledScreen {

    public StackPane pane;
    public VBox paneItemRoot;
    public StackPane paneSearch;
    static String item = null;
    ScreensController myController;
    private ScreensController mainContainer = new ScreensController();

    String getStringValue(String value){
        String aux = null;
        switch (value){
            case "Inicio": aux= "/fxml/Empleados/menu.fxml"; break;
            case "Clientes": aux= "/fxml"; break;
            case "Compras": aux= "/fxml"; break;
            case "Reportes": aux= "/fxml/Reports/PaneReports.fxml"; break;
            case "Ventas": aux= "/fxml/Ventas/PanelVentas.fxml"; break;
            case "Calendario": aux= "/fxml/Calendar/CalendarPane.fxml"; break;
            case "Estadisiticas": aux= "/fxml/Stadistic.fxml"; break;
            case "Empleados": aux= "/fxml/Empleados/Employees.fxml"; break;
            case "Productos": aux= "/fxml/Productos/Productos.fxml"; break;
            case "Proveedores": aux= "/fxml/Proveedores/Proveedores.fxml"; break;

        }
        return aux;
    }

    private String items = getItemForIsAdmin();
    private String[] itemsX = items.split(",");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            loadSearchPane();

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
                    mainContainer.setScreen("screen"+button.getText());

                });
            }



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
            return "Inicio,Clientes,Compras,Reportes,Ventas,Productos,Proveedores";
        }else {
            return"Inicio,Clientes,Compras,Reportes,Ventas,Calendario";
            //return "Inicio,Clientes,Compras,Reportes,Ventas,Calendario,Estadisiticas,Empleados,Productos,Proveedores";
        }
    }

    private ScreensController setContainerScreen() {

        for (String aux: itemsX) {
            mainContainer.loadScreen("screen"+aux, getStringValue(aux));
        }
        mainContainer.setScreen("screenInicio");
        return mainContainer;
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
}


package Controllers;

import Controllers.ScreenController.ImplementsU.ControlledScreen;
import Controllers.item.ControllerComponent;
import Controllers.ScreenController.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
    public Button btnFacebook;
    public Button btnWhatsapp;
    public Button btnTwiter;
    String aux = null;

    PaneSearch search = new PaneSearch();
    ScreensController myController;

    public static ScreensController mainContainer = new ScreensController();

    String getStringValue(String value){
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
    static ArrayList<HBox> arrayListAuxBoxItem = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            loadSearchPane();
            for (String x: itemsX) {
                item = x;
                loadPane();
            }
        }catch (Exception e){}

        for (int i = 0; i < paneItemRoot.getChildren().size() ; i++) {
            HBox hBox = (HBox) paneItemRoot.getChildren().get(i);
            arrayListAuxBoxItem.add(hBox);
        }

        setItemSelected(0);

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

    }

    public static void setItemSelected(int posLabel) {

        for (int i = 0; i <arrayListAuxBoxItem.size() ; i++) {
            arrayListAuxBoxItem.get(i).getChildren().get(0).setVisible(false);
            Pane pane = (Pane) arrayListAuxBoxItem.get(i).getChildren().get(1);
            pane.getChildren().get(0).setStyle(null);
        }

        arrayListAuxBoxItem.get(posLabel).getChildren().get(0).setVisible(true);
        Pane paneInit = (Pane) arrayListAuxBoxItem.get(posLabel).getChildren().get(1);
        paneInit.getChildren().get(0).setStyle("-fx-background-color:#3C3B54;");
    }

    private void loadPane()  {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/components/itemDashboard.fxml"));
        } catch (IOException ignored) {}
        paneItemRoot.getChildren().addAll(root);
    }

    private void loadSearchPane() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/components/paneSearch.fxml"));
        } catch (IOException ignored) { }
        paneSearch.getChildren().addAll(root);
        double width= Toolkit.getDefaultToolkit().getScreenSize().width;
        paneSearch.setPrefWidth(width-260);

    }

    private String getItemForIsAdmin (){
        if(ControllerComponent.admin) {
            return "Inicio,Clientes,Compras,Reportes,Ventas,Productos,Proveedores";
        }else {
            return"Inicio,Clientes,Compras,Reportes,Ventas,Calendario";
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

    public void openUrlFacebook(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnFacebook){
            search.goToURL("https://bit.ly/2twUnCl");
        }
    }

    public void openUrlWhatsapp(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnWhatsapp){
            search.goToURL("https://bit.ly/2GZ4Aue");
        }
    }

    public void openUrlTwiter(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnTwiter){
            search.goToURL("https://bit.ly/3bgnA5i");
        }
    }

    public void openUrlESC(MouseEvent mouseEvent) {
        search.goToURL("https://bit.ly/2SmkXpX");
    }
    // escodgt.com

}


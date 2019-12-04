package controllers.Productos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


import static com.sun.java.accessibility.util.AWTEventMonitor.removeMouseListener;

public class Controller {

    @FXML
    private Button agregarV;


/*
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Productos.fxml"));
        primaryStage.setTitle("Productos");
        primaryStage.setScene(new Scene(root, 1340, 890));
        primaryStage.show();
    }*/
/*
    public void OpenModal_InsertSale(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/../src/fxml/Productos/nuevoproducto.fxml"));
            Parent root = loader.load();
            //ModalVentaController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    @FXML
    public void Abrir(ActionEvent actionEvent) throws IOException {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Productos/nuevoproducto.fxml"));
        Parent root = loader.load();
//      ModalVentaController controller = loader.getController();
        newproducto controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }catch (IOException e) {
        e.printStackTrace();
    }
    }

/*    public void click(ActionEvent actionEvent)
    {
        agregarV.getStylesheets().add(this.getClass().getResource(
                "style.css"
        ).toExternalForm());
    }
*/

    }

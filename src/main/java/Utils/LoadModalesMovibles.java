package Utils;

import Controllers.AlertaController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
public class LoadModalesMovibles {
    private static double posicionX = 0;
    private static double posicionY = 0;

//CARGAR CUALQUIER MODAL
    public static Object LoadModalMovible(URL loc, Stage parentStage){
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else { stage = new Stage(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL); }
            parent.setOnMousePressed(mouseEvent ->{
                posicionX = mouseEvent.getSceneX();
                posicionY = mouseEvent.getSceneY();
            });
            Stage finalStage = stage;
            parent.setOnMouseDragged(mouseEvent -> {
                finalStage.setX(mouseEvent.getScreenX() - posicionX);
                finalStage.setY(mouseEvent.getScreenY()- posicionY);
            });
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage()+" \nCausado por: "+ex.getCause());
        }
        return controller;
    }
//CIERRA CUALQUIER MODAL
    public static void CerrarModal (Node node){Stage stage = (Stage) node.getScene().getWindow();stage.close();}
//CARGAR MODAL DE ALERTA
    public static void LoadAlert(URL fxmlAlerta, String TituloModal, String CuerpoModal,
                                 Image imgModal, Node PanelaCerrar){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlAlerta);
            Parent parent = fxmlLoader.load();
            AlertaController alert = fxmlLoader.getController();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            parent.setOnMousePressed(mouseEvent ->{
                posicionX = mouseEvent.getSceneX();
                posicionY = mouseEvent.getSceneY();
            });
            Stage finalStage = stage;
            parent.setOnMouseDragged(mouseEvent -> {
                finalStage.setX(mouseEvent.getScreenX() - posicionX);
                finalStage.setY(mouseEvent.getScreenY()- posicionY);
            });
            alert.setTitleBody(TituloModal,CuerpoModal,imgModal);
            alert.setContainerBack(PanelaCerrar);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MenuController {
    @FXML private Pane CardPaneNewPro;
    @FXML private ImageView NewImagen;
    @FXML private RadioButton Radiouno;
    @FXML private RadioButton Radiodos;
    @FXML private RadioButton Radiotres;
    @FXML private RadioButton Radiocuatro;
    @FXML private ToggleGroup grupo;

    @FXML
    private void dispalyImage(ActionEvent event){
        CardPaneNewPro.getChildren().clear();
        String name="";
        if(Radiouno.isSelected()){name="a";}
        if(Radiodos.isSelected()){name="a2";}
        if(Radiotres.isSelected()){name="d";}
        if(Radiocuatro.isSelected()){name="g4";}

            Image imagen= new Image("file///C:/Users/julio/Pictures/photomedia/photomedia/img/elements/"+name+".jpg");
            javafx.scene.image.ImageView imageview=new javafx.scene.image.ImageView(imagen);
            imageview.setFitHeight(281.0);
            imageview.setFitWidth(273.0);
            CardPaneNewPro.getChildren().add(imageview);
    }
}

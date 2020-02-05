package Utils;

import javafx.stage.FileChooser;

import java.io.File;

public class ImageChooser {

    public String getImage(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Buscar imagen");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Todas las imagenes",
                "*.JPG","*.PNG","*.jpeg"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null)
        {
            String path = selectedFile.getPath();
            return path;
        }else {return null;}
    }
}
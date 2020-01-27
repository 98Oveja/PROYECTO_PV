package models.Employ;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class validatorImage {
    public String loadImage(String UrlImage, String imaDefault){
        Image image;
        try{
            URL url1= new URL(UrlImage);
            URLConnection connection = url1.openConnection();
            InputStream inputStreamReader = connection.getInputStream();
            image = new Image(inputStreamReader);
            System.out.println("logro cargar la imagen buena");
            return UrlImage;

        }catch (Exception ex){
            System.out.println("No se encontro la imagen en el sistema.. Usando imagen default");
            return imaDefault;
        }
    }
}

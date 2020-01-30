package Controllers.employees;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class viewEmployController {
    public Label Names;
    public Label LastNames;
    public Label Direction;
    public Label NumberPhone;
    public Label Place;
    public Label Email;
    public ImageView PhotoEmploy;
    public StackPane containerView;
    double widthIma, heightIma;

    public void initData(String name, String lastname, String dir, String phone, String plac, String email, Image ima){
        Names.setText("Nombres: "+ name);
        LastNames.setText("Apellidos: " + lastname);
        Direction.setText("Direccion: " + dir);
        NumberPhone.setText("Movil: "+phone);
        Place.setText("Puesto: "+plac);
        Email.setText("Correo: " + email);

        Ajuste(ima.getWidth(),ima.getHeight());
        PhotoEmploy.setFitWidth(widthIma);
        PhotoEmploy.setFitHeight(heightIma);
        PhotoEmploy.setImage(ima);
        containerView.setPrefHeight(heightIma);
        containerView.setPrefWidth(widthIma);
    }

    public void closeModal() {
        Stage stage = (Stage) containerView.getScene().getWindow();
        stage.close();
    }

    public void Ajuste(double m, double n){
        if(m>720){      widthIma = m*0.80;      heightIma = n*0.80;Ajuste(widthIma,heightIma);}
        else if(m<500){ widthIma = m*1.20;      heightIma = n*1.20;Ajuste(widthIma,heightIma);}
        else{widthIma=m;heightIma=n;}
    }

}

package controllers;

import com.jfoenix.controls.JFXButton;
import controllers.ScreenController.ImplementsU.ControlledScreen;
import controllers.ScreenController.ScreensController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import models.Employ.newProducts;
import utils.ConnectionUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable, ControlledScreen {
    public JFXButton btnViewProd;
    public JFXButton btnViewCli;
    public JFXButton btnViewRep;
    public JFXButton btnViewVen;
    public JFXButton btnViewEst;
    public StackPane ContenedorMenu;

    ScreensController myController;

    @FXML
    private ImageView NewImageProducts;
    @FXML
    private Label titleProduct, brandProduct, desProduct;
    @FXML
    public ArrayList<newProducts> products= new ArrayList<>();


    public void produtosAnimation() {
        newProducts();
        Image images = new Image("file:/" +products.get(0).img.replace("*","\\"));
        Image images1 = new Image("file:/"+products.get(1).img.replace("*","\\"));
        Image images2 = new Image("file:/"+products.get(2).img.replace("*","\\"));
        Image images3 = new Image("file:/"+products.get(3).img.replace("*","\\"));
        Image images4 = new Image("file:/"+products.get(4).img.replace("*","\\"));
        Timeline timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0), (event) -> {
            NewImageProducts.setImage(images);
            titleProduct.setText(products.get(0).name);
            brandProduct.setText(products.get(0).mark);
            desProduct.setText(products.get(0).description);
        });

        KeyFrame keyFrame2=new KeyFrame(Duration.seconds(3), (event)-> {
            NewImageProducts.setImage(images1);
            titleProduct.setText(products.get(1).name);
            brandProduct.setText(products.get(1).mark);
            desProduct.setText(products.get(1).description);
        });
        KeyFrame keyFrame3=new KeyFrame(Duration.seconds(6), (event)->{NewImageProducts.setImage(images2);
            titleProduct.setText(products.get(2).name);
            brandProduct.setText(products.get(2).mark);
            desProduct.setText(products.get(2).description);
        });
        KeyFrame keyFrame4=new KeyFrame(Duration.seconds(9), (event)->{NewImageProducts.setImage(images3);
            titleProduct.setText(products.get(3).name);
            brandProduct.setText(products.get(3).mark);
            desProduct.setText(products.get(3).description);
        });
        KeyFrame keyFrame5=new KeyFrame(Duration.seconds(12), (event)->{NewImageProducts.setImage(images4);
            titleProduct.setText(products.get(4).name);
            brandProduct.setText(products.get(4).mark);
            desProduct.setText(products.get(4).description);
        });

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(keyFrame);
        timeline.getKeyFrames().add(keyFrame2);
        timeline.getKeyFrames().add(keyFrame3);
        timeline.getKeyFrames().add(keyFrame4);
        timeline.getKeyFrames().add(keyFrame5);
        timeline.play();
    }

    @FXML
    public void newProducts(){
        try{

            String sqlSelect= "SELECT ID_PRODUCTO, MARCA, NOMBRE, DESCRIPCION, IMG FROM PRODUCTOS ORDER BY ID_PRODUCTO DESC ";
            ConnectionUtil connectionClass= new ConnectionUtil();
            Connection connection= connectionClass.getConnection();  /*coneccion establecida*/
            Statement statement= connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelect);//listado de los productos nuevos

            if (resultSet != null) {
                int num=0;
                while(resultSet.next()) {
                    newProducts p= new newProducts();
                    p.id= resultSet.getInt("ID_PRODUCTO");
                    p.mark = resultSet.getString("MARCA");
                    p.name = resultSet.getString("NOMBRE");
                    p.description = resultSet.getString("DESCRIPCION");
                    p.img = resultSet.getString("IMG");
                    products.add(p);
                    num++;
                }
            }
        }catch (Exception ex){
            System.out.println(ex + " mi mensaje de error");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
            //produtosAnimation();

    }



    public void handleActionViewCli(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnViewCli){

        }
    }

    public void handleActionViewRep(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnViewRep){

        }
    }

    public void handleActionViewVent(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnViewVen){

        }
    }

    public void handleActionViewEst(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnViewEst){
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
}

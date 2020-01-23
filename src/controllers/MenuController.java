package controllers;

import com.jfoenix.controls.JFXButton;
import controllers.ScreenController.ImplementsU.ControlledScreen;
import controllers.ScreenController.ScreensController;
import javafx.scene.layout.StackPane;
import models.Employ.newProducts;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utils.ConnectionUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
        List<Image> imageList = new ArrayList<Image>();
        List<KeyFrame>  keyFrames =  new ArrayList<KeyFrame>();
        int timeSeparation=3;
        Timeline timeline = new Timeline();

        int pos=0;
        for(int x=0; x < products.size() && x < 5; x++ ){
            if(products.get(pos).img.equals(null) || products.get(pos).img.contains("null")){
                Image imgDefault = new Image("images/herramientas.png");
                imageList.add(imgDefault);
            }else{
                Image img = new Image("file:/" +products.get(pos).img.replace("*","\\"));
                imageList.add(img);
            }
            int finalPos = pos;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(timeSeparation), (event) -> {
                NewImageProducts.setImage(imageList.get(finalPos));
                titleProduct.setText(products.get(finalPos).name);
                brandProduct.setText(products.get(finalPos).mark);
                desProduct.setText(products.get(finalPos).description);
            });
            keyFrames.add(keyFrame);
            timeline.getKeyFrames().add(keyFrame);
            timeSeparation = timeSeparation + 3;
            pos++;
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
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
            if(products.size() < 5){
                produtosAnimation();
            }

    }


    public void handleActionViewProd(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == btnViewProd){
           HomeController hm= new HomeController();
//           hm.handleActionSetViewSelect("Estadisticas");
        }
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

package controllers.employees;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Employ.dataEmploy;
import models.Employ.sqlEmploy;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeesController implements Initializable {
    @FXML
    public Pane containerFather;
    @FXML
    public GridPane containercard;
    public Button mini1;
    public Button mini2;
    public Button mini3;
    public Button mini4;
    public Button mini5;
    public Button mini6;
    public int posx, posy;
    public int idcard,cantEmploy;
    public sqlEmploy dataEmp = new sqlEmploy();
    public List<dataEmploy> arrayEmploy= new ArrayList<>();

    @FXML
    private void pressedAddModal() {
        try {
            FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/AddEmployees.fxml"));
            Parent root = Loader.load();
            Scene dialogo = new Scene(root);
            Stage stagedialog = new Stage();
            stagedialog.initStyle(StageStyle.UNDECORATED);
            stagedialog.initModality(Modality.APPLICATION_MODAL);
            stagedialog.setScene(dialogo);
            stagedialog.showAndWait();
        }catch (Exception ex){ ex.printStackTrace();}
    }

    public void cambio(ActionEvent actionEvent) {
        if (actionEvent.getSource()==mini2){
            mini2.setStyle("-fx-background-color: #3B86FF; -fx-text-fill: #fff;");
            mini3.setStyle(".PanelLateralOpciones");
            mini4.setStyle(".PanelLateralOpciones");
            mini5.setStyle(".PanelLateralOpciones");
        }
        else if (actionEvent.getSource()==mini3){
            mini3.setStyle("-fx-background-color: #3B86FF; -fx-text-fill: #fff;");
            mini2.setStyle(".PanelLateralOpciones");
            mini4.setStyle(".PanelLateralOpciones");
            mini5.setStyle(".PanelLateralOpciones");
        }
        else if (actionEvent.getSource()==mini4){
            mini4.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini5.setStyle(".PanelLateralOpciones");
            mini2.setStyle(".PanelLateralOpciones");
            mini3.setStyle(".PanelLateralOpciones");
        }
        else if (actionEvent.getSource()==mini5){
            mini5.setStyle("-fx-background-color: #3B86FF;" +
                    "-fx-text-fill: #fff;");
            mini2.setStyle(".PanelLateralOpciones");
            mini3.setStyle(".PanelLateralOpciones");
            mini4.setStyle(".PanelLateralOpciones");
        }
    }

    public void card() throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/fxml/Empleados/cardEmpoy.fxml"));
        Parent parent= fxmlLoader.load();
        CardEmployController cn =fxmlLoader.getController();
        cn.addData(arrayEmploy.get(idcard));
        cn.initDatoCard();
        containercard.setRowIndex(parent,posx);
        containercard.setColumnIndex(parent,posy);
        containercard.getChildren().addAll(parent);
            posx=posx+1;
        if(posx==3){
            posy=1;
            posx=0;
        }
     idcard =idcard+1;
    }
    public void loaderArrayData(){
        arrayEmploy = dataEmp.employDB();
        cantEmploy = arrayEmploy.size();
        posx = 0;
        posy = 0;
        idcard=0;
    }

    public void initShowCard( ) throws IOException {
        if(cantEmploy != 0){
        while (idcard < cantEmploy && idcard < 6 ){
                card();
        }
        }else{
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/fxml/Empleados/cardEmpoy.fxml"));
            Parent parent= fxmlLoader.load();
            CardEmployController cn =fxmlLoader.getController();
            cn.setImgUser("images/male_user_.png");
            containercard.getChildren().addAll(parent);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loaderArrayData();
        try {
            initShowCard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  }


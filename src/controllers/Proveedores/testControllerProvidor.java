package controllers.Proveedores;

import controllers.employees.CardEmployController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import models.Employ.dataEmploy;
import models.Employ.sqlEmploy;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class testControllerProvidor  implements Initializable {
    @FXML
    public GridPane container;
    public int posx, posy;
    public int idcard,cantEmploy;
    public sqlEmploy dataEmp = new sqlEmploy();
    public List<dataEmploy> arrayEmploy= new ArrayList<>();

    public void card() throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/fxml/Empleados/cardEmpoy.fxml"));
        Parent parent= fxmlLoader.load();
        CardEmployController cn =fxmlLoader.getController();
        cn.addData(arrayEmploy.get(idcard));
        cn.initDatoCard();
        container.setRowIndex(parent,posx);
        container.setColumnIndex(parent,posy);
        container.getChildren().addAll(parent);
        posx=posx+1;
        System.out.println(" linea x : "+posx);
        System.out.println(" linea y : "+posy);
        if(posx==3){
            posy=1;
            posx=0;
        }else {}

        idcard =idcard+1;
    }
    public void loaderArrayData(){
        arrayEmploy = dataEmp.employDB();
    }

    public void calcularDatashow(){
        cantEmploy = arrayEmploy.size();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        posx = 0;
        posy = 0;
        idcard=0;
        loaderArrayData();

        System.out.println(cantEmploy+"**-*-*-*-*-*--");

        while (idcard < 6 ){
            try {
                card();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

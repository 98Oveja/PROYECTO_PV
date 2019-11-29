package controllers.Stadistic;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class StadisticController implements Initializable {


    public Label lblStatusWek;
    public Label lblWStatus;
    public ImageView imgStatusWek;
    public BarChart chartWek;
    public Label lblStatusDay;
    public Label lblDStatus;
    public ImageView imgStatusDay;
    public BarChart<String,Double> chartDay;
    public Label lblStatusMont;
    public Label lblMStatus;
    public BarChart chartMont;
    public LineChart mainChart;
    public Label lblPane;
    public JFXComboBox cbxTime;
    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
    XYChart.Series series3 = new XYChart.Series();
    XYChart.Series seriesAux = new XYChart.Series();

    void setdata() {

    series1.getData().add(new XYChart.Data("martillo", 25601.34));
    series1.getData().add(new XYChart.Data("carretas", 20148.82));
    series1.getData().add(new XYChart.Data("cemento" , 10000));
    series1.getData().add(new XYChart.Data("tubos"   , 35407.15));
    series1.getData().add(new XYChart.Data("cal"     , 12000));

    series2.getData().add(new XYChart.Data("martillo", 256013));
    series2.getData().add(new XYChart.Data("carretas", 20148));
    series2.getData().add(new XYChart.Data("cemento" , 1000035));
    series2.getData().add(new XYChart.Data("tubos"   , 35407));
    series2.getData().add(new XYChart.Data("cal"     , 12000));

    series3.getData().add(new XYChart.Data("martillo", 256015));
    series3.getData().add(new XYChart.Data("carretas", 2014855));
    series3.getData().add(new XYChart.Data("cemento" , 100005));
    series3.getData().add(new XYChart.Data("tubos"   , 3540731));
    series3.getData().add(new XYChart.Data("cal"     , 120008));

    seriesAux.getData().add(new XYChart.Data("martillo", 256015));
    seriesAux.getData().add(new XYChart.Data("carretas", 2014855));
    seriesAux.getData().add(new XYChart.Data("cemento" , 100005));
    seriesAux.getData().add(new XYChart.Data("tubos"   , 3540731));
    seriesAux.getData().add(new XYChart.Data("cal"     , 120008));

}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setdata();
        chartDay.getData().setAll(series1);
        chartWek.getData().setAll(series2);
        chartMont.getData().setAll(series3);
    }


    public void handlePaneDayAction(MouseEvent mouseEvent) {

        if(!(seriesAux == series1)) {
            action(series1,"Diario");
        }
    }

    public void handlePaneWeekAction(MouseEvent mouseEvent) {

        if(!(seriesAux == series2)) {
            action(series2,"Semanal");
        }
    }

    public void handlePaneMontAction(MouseEvent mouseEvent) {

        if(!(seriesAux == series3)) {
            action(series3,"Mensual");
            // chartMont.getData().setAll(mainChart.getData());
        }
    }
    private void action(XYChart.Series series, String date){
        if(date.equals("Diario")) {
            //chartDay.getData().setAll(series2);
            if (mainChart.getData() == series2.getData())chartWek.getData().setAll(mainChart.getData());
            if(mainChart.getData() == series3.getData())chartMont.getData().setAll(mainChart.getData());
        }
        if(date.equals("Semanal")) {
            if(mainChart.getData() == series1.getData()) chartDay.getData().setAll(mainChart.getData());
            //chartWek.getData().setAll(series2);
            if (mainChart.getData() == series3.getData()) chartMont.getData().setAll(mainChart.getData());

        }
        if(date.equals("Mensual")){

            if(mainChart.getData()==series1.getData())chartDay.getData().setAll(mainChart.getData());
            if(mainChart.getData()==series2.getData())chartWek.getData().setAll(mainChart.getData());
            //chartMont.getData().setAll(series3);
        }
        mainChart.getData().clear();
        mainChart.layout();
        seriesAux = series;
        seriesAux.setName(date);
        mainChart.getData().setAll(series);
        lblPane.setText(date);

    }

    private void setChartChill(XYChart.Series series){
        chartDay.getData().setAll(series2);
        chartWek.getData().setAll(series2);
        chartMont.getData().setAll(series3);
    }
}

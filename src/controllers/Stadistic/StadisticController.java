package controllers.Stadistic;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import utils.ConnectionUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class StadisticController implements Initializable {

    public Label lblStatusWek;           public Label lblWStatus;
    public ImageView imgStatusWek;       public BarChart chartWek;
    public Label lblStatusDay;           public Label lblDStatus;
    public ImageView imgStatusDay;       public BarChart<String,Double> chartDay;
    public Label lblStatusMont;          public Label lblMStatus;
    public BarChart chartMont;           public LineChart mainChart;
    public Label lblPane;                public JFXComboBox cbxTime;
    public ImageView imgStatusMont;

    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
    XYChart.Series series3 = new XYChart.Series();
    XYChart.Series series4 = new XYChart.Series();
    XYChart.Series series5 = new XYChart.Series();
    XYChart.Series series6 = new XYChart.Series();
    XYChart.Series seriesAux = new XYChart.Series();
    ConnectionUtil conn = new ConnectionUtil();
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ConnectionUtil connectionUtil = new ConnectionUtil();

    XYChart.Series setSeries(String query) throws Exception {
        XYChart.Series series = new XYChart.Series<>();

        con = conn.getConnection();
        preparedStatement = con.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        if (resultSet != null){
            while (resultSet.next()){
                System.out.println(resultSet.getInt("UNIDADES")+" "+resultSet.getString("NOMBRE"));
                series.getData().add(new XYChart.Data(resultSet.getString("NOMBRE"),resultSet.getInt("UNIDADES")));
            }
        }
        return series;
    }

    private void getSeries(){
        try {
            String slq =
                    "select count(NOMBRE)as UNIDADES, NOMBRE from DETALLE_VENTA inner join PRODUCTOS P on DETALLE_VENTA.ID_PRODUCTO = P.ID_PRODUCTO group by DETALLE_VENTA.ID_PRODUCTO;";
            setSeries(slq);
        }catch (Exception ex){
            System.out.println("error"+ex);
        }
    }
    void setdata() {
        String sql = null;
        sql = "select count(NOMBRE)as UNIDADES, NOMBRE from DETALLE_VENTA inner join PRODUCTOS P on DETALLE_VENTA.ID_PRODUCTO = P.ID_PRODUCTO group by DETALLE_VENTA.ID_PRODUCTO;";
        try {
            //mainChart.getData().addAll(setSeries(sql));
            String sql2 = "select count(NOMBRE)as UNIDADES, NOMBRE from DETALLE_VENTA inner join PRODUCTOS P on DETALLE_VENTA.ID_PRODUCTO = P.ID_PRODUCTO inner join VENTAS V on DETALLE_VENTA.ID_VENTA = V.ID_VENTA where V.FECHA < current_timestamp group by DETALLE_VENTA.ID_PRODUCTO order by UNIDADES;";

            chartDay.getData().setAll(setSeries(sql2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initChart();
        setstatusPaneDay();
        setstatusPaneWeek();
        setstatusPaneMont();
        setdata();
    }

    private void initChart() {
        chartDay.getData().setAll(series1);
        chartWek.getData().setAll(series2);
        chartMont.getData().setAll(series3);
        //mainChart.getData().addAll(series4,series5,series6);
    }

    public void handlePaneDayAction(MouseEvent mouseEvent) {
        //if(!(seriesAux == series1)) {
            //action(series1,"Diario");
        //}
        mainChart.getData().clear();
        mainChart.getData().addAll(series4);
    }

    public void handlePaneWeekAction(MouseEvent mouseEvent) {
        //if(!(seriesAux == series2)) {
          //  action(series2,"Semanal");
        //}s
        ;
        mainChart.getData().removeAll();
        mainChart.getData().add(series5);
    }

    public void handlePaneMontAction(MouseEvent mouseEvent) {
       //if(!(seriesAux == series3)) {
       //    action(series3,"Mensual");
       //}
        mainChart.getData().removeAll();
       mainChart.getData().setAll(series6);
    }


    private void action(XYChart.Series series, String date){
        if(date.equals("Diario")) {
            //chartDay.getData().setAll(series2);
            //if (mainChart.getData() == series2.getData())chartWek.getData().setAll(mainChart.getData());
            //if(mainChart.getData() == series3.getData())chartMont.getData().setAll(mainChart.getData());
            System.out.println("diario");
        }
        if(date.equals("Semanal")) {
            //if(mainChart.getData() == series1.getData()) chartDay.getData().setAll(mainChart.getData());
            //chartWek.getData().setAll(series2);
            //if (mainChart.getData() == series3.getData()) chartMont.getData().setAll(mainChart.getData());
            System.out.println("semanal");
        }
        if(date.equals("Mensual")){
            System.out.println("mensual");
            //if(mainChart.getData()==series1.getData())chartDay.getData().setAll(mainChart.getData());
            //if(mainChart.getData()==series2.getData())chartWek.getData().setAll(mainChart.getData());
            //chartMont.getData().setAll(series3);
        }

    }

    private void setChartChill(XYChart.Series series){
        chartDay.getData().setAll(series2);
        chartWek.getData().setAll(series2);
        chartMont.getData().setAll(series3);
    }

    private Image image = null;
    private double setArrow( boolean status){
        if(!status){
            image = new Image("/images/arrowred.png");
            return 180;
        }else {
            image = new Image("/images/arrowgreen.png");
            return 0;
        }
    }
    private void setStatusLabel(String data, String steam, boolean color, Label labelData, Label labelSteam, ImageView imgView){
        if(!color){
            labelData.setText(data);

            imgView.setRotate(setArrow(color));
            imgView.setImage(image);

            labelSteam.setText(steam);
            labelSteam.setTextFill(Color.valueOf("#ff4141"));
        }else {
            labelData.setText(data);

            imgView.setRotate(setArrow(color));
            imgView.setImage(image);

            labelSteam.setText(steam);
            labelSteam.setTextFill(Color.valueOf("#3cc480"));
        }
    }

    boolean ganancias =  false;
    private void setstatusPaneDay(){
        if(!ganancias) {
            setStatusLabel("456K","%12.9",true,lblStatusDay,lblDStatus,imgStatusDay);
        }else{

        }
    }
    private void setstatusPaneWeek(){
        if(!ganancias) {
            setStatusLabel("456K","%12.9",true,lblStatusWek,lblWStatus,imgStatusWek);
        }else{

        }
        //this.imgStatusWek;
        //this.lblWStatus;
        //this.lblStatusWek;
    }
    private void setstatusPaneMont(){

        if(!ganancias) {
            setStatusLabel("456K","%12.9",true,lblStatusMont,lblMStatus,imgStatusMont);
        }else{

        }
        //this.imgStatusMont;
        //this.lblMStatus;
        //this.lblStatusMont
    }
}

package controllers.Ventas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.FormatStyle;

public class ModalVentaController {
    @FXML
    AnchorPane panelContenedor;
    @FXML
    DatePicker calendarioIn;
    @FXML
    Button btnCerrarModal;

    public void CloseModal(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Close");
        alert.setContentText("Seguro que quieres Cerrar?");
        alert.showAndWait();

        //MANERA EN CERRA EL MODAL
          Stage stage = (Stage) panelContenedor.getScene().getWindow();
          stage.close();

    }


    public void calendaerioUpdate(ActionEvent actionEvent) {
        System.out.println("ModalVentaController.calendaerioUpdate");


//        DayOfWeek dayweek = item.getDayOfWeek();
//        if (dayweek == DayOfWeek.SATURDAY || dayweek == DayOfWeek.SUNDAY) {
//            this.setTextFill(Color.GREEN);
//        }

    }


    public void CambiarFormatoFecha_Completa(){
        calendarioIn.setConverter(new LocalDateStringConverter(FormatStyle.FULL));
    }
    public void CambiarFormatoFecha_Medio(){
        calendarioIn.setConverter(new LocalDateStringConverter(FormatStyle.MEDIUM));
    }

    public void FechaCompleta(MouseEvent event) {
        CambiarFormatoFecha_Completa();
    }

    public void FechaOriginal(MouseEvent event) {
        CambiarFormatoFecha_Medio();
    }

//        //    Actualizar el calendario
//        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
//            //        calendarioIn.setConverter(new LocalDateStringConverter(FormatStyle.MEDIUM));
//            @Override
//            public void updateItem(LocalDate item, boolean empty){
//                super.updateItem(item, empty);
//
//                this.setDisable(false);
//                this.setBackground(null);
//                this.setTextFill(Color.BLACK);
//
//                // deshabilitar las fechas futuras
//                if (item.isAfter(LocalDate.now())) {
//                    this.setDisable(true);
//                }
//
//                // marcar los dias de quincena
//                int day = item.getDayOfMonth();
//                if(day == 15 || day == 30) {
//
//                    Paint color = Color.RED;
//                    BackgroundFill fill = new BackgroundFill((javafx.scene.paint.Paint) color, null, null);
//
//                    this.setBackground(new Background(fill));
//                    this.setTextFill(Color.red);
//                }
//
//                // fines de semana en color verde
//                DayOfWeek dayweek = item.getDayOfWeek();
//                if (dayweek == DayOfWeek.SATURDAY || dayweek == DayOfWeek.SUNDAY) {
//                    this.setTextFill(Color.GREEN);
//                }
//            }
//
//            private void setTextFill(Color green) {
//            }
//        };







}

package controllers.Ventas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.swing.*;

public class CartaProducto{
        @FXML
        private Pane PanelContTarjet;
        @FXML
        private HBox containCard;
        @FXML
        private JFXTextField EtextCantidad;
        @FXML
        private JFXTextField EtextCodProd;
        @FXML
        private JFXTextField EtextProducto;
        @FXML
        private JFXTextField EtextPrecio;
        @FXML
        private JFXTextField EtextDescuento;
        @FXML
        private Button btnEditar;
        @FXML
        private ImageView imgEdit;
        @FXML
        private Button BtnEliminar;
        @FXML
        private ImageView imgCancel;
        @FXML
        private JFXTextField EtextSubTotal;
        //GETERS
        public HBox getContenedor(){
                return containCard;
        }
        public String getEtextCantidad(){
                return EtextCantidad.getText();
        }
        public String getEtextCodProd(){
                return EtextCodProd.getText();
        }
        public String getEtextProductos(){
                return EtextProducto.getText();
        }
        public String getEtextPrecio(){
                return EtextPrecio.getText();
        }
        public String getEtextDescuento(){
                return EtextDescuento.getText();
        }
        public String getEtextSubTotal(){
                return EtextSubTotal.getText();
        }
        public Button getBtnEditar() {
                return btnEditar;
        }
        public Button getBtnEliminar() {
                return BtnEliminar;
        }

        //SETERS
        public void setEtextCantidad(String dato){
                EtextCantidad.setText(dato);
        }
        public void setEtextCodProd(String dato){
                EtextCodProd.setText(dato);
        }
        public void setEtextProductos(String dato){
               EtextProducto.setText(dato);
        }
        public void setEtextPrecio(String dato){
                EtextPrecio.setText(dato);
        }
        public void setEtextDescuento(String dato){
                EtextDescuento.setText(dato);
        }
        public void setEtextSubTotal(String dato){
                EtextSubTotal.setText(dato);
        }
//      METODOS
        public void EdirarAlgo(ActionEvent actionEvent) {
                EtextCantidad.setEditable(true);
                EtextDescuento.setEditable(true);
        }

        public void ElimiarProduvto(ActionEvent actionEvent) {
                PanelContTarjet.getChildren().removeAll();

        }


//Actions




    }



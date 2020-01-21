package controllers.Ventas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import models.Ventas_Compras.Ventas;

import java.awt.*;
import java.text.DecimalFormat;

public class CartaProducto{
        @FXML private Pane PanelContTarjet;
        @FXML private HBox containCard;
        @FXML private JFXTextField EtextCantidad;
        @FXML private JFXTextField EtextCodProd;
        @FXML private JFXTextField EtextProducto;
        @FXML private JFXTextField EtextPrecio;
        @FXML private JFXTextField EtextDescuento;
        @FXML private Button btnEdirar;
        @FXML private ImageView imgEdit;
        @FXML private Button BtnEliminar;
        @FXML private ImageView imgCancel;
        @FXML private JFXTextField EtextSutTotal;
        public boolean hasEliminadounPanel;
        int numPanel;
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
                return EtextSutTotal.getText();
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
            EtextSutTotal.setText(dato);
        }
//      METODOS
        public void EdirarAlgo(ActionEvent actionEvent) {
                EtextCantidad.setEditable(true);
                EtextDescuento.setEditable(true);
                EtextCantidad.requestFocus();
                EtextDescuento.requestFocus();
        }
        public void ElimiarProduvto(ActionEvent actionEvent) {
        hasEliminadounPanel = true;
//        PanelContTarjet.getChildren().clear();
//        PanelContTarjet.getChildren().remove(this.PanelContTarjet.getParent())
        System.out.println("BRN de Eliminar el Panel Activado "+hasEliminadounPanel);
        System.out.println("Boton Precionado desde el Panel #"+ElNumeroDelPanel());
        }
        public void recalcularDescuento(KeyEvent keyEvent) {
                Ventas ventas = new Ventas();
                ventas.validarSoloNumeros(EtextCantidad);
                ventas.validarSoloNumeros(EtextDescuento);
                Integer canti =Integer.parseInt(EtextCantidad.getText());
                Integer descu = Integer.parseInt(EtextDescuento.getText());
                if ((canti<=0)||(descu<0)){
                        EtextCantidad.setText("1"); EtextDescuento.setText("0");
                        EtextSutTotal.setText(ventas.calculoDeDescuentos(EtextPrecio.getText(),EtextCantidad.getText(),EtextDescuento.getText()));
                }else{
                        EtextSutTotal.setText(ventas.calculoDeDescuentos(EtextPrecio.getText(),EtextCantidad.getText(),EtextDescuento.getText()));
                }
        }
        public void recalcularCantidad(KeyEvent keyEvent) {
                Ventas ventas = new Ventas();
                ventas.validarSoloNumeros(EtextCantidad);
                ventas.validarSoloNumeros(EtextDescuento);
                Integer canti =Integer.parseInt(EtextCantidad.getText());
                Integer descu = Integer.parseInt(EtextDescuento.getText());
                if ((canti<=0)||(descu<0)){
                        EtextCantidad.setText("1"); EtextDescuento.setText("0");
                        EtextSutTotal.setText(ventas.calculoDeDescuentos(EtextPrecio.getText(),EtextCantidad.getText(),EtextDescuento.getText()));
                }else{ EtextSutTotal.setText(ventas.calculoDeDescuentos(EtextPrecio.getText(),EtextCantidad.getText(),EtextDescuento.getText())); }
        }
        public boolean getPanelEliminado(boolean siEliminado){return hasEliminadounPanel;}
        public int ElNumeroDelPanel(){return numPanel;}
        public void setElNumPanel(int elPanel){this.numPanel = elPanel;}

}



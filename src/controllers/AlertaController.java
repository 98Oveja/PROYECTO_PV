package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.LoadModalesMovibles;
import java.net.URL;
import java.util.ResourceBundle;
public class AlertaController implements Initializable{
    public StackPane ContenedorPrincipal;
    public AnchorPane ContenedorSecundario;
    public ImageView imagenModal;
    public ImageView imgBotonClose;
    public Label tituloModal;
    public Button btnCerrarModal;
    public VBox VboxConenedor3;
    public Label CuerpoModal;
    public HBox HboxConenedor4;
    public JFXButton btnConfirmar;
    public JFXButton btnCancelar;
    public Node contenedorTracero;
//    GETERS AND SETERS
    public StackPane getContenedorPrincipal(){return ContenedorPrincipal;}
    public void setContenedorPrincipal(StackPane contenedorPrincipal){ContenedorPrincipal = contenedorPrincipal;}
    public AnchorPane getContenedorSecundario(){return ContenedorSecundario;}
    public void setContenedorSecundario(AnchorPane contenedorSecundario){ContenedorSecundario = contenedorSecundario;}
    public ImageView getImagenModal(){return imagenModal;}
    public void setImagenModal(ImageView imagenModal){this.imagenModal = imagenModal;}
    public Label getTituloModal(){return tituloModal;}
    public void setTituloModal(Label tituloModal){this.tituloModal = tituloModal;}
    public Button getBtnCerrarModal(){return btnCerrarModal;}
    public void setBtnCerrarModal(Button btnCerrarModal){this.btnCerrarModal = btnCerrarModal;}
    public ImageView getImgBotonClose(){return imgBotonClose;}
    public void setImgBotonClose(ImageView imgBotonClose){this.imgBotonClose = imgBotonClose;}
    public VBox getVboxConenedor3(){return VboxConenedor3;}
    public void setVboxConenedor3(VBox vboxConenedor3){VboxConenedor3 = vboxConenedor3;}
    public Label getCuerpoModal(){return CuerpoModal;}
    public void setCuerpoModal(Label cuerpoModal){CuerpoModal = cuerpoModal;}
    public JFXButton getBtnConfirmar(){return btnConfirmar;}
    public void setBtnConfirmar(JFXButton btnConfirmar){this.btnConfirmar = btnConfirmar;}
    public HBox getHboxConenedor4(){return HboxConenedor4;}
    public void setHboxConenedor4(HBox hboxConenedor4){HboxConenedor4 = hboxConenedor4; }
    public JFXButton getBtnCancelar(){return btnCancelar;}
    public void setBtnCancelar(JFXButton btnCancelar){this.btnCancelar = btnCancelar;}
    public AlertaController() {}
    public Node setContainerBack (Node contenedor){return this.contenedorTracero = contenedor;}
    public Node getContainerBack (){return this.contenedorTracero;}
    public void setTitleBody(String a, String c,Image modal){
        this.tituloModal.setText(a);
        this.CuerpoModal.setText(c);
        this.imagenModal.setImage(modal);
    }
    public void CerrarModalContenedor(){
        LoadModalesMovibles.CerrarModal(getContainerBack());
    }
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCerrarModal.setOnAction(actionEvent -> {
            LoadModalesMovibles.CerrarModal(ContenedorPrincipal); });
        btnCancelar.setOnAction(actionEvent -> {
            LoadModalesMovibles.CerrarModal(ContenedorPrincipal); });
        btnConfirmar.setOnAction(actionEvent -> {
            LoadModalesMovibles.CerrarModal(ContenedorPrincipal);
                CerrarModalContenedor();
        });
    }
}

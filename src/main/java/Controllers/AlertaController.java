package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
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
import Utils.LoadModalesMovibles;
import java.net.URL;
import java.util.ResourceBundle;
public class AlertaController implements Initializable{
    public StackPane ContenedorPrincipal;
    public AnchorPane ContenedorSecundario;
    public JFXButton btnConfirmar;
    public JFXButton btnCancelar;
    public Label tituloModal;
    public Label CuerpoModal;
    public ImageView imagenModal;
    public Button btnCloseView;
    private Node contenedorTracero;

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
        
        btnCancelar.setOnAction(actionEvent -> {
            LoadModalesMovibles.CerrarModal(ContenedorPrincipal); });
        btnConfirmar.setOnAction(actionEvent -> {
            LoadModalesMovibles.CerrarModal(ContenedorPrincipal);
                CerrarModalContenedor();
        });
    }

    public void actionCloseView(ActionEvent actionEvent) {
            LoadModalesMovibles.CerrarModal(ContenedorPrincipal);
    }
}

package Controllers;

import Controllers.item.ControllerComponent;
import Models.User;
import Models.interfaces.userImpl;
import Models.usages.UserImplem;
import Utils.closeView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Utils.ConnectionUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class ForgotPassEmailController implements Initializable {
    public Button closeButton;

    @FXML
    public JFXButton btnForgot;

    @FXML
 
    public Label lblStatus;
    public JFXTextField txtCode;
    public JFXPasswordField txtPass;
    public JFXPasswordField txtPassVerified;
    public Pane pane;

    String email = null;
    String status = null;
    String code  = null;


    public ForgotPassEmailController(){
    }

    public void handleButtonAction(MouseEvent event) {
        if (event.getSource() == btnForgot) {
            update();
        }
    }

    public void handleButtonActionKey(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            update();
        }
    }

    public void handleActionClose() {
        closeView closeView  = new closeView();
        closeView.Cerrar(this.pane);
    }

    private void update(){
        if(!txtCode.getText().isEmpty()) {
            if (status.equals("susses")) {
                if (txtPassVerified.getText().equals(txtPass.getText())) {
                    try {
                        Runnable updaterUser = () -> {
                            User user =  ControllerComponent.user;
                            userImpl userI = new UserImplem();
                            User ux = null;
                            ux.setId_ususer(user.getId_ususer());
                            ux.setName(user.getName());
                            ux.setEmail(user.getEmail());
                            ux.setLast_name(user.getLast_name());
                            ux.setDataSetting(user.getDataSetting());
                            ux.setUrlPhoto(user.getUrlPhoto());
                            ux.setPass(txtPassVerified.getText());
                            ux.setAdmin(user.getAdmin());
                            ux.setStatus(user.getStatus());
                            userI.update(ux);
                        };
                        Thread taskUpdateUser = new Thread(updaterUser, "UPDATE-USER");
                        taskUpdateUser.start();
                        Stage stage = (Stage) closeButton.getScene().getWindow();
                        System.out.println("susses update");
                        stage.close();

                    } catch (Exception e) {
                        lblStatus.setText("Error al actualizar contraseña");
                        lblStatus.setTextFill(Color.TOMATO);
                    }
                } else {
                    lblStatus.setText("las contraseñas no coinsiden");
                    lblStatus.setTextFill(Color.TOMATO);
                }
            }
        }else {
            lblStatus.setText("Ingrese su codigo de verificacion");
            lblStatus.setTextFill(Color.TOMATO);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtCode.textProperty().addListener((Observable, oldValue, newValue) -> {
            code  = getCode();
            System.out.println(code);
            if (newValue.equals(code)) {
                Runnable runnable = () -> {
                    System.out.println("son iguales");
                    lblStatus.setText("Verificacion aceptada :)");
                    lblStatus.setTextFill(Color.GREEN);
                    txtPass.setEditable(true);
                    txtPassVerified.setEditable(true);
                    txtPassVerified.textProperty().addListener((observable,oldf,newd )-> {
                        if (newd.equals(txtPass.getText())){
                            System.out.println("las contraseñas son igualess");
                            lblStatus.setText("las contraseñas son igualess");
                            lblStatus.setTextFill(Color.GREEN);
                            status = "susses";
                        }else{
                            lblStatus.setText("la contraseña no coincide :(");
                            lblStatus.setTextFill(Color.TOMATO);
                            status = "error";
                        }
                    });
                };
                Thread thread = new Thread(runnable, "UPDATE-UI");
                thread.start();
            }else{
                Runnable runnable = () -> {
                    status = "error";
                    lblStatus.setText("Verifique el codigo enviado");
                    lblStatus.setTextFill(Color.TOMATO);
                };
                Thread thread = new Thread(runnable, "UPDATE-USER");
                thread.start();

            }
        });
    }

    public static String getCode() {
        return ForgotPassController.code;
    }

    public static String getEmail() {
        return ForgotPassController.Destino;
    }

}

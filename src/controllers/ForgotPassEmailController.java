package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ConnectionUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class ForgotPassEmailController implements Initializable {
    public ImageView closeButton;

    @FXML
    public JFXButton btnForgot;

    @FXML
 
    public Label lblStatus;
    public JFXTextField txtCode;
    public JFXPasswordField txtPass;
    public JFXPasswordField txtPassVerified;

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

    public void handleActionClose(MouseEvent mouseEvent) {
        if(mouseEvent.getSource()== closeButton) {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }

    private void update(){
        if(!txtCode.getText().isEmpty()) {
            if (status.equals("susses")) {
                if (txtPassVerified.getText().equals(txtPass.getText())) {
                    //Accion del boton
                    try {
                        Connection con = null;
                        ConnectionUtil connectionUtil = new ConnectionUtil();
                        con = connectionUtil.getConnection();
                        String sql = "UPDATE USUARIOS SET CONTRASENA=? WHERE EMAIL=?;";
                        email = getEmail();
                        PreparedStatement preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setString(1, txtPass.getText());
                        preparedStatement.setString(2, email);
                        preparedStatement.executeUpdate();

                        con.close();
                        Stage stage = (Stage) closeButton.getScene().getWindow();
                        System.out.println("susses update");
                        //paso a la ventana principal

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
            //System.out.println(newValue);
            if (newValue.equals(code)) {
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


            }else{
                status = "error";
                lblStatus.setText("Verifique el codigo enviado");
                lblStatus.setTextFill(Color.TOMATO);
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

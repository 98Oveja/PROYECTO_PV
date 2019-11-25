package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.User;
import models.UserController;
import models.UserView;
import utils.CodeUtil;
import utils.ConnectionUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class ForgotPassEmailController implements Initializable {
    public ImageView closeButton;

    @FXML
    public Button btnForgot;

    @FXML
    public TextField txtCode;
    public TextField txtPass;
    public TextField txtPassVerified;
    public Label lblStatus;

    String email = null;
    String status = null;
    String code  = null;


    public ForgotPassEmailController(){
    }
    public void handleButtonAction(MouseEvent event) {
        if (event.getSource() == btnForgot) {
            if (status.equals("susses")){

                if(txtPassVerified.getText().equals(txtPass.getText())){
                    //Accion del boton
                    try{
                        Connection con = ConnectionUtil.conDB();
                        String sql= "UPDATE USUARIOS SET CONTRASENA=? WHERE EMAIL=?;";
                        email = getEmail();
                        PreparedStatement preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setString(1, txtPass.getText());
                        preparedStatement.setString(2, email);
                        preparedStatement.executeUpdate();

                        con.close();
                        Stage stage = (Stage) closeButton.getScene().getWindow();
                        System.out.println("susses update");
                        stage.close();

                    }catch (Exception e ){
                        lblStatus.setText("Error al actualizar contraseña");
                        lblStatus.setTextFill(Color.TOMATO);
                    }
                }else{
                    lblStatus.setText("las contraseñas no coinsiden");
                    lblStatus.setTextFill(Color.TOMATO);
                }
            }
        }
    }

    public void handleActionClose(MouseEvent mouseEvent) {
        if(mouseEvent.getSource()== closeButton) {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtCode.textProperty().addListener((Observable, oldValue, newValue) -> {
            code  = getCode();
            System.out.println(code);
            System.out.println(newValue);
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

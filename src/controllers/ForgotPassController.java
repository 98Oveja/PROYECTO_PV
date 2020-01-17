package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import navigator.ViewNavigator;
import utils.CodeUtil;
import utils.ConnectionUtil;
import utils.ParseEmail;
import utils.SendEmail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ForgotPassController {

    static String code, Destino;

    Connection con = null;
    PreparedStatement preparedStatement;
    ResultSet resultSet;


    String Remitente,Password, Asunto, Mensaje;

    ParseEmail email = new ParseEmail();

    @FXML
    public JFXTextField txtUsername;
    public JFXButton btnForgot;
    public Label lblErrors;
    public ForgotPassController(){
//        con = ConnectionUtil.conDB();
        ConnectionUtil con4 = new ConnectionUtil();
    }

    public void handleButtonActionKey(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            forgotFunction();
        }
    }

    public void handleButtonAction(MouseEvent event) throws IOException {
        if (event.getSource() == btnForgot) {
            forgotFunction();
        }
    }

    private void forgotFunction() throws IOException {
        if (email.isValid(txtUsername.getText())) {
            if (existEmail(txtUsername.getText())) {

                final Stage primaryStage = new Stage();
                final Stage dialog = new Stage();


                Remitente = "carls10vasquez@gmail.com";
                Password = "qnujurorzribvqln";
                Destino = txtUsername.getText();

                Asunto = "RECUPERCION DE CONTRASEÑA.";

                code = CodeUtil.generateCode();

                Mensaje = " Codigo de recuperacion:" + code;
                if(SendEmail.SendGMail(Remitente,Password,Destino,Asunto,Mensaje)) {
                    lblErrors.setText("Codigo enviado");
                    lblErrors.setTextFill(Color.GREEN);

                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initStyle(StageStyle.UNDECORATED);
                    dialog.initOwner(primaryStage);
                    dialog.setX(1000);
                    dialog.setY(330);

                    Scene dialogScene = null;
                    dialogScene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/ForgotPassEmail.fxml")));

                    dialog.setScene(dialogScene);
                    dialog.show();

                    txtUsername.clear();
                }
            } else {
                txtUsername.clear();
                lblErrors.setText("No existe el correo ingresado");
                lblErrors.setTextFill(Color.TOMATO);
            }
        } else {
            txtUsername.clear();
            lblErrors.setText("Correo invalido");
            lblErrors.setTextFill(Color.TOMATO);
        }
    }

    private boolean existEmail(String email){
        boolean status;
        String sql = "SELECT NOMBRE FROM USUARIOS Where EMAIL = ? ";
        try {

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                status = false;
            } else {
                status = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            status = false;
        }
        return status;
    }

    @FXML
    void previousPane() {
        ViewNavigator.loadVista(ViewNavigator.LOGIN_VIEW);
    }

}

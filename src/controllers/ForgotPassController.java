package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    Connection con = null;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    @FXML
    public Label lblErrors;
    @FXML
    public TextField txtUsername;

    ParseEmail email = new ParseEmail();
    String code = null;
    String user = null;
    public ForgotPassController(){
        con = ConnectionUtil.conDB();
    }

    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getUser() {
        return this.user;
    }
    public void setUser(String User){ this.user = user; }

    public void handleButtonActionKey(KeyEvent keyEvent) {
    }

    public void handleButtonAction(MouseEvent mouseEvent) throws IOException {
        if(email.isValid(txtUsername.getText())) {
            if(existEmail(txtUsername.getText())) {

                final Stage primaryStage = new Stage();
                final Stage dialog = new Stage();

                String Remitente,Password, Destino, Asunto, Mensaje;
                Remitente = "carls10vasquez@gmail.com";
                Password = "car1051Z";
                Destino = txtUsername.getText();
                Asunto = "RECUPERCION DE CONTRASEÑA.";
                code = CodeUtil.generateCode();
                setCode(code);
                Mensaje = " Codigo de recuperacion:" +code;

                if(SendEmail.SendGMail(Remitente,Password,Destino,Asunto,Mensaje)) {
                    lblErrors.setText("Codigo enviado");
                    lblErrors.setTextFill(Color.GREEN);

                    setUser(txtUsername.getText());

                    dialog.initModality(Modality.WINDOW_MODAL);
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
            }else{
                txtUsername.clear();
                lblErrors.setText("No existe el correo ingresado");
                lblErrors.setTextFill(Color.TOMATO);
            }
        }else {
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

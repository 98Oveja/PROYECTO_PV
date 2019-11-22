package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ForgotPassEmailController {
    public ImageView closeButton;

    @FXML
    public Button btnForgot;

    @FXML
    public TextField txtCode;
    public TextField txtPass;
    public TextField txtPassVerified;
    public Label lblStatus;

    ForgotPassController CODE = new ForgotPassController();

    public void handleButtonAction(MouseEvent event) {
        if (event.getSource() == btnForgot) {
            String code = CODE.getCode();
            if (code.equals(txtCode.getText())){
                txtPass.setEditable(true);
                txtPassVerified.setEditable(true);
                if(txtPassVerified.getText().equals(txtPass.getText())){
                    //Accion del boton
                    try{
                        Connection con = ConnectionUtil.conDB();
                        String sql= "UPDATE USUARIOS SET CONTRASENA=? WHERE EMAIL=?;";
                        PreparedStatement preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setString(1, txtPass.getText());
                        preparedStatement.setString(2, CODE.getUser());
                        preparedStatement.executeUpdate();

                        CODE.lblErrors.setText("Contraseña modificada Exitosamente");
                        CODE.lblErrors.setTextFill(Color.GREEN);

                        con.close();
                        Stage stage = (Stage) closeButton.getScene().getWindow();
                        stage.close();

                    }catch (Exception e ){
                        lblStatus.setText("Error al actualizar contraseña");
                        lblStatus.setTextFill(Color.TOMATO);
                    }
                }else{
                    lblStatus.setText("las contraseñas no coinsiden");
                    lblStatus.setTextFill(Color.TOMATO);
                }
            }else {
                lblStatus.setText("Verifique el codigo enviado");
                lblStatus.setTextFill(Color.TOMATO);
            }
        }
    }

    public void handleActionClose(MouseEvent mouseEvent) {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

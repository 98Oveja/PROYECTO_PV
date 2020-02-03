package Controllers;

import Utils.ThreadReadUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import Controllers.ScreenController.ImplementsU.ControlledScreen;
import Controllers.ScreenController.ScreensController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import Utils.CodeUtil;
import Utils.ParseEmail;

import java.io.IOException;

public class ForgotPassController implements ControlledScreen {

    static String code, Destino;
    public Button btnReturn;

    ScreensController myController;
    ParseEmail email = new ParseEmail();

    String Remitente,Password, Asunto, Mensaje;

    @FXML
    public JFXTextField txtUsername;
    public JFXButton btnForgot;
    public Label lblErrors;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    private void forgotFunction() throws IOException {
        if (email.isValid(txtUsername.getText())) {
            existEmail(txtUsername.getText());
        } else {
            txtUsername.clear();
            lblErrors.setText("Correo invalido");
            lblErrors.setTextFill(Color.TOMATO);
        }
    }

    private boolean statusExistUser;

    private void existEmail(String text) {
        ThreadReadUser threadReadUser = new ThreadReadUser(text);
        new Thread(threadReadUser).start();

        threadReadUser.setOnRunning(workerStateEvent -> {
            btnForgot.setDisable(true);
        });

        threadReadUser.setOnSucceeded(workerStateEvent -> {
            btnForgot.setDisable(false);
            try {
                statusExistUser = threadReadUser.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(statusExistUser){
                Remitente = "carls10vasquez@gmail.com";
                Password = "qnujurorzribvqln";
                Destino = txtUsername.getText();
                Asunto = "RECUPERCION DE CONTRASEÑA.";
                Mensaje = " Codigo de recuperacion:" + code;

                code = CodeUtil.generateCode();
                //SendEmail.SendGMail(Remitente,Password,Destino,Asunto,Mensaje);
                //if() {
                Utils.LoadModalesMovibles.LoadModalMovible(getClass().getResource("/fxml/ForgotPassEmail.fxml"), null);
                txtUsername.clear();
                //}else{
                txtUsername.clear();
                lblErrors.setText("Error al enviar el correo.");
                lblErrors.setTextFill(Color.TOMATO);
                //}

            }else {
                txtUsername.clear();
                lblErrors.setText("No existe el correo ingresado");
                lblErrors.setTextFill(Color.TOMATO);
            }
        });
    }

    public void actionReturnPane(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnReturn) {
            myController.setScreen(LoginController.screen1ID);
        }
    }

    public void actionSendEmail(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnForgot) {
            forgotFunction();
        }
    }
}

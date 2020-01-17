package controllers;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import models.User;
import navigator.ViewNavigator;
import utils.ConnectionUtil;
import utils.CreateThread;
import utils.ParseEmail;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public static int code;
    public JFXProgressBar statusProgess;
    public Pane paneText;
    public Pane pane_MAIN;
    @FXML private Label lblErrors;              @FXML private JFXTextField txtUsername;
    @FXML public JFXPasswordField txtPassword;  @FXML private Button btnSignin;

    Connection con;
    User user;
    ConnectionUtil connectionUtil = new ConnectionUtil();
    public LoginController() {
        con = connectionUtil.getConnection();
    }

    @FXML
    public void handleButtonAction(MouseEvent event){
        if(event.getSource() == btnSignin){
//           logIn();
            ViewNavigator.loadVista(ViewNavigator.HOME);
        }
    }

    public void handleButtonActionKey(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            //if(logIn().equals("Success")) {
              //  ViewNavigator.loadVista(ViewNavigator.HOME);
                //code = 1;
            //}
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            if (con == null) {
                lblErrors.setTextFill(Color.TOMATO);
                lblErrors.setText("Server Error : Check.");
               btnSignin.setDisable(true);
            } else if (btnSignin.isDisable()) {
                System.out.println("boton desactivado por conexion");
            }

    }

    String status = "";
    private void logIn() {

        String email = txtUsername.getText();
        String password = txtPassword.getText();
        ParseEmail validate = new ParseEmail();

        if(!validate.isValid(email)&&!password.isEmpty()){
           setLblError(Color.TOMATO, "Usuario no valido");
            status = "Error";
        }
        if( password.isEmpty() && !email.isEmpty() ){
            setLblError(Color.TOMATO, "Ingrese una contraseña valida");
            status = "Error";
        }
        if(email.isEmpty() && password.isEmpty() ){
            setLblError(Color.GREEN, "Campos vacios");
            status = "Error";
        }if(!email.isEmpty() && !password.isEmpty()) {

            CreateThread task = new CreateThread(txtUsername.getText(), txtPassword.getText());
            new Thread(task).start();

            task.setOnRunning(workerStateEvent -> {
                statusProgess.setVisible(true);
                btnSignin.setDisable(true);
            });
            task.setOnSucceeded(workerStateEvent -> {
                btnSignin.setDisable(false);
                statusProgess.setVisible(false);
                user = task.getUserAux();
                System.out.println(user);
                if(user == null){
                    status = "Error";
                    setLblError(Color.TOMATO, "Usuario invalido");
                }else if(user!= null){
                    status="Success";
                    setLblError(Color.GREEN,"Login success...");
                    ViewNavigator.loadVista(ViewNavigator.HOME);
                }
            });
        }

    }
    
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
    }

    @FXML
    void nextPane(ActionEvent event) {
        ViewNavigator.loadVista(ViewNavigator.LOGIN_VIEW_PASS);
    }
}

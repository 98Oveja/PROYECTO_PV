package controllers;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


import navigator.ViewNavigator;
import utils.ConnectionUtil;
import utils.ParseEmail;
import utils.ViewUtil;

public class LoginController implements Initializable {


    @FXML
    private Label lblErrors;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    public JFXPasswordField txtPassword;

    @FXML
    private Button btnSignin;

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public LoginController() {
        con = ConnectionUtil.conDB();
    }

    @FXML
    public void handleButtonAction(MouseEvent event) {

        if (event.getSource() == btnSignin) {
            if(logIn().equals("Success")) {
                ViewNavigator.loadVista(ViewNavigator.MODALVENTAS);
            }
        }
    }

    public void handleButtonActionKey(KeyEvent keyEvent) {

        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            if(logIn().equals("Success")) {
                ViewNavigator.loadVista(ViewNavigator.MODALVENTAS);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (con == null) {
           lblErrors.setTextFill(Color.TOMATO);
           lblErrors.setText("Server Error : Check.");
        }
    }

    private String logIn() {
        String status = "Success";
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        ParseEmail validate = new ParseEmail();

        if(!validate.isValid(email)&&!password.isEmpty()){
            setLblError(Color.TOMATO, "Usuario no valido");
            return status = "Error";
        }
        if( password.isEmpty() && !email.isEmpty() ){
            setLblError(Color.TOMATO, "Ingrese una contraseña valida");
            status = "Error";
        }
        if(email.isEmpty() && password.isEmpty() ){
            setLblError(Color.GREEN, "Campos vacios");
            status = "Error";
        }if(!email.isEmpty() && !password.isEmpty()) {
            String sql = "SELECT * FROM USUARIOS Where EMAIL = ? and CONTRASENA = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    status = "Error";
                    setLblError(Color.TOMATO,"Usuario invalido");
                    txtPassword.setText(null);
                } else {

                    setLblError(Color.GREEN, "Login Successful...");
                }
            } catch (SQLException ex) {
                status = "Exception";
            }
        }
        
        return status;
    }
    
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }


    @FXML
    void nextPane(ActionEvent event) {
        ViewNavigator.loadVista(ViewNavigator.LOGIN_VIEW_PASS);
    }
}

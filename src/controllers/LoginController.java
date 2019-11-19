package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import utils.ConnectionUtil;

public class LoginController implements Initializable {

    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

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

            if (logIn().equals("Success")) {
                try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/OnBoard.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }
    public void handleButtonActionKey(KeyEvent keyEvent) {

        String type = keyEvent.getEventType().getName();
        KeyCode keyCode = keyEvent.getCode();
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            if(logIn().equals("Success")) {
                try {
                    Node node = (Node) keyEvent.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/OnBoard.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

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
        if(email.isEmpty() && !password.isEmpty()){
            setLblError(Color.TOMATO, "No se ha llenado el campo Usuario");
            status = "Error";
        }
        if( password.isEmpty() && !email.isEmpty() ){
            setLblError(Color.TOMATO, "Ingrese una contraseña valida");
            status = "Error";
        }
        if(email.isEmpty() && password.isEmpty()){
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
                System.err.println(ex.getMessage());
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

}

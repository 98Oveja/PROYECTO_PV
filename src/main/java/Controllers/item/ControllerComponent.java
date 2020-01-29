package Controllers.item;

import Controllers.MainController;
import Navigator.ViewNavigator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import Controllers.LoginController;
import Controllers.ScreenController.ImplementsU.ControlledScreen;
import Controllers.ScreenController.ScreensController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import Models.User;
import Utils.CreateThread;
import Utils.ParseEmail;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ControllerComponent implements Initializable, ControlledScreen {

    public Button btnForgot;
    public JFXProgressBar statusProgess;
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public Label lblErrors;
    public JFXButton btnSignin;
    public VBox VboxD;
    User user;
    String status = "";
    public static boolean admin = false;

    ScreensController myController;

     public static String screen1ID = "screen1";
     public static String screen1File = "/fxml/Home.fxml";

    @FXML
    void nextPane(ActionEvent event)  {
        if(event.getSource() == btnForgot) {
            myController.setScreen(LoginController.screen2ID);
        }
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            Runnable updater = new Runnable() {
                @Override
                public void run() {
                    ViewNavigator.loadVista(ViewNavigator.HOME);
                }
            };

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(updater);
        }
    });

    @FXML
    public void handleButtonAction(MouseEvent event){
 /*
        if(event.getSource() == btnSignin){
          logIn();
        }

 Task t = new Task() {
            @Override
            protected Object call() throws Exception {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interrupted) {
                    if (isCancelled()) {
                        updateMessage("Cancelled");
                    }
                }
                return null;
            }
        };
        new Thread(t).start();
        t.setOnRunning(workerStateEvent -> {
            System.out.println("run ---------------------------> fxml ");
        });
        t.setOnSucceeded(workerStateEvent -> {
            System.out.println("success  ---------------------> fxml ");
        });


--------------------------------------
*/
        thread.setDaemon(true);
        thread.start();
        admin = true;
    }

    public void handleButtonActionKey(KeyEvent keyEvent) {
       if(keyEvent.getCode().equals(KeyCode.ENTER)){
            logIn();
        }
    }

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

                if(user == null){
                    status = "Error";
                    setLblError(Color.TOMATO, "Usuario invalido");
                }else if(user!= null){
                    status="Success";
                    setLblError(Color.GREEN,"Login success...");

                    if (user.getAdmin().equals("Admin")){
                        admin = true;
                    }
                    //ViewNavigator.loadVista(ViewNavigator.HOME);
                    thread.setDaemon(true);
                    thread.start();
                }
            });

        }

    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}

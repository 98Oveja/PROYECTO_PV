package Controllers.UserSetting;

import Controllers.employees.viewEmployController;
import Controllers.item.ControllerComponent;
import Models.Employ.validatorImage;
import Models.User;
import Utils.ImageChooser;
import Utils.MailServerInfo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;


public class SettingsUser implements Initializable {

    public BorderPane imgUser;
    public Label nameUser;
    public Label userC;
    public JFXButton btnViewUser;
    public JFXTextField serverName;
    public JFXTextField smtpPort;
    public JFXTextField emailAddress;
    public JFXPasswordField emailPassword;
    public JFXCheckBox sslCheckbox;
    public ImageView imgNewUser;
    public JFXTextField txtName;
    public JFXTextField txtLastName;
    public JFXTextField txtAdmin;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPass;
    public Button btnAdd;
    public JFXButton btnSaveUser;
    User user =  ControllerComponent.user;
    String path;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            nameUser.setText(user.getName()+" "+user.getLast_name());
            userC.setText(user.getAdmin());
            initImgUser();
            loadMailServerConfigurations();
    }

    Circle circle = new Circle(100,100,50);
    validatorImage valIma= new validatorImage();
    Image img;

    private void initImgUser() {

        String auxUser = user.getUrlPhoto();



        if(!auxUser.isEmpty() &&  auxUser.contains("*")){
            auxUser = "file:/" + user.getUrlPhoto().replace("*","\\");
        }else {
            auxUser = "file:/ssc/jsajd/a.png";
        }

        System.out.println(auxUser);
        String trueValimg =  valIma.loadImage(auxUser,"images/index.jpeg");
        img = new Image(trueValimg);

        circle.setFill(new ImagePattern(img));
        imgUser.setCenter(circle);
    }

    public void viewUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader Loader= new FXMLLoader(getClass().getResource("/fxml/Empleados/viewEmloy.fxml"));
        Parent root = Loader.load();
        viewEmployController viewEm = Loader.getController();
        viewEm.initData(user.getName()+" "+" ",user.getLast_name()+" "+" ",""," ",user.getAdmin(),"",img);
        Scene dialogo = new Scene(root);
        Stage stagedialog = new Stage();
        stagedialog.initStyle(StageStyle.UNDECORATED);
        stagedialog.initModality(Modality.APPLICATION_MODAL);
        stagedialog.setScene(dialogo);
        stagedialog.showAndWait();
    }

    public void saveMailServerConfuration(ActionEvent actionEvent) {
        MailServerInfo mailServerInfo = readMailSererInfo();
        DataHelper.updateMailServerInfo(mailServerInfo);
    }

    public void handleTestMailAction(ActionEvent actionEvent) {
        MailServerInfo mailServerInfo = readMailSererInfo();
        if (mailServerInfo != null) {

        }
    }
    private MailServerInfo readMailSererInfo() {
        try {
            MailServerInfo mailServerInfo
                    = new MailServerInfo(serverName.getText(), Integer.parseInt(smtpPort.getText()), emailAddress.getText(), emailPassword.getText(), sslCheckbox.isSelected());
            if (!mailServerInfo.validate() ) {
                throw new InvalidParameterException();
            }
            return mailServerInfo;
        } catch (Exception ignored) {
        }
        return null;
    }

    private void loadMailServerConfigurations() {
        MailServerInfo mailServerInfo = DataHelper.loadMailServerInfo();
        serverName.setText(mailServerInfo.getMailServer());
        smtpPort.setText(String.valueOf(mailServerInfo.getPort()));
        emailAddress.setText(mailServerInfo.getEmailID());
        emailPassword.setText(mailServerInfo.getPassword());
        sslCheckbox.setSelected(mailServerInfo.getSslEnabled());
    }

    public void Addphoto(ActionEvent actionEvent) {
        ImageChooser imageChooser = new ImageChooser();
        path = imageChooser.getImage();
        Image image = new Image("file:/"+path);
        imgNewUser.setImage(image);
    }

    public void handleActionSaveUser(ActionEvent actionEvent) {
        User user = new User(
                1,
                txtEmail.getText(),
                txtName.getText(),
                txtLastName.getText(),
                txtPass.getText(),
                1,
                txtAdmin.getText(),
                path,
                "smtp.gmail.com;587;user@esc.com;xxxxxx;true"
        );
        DataHelper.insertUserInfo(user);
    }
}

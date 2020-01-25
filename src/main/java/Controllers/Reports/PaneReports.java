package Controllers.Reports;

import Controllers.ScreenController.ImplementsU.ControlledScreen;
import Controllers.ScreenController.ScreensController;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

public class PaneReports implements Initializable, ControlledScreen {
    public StackPane mainContainer;
    ScreensController myController;

    double height = Toolkit.getDefaultToolkit().getScreenSize().height;
    double width = Toolkit.getDefaultToolkit().getScreenSize().width;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainContainer.setPrefWidth(width-260);
        mainContainer.setPrefHeight(height-110);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
}

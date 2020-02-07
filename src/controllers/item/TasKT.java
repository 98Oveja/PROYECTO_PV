package controllers.item;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TasKT extends TimerTask {

    private Label label;
    JFXButton auxBtn;

    static private final Logger LOGGER = Logger.getLogger("");

    public TasKT(JFXButton auxBtn) {
        this.auxBtn = auxBtn;
        auxBtn.setDisable(true);

    }

    @Override
    public void run() {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        if (connectionUtil.getConnection() != null) {
            auxBtn.setDisable(false);
        }
    }
}

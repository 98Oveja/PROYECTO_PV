package Controllers.item;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import Utils.ConnectionUtil;

import java.util.TimerTask;
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

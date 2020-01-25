package Controllers.Calendar;

import Controllers.ScreenController.ImplementsU.ControlledScreen;
import Controllers.ScreenController.ScreensController;

public class CalendarController implements ControlledScreen {
    ScreensController myController;
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
}

package controllers.Calendar;

import controllers.ScreenController.ImplementsU.ControlledScreen;
import controllers.ScreenController.ScreensController;

public class CalendarController implements ControlledScreen {
    ScreensController myController;
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
}

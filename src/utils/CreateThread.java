package utils;

import javafx.concurrent.Task;
import models.User;
import models.interfaces.userImpl;
import models.usages.UserImplem;

public class CreateThread extends Task<User>{

    String email;
    String pass;
    User userAux;

    public User getUserAux() {
        return userAux;
    }

    public CreateThread(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public CreateThread() {
    }

    @Override
    protected User call() {
        updateMessage("Processing...");
        userImpl user = new UserImplem();
        userAux = user.read(email,pass);
        updateMessage("Success.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interrupted) {
            if (isCancelled()) {
                updateMessage("Cancelled");
            }
        }

        return userAux;
    }

}

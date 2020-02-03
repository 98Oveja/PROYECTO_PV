package Utils;

import Models.interfaces.userImpl;
import Models.usages.UserImplem;
import javafx.concurrent.Task;

public class ThreadReadUser extends Task<Object> {

    private userImpl user = new UserImplem();
    private boolean existUser;
    private String email;

    public boolean isExistUser() {
        return existUser;
    }
    public ThreadReadUser(String email) {
        this.email= email;
    }
    @Override
    public Boolean call() throws Exception {
        existUser = user.existEmail(email);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException interrupted) {
            if (isCancelled()) {
                updateMessage("Cancelled");
            }
        }
        return existUser;
    }

}

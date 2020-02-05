package Controllers.UserSetting;

import Controllers.item.ControllerComponent;
import Models.User;
import Models.interfaces.userImpl;
import Models.usages.UserImplem;
import Utils.MailServerInfo;

public class DataHelper {
    static User user =  ControllerComponent.user;
    static User ux;


    public static void updateMailServerInfo(MailServerInfo mailServerInfo) {
        Runnable runnable = () -> {
            userImpl userI = new UserImplem();
            String auxData = mailServerInfo.getMailServer()+";"+
                    mailServerInfo.getPort()+";"+
                    mailServerInfo.getEmailID()+";"+
                    mailServerInfo.getPassword()+";"+
                    mailServerInfo.getSslEnabled()+";"
                    ;

            ux = new User(
                    user.getId_ususer(),
                    user.getEmail(),
                    user.getName(),
                    user.getLast_name(),
                    user.getPass(),
                    user.getStatus(),
                    user.getAdmin(),
                    user.getUrlPhoto(),
                    auxData
            );

            userI.update(ux);
            ControllerComponent.user = ux;
        };
        Thread thread = new Thread(runnable, "UPDATE-USER");
        thread.start();

    }

    public static MailServerInfo loadMailServerInfo() {
        ux = ControllerComponent.user;
        String aux = ux.getDataSetting();
        String[] infoServer = aux.split(";");
        return new MailServerInfo(infoServer[0],Integer.parseInt(infoServer[1]),infoServer[2], infoServer[3], Boolean.valueOf(infoServer[4]));
    }

    public static void insertUserInfo(User user) {
        Runnable runnable = () -> {
            userImpl userI = new UserImplem();
            userI.insert(user);
        };
        new Thread(runnable,"INSERT-NEW-USER").start();

    }
}

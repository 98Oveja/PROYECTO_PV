package Utils;


import Controllers.item.ControllerComponent;
import Models.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    public static boolean SendGMail(String Destino, String Asunto, String Mensaje) {
        Properties propiedades = new Properties();
        User ux = ControllerComponent.user;
        String aux = ux.getDataSetting();
        String[] infoServer = aux.split(";");
        propiedades.put("mail.smtp.host",infoServer[0]);
        propiedades.setProperty("mail.smtp.starttls.enable","true");
        propiedades.setProperty("mail.smtp.port",infoServer[1]);
        propiedades.setProperty("mail.smtp.user",infoServer[2]);
        propiedades.setProperty("mail.smtp.auth",infoServer[4]);

        Runnable runnable = () -> {
            try {
                Session IniciarSesion = Session.getDefaultInstance(propiedades,null);
                BodyPart texto = new MimeBodyPart();
                texto.setText(Mensaje);
                MimeMessage Correo = new MimeMessage(IniciarSesion);

                Correo.setFrom(new InternetAddress(infoServer[2]));
                Correo.addRecipient(Message.RecipientType.TO,new InternetAddress(Destino));
                Correo.setSubject(Asunto);
                Correo.setText(Mensaje);

                Transport Transporte = IniciarSesion.getTransport("smtp");
                Transporte.connect(infoServer[2],infoServer[3]);
                Transporte.sendMessage(Correo,Correo.getRecipients(Message.RecipientType.TO));
                Transporte.close();
            }catch (Exception e){
                System.out.println("error al enviar correo");
            }
        };
        Thread thread = new Thread(runnable, "SEND-EMAIL");
        thread.start();
        return true;
    }
}

package utils;


import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public static boolean SendGMail(String Remitente, String Password, String Destino, String Asunto, String Mensaje) {
        Properties propiedades = new Properties();
        //PROPIEDADES ANTE DE CONECTARSE A GMAIL
        propiedades.put("mail.smtp.host","smtp.gmail.com");
        propiedades.setProperty("mail.smtp.starttls.enable","true");
        propiedades.setProperty("mail.smtp.port","587");
        propiedades.setProperty("mail.smtp.user",Remitente);
        propiedades.setProperty("mail.smtp.auth","true");
        //Instanciando el mensaje
        try{
            //INICIAMOS LA SESION PARA ENLACE A GMAIL
            Session IniciarSesion = Session.getDefaultInstance(propiedades,null);
            BodyPart texto = new MimeBodyPart();
            texto.setText(Mensaje);
            MimeMessage Correo = new MimeMessage(IniciarSesion);
            //====================
            Correo.setFrom(new InternetAddress(Remitente));
            Correo.addRecipient(Message.RecipientType.TO,new InternetAddress(Destino));
            Correo.setSubject(Asunto);
            Correo.setText(Mensaje);
            //variable para ejecutar todo las variables de correo
            Transport Transporte = IniciarSesion.getTransport("smtp");
            Transporte.connect(Remitente,Password);
            Transporte.sendMessage(Correo,Correo.getRecipients(Message.RecipientType.TO));
            Transporte.close();
            //Notificacion de exito para cuando se envio el correo
            System.out.println("exito");
            return true;
        }catch(MessagingException ex){
            System.out.println("sadasds");
            return false;

        }
    }
}

package EshopDash;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SendMail {

    public static void send(String to, String sub, String msg, final String user, final String pass) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);
            System.err.println("mail sended");
            //JOptionPane.showMessageDialog(null,"Email sended!");

        } catch (MessagingException e) {
            //JOptionPane.showMessageDialog(null,"Something happened!");
            System.err.println("mail sended");

            throw new RuntimeException(e);
        }

    }
}

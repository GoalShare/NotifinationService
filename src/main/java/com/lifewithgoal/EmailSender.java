package com.lifewithgoal;

import com.lifewithgoal.entity.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by darshanas on 8/6/2017.
 */
public class EmailSender {

    private final static Session session;
    private final static String frm = "no-reply@lifewithgoals.com";
    private final static String pass = "123456789";

    static {

        String host = "smtp.zoho.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.pop3.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.startssl.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(frm, pass);
                    }
                });
    }

    public static boolean sendEmail(Email email){



        try{
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(frm));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email.getTo()));

            // Set Subject: header field
            message.setSubject(email.getSubject());

            // Now set the actual message
            message.setContent(email.getBody(), "text/html; charset=utf-8");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return true;

    }

}

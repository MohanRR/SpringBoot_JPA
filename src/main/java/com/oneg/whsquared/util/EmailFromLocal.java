package com.oneg.whsquared.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailFromLocal {

    // Assuming you are sending email through relay.jangosmtp.net
    final static String host = "smtp.office365.com";
    // Sender's email ID needs to be mentioned
    final static String  from = "admin@theinstapo.com";
    final static String username = "admin@theinstapo.com";// change accordingly
    final static String password = "admin@2015";// change accordingly
    static Properties props = getProps();

    public static Properties getProps() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        return props;
    }

    public static void sendEmail(String content, String to) {

        // Get the Session object.
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Welcome to WhatsHappenins!");

            message.setContent(content, "text/html");
            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static void sendEmail(String content, String subject, String to) {

        // Get the Session object.
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subject);

            message.setContent(content, "text/html");
            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void sendEmail(String to, String subject, Map<String, String> replaceMap, String teplateFilePath) {
        String content = returnFileAsString(teplateFilePath);
        content = searchAndReplace(replaceMap, content);
        sendEmail(content, subject, to);
    }

    public String searchAndReplace(Map<String, String> replaceMap, String original) {
        Set<String> keySet;
        String workingStr = original;

        keySet = replaceMap.keySet();

        for (String key : keySet) {
            workingStr = workingStr.replaceAll("#\\@\\{" + key + "}", replaceMap.get(key));
        }

        return workingStr;
    }

    public String returnResourceAsString(URL url) {
        try {
            return returnStreamAsString(url.openStream());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String returnStreamAsString(InputStream fis) {
        ByteArrayOutputStream baos;
        byte buf[] = new byte[2048];
        int red;
        String ret;

        try {
            baos = new ByteArrayOutputStream();

            while ((red = fis.read(buf)) != -1) {
                baos.write(buf, 0, red);
            }

            ret = new String(baos.toByteArray());
            baos.close();

            return ret;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return "";
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String returnFileAsString(String filename) {
        try {
            InputStream inputStream = EmailFromLocal.class.getClassLoader().getResourceAsStream(filename);
            return returnStreamAsString(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

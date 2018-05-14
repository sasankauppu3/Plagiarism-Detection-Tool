package edu.northeastern.cs5500.team111.util;
import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
/**
 * Created by lixin on 4/5/18.
 */
public class EmailSystem {
    // Sender's username
    private String USER_NAME = "msdteam111@gmail.com";

    // Sender's password
    private String PASS = "msd_team111";

    // System properties
    private Properties props;

    // System session
    private Session session;

    /**
     * Constructor to set up everything
     * @throws MessagingException
     */
    public EmailSystem() throws MessagingException {
        this.props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        this.session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER_NAME, PASS);
                    }
                });
    }

    /**
     * Send emails from the sender in this class
     * @param emails list of emails to be sent
     * @return boolean return whether they are successfully sent.
     */
    public boolean sendMails(List<EmailObject> emails) {

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER_NAME));

            for(EmailObject email: emails) {
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(email.getReceiver()));
                message.setSubject(email.getSubject());
                message.setText(email.getText());

                Transport.send(message);
            }
            return true;

        } catch (MessagingException e) {
            LoggerFactory.getLogger(PlagiarismdetectorApplication.class).error(e.getMessage());
            return false;
        }
    }
}

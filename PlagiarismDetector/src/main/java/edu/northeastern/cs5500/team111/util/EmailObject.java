package edu.northeastern.cs5500.team111.util;


/**
 * Created by lixin on 4/5/18.
 */
public class EmailObject {
    // Receiver of this email
    private String receiver;

    // Subject of this email
    private String subject;

    // Content of this email
    private String text;

    /**
     * Constructor for the email
     * @param receiver
     * @param subject
     * @param text
     */
    public EmailObject(String receiver, String subject,String text) {
        this.receiver = receiver;
        this.subject = subject;
        this.text = text;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

}

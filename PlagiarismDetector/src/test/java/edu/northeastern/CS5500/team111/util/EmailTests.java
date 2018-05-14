package edu.northeastern.CS5500.team111.util;

import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import edu.northeastern.cs5500.team111.util.EmailObject;
import edu.northeastern.cs5500.team111.util.EmailSystem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *  Test case for Emails, to ensure emails are properly sent to the relevant parties
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class EmailTests {

    @Test
    public void sendSingleEmail() throws IOException, MessagingException {
        List<EmailObject> emails = new ArrayList<EmailObject>();
        EmailSystem sys = new EmailSystem();
        emails.add(new EmailObject("cug.ie.lixin@gmail.com", "test1", "content1"));
        assertEquals(sys.sendMails(emails),true);
    }
    @Test
    public void sendMultiEmail() throws IOException, MessagingException {
        List<EmailObject> emails = new ArrayList<EmailObject>();
        EmailSystem sys = new EmailSystem();
        emails.add(new EmailObject("cug.ie.lixin@gmail.com", "test1", "content1"));
        emails.add(new EmailObject("li.xin125@husky.neu.edu", "test2", "content2"));
        assertEquals(sys.sendMails(emails),true);
    }
}

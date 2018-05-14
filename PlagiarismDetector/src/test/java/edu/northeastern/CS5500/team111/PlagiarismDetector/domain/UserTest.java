package edu.northeastern.CS5500.team111.PlagiarismDetector.domain;

import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.User;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.SerializationUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  Test case for User Class and ensure that data is being stored in acceptable model
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User("Amritansh", "Tripathi", "tripathi.a@husky", "dasd", UserType.INSTRUCTOR);
    }

    @Test
    public void beanIsSerializable() {
        final byte[] serializedMyBean = SerializationUtils.serialize(user);
        final User deserializedMyBean = (User) SerializationUtils.deserialize(serializedMyBean);

        assertEquals(user.getEmail(), deserializedMyBean.getEmail());
        assertEquals(user.getPassword(), deserializedMyBean.getPassword());
        assertEquals(user.getFirstName(), deserializedMyBean.getFirstName());
        assertEquals(user.getLastName(), deserializedMyBean.getLastName());

    }

    @Test
    public void testFirstNameSetter() {
        String firstName = "test";
        user.setFirstName(firstName);
        assertTrue(user.getFirstName().equals(firstName));
    }

    @Test
    public void testLastNameSetter() {
        String lastname = "test";
        user.setLastName(lastname);
        assertTrue(user.getLastName().equals(lastname));
    }

    @Test
    public void testUserType() {
        user.setUserType(UserType.ADMIN);
        assertTrue(user.getUserType().equals(UserType.ADMIN));
    }

}
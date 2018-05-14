package edu.northeastern.CS5500.team111.PlagiarismDetector.validation;


import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.controllers.UserController;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.User;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.UserType;
import edu.northeastern.cs5500.team111.plagiarismdetector.validation.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class UserValidatorTest {

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserController userController;

    @Test
    public void validateExistingUser() throws Exception {
        User mockUser = new User("Amritcdsansh", "Tdvripathi", "gmadvil@gmail.com", "1234567", UserType.INSTRUCTOR);
        Errors errors = new BeanPropertyBindingResult(mockUser, "objectName");

        userController.createUser(mockUser,(BindingResult) errors);
        userValidator.validate(mockUser, errors);
        assertTrue(errors.hasErrors());
    }

}
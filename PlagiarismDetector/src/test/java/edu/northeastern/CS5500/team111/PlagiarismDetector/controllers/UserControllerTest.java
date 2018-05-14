package edu.northeastern.CS5500.team111.PlagiarismDetector.controllers;

import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.controllers.UserController;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.User;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.UserType;
import edu.northeastern.cs5500.team111.plagiarismdetector.repo.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *  Test case for UserController Class and ensure that user login/register and delete functionality are proper
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void createUserWhenAlreadyNotCreated() {
        User mockUser = new User("Amritansh", "Tripathi", "gmail@gmail.com", "1234567", UserType.INSTRUCTOR);
        Errors errors = new BeanPropertyBindingResult(mockUser, "objectName");
        assertTrue(userController.createUser(mockUser, (BindingResult) errors).equals(mockUser));
    }

    @Test(expected = RuntimeException.class)
    public void doNotCreateUserWhenAlreadyNotCreatedAndThrowException() throws Exception {
        User mockUser = new User("Amritansh", "tripathi", "gmail@gmail.com", "1234567", UserType.INSTRUCTOR);
        Errors errors = new BeanPropertyBindingResult(mockUser, "objectName");
        userController.createUser(mockUser, (BindingResult) errors);
        userController.createUser(mockUser, (BindingResult) errors);
    }

    @Test
    public void getUsers() {
        User mockUser = new User("Amrit3ansh", "tripa3thi", "gm3ail@gmail.com", "1234567", UserType.INSTRUCTOR);
        Errors errors = new BeanPropertyBindingResult(mockUser, "objectName");
        userController.createUser(mockUser, (BindingResult) errors);
        assertTrue(userController.getAllUsers().size() > 0);
    }


    @Test
    public void loginUsers() {
        User mockUser = new User("Amri2tansh", "tri2pathi", "gma2il@gmail.com", "1234567", UserType.INSTRUCTOR);
        Errors errors = new BeanPropertyBindingResult(mockUser, "objectName");
        userController.createUser(mockUser, (BindingResult) errors);
        assertTrue(userController.loginUser(mockUser) instanceof User);
    }

    @Test(expected = RuntimeException.class)
    public void whenNoPasswordIsEnteredDuringRegister() {
        User mockUser = new User("Amri2tansh", "tri2pathi", "gma2il@gmail.com", "", UserType.INSTRUCTOR);
        Errors errors = new BeanPropertyBindingResult(mockUser, "objectName");
        userController.createUser(mockUser, (BindingResult) errors);
    }

    @Test
    public void emailShoudlBeCaseInsesitive() {
        User mockCreateUser = new User("Amri2tansh", "tri2pathi", "gmail@dmail.com", "1234567", UserType.INSTRUCTOR);
        Errors errors = new BeanPropertyBindingResult(mockCreateUser, "objectName");
        userController.createUser(mockCreateUser, (BindingResult) errors);
        User mockUser = new User("Amri2tansh", "tri2pathi", "gmaiL@Dmail.com", "1234567", UserType.INSTRUCTOR);
        assertTrue(userController.loginUser(mockUser) instanceof User);
    }

    @Test(expected = RuntimeException.class)
    public void throwExceptionIfEmailIsEmpty() {
        User mockUser = new User("Amri2tansh", "tri2pathi", "", "1234567", UserType.INSTRUCTOR);
        Errors errors = new BeanPropertyBindingResult(mockUser, "objectName");
        userController.createUser(mockUser, (BindingResult) errors);
    }

    @Test
    public void deleteUser(){
        Mockito.doNothing().when(userRepository).delete(1L);
    }
}
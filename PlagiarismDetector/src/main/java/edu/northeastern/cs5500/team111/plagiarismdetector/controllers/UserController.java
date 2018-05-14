package edu.northeastern.cs5500.team111.plagiarismdetector.controllers;

import edu.northeastern.cs5500.team111.plagiarismdetector.domain.User;
import edu.northeastern.cs5500.team111.plagiarismdetector.repo.UserRepository;
import edu.northeastern.cs5500.team111.plagiarismdetector.validation.UserValidator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Rest controller class to handle services related to login and register user
 */
@RestController
public class UserController {

    private UserRepository userRepository;

    private UserValidator userValidator;

    /**
     * @param userRepository Repository for user domain object
     * @param userValidator  UserValidator to validate the user object
     */
    @Autowired
    public UserController(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    /**
     * Method to create a new user if does not exist already
     *
     * @param user          User object to be added received in post body
     * @param bindingResult variable to bind errors.
     * @return User object after it is added in the database
     */
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public User createUser(@RequestBody User user, BindingResult bindingResult) {
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));


        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ForbiddenException();
        }
        return userRepository.save(user);
    }

    /**
     * Method to check login credentials of user
     *
     * @param userReq the User object
     * @return Logged in User object
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User loginUser(@RequestBody User userReq) {
        User curUser = userRepository.findByEmail(userReq.getEmail().toLowerCase());

        if (BCrypt.checkpw(userReq.getPassword(), curUser.getPassword())) {
            return curUser;
        } else {
            return userReq;
        }
    }

    /**
     * Method to check login credentials of user
     *
     * @param userReq the User object
     * @return Logged in User object
     */
    @RequestMapping(path = "/getUser", method = RequestMethod.POST)
    public User getUser(@RequestBody User userReq) {
        User curUser = userRepository.findByEmail(userReq.getEmail());

        if (curUser == null) {
            throw new NotFoundException();
        }

        return curUser;
    }

    /**
     * Method to delete a particular user
     *
     * @param userId the id of the User
     *               (deletes if the user exists)
     */
    @RequestMapping(path = "/users/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String userId) {
        userRepository.delete(Long.parseLong(userId));
    }

    /**
     * @return Returns all the user present in database
     */
    @RequestMapping(path = "getUsers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class NotFoundException extends RuntimeException {
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    private class ForbiddenException extends RuntimeException {
    }
}

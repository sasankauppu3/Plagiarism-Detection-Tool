package edu.northeastern.cs5500.team111.plagiarismdetector.validation;

import edu.northeastern.cs5500.team111.plagiarismdetector.domain.User;
import edu.northeastern.cs5500.team111.plagiarismdetector.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * UserValidator class to handle back end validation
 */
@Component
public class UserValidator implements Validator {

    private UserRepository userRepository;

    private static final String NON_EMPTY = "NotEmpty";

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param aClass any class
     * @return boolean based on suppport
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * Validate function validates the User object
     *
     * @param o      of type User
     * @param errors if any
     */
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", NON_EMPTY);
        if (user.getFirstName().length() < 2 || user.getFirstName().length() > 32) {
            errors.rejectValue("firstName", "Size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", NON_EMPTY);
        if (user.getLastName().length() < 2 || user.getLastName().length() > 32) {
            errors.rejectValue("lastName", "Size");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userType", NON_EMPTY);
        if (user.getUserType() == null || user.getUserType().toString().equalsIgnoreCase("")) {
            errors.rejectValue("userType", "Null");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", NON_EMPTY);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", NON_EMPTY);

    }
}

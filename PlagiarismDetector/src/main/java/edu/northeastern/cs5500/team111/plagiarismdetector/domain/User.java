package edu.northeastern.cs5500.team111.plagiarismdetector.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * User Entity class for ORM, which represents any user operating the system
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;


    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private UserType userType;

    /**
     * Constructor for User Class
     * @param firstName First name of the user
     * @param lastName last name of the user
     * @param email email address of the user
     * @param password password of the user
     * @param userType user type of the user
     */
    public User(String firstName, String lastName, String email, String password, UserType userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Empty constructor for initial DB access
     */

    protected User() {
    }

    /**
     * Getters and Setters
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}

package edu.northeastern.cs5500.team111.plagiarismdetector.repo;


import edu.northeastern.cs5500.team111.plagiarismdetector.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
/**
 * Interface to define repository for user
 */
@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User,Long> {

    /**
     * Method to find user by email
     * @param email the email of the user to find
     * @return the user containing that email
     */
    User findByEmail(String email);

    /**
     * Method to find User by Email and Password
     * @param email the email of the user to find
     * @param password the password of the user to find
     * @return the user with email and password
     */
    User findByEmailAndPassword(String email,String password);
}

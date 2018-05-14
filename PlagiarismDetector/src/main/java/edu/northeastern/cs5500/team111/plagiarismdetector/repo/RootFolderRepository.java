package edu.northeastern.cs5500.team111.plagiarismdetector.repo;

import edu.northeastern.cs5500.team111.plagiarismdetector.domain.RootFolder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Interface to define a repository of RootFolder
 */
@RepositoryRestResource(exported = false)
public interface RootFolderRepository extends CrudRepository<RootFolder,Long> {

    /**
     * Method to find Root folder based on user id
     * @param userId user id of the user in session
     * @return List of root folders which contains the provided userId
     */
    List<RootFolder> getByUserId(Long userId);
}

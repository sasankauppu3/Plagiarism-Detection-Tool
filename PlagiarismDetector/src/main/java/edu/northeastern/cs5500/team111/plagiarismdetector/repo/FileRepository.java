package edu.northeastern.cs5500.team111.plagiarismdetector.repo;

import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Interface to define a repository for a PythonFile
 */
@RepositoryRestResource(exported = false)
public interface FileRepository extends CrudRepository<PythonFile, Long> {

    /**
     *
     * Method to find PythonFiles containing given userId
     * @param userId user id of the user in session
     * @return List of python files containing given userId
     */
    List<PythonFile> findByUserId(long userId);

    /**
     * Method to find Python files containing given rootfolderId
     * @param rootFolderId rootFolder Id for which files are required.
     * @return List of python files containing the provided rootFolderId
     */
    List<PythonFile> findByRootFolderId(long rootFolderId);

    /**
     * Method to delete pythonFile containing given rootFolderID
     * @param rootFolderID rootFoldId for which files are to be deleted
     */
    void deleteByRootFolderId(long rootFolderID);
}

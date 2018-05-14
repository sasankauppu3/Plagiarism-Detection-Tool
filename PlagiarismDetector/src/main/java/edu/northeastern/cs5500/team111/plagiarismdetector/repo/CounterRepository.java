package edu.northeastern.cs5500.team111.plagiarismdetector.repo;

import edu.northeastern.cs5500.team111.plagiarismdetector.domain.Counter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lixin on 4/6/18.
 */
public interface CounterRepository extends CrudRepository<Counter,Long> {

    /**
     * Find a list of counter records by searching folder id
     * @param rootFolderId
     * @return list of counters
     */
    List<Counter> findByRootFolderId(Long rootFolderId);

    /**
     * Find a list of counter records by searching folder id and algorithmName
     * @param rootFolderId
     * @return list of counters
     */
    List<Counter> findByRootFolderIdAndAlgorithmName(Long rootFolderId, String algorithmName);
}
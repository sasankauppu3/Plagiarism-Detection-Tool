package edu.northeastern.cs5500.team111.plagiarismdetector.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Root folder entity for ORM, which works as a base structure for all files
 */
@Entity
public class RootFolder implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private List<Long> studentFolderIds;
    private String rootFolderName;
    private Long userId;

    /**
     * @param studentFolderIds List of sub folders ids in this root folder
     * @param rootFolderName name of the root folder
     * @param userId user id of the root folder
     */
    public RootFolder( List<Long> studentFolderIds, String rootFolderName, Long userId) {
        this.studentFolderIds = studentFolderIds;
        this.rootFolderName = rootFolderName;
        this.userId = userId;
    }

    /**
     * default contructor for root folder
     */
    public RootFolder() {
    }

    /**
     * @param rootFolderName name of the root folder
     * @param userId user id of the root folder
     */
    public RootFolder(String rootFolderName, Long userId) {
        this.rootFolderName = rootFolderName;
        this.userId = userId;
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

    public List<Long> getStudentFolderIds() {
        return studentFolderIds;
    }


    public String getRootFolderName() {
        return rootFolderName;
    }

    public void setRootFolderName(String rootFolderName) {
        this.rootFolderName = rootFolderName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

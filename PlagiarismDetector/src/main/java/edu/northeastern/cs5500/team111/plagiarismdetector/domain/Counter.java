package edu.northeastern.cs5500.team111.plagiarismdetector.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;

/**
 * File entity for ORM
 */
@Entity
public class Counter implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    // Folder id this counter related to
    private Long rootFolderId;

    // Algorithm name this counter related to
    private String algorithmName;

    // increase when the algorithm is called
    private int count;

    /**
     * Constructor
     * @param rootFolderId a particular root folder
     * @param algorithmName algorithm performed on that root folder
     * @param count times the algorithm was performed
     */
    public Counter(Long rootFolderId, String algorithmName, int count) {
        this.rootFolderId = rootFolderId;
        this.algorithmName = algorithmName;
        this.count = count;
    }
    /**
     * Default constructor
     */
    public Counter() {
    }

    /**
     * getters & setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRootFolderId() {
        return rootFolderId;
    }

    public void setRootFolderId(Long rootFolderId) {
        this.rootFolderId = rootFolderId;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

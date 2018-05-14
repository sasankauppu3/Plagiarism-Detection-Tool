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
public class PythonFile implements Serializable {

    @Id
    @GeneratedValue
    private Long id;


    private String filename;

    @Lob
    private byte[] fileContent;

    private Long userId;

    private Long rootFolderId;

    private String studentName;

    /**
     * @param filename    name of the file
     * @param fileContent content of the file
     * @param userId      user if of the file
     * @param  studentName name of the student to which file belongs
     */
    public PythonFile(String filename, byte[] fileContent, Long userId, String studentName) {
        this.filename = filename;
        this.fileContent = fileContent;
        this.userId = userId;
        this.studentName = studentName;
    }

    /**
     * @param filename    name of the file
     * @param fileContent content of the file
     * @param userId      user if of the file
     */
    public PythonFile(String filename, byte[] fileContent, Long userId) {
        this.filename = filename;
        this.fileContent = fileContent;
        this.userId = userId;
    }

    /**
     * Default constructor
     */
    public PythonFile() {
    }

    /**
     * @param filename    name of the file
     * @param fileContent content of the file
     * @param userId      user if of the file
     * @param  studentName name of the student to which file belongs
     */
    public PythonFile(String filename, byte[] fileContent, Long userId, Long rootFolderId, String studentName) {
        this.filename = filename;
        this.fileContent = fileContent;
        this.userId = userId;
        this.rootFolderId = rootFolderId;
        this.studentName = studentName;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRootFolderId() {
        return rootFolderId;
    }

    public void setRootFolderId(Long rootFolderId) {
        this.rootFolderId = rootFolderId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}

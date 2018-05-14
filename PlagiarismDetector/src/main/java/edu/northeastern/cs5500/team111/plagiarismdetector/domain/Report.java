package edu.northeastern.cs5500.team111.plagiarismdetector.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Report model to be returned to show comparison result
 */
public class Report implements Serializable{

    private String file1;
    private String file2;
    private Double similarity;
    private String file1Content;
    private String file2Content;

    /**
     * @param file1 file 1 to be compared
     * @param file2 file 2 to be compared
     * @param similarity the similarity of the two files
     */
    public Report(String file1, String file2, Double similarity) {
        this.file1 = file1;
        this.file2 = file2;
        this.similarity = similarity;
    }


    /**
     * Getters and Setters
     */

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public String getFile2() {
        return file2;
    }

    public void setFile2(String file2) {
        this.file2 = file2;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    public String getFile1Content() {
        return file1Content;
    }

    public void setFile1Content(String file1Content) {
        this.file1Content = file1Content;
    }

    public String getFile2Content() {
        return file2Content;
    }

    public void setFile2Content(String file2Content) {
        this.file2Content = file2Content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return Objects.equals(getFile1(), report.getFile1()) &&
                Objects.equals(getFile2(), report.getFile2()) &&
                Objects.equals(getSimilarity(), report.getSimilarity());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFile1(), getFile2(), getSimilarity());
    }
}

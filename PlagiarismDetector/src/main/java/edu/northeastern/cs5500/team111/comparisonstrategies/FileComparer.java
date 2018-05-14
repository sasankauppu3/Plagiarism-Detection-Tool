package edu.northeastern.cs5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;

/**
 * Interface to give a contract for comparison algorithms
 */
public interface FileComparer {

    /**
     * Method to compare two python files
     * @param f1 first file to be compared of type PythonFile
     * @param f2 second file to be compared of type PythonFile
     * @return a similarity score
     */
    double compareTwoFiles(PythonFile f1, PythonFile f2);

    /**
     * Method to compare two python files and generate Line By Line
     * Comparision
     * @param f1 first file to be compared of type PythonFile
     * @param f2 second file to be compared of type PythonFile
     * @return a Comparision Report containing line by line comparision
     */
    ComparisionReport compareTwoFilesAdvanced(PythonFile f1, PythonFile f2);
}

package edu.northeastern.cs5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Comparision Report Class, used to generate
 * line by line comparision report
 */

public class ComparisionReport {

    double similarity;

    private HashMap<Integer,List<Pair>> linesMap1;
    private HashMap<Integer,List<Pair>> linesMap2;

    private PythonFile file1;
    private PythonFile file2;

    /**
     * Class constructor, initializing parameters
     */
    public ComparisionReport()
    {
        similarity = 0.0;
        linesMap1  = new HashMap<>();
        linesMap2  = new HashMap<>();
    }

    /**
     * Add line matches to first file's Hash Map
     * @param lineMatches list of lineMatches to be added to the first Line map
     */
    public void addToLinesMap1(List<LineMatch> lineMatches) {
        addToLinesMap(lineMatches,linesMap1);
    }

    /**
     * Add line matches to second file's Hash Map
     * @param lineMatches list of lineMatches to be added to the second Line map
     */
    public void addToLinesMap2(List<LineMatch> lineMatches) {
        addToLinesMap(lineMatches, linesMap2);
    }

    /**
     * Add line numbers to Hash Map
     * @param lineMatches List of LineMatches to be added
     * @param lineMatches1 Map for line and pair for indices (starting and ending)
     */
    private void addToLinesMap(List<LineMatch> lineMatches, Map<Integer,List<Pair>> lineMatches1)
    {
        for (LineMatch match: lineMatches) {
            // Include in the map if already exists otherwise create a new key,value set
            if(lineMatches1.containsKey(match.line)) {
                lineMatches1.get(match.line).add(new Pair(match.startIndex,match.stopIndex));
            } else {
                lineMatches1.put(match.line,new ArrayList<>());
                lineMatches1.get(match.line).add(new Pair(match.startIndex,match.stopIndex));
            }
        }
    }

    /**
     * Getters $ Setters
     */

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public Map<Integer, List<Pair>> getLinesMap1() {
        return linesMap1;
    }

    public Map<Integer, List<Pair>> getLinesMap2() {
        return linesMap2;
    }

    public PythonFile getFile2() {
        return file2;
    }

    public void setFile2(PythonFile file2) {
        this.file2 = file2;
    }

    public PythonFile getFile1() {
        return file1;
    }

    public void setFile1(PythonFile file1) {
        this.file1 = file1;
    }
}

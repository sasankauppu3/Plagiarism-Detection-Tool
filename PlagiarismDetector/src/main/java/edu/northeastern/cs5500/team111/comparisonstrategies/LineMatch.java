package edu.northeastern.cs5500.team111.comparisonstrategies;

/**
 * Line Match Object stores the line number
 * and start and stop index of the that line
 */
public class LineMatch {

    int line;
    int startIndex;
    int stopIndex;

    /**
     * Getters $ Setters
     */
    public void setLine(int line) {
        this.line = line;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setStopIndex(int stopIndex) {
        this.stopIndex = stopIndex;
    }
}
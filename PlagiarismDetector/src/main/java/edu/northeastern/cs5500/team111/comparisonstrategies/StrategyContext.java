package edu.northeastern.cs5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;

/**
 * Context to store the strategy to be used
 */
public class StrategyContext {

    private FileComparer fileComparer;

    /**
     * @param strategy Type of strategy to be used
     */
    public StrategyContext(ComparisonAlgorithm strategy) {

        switch (strategy) {
            case LCS:
                this.fileComparer = new LCSComparer();
                break;
            case JACCARD:
                this.fileComparer = new JaccardComparer();
                break;
            case EDITDISTANCE:
                this.fileComparer = new TokenEditDistComparer();
                break;
            case LCS_WITH_AST:
                this.fileComparer = new LCSComparerAdvanced();
                break;
            case WEIGHTED_DISTRIBUTION:
                this.fileComparer = new WeightedDistribution();
                break;
            case MACHINE_LEARNING:
                this.fileComparer = new MachineLearningModel();
                break;
        }
    }

    /**
     * Method to compare and get a line by line report
     *
     * @param file1 first file to be compared of type pythonFile
     * @param file2 second file to be compared of type pythonFile
     * @return a Comparision report with detailed results
     */
    public ComparisionReport getComparisonReport(PythonFile file1, PythonFile file2) {
        return fileComparer.compareTwoFilesAdvanced(file1, file2);
    }

    /**
     * Getter for Filecomparer variable
     * @return Filecomparer initialised for this object
     */
    public FileComparer getFileComparer() {
        return fileComparer;
    }
}

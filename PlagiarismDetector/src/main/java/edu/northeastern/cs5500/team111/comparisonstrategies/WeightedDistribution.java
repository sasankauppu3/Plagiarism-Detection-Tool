package edu.northeastern.cs5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;

/**
 * Weighted Distribution class takes all current algorithms and creates
 * a new weighted score based on their assigned weights
 */
public class WeightedDistribution implements FileComparer {

    private LCSComparer lcsComparer = new LCSComparer();
    private JaccardComparer jaccardComparer = new JaccardComparer();
    private TokenEditDistComparer tokenEditDistComparer = new TokenEditDistComparer();
    private LCSComparerAdvanced lcsComparerAdvanced = new LCSComparerAdvanced();

    /**
     * Method to compare two python files
     * @param f1 first file to be compared of type PythonFile
     * @param f2 second file to be compared of type PythonFile
     * @return a similarity score
     */
    @Override
    public double compareTwoFiles(PythonFile f1, PythonFile f2) {

        double lcsResult = lcsComparer.compareTwoFiles(f1,f2);
        double jaccardResult = jaccardComparer.compareTwoFiles(f1,f2);
        double tokenEDCResult = tokenEditDistComparer.compareTwoFiles(f1,f2);
        double lcsAdvResult = lcsComparerAdvanced.compareTwoFiles(f1,f2);

        int totalWeight = ComparisonAlgorithm.LCS.getWeight()+
                ComparisonAlgorithm.EDITDISTANCE.getWeight() +
                ComparisonAlgorithm.LCS_WITH_AST.getWeight() +
                ComparisonAlgorithm.JACCARD.getWeight();

        return  (ComparisonAlgorithm.LCS.getWeight() * lcsResult +
                ComparisonAlgorithm.EDITDISTANCE.getWeight() * jaccardResult +
                ComparisonAlgorithm.LCS_WITH_AST.getWeight() * tokenEDCResult +
                ComparisonAlgorithm.JACCARD.getWeight() * lcsAdvResult) / totalWeight;
    }

    /**
     * Method to compare two python files and generate Line By Line
     * Comparision
     * @param f1 first file to be compared of type PythonFile
     * @param f2 second file to be compared of type PythonFile
     * @return a Comparision Report containing line by line comparision
     */
    @Override
    public ComparisionReport compareTwoFilesAdvanced(PythonFile f1, PythonFile f2) {
        ComparisionReport cr = new ComparisionReport();
        cr.setSimilarity(compareTwoFiles(f1,f2));
        cr.setFile1(f1);
        cr.setFile2(f2);
        return cr;
    }
}

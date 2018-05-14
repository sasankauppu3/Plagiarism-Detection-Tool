package edu.northeastern.cs5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of filecomparer class to compute the similarity of two files using Longest common sub sequence
 * algorithm
 */
public class LCSComparer implements FileComparer {

    /**
     * Get the LCS rate of two strings
     *
     * @param text1 a String
     * @param text2 a String
     * @return rate of LCS referring the plagiarism rate
     */
    private double longestCommonSubsequence(String text1, String text2) {
        List<LCSObject> wordList1 = LCSUtils.extractWords(text1);
        List<LCSObject> wordList2 = LCSUtils.extractWords(text2);

        LCSObject[] wordArray1 = wordList1.toArray(new LCSObject[wordList1.size()]);
        LCSObject[] wordArray2 = wordList2.toArray(new LCSObject[wordList2.size()]);
        int[][] lcxMatrix = LCSUtils.lcsMatrixGenerator(wordArray1, wordArray2);
        return (double) lcxMatrix[0][0] / (double) Math.max(wordList1.size(),
                wordList2.size());
    }

    /**
     * Method to compare two python files
     *
     * @param f1 first file to be compared of type PythonFile
     * @param f2 second file to be compared of type PythonFile
     * @return a similarity score
     */
    @Override
    public double compareTwoFiles(PythonFile f1, PythonFile f2) {
        String content1 = new String(f1.getFileContent());
        String content2 = new String(f2.getFileContent());
        return longestCommonSubsequence(content1, content2) * 100;
    }


    /**
     * lcsScoreAdvanced method to compare two python files
     * and generate a comparision report
     *
     * @param text1 first file to be compared converted to String
     * @param text2 second file to be compared converted to String
     * @return a comparision Report
     */
    private ComparisionReport lcsScoreAdvanced(String text1, String text2) {
        List<LCSObject> wordList1 = LCSUtils.extractWords(text1);
        List<LCSObject> wordList2 = LCSUtils.extractWords(text2);

        LCSObject[] wordArray1 = wordList1.toArray(new LCSObject[wordList1.size()]);
        LCSObject[] wordArray2 = wordList1.toArray(new LCSObject[wordList2.size()]);

        int[][] lcxMatrix = LCSUtils.lcsMatrixGenerator(wordArray1, wordArray2);
        ComparisionReport cr = new ComparisionReport();
        cr.similarity = (double) lcxMatrix[0][0] / (double) Math.max(wordList1.size(),
                wordList2.size()) * 100;
        List<ArrayList<Object>> intersection = LCSUtils.getMatchResult(wordArray1, wordArray2, lcxMatrix);

        cr.addToLinesMap1(LCSUtils.lcsObjectTransfer(intersection.get(0)));
        cr.addToLinesMap2(LCSUtils.lcsObjectTransfer(intersection.get(1)));

        return cr;
    }

    /**
     * compareTwoFilesAdvanced method to compare two python files
     * and generate a comparision report
     *
     * @param f1 first file to be compared of type PythonFile
     * @param f2 second file to be compared of type PythonFile
     * @return a comparision Report
     */
    @Override
    public ComparisionReport compareTwoFilesAdvanced(PythonFile f1, PythonFile f2) {

        String content1 = new String(f1.getFileContent());
        String content2 = new String(f2.getFileContent());
        ComparisionReport comparisionReport = lcsScoreAdvanced(content1, content2);
        comparisionReport.setFile1(f1);
        comparisionReport.setFile2(f2);

        return comparisionReport;

    }
}


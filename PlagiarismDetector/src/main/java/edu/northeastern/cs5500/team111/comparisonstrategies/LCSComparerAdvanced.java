package edu.northeastern.cs5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.astgen.Ast;
import edu.northeastern.cs5500.team111.astgen.AstWalk;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of filecomparer class to compute the similarity of two files using Longest common sub sequence
 * algorithm by comparing each AST node using this algorithm
 * Created by lixin on 3/21/18.
 */
public class LCSComparerAdvanced implements FileComparer {

    /**
     * Get the LCS rate of two arrays of objects
     *
     * @param wordList1 a list of objects
     * @param wordList2 a list of objects
     * @return rate of LCS referring the plagiarism rate
     */
    private double longestCommonSubsequence(Object[] wordList1, Object[] wordList2) {
        int[][] lcxMatrix = LCSUtils.lcsMatrixGenerator(wordList1, wordList2);
        return (double) lcxMatrix[0][0] / (double) Math.max(wordList1.length, wordList2.length);
    }


    /**
     * Getting the pre-order of a ast tree
     *
     * @param payloads return pre-order results
     * @param walk     ast tree
     */
    private void preOrder(List<Object> payloads, AstWalk walk) {
        if (walk.getChildren().isEmpty()) {
            return;
        }
        payloads.add(walk.getPayload());
        for (AstWalk a : walk.getChildren()) {
            preOrder(payloads, a);
        }
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
        Ast ast1 = new Ast(f1);
        Ast ast2 = new Ast(f2);
        AstWalk walk1 = ast1.getAstWalk();
        AstWalk walk2 = ast2.getAstWalk();
        List<Object> payloads1 = new ArrayList<>();
        List<Object> payloads2 = new ArrayList<>();
        preOrder(payloads1, walk1);
        preOrder(payloads2, walk2);
        Object[] ns1 = payloads1.toArray();
        Object[] ns2 = payloads2.toArray();
        return longestCommonSubsequence(ns1, ns2) * 100;
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

        Ast ast1 = new Ast(f1);
        Ast ast2 = new Ast(f2);
        AstWalk walk1 = ast1.getAstWalk();
        AstWalk walk2 = ast2.getAstWalk();
        List<Object> payloads1 = new ArrayList<>();
        List<Object> payloads2 = new ArrayList<>();
        preOrder(payloads1, walk1);
        preOrder(payloads2, walk2);
        Object[] tokens1 = payloads1.toArray();
        Object[] tokens2 = payloads1.toArray();
        int[][] lcxMatrix = LCSUtils.lcsMatrixGenerator(tokens1, tokens2);
        ComparisionReport cr = new ComparisionReport();
        cr.similarity = (double) lcxMatrix[0][0] / (double) Math.max(tokens1.length,
                tokens2.length) * 100;
        List<ArrayList<Object>> intersection = LCSUtils.getMatchResult(tokens1, tokens2, lcxMatrix);

        cr.addToLinesMap1(LCSUtils.tokenObjectTransfer(intersection.get(0)));
        cr.addToLinesMap2(LCSUtils.tokenObjectTransfer(intersection.get(1)));
        cr.setFile1(f1);
        cr.setFile2(f2);

        return cr;

    }

}


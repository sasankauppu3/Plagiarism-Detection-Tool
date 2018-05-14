package edu.northeastern.cs5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.astgen.Ast;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of FileComparer class to compute the similarity using jaccard algorithm.
 */
public class JaccardComparer implements FileComparer {

    /**
     * Function to Calculate Jaccard score between two AST objects
     * @param ast1 An ast object
     * @param ast2 An ast object
     * @return the percentage match between two AST's
     */
    private double jaccardScore(Ast ast1, Ast ast2) {
        Set<Integer> setA = new HashSet<>(ast1.getHashcodesList());
        Set<Integer> setB = new HashSet<>(ast2.getHashcodesList());

        Set<Integer> intersection = setA.stream()
                .filter(setB::contains)
                .collect(Collectors.toSet());

        // Calculate the union of the two sets
        Set<Integer> union = Stream.concat(setA.stream(), setB.stream())
                .distinct().sorted()
                .collect(Collectors.toSet());


        return ((float)intersection.size() / union.size()) * 100;
    }


    /**
     * jaccardScoreAdvanced is a function to Calculate Jaccard score
     * & give a line by line comparision report between two AST objects
     * @param ast1 An ast object
     * @param ast2 An ast object
     * @return the Comparision Report for two AST's
     */
    private ComparisionReport jaccardScoreAdvanced(Ast ast1, Ast ast2) {
        Set<Integer> setA = new HashSet<>(ast1.getHashcodesList());
        Set<Integer> setB = new HashSet<>(ast2.getHashcodesList());

        Map<Integer, List<LineMatch>> lineMapA = ast1.getHashcodesLineMatch();
        Map<Integer, List<LineMatch>> lineMapB = ast2.getHashcodesLineMatch();

        Set<Integer> intersection = setA.stream()
                .filter(setB::contains)
                .collect(Collectors.toSet());

        // Calculate the union of the two sets
        Set<Integer> union = Stream.concat(setA.stream(), setB.stream())
                .distinct().sorted()
                .collect(Collectors.toSet());

        ComparisionReport cr = new ComparisionReport();
        cr.setSimilarity(1.0 * intersection.size() / union.size() * 100);

        for (Integer id : intersection) {
            cr.addToLinesMap1(lineMapA.get(id));
            cr.addToLinesMap2(lineMapB.get(id));
        }
        return cr;
    }

    /** compareTwoFiles is a method to compare two python files
     * @param f1 first file to be compared of type PythonFile
     * @param f2 second file to be compared of type PythonFile
     * @return a similarity score
     */
    @Override
    public double compareTwoFiles(PythonFile f1, PythonFile f2) {
        Ast ast1 = new Ast(f1);
        Ast ast2 = new Ast(f2);

        return jaccardScore(ast1, ast2);
    }

    /** compareTwoFilesAdvanced method to compare two python files
     * and generate a comparision report
     * @param f1 first file to be compared of type PythonFile
     * @param f2 second file to be compared of type PythonFile
     * @return a comparision Report
     */
    @Override
    public ComparisionReport compareTwoFilesAdvanced(PythonFile f1, PythonFile f2) {
        Ast ast1 = new Ast(f1);
        Ast ast2 = new Ast(f2);

        ComparisionReport comparisionReport = jaccardScoreAdvanced(ast1, ast2);

        comparisionReport.setFile1(f1);
        comparisionReport.setFile2(f2);

        return comparisionReport;
    }
}

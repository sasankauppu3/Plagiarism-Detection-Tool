package edu.northeastern.cs5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.astgen.Ast;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import edu.northeastern.cs5500.team111.util.AlgoProperties;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of FileComparer class to compute the similarity using Edit distance algorithm.
 * The edit distance is calculated on each AST Node.
 */
public class TokenEditDistComparer implements FileComparer {

    private int nameTokenID = 36;
    private TokenEditDistUtils tokenEditDistUtils = new TokenEditDistUtils();
    private ComparisionReport cr;

    /**
     * Function to calculate two token maps for similarity excluding token of type Name
     *
     * @param tokenMap1 list of tokens for file one
     * @param tokenMap2 list of tokens for file two
     * @return the similarity score between both
     */
    private double calculateOtherTokenSimilarity(Map<Integer, ArrayList<Token>> tokenMap1,
                                                 Map<Integer, ArrayList<Token>> tokenMap2) {
        int otherTokenCount = 0;
        double otherTokenSimilarity = 0.0;

        Set<Integer> tokenSet1 = tokenMap1.keySet();
        Set<Integer> tokenSet2 = tokenMap2.keySet();
        tokenSet1.retainAll(tokenSet2);

        for (int i : tokenSet1) {
            //Ignoring Name Token
            if (i != nameTokenID) {

                double commonTokenCount = (Math.min(tokenMap1.get(i).size(),
                        tokenMap2.get(i).size()) * 2.0);

                double totalTokenCount = (tokenMap1.get(i).size() + tokenMap2.get(i).size());

                otherTokenSimilarity += (commonTokenCount / totalTokenCount);
                otherTokenCount += 1;
            }
        }

        if (otherTokenCount == 0) {
            otherTokenSimilarity = 0;
        } else {
            otherTokenSimilarity = otherTokenSimilarity / otherTokenCount;
        }

        return otherTokenSimilarity;
    }

    /**
     * calculateNameTokenSimilarity uses fuzzy matching between two lists of strings
     * and finds out the least number of edits required to among all the
     * pairs of strings
     * using Hungarian Algorithm and calculates a similarity score accordingly
     *
     * @param tokenMap1 List1 of strings
     * @param tokenMap2 List2 of strings
     * @return the similarity ratio
     */
    private double calculateNameTokenSimilarity(Map<Integer, ArrayList<Token>> tokenMap1,
                                               Map<Integer, ArrayList<Token>> tokenMap2) {

        ArrayList<String> tokenList1 = (ArrayList<String>) tokenEditDistUtils.removeTokenListDuplicates(tokenMap1.get(nameTokenID));
        ArrayList<String> tokenList2 = (ArrayList<String>) tokenEditDistUtils.removeTokenListDuplicates(tokenMap2.get(nameTokenID));

        double similarity = 0.0;


        double[][] editDistMatrix = new double[tokenList1.size()][tokenList2.size()];

        for (int i = 0; i < tokenList1.size(); i++) {
            for (int j = 0; j < tokenList2.size(); j++) {
                editDistMatrix[i][j] = tokenEditDistUtils.editDistance(tokenList1.get(i),
                        tokenList2.get(j));
            }
        }

        HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm(editDistMatrix);
        int[] optimal = hungarianAlgorithm.execute();

        for (int i = 0; i < optimal.length; i++) {
            if (optimal[i] != -1) {
                double ed = editDistMatrix[i][optimal[i]];
                double maxLen = Math.max(tokenList1.get(i).length(),
                        tokenList2.get(optimal[i]).length());

                if (ed < 3) {
                    similarity += (maxLen - ed) / maxLen;
                }
            }
        }
        similarity = similarity / Math.max(tokenList1.size(), tokenList2.size());

        return similarity;
    }


    /**

     * calculateNameTokenSimilarity uses fuzzy matching between two lists of strings
     * and finds out the least number of edits required to among all the
     * pairs of strings
     * using Hungarian Algorithm and calculates a similarity score accordingly
     *
     * @param tokenMap1 List1 of strings
     * @param tokenMap2 List2 of strings
     */

    private void calculateNameTokenSimilarityAdvanced(Map<Integer, ArrayList<Token>> tokenMap1,
                                                     Map<Integer, ArrayList<Token>> tokenMap2) {

        ArrayList<String> tokenList1 = (ArrayList<String>) tokenEditDistUtils.removeTokenListDuplicates(tokenMap1.get(nameTokenID));
        ArrayList<String> tokenList2 = (ArrayList<String>) tokenEditDistUtils.removeTokenListDuplicates(tokenMap2.get(nameTokenID));
        Map<String, List<LineMatch>> tokenLineMatch1 = tokenEditDistUtils.getHashCodeLineMatch(tokenMap1.get
                (nameTokenID));
        Map<String, List<LineMatch>> tokenLineMatch2 = tokenEditDistUtils.getHashCodeLineMatch(tokenMap2.get
                (nameTokenID));

        double similarity = 0.0;


        double[][] editDistMatrix = new double[tokenList1.size()][tokenList2.size()];

        for (int i = 0; i < tokenList1.size(); i++) {
            for (int j = 0; j < tokenList2.size(); j++) {
                editDistMatrix[i][j] = tokenEditDistUtils.editDistance(tokenList1.get(i),
                        tokenList2.get(j));
            }
        }

        HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm(editDistMatrix);
        int[] optimal = hungarianAlgorithm.execute();

        for (int i = 0; i < optimal.length; i++) {
            if (optimal[i] != -1) {
                double ed = editDistMatrix[i][optimal[i]];
                double maxLen = Math.max(tokenList1.get(i).length(),
                        tokenList2.get(optimal[i]).length());

                if (ed < 3) {
                    similarity += (maxLen - ed) / maxLen;
                    cr.addToLinesMap1(tokenLineMatch1.get(tokenList1.get(i)));
                    cr.addToLinesMap2(tokenLineMatch2.get(tokenList2.get(optimal[i])));
                }
            }
        }
        similarity = similarity / Math.max(tokenList1.size(), tokenList2.size());

        cr.similarity += AlgoProperties.loadFloat("HUN_CONSTANT") * similarity;

        cr.setSimilarity(cr.similarity*100);
    }



    /**
     * Method to compare two python files
     * @param f1 first file to be compared of type PythonFile
     * @param f2 second file to be compared of type PythonFile
     * @return a similarity score
     */
    @Override
    public double compareTwoFiles(PythonFile f1, PythonFile f2) {
        Ast ast1 = new Ast(f1);
        Ast ast2 = new Ast(f2);

        Map<Integer, ArrayList<Token>> tokenMap1 = ast1.getTokenMap();
        Map<Integer, ArrayList<Token>> tokenMap2 = ast2.getTokenMap();

        double otherTokenSimilarity = calculateOtherTokenSimilarity(tokenMap1, tokenMap2);
        double nameTokenSimilarity = calculateNameTokenSimilarity(tokenMap1, tokenMap2);

        //Heuristic similarity
        return (AlgoProperties.loadFloat("TOKEN_OTHER_CONSTANT") * otherTokenSimilarity + AlgoProperties.loadFloat("TOKEN_NAME_CONSTANT") * nameTokenSimilarity) * 100;
    }

    /**
     * Method to compare and get a line by line report
     *
     * @param f1 first file to be compared of type pythonFile
     * @param f2 second file to be compared of type pythonFile
     * @return a Comparision report with detailed results
     */
    @Override
    public ComparisionReport compareTwoFilesAdvanced(PythonFile f1, PythonFile f2) {
        Ast ast1 = new Ast(f1);
        Ast ast2 = new Ast(f2);

        Map<Integer, ArrayList<Token>> tokenMap1 = ast1.getTokenMap();
        Map<Integer, ArrayList<Token>> tokenMap2 = ast2.getTokenMap();

        cr = new ComparisionReport();

        cr.setFile1(f1);
        cr.setFile2(f2);
        cr.setSimilarity(0.2*calculateOtherTokenSimilarity(tokenMap1, tokenMap2));
        calculateNameTokenSimilarityAdvanced(tokenMap1, tokenMap2);

        return cr;
    }
}

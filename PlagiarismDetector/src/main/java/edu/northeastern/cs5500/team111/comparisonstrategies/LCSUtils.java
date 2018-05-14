package edu.northeastern.cs5500.team111.comparisonstrategies;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Util class for LCSComparer and LCSComparerAdvance
 */
public class LCSUtils {

    /**
     * Class constructor nothing to intialize
     */
    private LCSUtils() {}

    // the delimiters
    private static final Pattern DELIMITERS = Pattern.compile("[,. ()]+");

    /**
     * Getting the dynamic programming matrix for LCS
     * @param wordList1 list of objects
     * @param wordList2 list of objects
     * @return a DP matrix containing the information for LCS
     */
    public static int[][] lcsMatrixGenerator(Object[] wordList1, Object[] wordList2){
        int[][] lcxMatrix = new int[wordList1.length + 1][wordList2.length + 1];

        for (int i = wordList1.length - 1; i >=0; i--) {
            for (int j = wordList2.length - 1; j >= 0; j--) {
                if (wordList1[i].equals(wordList2[j])){
                    lcxMatrix[i][j] = lcxMatrix[i + 1][j + 1] + 1;
                }
                else {
                    lcxMatrix[i][j] = Math.max(lcxMatrix[i + 1][j],lcxMatrix[i][j + 1]);
                }
            }
        }
        return lcxMatrix;
    }

    /**
     * Getting the similar objects from the LCS matrix
     * @param wordList1 list of objects
     * @param wordList2 list of objects
     * @param matrix LCS matrix
     * @return two lists of objects that are the overlap of two lists of objects
     */
    public static List<ArrayList<Object>> getMatchResult(Object[] wordList1, Object[] wordList2, int[][] matrix){
        int i = 0;
        int j = 0;
        ArrayList<Object> lcsResultList1 = new ArrayList<Object>();
        ArrayList<Object> lcsResultList2 = new ArrayList<Object>();
        while (i < wordList1.length && j < wordList2.length) {
            if (wordList1[i].equals(wordList2[j])) {
                lcsResultList1.add(wordList1[j]);
                lcsResultList2.add(wordList2[j]);
                i++;
                j++;
            }
            else if (matrix[i + 1][j] >= matrix[i][j + 1]) {
                i++;
            }
            else {
                j++;
            }
        }
        List<ArrayList<Object>> resPair = new ArrayList<ArrayList<Object>>();
        resPair.add(lcsResultList1);
        resPair.add(lcsResultList2);
        return resPair;
    }

    /**
     * Using regular expression to break down the words from a string of text.
     * @param text text of string to be broken down
     * @return list of String that contains list of words
     */
    public static List<LCSObject> extractWords(String text){
        Matcher matcher = DELIMITERS.matcher(text);
        int start = 0;
        List<LCSObject> words = new ArrayList<LCSObject>();
        while (matcher.find()) {
            words.add(new LCSObject(text.substring(start, matcher.start()),start, matcher.start()));
            start = matcher.end();
        }
        if (text.length() > start) {
            words.add(new LCSObject(text.substring(start, text.length()), start, text.length()));
        }
        return words;
    }

    /**
     * lcsObjectTransfer method Adapter to base the object to LCSObject
     * type and return line matches
     * @param arrayList list of the objects passed
     * @return list of line matches
     */
    public static List<LineMatch> lcsObjectTransfer(List<Object> arrayList){
        List<LineMatch> matches = new ArrayList<>();
        for(int i = 0; i< arrayList.size(); i++){
            LCSObject c = (LCSObject) arrayList.get(i);
            LineMatch lineMatch = new LineMatch();
            lineMatch.setLine(i + 1);
            lineMatch.setStartIndex(c.getStart());
            lineMatch.setStopIndex(c.getEnd());
            matches.add(new LineMatch());
        }
        return matches;

    }

    /**
     * tokenObjectTransfer method Adapter to base the object to
     * tokenObject type and return line matches
     * @param arrayList list of the objects passed
     * @return list of line matches
     */
    public static List<LineMatch> tokenObjectTransfer(List<Object> arrayList){
        List<LineMatch> matches = new ArrayList<>();
        for (Object anArrayList : arrayList) {
            if (anArrayList instanceof Token) {
                Token token = (Token) anArrayList;
                LineMatch lineMatch = new LineMatch();
                lineMatch.setLine(token.getLine());
                lineMatch.setStartIndex(token.getCharPositionInLine());
                lineMatch.setStopIndex(token.getCharPositionInLine() + token.getText().length());
                matches.add(new LineMatch());
            }
        }
        return matches;
    }
}

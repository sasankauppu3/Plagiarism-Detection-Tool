package edu.northeastern.cs5500.team111.comparisonstrategies;

import org.antlr.v4.runtime.Token;

import java.util.*;

/**
 * Util class for TokenEditDistComparer
 */
public class TokenEditDistUtils {

    /**
     * Function to calculate edit distance between two strings
     * @param s1 string 1
     * @param s2 string 2
     * @return integer which represents the edit distance
     */
    public int editDistance(String s1, String s2) {
        int[][] edits = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++)
            edits[i][0] = i;
        for (int j = 1; j <= s2.length(); j++)
            edits[0][j] = j;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int u = (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1);
                edits[i][j] = Math.min(edits[i - 1][j] + 1,
                        Math.min(edits[i][j - 1] + 1, edits[i - 1][j - 1] + u));
            }
        }
        return edits[s1.length()][s2.length()];
    }


    /**
     * getHashCodeLineMatch method makes the map of keyword and line matches
     * based on list of tokens passed
     * @param tokenList list of tokens
     * @return a map of the keyword and line matches
     */
    public Map<String,List<LineMatch>> getHashCodeLineMatch(List<Token> tokenList) {

        Map<String,List<LineMatch>> hashCodeLineMatch = new HashMap<>();

        for (Token token : tokenList) {
            LineMatch temp = new LineMatch();
            temp.setLine(token.getLine());
            temp.setStartIndex(token.getCharPositionInLine());
            temp.setStopIndex(token.getCharPositionInLine()+token.getText().length());

            if (hashCodeLineMatch.containsKey(token.getText())) {
                hashCodeLineMatch.get(token.getText()).add(temp);
            } else {
                hashCodeLineMatch.put(token.getText(), new ArrayList<>());
                hashCodeLineMatch.get(token.getText()).add(temp);
            }
        }

        return hashCodeLineMatch;
    }

    /**
     *  Function to remove duplicate tokens from a list and return the respective strings
     * @param tokenList an arrayList of tokens
     * @return an arrayList of strings
     */
    public List<String> removeTokenListDuplicates(List<Token> tokenList)
    {
        HashSet<String> tokListStr = new HashSet<>();

        for (Token token : tokenList) {
            tokListStr.add(token.getText());
        }

        return new ArrayList<>(tokListStr);
    }
}

package edu.northeastern.cs5500.team111.comparisonstrategies;

/**
 * Data Structure to store a Key Value Pair
 * since there were issues with Java.util library
 */
public class Pair {
    private Integer key;
    private Integer value;

    /**
     * Constructor
     */
     Pair(Integer a, Integer b) {
        key = a;
        value = b;
    }

    /**
     * Getters & Setters
     */
    public Integer getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}

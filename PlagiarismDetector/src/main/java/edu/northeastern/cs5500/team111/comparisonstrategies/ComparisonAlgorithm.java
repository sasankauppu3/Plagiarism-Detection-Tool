package edu.northeastern.cs5500.team111.comparisonstrategies;

/**
 * enum defining different comparison algorithms used
 * with their corresponding weights
 */
public enum ComparisonAlgorithm {

    LCS(1){
        @Override
        public String toString() {
            return "Longest Common Subsequence";
        }
    },
    LCS_WITH_AST(2) {
        @Override
        public String toString() {
            return "Longest Common Subsequence With AST";
        }
    },
    JACCARD(1) {
        @Override
        public String toString() {
            return "Jaccard";
        }
    },
    EDITDISTANCE(10) {
        @Override
        public String toString() {
            return "Edit Distance";
        }
    },

    WEIGHTED_DISTRIBUTION(0) {
        @Override
        public String toString() {
            return "Weighted Distribution";
        }
    },

    MACHINE_LEARNING(0){
        @Override
        public String toString() {
            return "Machine Learning";
        }
    };

    // declaring private variable for getting values
    private int weight;

    // getter method
    public int getWeight() {
        return this.weight;
    }

    // enum constructor - cannot be public or protected
    ComparisonAlgorithm(int weight) {
        this.weight = weight;
    }

}

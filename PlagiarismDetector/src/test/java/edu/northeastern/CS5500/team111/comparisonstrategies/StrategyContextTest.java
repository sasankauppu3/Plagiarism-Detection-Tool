package edu.northeastern.CS5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.comparisonstrategies.StrategyContext;
import edu.northeastern.cs5500.team111.comparisonstrategies.ComparisonAlgorithm;
import edu.northeastern.cs5500.team111.comparisonstrategies.LCSComparer;
import edu.northeastern.cs5500.team111.comparisonstrategies.LCSComparerAdvanced;
import edu.northeastern.cs5500.team111.comparisonstrategies.JaccardComparer;
import edu.northeastern.cs5500.team111.comparisonstrategies.TokenEditDistComparer;
import edu.northeastern.cs5500.team111.comparisonstrategies.MachineLearningModel;
import edu.northeastern.cs5500.team111.comparisonstrategies.WeightedDistribution;
import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  Test case to test proper Strategies are called when requested
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class StrategyContextTest {

    @Test
    public void whenStrategyIsLCS(){
        StrategyContext context = new StrategyContext(ComparisonAlgorithm.LCS);
        Assert.assertTrue(context.getFileComparer() instanceof LCSComparer);
    }

    @Test
    public void whenStrategyIsLCSWithAST(){
        StrategyContext context = new StrategyContext(ComparisonAlgorithm.LCS_WITH_AST);
        Assert.assertTrue(context.getFileComparer() instanceof LCSComparerAdvanced);
    }

    @Test
    public void whenStrategyIsJaccard(){
        StrategyContext context = new StrategyContext(ComparisonAlgorithm.JACCARD);
        Assert.assertTrue(context.getFileComparer() instanceof JaccardComparer);
    }

    @Test
    public void whenStrategyIsEditDistance(){
        StrategyContext context = new StrategyContext(ComparisonAlgorithm.EDITDISTANCE);
        Assert.assertTrue(context.getFileComparer() instanceof TokenEditDistComparer);
    }

    @Test
    public void whenStrategyIsWeightedDistance(){
        StrategyContext context = new StrategyContext(ComparisonAlgorithm.WEIGHTED_DISTRIBUTION);
        Assert.assertTrue(context.getFileComparer() instanceof WeightedDistribution);
    }

    @Test
    public void whenStrategyIsMachineLearning(){
        StrategyContext context = new StrategyContext(ComparisonAlgorithm.MACHINE_LEARNING);
        Assert.assertTrue(context.getFileComparer() instanceof MachineLearningModel);
    }


}
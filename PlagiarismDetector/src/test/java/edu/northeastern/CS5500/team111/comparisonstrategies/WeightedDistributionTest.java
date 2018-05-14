package edu.northeastern.CS5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.comparisonstrategies.WeightedDistribution;
import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;

/**
 *  Test case for WeightedDistribution Class and ensure algorithm is working as expected
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes={PlagiarismdetectorApplication.class})
public class WeightedDistributionTest {
    private WeightedDistribution weightedDistribution = new WeightedDistribution();

    private File f1;
    private File f2;
    private File f3;
    private PythonFile pf1;
    private PythonFile pf2;
    private PythonFile pf3;

    public WeightedDistributionTest() throws IOException{
        f1 = new File("code1.py");
        f2 = new File("code2.py");
        f3 = new File("code3.py");

        pf1 = new PythonFile(f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1");
        pf2 = new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()), 123L, "s2");
        pf3 = new PythonFile(f3.getName(), Files.readAllBytes(f3.toPath()), 123L, "s3");
    }

    @Test
    public void compareTwoSlightlyDifferentFiles() {
        assertEquals(62.8328596275005, weightedDistribution.compareTwoFiles(pf1,pf2),2);
    }


    @Test
    public void compareTwoCompletelyDifferentFiles() throws IOException {
        assertEquals(3.144593872044036, weightedDistribution.compareTwoFiles(pf1,pf3),10);
    }

    @Test
    public void compareTwoSameFiles() throws IOException{
        assertEquals(100.0, weightedDistribution.compareTwoFiles(pf1,pf1),2);
    }
}
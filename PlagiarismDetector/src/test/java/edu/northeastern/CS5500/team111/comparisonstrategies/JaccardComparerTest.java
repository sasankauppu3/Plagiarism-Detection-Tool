package edu.northeastern.CS5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.comparisonstrategies.JaccardComparer;
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
 *  Test case for Jaccard Class and ensure algorithm is working as expected
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class JaccardComparerTest {

    private JaccardComparer jaccardComparer = new JaccardComparer();
    private File f1;
    private File f2;
    private File f3;
    private PythonFile pf1;
    private PythonFile pf2;
    private PythonFile pf3;

    public JaccardComparerTest() throws IOException{

        f1 = new File("code1.py");
        f2 = new File("code2.py");
        f3 = new File("code3.py");

        pf1 = new PythonFile(f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1");
        pf2 = new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()), 123L, "s2");
        pf3 = new PythonFile(f3.getName(), Files.readAllBytes(f3.toPath()), 123L, "s3");
    }


    @Test
    public void compareTwoSlightlyDifferentFiles()  {
        assertEquals(55.5555, jaccardComparer.compareTwoFiles(pf1, pf2), .001);
    }

    @Test
    public void compareTwoCompletelyDifferentFiles() {
        assertEquals(0.9433, jaccardComparer.compareTwoFiles(pf1,pf3), .001);
    }

    @Test
    public void compareTwoSameFiles() {
        assertEquals(100.0, jaccardComparer.compareTwoFiles(pf1,pf1), 0.001);
    }

    @Test
    public void comparisionAdvancedTestTwoSameFiles() {
        assertEquals(100.0, jaccardComparer.compareTwoFilesAdvanced(pf1,pf1).getSimilarity(),0.001);
    }

    @Test
    public void comparisionAdvancedTwoSlightlyDifferentFiles() {
        assertEquals(55.5555, jaccardComparer.compareTwoFilesAdvanced(pf1,pf2).getSimilarity(), .001);
    }

    @Test
    public void comparisionAdvancedTwoCompletelyDifferentFiles() {
        assertEquals(0.9433, jaccardComparer.compareTwoFilesAdvanced(pf1,pf3).getSimilarity(), .001);
    }
}
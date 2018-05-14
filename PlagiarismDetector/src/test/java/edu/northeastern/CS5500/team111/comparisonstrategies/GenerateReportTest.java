package edu.northeastern.CS5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.comparisonstrategies.ComparisionReport;

import edu.northeastern.cs5500.team111.comparisonstrategies.GenerateReport;
import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import org.junit.Test;

import edu.northeastern.cs5500.team111.comparisonstrategies.JaccardComparer;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

/**
 *  Test case for Generate Report class and ensure correct report is generated
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class GenerateReportTest {

    File f1;
    File f2;
    ComparisionReport cr;
    JaccardComparer jaccardComparer;

    public GenerateReportTest(){
        f1 = new File("code1.py");
        f2 = new File("code2.py");
        jaccardComparer = new JaccardComparer();
        try {
            cr =  jaccardComparer.compareTwoFilesAdvanced(new PythonFile
                (f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1"),
                new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()),
                123L, "s2"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getReport() throws Exception {
        GenerateReport gr = new GenerateReport(cr);
        assertEquals(55.55555555555556, gr.getReport().getSimilarity(), .001);
    }


}
package edu.northeastern.CS5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.comparisonstrategies.ComparisionReport;
import edu.northeastern.cs5500.team111.comparisonstrategies.LCSComparerAdvanced;

import edu.northeastern.cs5500.team111.comparisonstrategies.LCSObject;
import edu.northeastern.cs5500.team111.comparisonstrategies.LCSUtils;
import edu.northeastern.cs5500.team111.plagiarismdetector
        .PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *  Test case for LCSComparer Class and ensure algorithm is working as expected
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes={PlagiarismdetectorApplication.class})
public class LCSComparerTest {

    private LCSComparerAdvanced lcsComparer = new LCSComparerAdvanced();

    private File f1;
    private File f2;
    private File f3;
    private PythonFile pf1;
    private PythonFile pf2;
    private PythonFile pf3;

    public LCSComparerTest() throws IOException{

        f1 = new File("code1.py");
        f2 = new File("code2.py");
        f3 = new File("code3.py");

        pf1 = new PythonFile(f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1");
        pf2 = new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()), 123L, "s2");
        pf3 = new PythonFile(f3.getName(), Files.readAllBytes(f3.toPath()), 123L, "s3");
    }

    @Test
    public void compareTwoSlightlyDifferentFiles(){
        assertEquals(99,lcsComparer.compareTwoFiles(pf1,pf2),1);
    }

    @Test
    public void compareTwoCompletelyDifferentFiles(){
        assertEquals(9.503,lcsComparer.compareTwoFiles(pf1,pf3),.001);
    }

    @Test
    public void compareTwoSameFiles(){
        assertEquals(100,lcsComparer.compareTwoFiles(pf1,pf1),0.09);
    }

    @Test
    public void comparisionAdvancedTest(){
        assertEquals(100, lcsComparer.compareTwoFilesAdvanced(pf1,pf1).getSimilarity(),5);
    }

    @Test
    public void objectEqualTest() throws IOException {
        //TODO
        List<LCSObject> wordList = LCSUtils.extractWords("lixin, wang def    haha");
        LCSObject o = new LCSObject("lixin", 4, 5);
        assertEquals(wordList.get(0).equals(o), true);
        assertEquals(wordList.get(1).equals(o), false);
    }

    @Test
    public void textExtractionTest() throws IOException {
        //TODO
        List<LCSObject> wordList = LCSUtils.extractWords("lixin, wang def    haha");
        List<LCSObject> tempList = new ArrayList<LCSObject>();
        tempList.add(new LCSObject("lixin", 0, 5));
        tempList.add(new LCSObject("wang", 7, 11));
        tempList.add(new LCSObject("def", 12, 15));
        tempList.add(new LCSObject("haha", 19, 23));
        assertEquals(tempList, wordList);
    }


}
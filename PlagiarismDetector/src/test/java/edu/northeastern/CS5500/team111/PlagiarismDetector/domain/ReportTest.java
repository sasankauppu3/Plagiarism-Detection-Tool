package edu.northeastern.CS5500.team111.PlagiarismDetector.domain;

import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.Report;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.SerializationUtils;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  Test case for Report Class and ensure that data is being stored in acceptable model
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class ReportTest {

    private Report report;

    @Before
    public void setUp() {
        File f1 = new File("code1.py");
        File f2 = new File("code2.py");
        report = new Report(f1.getName(), f2.getName(), 1.0);
    }

    @Test
    public void beanIsSerializable() {
        final byte[] serializedMyBean = SerializationUtils.serialize(report);
        final Report deserializedMyBean = (Report) SerializationUtils.deserialize(serializedMyBean);

        assertEquals(report.getFile1(), deserializedMyBean.getFile1());
        assertEquals(report.getFile2(), deserializedMyBean.getFile2());
        assertEquals(report.getSimilarity(), deserializedMyBean.getSimilarity());

    }

    @Test
    public void testFile1NameGetterAndSetter() {
        String name = "test";
        report.setFile1(name);
        assertTrue(report.getFile1().equals(name));
    }

    @Test
    public void testFile2NameGetterAndSetter() {
        String name = "test";
        report.setFile2(name);
        assertTrue(report.getFile2().equals(name));
    }


    @Test
    public void testFileIDGetterAndSetter() {
        double similarity = 1;
        report.setSimilarity(similarity);
        assertTrue(report.getSimilarity().equals(similarity));
    }

    @Test
    public void testFileContent() {
        String file1content = "this is file1 test content";
        String file2content = "this is file2 test content";
        report.setFile1Content(file1content);
        report.setFile2Content(file2content);
        assertTrue(report.getFile1Content().equals(file1content));
        assertTrue(report.getFile2Content().equals(file2content));
    }

    @Test
    public void testEquals() {
        assertTrue(report.equals(report));
    }
}
package edu.northeastern.CS5500.team111.PlagiarismDetector.service;

import edu.northeastern.cs5500.team111.comparisonstrategies.ComparisonAlgorithm;
import edu.northeastern.cs5500.team111.comparisonstrategies.LCSComparerAdvanced;
import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.Report;
import edu.northeastern.cs5500.team111.plagiarismdetector.service.FileCompareService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *  Test case for FileComparerService Class and ensure that comparision
 *  services are working properly for files and folders
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class FileCompareServiceTest {

    @Autowired
    private FileCompareService fileCompareService;

    private PythonFile file1, file2;

    private List<PythonFile> files;

    private List<PythonFile> sameFileList;

    private List<PythonFile> someFilesNull;

    private LCSComparerAdvanced lcsComparerAdvance;

    @Before
    public void setUp() throws Exception {
        File f1 = new File("code1.py");
        File f2 = new File("code2.py");
        file1 = new PythonFile(f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1");
        file2 = new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()), 123L, "s2");
        file1.setId(1L);
        file2.setId(2L);
        file1.setFileContent(Files.readAllBytes(f1.toPath()));
        file2.setFileContent(Files.readAllBytes(f2.toPath()));
        files = Arrays.asList(file1, file2);
        sameFileList = Arrays.asList(file1, file1);
        someFilesNull = Arrays.asList(file1, null, null, file2, null);
        lcsComparerAdvance = new LCSComparerAdvanced();
    }


    @Test
    public void advancedCompareFilesUsingJaccard() {
        List<Report> actualReportList = fileCompareService.compareUploadedFiles(files, ComparisonAlgorithm.JACCARD);
        Assert.assertEquals(1, actualReportList.size());
        Assert.assertEquals(file1.getFilename(), actualReportList.get(0).getFile1());
        Assert.assertEquals(file2.getFilename(), actualReportList.get(0).getFile2());
        Assert.assertTrue(actualReportList.get(0).getSimilarity().equals(55.55555555555556));
    }

    @Test
    public void advancedCompareFilesUsingLCSWithAST() {
        List<Report> expectedReportList = Arrays.asList(
                new Report(file1.getFilename(), file2.getFilename(), lcsComparerAdvance.compareTwoFiles(file1, file2))

        );
        List<Report> actualReportList = fileCompareService.compareUploadedFiles(files, ComparisonAlgorithm
                .LCS_WITH_AST);
        assertEquals(expectedReportList.get(0).getSimilarity(), actualReportList.get(0).getSimilarity(), 5);
    }

    @Test
    public void advancedCompareFilesUsingEditDistance() {

        List<Report> actualReportList = fileCompareService.compareUploadedFiles(files, ComparisonAlgorithm
                .EDITDISTANCE);
        Assert.assertEquals(1, actualReportList.size());
        Assert.assertEquals(file1.getFilename(), actualReportList.get(0).getFile1());
        Assert.assertEquals(file2.getFilename(), actualReportList.get(0).getFile2());
        Assert.assertTrue(actualReportList.get(0).getSimilarity().equals(94.96201108832688));
    }


    @Test
    public void advancedCompareFilesWhenNull() {
        Assert.assertArrayEquals(Collections.emptyList().toArray(), fileCompareService.compareUploadedFiles(null,
                ComparisonAlgorithm.LCS)
                .toArray());
    }

    @Test
    public void compareFilesWhenSame() {
        List<Report> expectedReportList = Collections.emptyList();
        List<Report> actualReportList = fileCompareService.compareUploadedFiles(sameFileList, ComparisonAlgorithm
                .EDITDISTANCE);
        Assert.assertArrayEquals(expectedReportList.toArray(), actualReportList.toArray());
    }

    @Test
    public void whenFilesAreEmpty() {
        Assert.assertArrayEquals(Collections.emptyList().toArray(), fileCompareService.compareUploadedFiles
                (Collections.emptyList(), ComparisonAlgorithm.LCS).toArray());
    }

    @Test
    public void whenSomeFilesAreNull() {
        List<Report> actualReportList = fileCompareService.compareUploadedFiles(someFilesNull, ComparisonAlgorithm
                .JACCARD);
        Assert.assertEquals(1, actualReportList.size());
        Assert.assertEquals(file1.getFilename(), actualReportList.get(0).getFile1());
        Assert.assertEquals(file2.getFilename(), actualReportList.get(0).getFile2());
    }

}
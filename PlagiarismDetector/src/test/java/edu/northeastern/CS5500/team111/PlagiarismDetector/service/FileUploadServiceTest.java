package edu.northeastern.CS5500.team111.PlagiarismDetector.service;

import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.service.FileUploadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *  Test case for FolderUpload and ensure that folders are properly uploaded and stored in appropriate models
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class FileUploadServiceTest {


    @Autowired
    private FileUploadService fileUploadService;

    @Test
    public void testWhenValidListOfFiles() throws Exception {
        File f1 = new File("code1.py");
        File f2 = new File("code2.py");
        MultipartFile[] files = {
                new MockMultipartFile("file1","hw1/s1/file1","JSON", Files.readAllBytes(f1.toPath())),
                new MockMultipartFile("file1","hw1/s1/file1", "JSON",Files.readAllBytes(f2.toPath())),
                new MockMultipartFile("file3","hw1/s2/file3","JSON", Files.readAllBytes(f1.toPath())),
                new MockMultipartFile("file4","hw1/s2/file4","JSON", Files.readAllBytes(f2.toPath())),
                new MockMultipartFile("file5","hw1/s3/file5","JSON", Files.readAllBytes(f2.toPath())),
                new MockMultipartFile("file6","hw1/s3/file6", "JSON",Files.readAllBytes(f1.toPath())),
        };

        assertTrue(fileUploadService.uploadFiles(files, 123L));
    }


    @Test
    public void testWhenFilesIsNull() {
        MultipartFile[] files = null;
        assertFalse(fileUploadService.uploadFiles(files, 123L));
    }

    @Test
    public void testWhenOneFileIsNull() throws Exception {
        File f1 = new File("code1.py");
        File f2 = new File("code2.py");
        MultipartFile[] files = {
                new MockMultipartFile("hw1/s1/file1", Files.readAllBytes(f1.toPath())),
                new MockMultipartFile("hw1/s1/file1", Files.readAllBytes(f2.toPath())),
                new MockMultipartFile("hw1/s2/file3", Files.readAllBytes(f1.toPath())),
                new MockMultipartFile("hw1/s2/file4", Files.readAllBytes(f2.toPath())),
                new MockMultipartFile("hw1/s3/file5", Files.readAllBytes(f2.toPath())),
                new MockMultipartFile("hw1/s3/file6", Files.readAllBytes(f1.toPath())),
                null
        };

        assertTrue(fileUploadService.uploadFiles(files, 123L));
    }


    @Test
    public void whenMultipleFilesAreNull() throws Exception {
        File f1 = new File("code1.py");
        File f2 = new File("code2.py");

        MultipartFile[] files = {
                new MockMultipartFile("hw1/s1/file1", Files.readAllBytes(f1.toPath())),
                null,
                new MockMultipartFile("hw1/s1/file1", Files.readAllBytes(f2.toPath())),
                null,
                new MockMultipartFile("hw1/s2/file3", Files.readAllBytes(f1.toPath())),
                new MockMultipartFile("hw1/s2/file4", Files.readAllBytes(f2.toPath())),
                new MockMultipartFile("hw1/s3/file5", Files.readAllBytes(f2.toPath())),
                new MockMultipartFile("hw1/s3/file6", Files.readAllBytes(f1.toPath())),
                null
        };

        assertTrue(fileUploadService.uploadFiles(files, 123L));
    }

    @Test
    public void whenMultipartListIsEmpty() {
        MultipartFile[] files = {};
        assertFalse(fileUploadService.uploadFiles(files, 123L));
    }
}
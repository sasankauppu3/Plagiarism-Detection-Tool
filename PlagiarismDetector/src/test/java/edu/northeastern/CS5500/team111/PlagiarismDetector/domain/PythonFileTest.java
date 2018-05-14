package edu.northeastern.CS5500.team111.PlagiarismDetector.domain;

import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.SerializationUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  Test case for PythonFile Class and ensure that data is being stored in acceptable model
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class PythonFileTest {

    private PythonFile pythonFile;
    private File f1;

    @Before
    public void setUp() throws Exception {
        pythonFile = new PythonFile();
        f1 = new File("code1.py");
        pythonFile.setId(123L);
        pythonFile.setFileContent(Files.readAllBytes(f1.toPath()));
        pythonFile.setFilename(f1.getName());
    }

    @Test
    public void beanIsSerializable() {
        final byte[] serializedMyBean = SerializationUtils.serialize(pythonFile);
        final PythonFile deserializedMyBean = (PythonFile) SerializationUtils.deserialize(serializedMyBean);
        assertEquals(pythonFile.getFilename(), deserializedMyBean.getFilename());
        assertEquals(pythonFile.getId(), deserializedMyBean.getId());
        Assert.assertArrayEquals(pythonFile.getFileContent(), deserializedMyBean.getFileContent());
    }

    @Test
    public void testFileNameGetterAndSetter() {
        String name = "test";
        pythonFile.setFilename(name);
        assertTrue(pythonFile.getFilename().equals(name));

    }

    @Test
    public void testFileContentGetterAndSetter() throws IOException {
        pythonFile.setFileContent(Files.readAllBytes(f1.toPath()));
        Assert.assertArrayEquals(Files.readAllBytes(f1.toPath()), pythonFile.getFileContent());
    }

    @Test
    public void testFileIDGetterAndSetter() {
        Long id = 123L;
        pythonFile.setId(id);
        assertTrue(pythonFile.getId().equals(id));
    }

    @Test
    public void testSetUserId() {
        pythonFile.setUserId(1L);
        assertTrue(pythonFile.getUserId().equals(1L));
    }

    @Test
    public void testSetRootFolderID(){
        pythonFile.setRootFolderId(1L);
        assertTrue(pythonFile.getRootFolderId().equals(1L));
    }

    @Test
    public void testSetStudentName(){
        pythonFile.setStudentName("student1");
        assertTrue(pythonFile.getStudentName().equals("student1"));
    }
}
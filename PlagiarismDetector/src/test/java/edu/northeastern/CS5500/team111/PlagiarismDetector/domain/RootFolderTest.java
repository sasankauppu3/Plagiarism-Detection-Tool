package edu.northeastern.CS5500.team111.PlagiarismDetector.domain;

import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.RootFolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.SerializationUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  Test case for RootFolder Class and ensure that data is being stored in acceptable model
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class RootFolderTest {

    private RootFolder rootFolder;

    @Before
    public void setUp() {
        rootFolder = new RootFolder(Arrays.asList(1L, 2L), "root", 123L);
    }

    @Test
    public void beanIsSerializable() {
        final byte[] serializedMyBean = SerializationUtils.serialize(rootFolder);
        final RootFolder deserializedMyBean = (RootFolder) SerializationUtils.deserialize(serializedMyBean);

        assertEquals(rootFolder.getRootFolderName(), deserializedMyBean.getRootFolderName());
        assertEquals(rootFolder.getUserId(), deserializedMyBean.getUserId());

    }

    @Test
    public void testRootFolderNameGetterAndSetter() {
        String name = "test";
        rootFolder.setRootFolderName(name);
        assertTrue(rootFolder.getRootFolderName().equals(name));
    }

    @Test
    public void testFileIDs() {
        List<Long> expected = Arrays.asList(1L, 2L);
        for (int i = 0; i < expected.size(); i++)
            assertTrue(rootFolder.getStudentFolderIds().get(i).equals(expected.get(i)));
    }

    @Test
    public void testUserIDGetterAndSetter() {
        rootFolder.setUserId(123L);
        assertTrue(rootFolder.getUserId().equals(123L));
    }

    @Test
    public void tesIDSetter() {
        rootFolder.setId(1L);
        assertTrue(rootFolder.getId().equals(1L));
    }
}
package edu.northeastern.CS5500.team111.astgen;

import edu.northeastern.cs5500.team111.astgen.Ast;
import edu.northeastern.cs5500.team111.astgen.AstWalk;
import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *  Test case for Abstract Syntax Trees
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class AstTest {
    File f1 = new File("code1.py");
    private Ast astTest;
    private AstWalk astWalkTest;

    public AstTest() throws IOException {
        astTest = new Ast(new PythonFile
                (f1.getName(), Files.readAllBytes(f1.toPath()), 123L));
        astWalkTest = new AstWalk(astTest.parse());
    }

    @Test
    public void getTokenMap() {
        assertEquals(astTest.getTokenMap().size(), astWalkTest.getAstTokenMap().size());
    }

    @Test
    public void getHashcodesList() {
        assertEquals(astTest.getHashcodesList(), astWalkTest.getAstHashCodes());
    }

}
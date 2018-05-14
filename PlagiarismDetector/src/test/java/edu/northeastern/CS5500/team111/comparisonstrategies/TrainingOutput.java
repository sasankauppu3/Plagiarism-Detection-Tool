package edu.northeastern.CS5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.comparisonstrategies.JaccardComparer;
import edu.northeastern.cs5500.team111.comparisonstrategies.LCSComparer;
import edu.northeastern.cs5500.team111.comparisonstrategies.LCSComparerAdvanced;
import edu.northeastern.cs5500.team111.comparisonstrategies.TokenEditDistComparer;
import edu.northeastern.cs5500.team111.comparisonstrategies.MossComparer;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import it.zielke.moji.MossException;
import org.junit.Test;

import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *  Test case for Output tester for MOSS training
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class TrainingOutput {
    private JaccardComparer jaccardComparer = new JaccardComparer();
    private TokenEditDistComparer tokenEditDistComparer = new TokenEditDistComparer();
    private LCSComparerAdvanced lcsComparerAdvanced = new LCSComparerAdvanced();
    private LCSComparer lcsComparer = new LCSComparer();
    private MossComparer mc = new MossComparer();

    @Test
    public void test() throws IOException, MossException {
        File file = new File("moss-train/");
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });

        System.out.println("jaccardComparer");
        for (String i:directories) {
            File f1 = new File("moss-train/"+i+"/s1/code.py");
            File f2 = new File("moss-train/"+i+"/s2/code.py");


            System.out.println(jaccardComparer.compareTwoFiles(
                    new PythonFile(f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1"),
                    new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()), 123L, "s2")));

        }

        System.out.println("tokenEditDistComparer");
        for (String i:directories) {
            File f1 = new File("moss-train/"+i+"/s1/code.py");
            File f2 = new File("moss-train/"+i+"/s2/code.py");


            System.out.println(tokenEditDistComparer.compareTwoFiles(
                    new PythonFile(f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1"),
                    new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()), 123L, "s2")));

        }

        System.out.println("lcsComparer");
        for (String i:directories) {
            File f1 = new File("moss-train/"+i+"/s1/code.py");
            File f2 = new File("moss-train/"+i+"/s2/code.py");


            System.out.println(lcsComparer.compareTwoFiles(
                    new PythonFile(f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1"),
                    new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()), 123L, "s2")));

        }

        System.out.println("lcsComparerAdvanced");
        for (String i:directories) {
            File f1 = new File("moss-train/"+i+"/s1/code.py");
            File f2 = new File("moss-train/"+i+"/s2/code.py");


            System.out.println(lcsComparerAdvanced.compareTwoFiles(
                    new PythonFile(f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1"),
                    new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()), 123L, "s2")));

        }

        System.out.println("mc");
        for (String i:directories) {
            File f1 = new File("moss-train/"+i+"/s1/code.py");
            File f2 = new File("moss-train/"+i+"/s2/code.py");

            System.out.println(mc.compare("moss-train/"+i+"/"));

        }


    }
}

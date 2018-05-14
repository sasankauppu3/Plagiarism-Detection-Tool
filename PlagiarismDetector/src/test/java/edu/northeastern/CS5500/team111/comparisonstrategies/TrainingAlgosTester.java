package edu.northeastern.CS5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.comparisonstrategies.JaccardComparer;
import edu.northeastern.cs5500.team111.comparisonstrategies.LCSComparer;
import edu.northeastern.cs5500.team111.comparisonstrategies.LCSComparerAdvanced;
import edu.northeastern.cs5500.team111.comparisonstrategies.TokenEditDistComparer;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
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
 *  Test case for training model in MOSS comparer
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class TrainingAlgosTester {
    private JaccardComparer jaccardComparer = new JaccardComparer();
    private TokenEditDistComparer tokenEditDistComparer = new TokenEditDistComparer();
    private LCSComparerAdvanced lcsComparerAdvanced = new LCSComparerAdvanced();
    private LCSComparer lcsComparer = new LCSComparer();

    double[] jaccard = new double[]{100.0,55.55555555555556,0.9433962264150944,0.0,68.0};
    double[] tokendist = new double[]{100.0,94.96201108832688,11.31014566834548,6.428571428571428,83.83374531324621};
    double[] lcscompare = new double[]{100.0,40.0,3.523035230352303,0.0,48.53556485355649};
    double[] lcscompareradv = new double[]{100.0,99.07407407407408,9.503695881731785, 4.62962962962963,   91.21338912133892};


    @Test
    public void test() throws IOException {
        File file = new File("moss-train/");
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });

        Arrays.sort(directories);
        Integer ctr=0;
        for (String i:directories) {
            File f1 = new File("moss-train/"+i+"/s1/code.py");
            File f2 = new File("moss-train/"+i+"/s2/code.py");


            assertEquals(jaccardComparer.compareTwoFiles(
                    new PythonFile(f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1"),
                    new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()), 123L, "s2")),jaccard[ctr++],0.001);

        }

        ctr = 0;
        for (String i:directories) {
            File f1 = new File("moss-train/"+i+"/s1/code.py");
            File f2 = new File("moss-train/"+i+"/s2/code.py");


            assertEquals(tokenEditDistComparer.compareTwoFiles(
                    new PythonFile(f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1"),
                    new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()), 123L, "s2")),tokendist[ctr++],0.001);

        }


        ctr=0;
        for (String i:directories) {
            File f1 = new File("moss-train/"+i+"/s1/code.py");
            File f2 = new File("moss-train/"+i+"/s2/code.py");


            assertEquals(lcsComparer.compareTwoFiles(
                    new PythonFile(f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1"),
                    new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()), 123L, "s2")),lcscompare[ctr++],0.001);

        }


        ctr=0;
        for (String i:directories) {
            File f1 = new File("moss-train/"+i+"/s1/code.py");
            File f2 = new File("moss-train/"+i+"/s2/code.py");
            assertEquals(lcsComparerAdvanced.compareTwoFiles(
                    new PythonFile(f1.getName(), Files.readAllBytes(f1.toPath()), 123L, "s1"),
                    new PythonFile(f2.getName(), Files.readAllBytes(f2.toPath()), 123L, "s2")),lcscompareradv[ctr++],0.001);

        }





    }

}

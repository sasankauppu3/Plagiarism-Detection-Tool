package edu.northeastern.CS5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.comparisonstrategies.MossComparer;
import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import it.zielke.moji.MossException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 *  Test case for MOSSComparer Class and ensure algorithm is working as expected
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes={PlagiarismdetectorApplication.class})
public class MossComparerTest {

    double[] mossoutput = new double[]{96.0, 76.0,0.0, 0.0,  83.0};
    private MossComparer mc = new MossComparer();

    @Test

    public void testMossTrainData() throws IOException, MossException {
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
            assertEquals(mc.compare("moss-train/" + i + "/"),mossoutput[ctr++],0.001);
            break;
        }
    }

}
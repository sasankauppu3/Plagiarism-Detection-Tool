package edu.northeastern.cs5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;

/**
 * Class to give the similarity score of two files using the weight of each algorithm. The weight for each algorithm is
 * is calculated using gradient descent algorithm.
 */
public class MachineLearningModel implements FileComparer{

    private JaccardComparer jaccardComparer = new JaccardComparer();
    private LCSComparer lcsComparer = new LCSComparer();
    private LCSComparerAdvanced lcsComparerAdvanced = new LCSComparerAdvanced();
    private TokenEditDistComparer tokenEditDistComparer = new TokenEditDistComparer();

    private double[] jaccard = new double[]{100.0,55.55555555555556,0.9433962264150944,0.0,68.0};
    private double[] tokenDist = new double[]{100.0,94.96201108832688,11.31014566834548,6.428571428571428,83.83374531324621};
    private double[] lcsCompare = new double[]{100.0,35.1063829787234,3.275529865125241,0.0,45.794392523364486};
    private double[] lcsComparerAdv = new double[]{100.0,99.07407407407408,9.503695881731785, 4.62962962962963,   91.21338912133892};
    private double[] mossOutput = new double[]{96.0, 76.0,0.0, 0.0,  83.0};

    private double jaccardWeight=0.25;
    private double tokenEditWeight =0.25;
    private double lcsWeight=0.25;
    private double lcsAdvWeight=0.25;

    private double learningWeight = 0.001;
    private int iterations=100;

    /**
     * Machine Learning Model Constructor
     * We train our models based on moss's comparision output
     * and assign specified weights
     */
    public MachineLearningModel()
    {
        for (int j=0;j<iterations;j++) {
            for (int i = 0; i < mossOutput.length; i++) {
                double wscore = jaccardWeight * jaccard[i] + tokenEditWeight * tokenDist[i] + lcsWeight * lcsCompare[i] + lcsComparerAdv[i] * lcsAdvWeight;

                double error = -1 * (mossOutput[i] - wscore);

                jaccardWeight = jaccardWeight - learningWeight * error * jaccard[i];
                tokenEditWeight = tokenEditWeight - learningWeight * error * (tokenDist[i]);
                lcsWeight = lcsWeight - learningWeight * error * (lcsCompare[i]);
                lcsAdvWeight = lcsAdvWeight - learningWeight * error * (lcsComparerAdv[i]);

                double totalWeight = jaccardWeight + lcsAdvWeight + lcsWeight + jaccardWeight;
                jaccardWeight = jaccardWeight / totalWeight;
                tokenEditWeight = tokenEditWeight / totalWeight;
                lcsWeight = lcsWeight / totalWeight;
                lcsAdvWeight = lcsAdvWeight / totalWeight;

            }
        }
    }

    /**
     * Method to compare two python files
     * @param f1 first file to be compared of type PythonFile
     * @param f2 second file to be compared of type PythonFile
     * @return a similarity score
     */
    @Override
    public double compareTwoFiles(PythonFile f1, PythonFile f2) {
        return Math.min(jaccardWeight * jaccardComparer.compareTwoFiles(f1,f2)
                + tokenEditWeight * tokenEditDistComparer.compareTwoFiles(f1,f2)
                + lcsWeight * lcsComparer.compareTwoFiles(f1,f2)
                + lcsAdvWeight * lcsComparerAdvanced.compareTwoFiles(f1,f2),100.0);

    }

    /**
     * Method to compare two python files and generate Line By Line
     * Comparision
     * @param f1 first file to be compared of type PythonFile
     * @param f2 second file to be compared of type PythonFile
     * @return a Comparision Report containing line by line comparision
     */
    @Override
    public ComparisionReport compareTwoFilesAdvanced(PythonFile f1, PythonFile f2) {

        ComparisionReport cr = tokenEditDistComparer.compareTwoFilesAdvanced(f1,f2);
        cr.setSimilarity(Math.min(jaccardWeight * jaccardComparer.compareTwoFiles(f1,f2)
                + tokenEditWeight * tokenEditDistComparer.compareTwoFiles(f1,f2)
                + lcsWeight * lcsComparer.compareTwoFiles(f1,f2)
                + lcsAdvWeight * lcsComparerAdvanced.compareTwoFiles(f1,f2),100.0));
        return cr;
    }
}

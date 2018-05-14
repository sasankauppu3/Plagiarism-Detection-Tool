package edu.northeastern.cs5500.team111.comparisonstrategies;

import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.Report;

import java.util.List;
import java.util.Map;

/**
 * Generate Report Class, containing comparision report which has
 * line by line comparision report, and a Report which stores the final
 * highlighted differences between two files
 */

public class GenerateReport {

    private static final Integer BASE_CHAR = 47;
    private ComparisionReport comparisionReport;
    private Report report;

    /**
     * Class constructor, initializing parameters
     */
    public GenerateReport(ComparisionReport comparisionReport) {
        this.comparisionReport = comparisionReport;
        this.report = new Report(comparisionReport.getFile1().getFilename(),
                comparisionReport.getFile2().getFilename(), comparisionReport.getSimilarity());
        createReport();
    }

    /**
     * createReport class Takes content from comparision Report and generates a
     * Final presentable report.
     */
    private void createReport() {
        PythonFile file1 = comparisionReport.getFile1();
        PythonFile file2 = comparisionReport.getFile2();

        report.setFile1Content(refactorFileContent(file1, comparisionReport.getLinesMap1()));
        report.setFile2Content(refactorFileContent(file2, comparisionReport.getLinesMap2()));
    }

    /**
     * refactorFileContent loops through a python file and with the help of
     * ine indices passed in linesMap creates highlighted lines
     *
     * @param file     Python File for which highlights have to be generated
     * @param linesMap Map for line and pair for indices (starting and ending)
     *                 which are to be highlighted
     * @return a String containing the highlighted content of Python file
     */
    private String refactorFileContent(PythonFile file, Map<Integer, List<Pair>> linesMap) {

        String file1Content = new String(file.getFileContent());

        // Split based on next line
        String[] contentInEachLine = file1Content.split("\n");

        for (Integer lineNum : linesMap.keySet()) {
            List<Pair> charMatches = sort(linesMap.get(lineNum));

            Integer charCounter = 0;
            // Loop through line indices
            for (Pair charMatch : charMatches) {

                String content = contentInEachLine[lineNum - 1];

                Integer startIndex = charMatch.getKey() + charCounter;
                Integer stopIndex = charMatch.getValue() + charCounter;

                // Highlight the dubious lines
                String updatedString = content.substring(0, startIndex) +
                        "<span style=\"background-color: #FFFF00\">" + content.substring(startIndex,
                        stopIndex) + "</span>" + content.substring(stopIndex, content.length());

                charCounter += BASE_CHAR;
                contentInEachLine[lineNum - 1] = updatedString;
            }
        }
        return addAppropriateLineBreaks(contentInEachLine);
    }

    /**
     * Getter
     *
     * @return The Report after highlighting and completion
     */
    public Report getReport() {
        return report;
    }

    /**
     * Give appropriate line breaks to be represented in HTML
     *
     * @param array String array that contains lines
     * @return a single String after appending the array
     */
    private String addAppropriateLineBreaks(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String anArray : array) {
            sb.append(anArray).append("<br>");
        }
        return sb.toString();
    }


    /**
     * Sort a list of key pairs
     *
     * @param tempList List of pair containing lines
     * @return The passed pair but ordered.
     */
    private List<Pair> sort(List<Pair> tempList) {

        int n = tempList.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (tempList.get(j).getKey() > tempList.get(j + 1).getKey()) {
                    // swap temp and arr[i]

                    Pair temp = tempList.get(j);
                    tempList.set(j, tempList.get(j + 1));
                    tempList.set(j + 1, temp);
                }

        return tempList;
    }
}


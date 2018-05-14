package edu.northeastern.cs5500.team111.plagiarismdetector.service;

import edu.northeastern.cs5500.team111.comparisonstrategies.ComparisonAlgorithm;
import edu.northeastern.cs5500.team111.comparisonstrategies.GenerateReport;
import edu.northeastern.cs5500.team111.comparisonstrategies.StrategyContext;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.Report;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Service to compare files
 */
@Service
public class FileCompareService {


    /**
     * Method to compare list of python files using the provided comparison strategy
     * with better implementation
     *
     * @param files    List of python files to be compared
     * @param strategy comparison strategy
     * @return list of reports. List is empty if files are null or files size is zero
     */
    public List<Report> compareUploadedFiles(List<PythonFile> files, ComparisonAlgorithm strategy) {

        //files should not be null or empty
        if (files == null || files.isEmpty()) {
            return Collections.emptyList();
        }
        int size = files.size();

        int[][] visited = new int[size][size];
        for (int[] aVisited : visited) {
            Arrays.fill(aVisited, 0);
        }

        //initialise list of reports
        List<Report> reports = new ArrayList<>();

        //iterate over each file to be compared
        for (int i = 0; i < files.size(); i++) {
            PythonFile file1 = files.get(i);

            if (file1 == null) {
                continue;
            }

            visited[i][i] = 1;

            int j = 0;

            while (j < files.size()) {
                PythonFile file2 = files.get(j);

                if (file2 != null
                        && !file1.getStudentName().equals(file2.getStudentName())
                        && visited[i][j] != 1) {

                    visited[j][i] = 1;
                    visited[i][j] = 1;

                    StrategyContext context = new StrategyContext(strategy);
                    GenerateReport generateReport = new GenerateReport(context.getComparisonReport(file1, file2));
                    Report report = generateReport.getReport();

                    reports.add(report);
                }
                j++;
            }
        }
        return reports;
    }

}

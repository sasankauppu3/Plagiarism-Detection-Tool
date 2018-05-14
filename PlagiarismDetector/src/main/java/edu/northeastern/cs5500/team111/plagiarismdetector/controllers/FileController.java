package edu.northeastern.cs5500.team111.plagiarismdetector.controllers;

import edu.northeastern.cs5500.team111.comparisonstrategies.ComparisonAlgorithm;
import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.Counter;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.Report;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.RootFolder;
import edu.northeastern.cs5500.team111.plagiarismdetector.repo.CounterRepository;
import edu.northeastern.cs5500.team111.plagiarismdetector.repo.FileRepository;
import edu.northeastern.cs5500.team111.plagiarismdetector.repo.RootFolderRepository;
import edu.northeastern.cs5500.team111.plagiarismdetector.service.FileCompareService;
import edu.northeastern.cs5500.team111.plagiarismdetector.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/**
 * Controller class to handle rest request related to file upload and comparison
 */
@RestController
public class FileController {

    private FileRepository fileRepository;

    private RootFolderRepository rootFolderRepository;

    private FileCompareService fileCompareService;

    private FileUploadService fileUploadService;

    private CounterRepository counterRepository;

    /**
     * constructor for the class.
     *
     * @param fileRepository       Autowired instance of FileRepository
     * @param counterRepository    Autowired instance of CounterRepository
     * @param rootFolderRepository Autowired instance of rootFolderRepository
     * @param fileCompareService   Autowired instance of file compare service
     * @param fileUploadService    Autowired instance of file upload service
     */
    @Autowired
    public FileController(FileRepository fileRepository, CounterRepository counterRepository, RootFolderRepository
            rootFolderRepository,
                          FileCompareService fileCompareService, FileUploadService fileUploadService) {

        this.fileRepository = fileRepository;
        this.rootFolderRepository = rootFolderRepository;
        this.counterRepository = counterRepository;
        this.fileCompareService = fileCompareService;
        this.fileUploadService = fileUploadService;
    }

    /**
     * Method to map get All files from the FileRepository
     *
     * @param userId the User ID for which all files are required
     * @return List of all files for the given userId
     */
    @RequestMapping(path = "/{userId}/getFiles", method = RequestMethod.GET)
    public List<PythonFile> getAllFilesForUser(@PathVariable String userId) {
        try {
            return fileRepository.findByUserId(Long.parseLong(userId));
        } catch (Exception e) {
            PlagiarismdetectorApplication.getLoggerInstance().error(e.getMessage());
        }
        return Collections.emptyList();

    }

    /**
     * Method to map get request to return all root folders for the given user
     *
     * @param userId he User ID for which all folder are required
     * @return List of all rootFolder for the given userId
     */
    @RequestMapping(path = "/{userId}/getFolders", method = RequestMethod.GET)
    public List<RootFolder> getAllFoldersForUser(@PathVariable String userId) {
        try {
            return rootFolderRepository.getByUserId(Long.parseLong(userId));
        } catch (Exception e) {
            PlagiarismdetectorApplication.getLoggerInstance().error(e.getMessage());
        }
        return Collections.emptyList();
    }


    /**
     * @param rootFolderId the root folder id which is to be deleted
     * @return ok code is files are successfully deleted else bad_request code if files could not be deleted
     */
    @RequestMapping(path = "/{rootFolderId}/getFolder", method = RequestMethod.DELETE)
    public HttpStatus deleteFolder(@PathVariable String rootFolderId) {
        try {
            Long id = Long.parseLong(rootFolderId);
            rootFolderRepository.delete(id);
            fileRepository.deleteByRootFolderId(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            PlagiarismdetectorApplication.getLoggerInstance().error(e.getMessage());
        }
        return HttpStatus.BAD_REQUEST;

    }

    /**
     * Method to map get request to return the comparison report.
     *
     * @param rootFolderId    the root folder in which files have to compared
     * @param comparisionType the type of comparison strategy to compare
     * @return List of comparison reports for all compared files
     */
    @RequestMapping(path = "/{rootFolderId}/{comparisionType}/compare", method = RequestMethod.GET)
    public List<Report> compareFiles(@PathVariable String rootFolderId, @PathVariable ComparisonAlgorithm
            comparisionType) {

        Long folderId = Long.parseLong(rootFolderId);
        List<PythonFile> files = fileRepository.findByRootFolderId(folderId);
        if (!counterRepository.findByRootFolderIdAndAlgorithmName(folderId, comparisionType.toString()).isEmpty()) {
            Counter counter = counterRepository.findByRootFolderIdAndAlgorithmName(folderId, comparisionType.toString
                    ()).get(0);
            counter.setCount(counter.getCount() + 1);
            counterRepository.save(counter);
        } else {
            counterRepository.save(new Counter(folderId, comparisionType.toString(), 1));
        }

        return fileCompareService.compareUploadedFiles(files, comparisionType);
    }

    /**
     * Method to map get request to return the comparison statistics.
     *
     * @param rootFolderId the root folder for which to get statistics
     * @return List of comparison statistics for all compared files
     */
    @RequestMapping(path = "/{rootFolderId}/statistics", method = RequestMethod.GET)
    public List<Counter> statsFiles(@PathVariable Long rootFolderId) {
        return counterRepository.findByRootFolderId(rootFolderId);
    }

    /**
     * Method to map post request to upload files
     *
     * @param request Multipart array containing files
     * @param userId  the userid to which file belongs
     * @return ok if files are successfully uploaded else bad_request code if could not be uploaded
     */
    @RequestMapping(path = "/folderUpload", method = RequestMethod.POST)
    public HttpStatus uploadFiles(@RequestParam("file") MultipartFile[] request, @RequestParam("userId") Long userId) {
        if (fileUploadService.uploadFiles(request, userId)) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

}

package edu.northeastern.cs5500.team111.plagiarismdetector.service;

import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.RootFolder;
import edu.northeastern.cs5500.team111.plagiarismdetector.repo.FileRepository;
import edu.northeastern.cs5500.team111.plagiarismdetector.repo.RootFolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * Service to upload files
 */
@Service
public class FileUploadService {

    private FileRepository fileRepository;

    private RootFolderRepository rootFolderRepository;

    /**
     * @param fileRepository Autowired instance of fileRepository
     * @param rootFolderRepository Autowired intance of rootFolder Repository
     */
    @Autowired
    public FileUploadService(FileRepository fileRepository, RootFolderRepository rootFolderRepository) {
        this.fileRepository = fileRepository;
        this.rootFolderRepository = rootFolderRepository;
    }

    /**
     * Method to upload files
     * @param files Multipart array containg files
     * @param userId userId to which file belongs
     * @return true if upload is successful else false
     */
    public boolean uploadFiles(@Nullable MultipartFile[] files, Long userId) {
        if (files != null && files.length > 0) {
            try {
                //get the name of the root folder
                String rootFolderName = files[0].getOriginalFilename().split("/")[0];
                //initialise root folder
                RootFolder rootFolder = new RootFolder(rootFolderName, userId);
                //save the root folder in database
                rootFolder = rootFolderRepository.save(rootFolder);
                //iterate over all files
                for (MultipartFile file : files) {
                    //get directory path for file by splitting on "/"
                    if (file != null) {
                        String[] dirStructure = file.getOriginalFilename().split("/");
                        //ignore files directly under root folder
                        if (dirStructure.length < 3) continue;
                        //Extract student and files names
                        String studentName = dirStructure[1];
                        //To represent which file belongs to which user
                        String fileName = studentName + ": " + dirStructure[dirStructure.length - 1];
                        //Initialize new python file
                        PythonFile pythonFile = new PythonFile(fileName, file.getBytes(), userId, rootFolder.getId(),
                                studentName);
                        //save the file in the repository
                        fileRepository.save(pythonFile);
                    }
                }

                return true;
            } catch (IOException e) {
                PlagiarismdetectorApplication.getLoggerInstance().error(e.getMessage());
            }
        } else {
            PlagiarismdetectorApplication.getLoggerInstance().error("files to be uploaded are null or List " +
                    "contains no file");
        }
        return false;
    }

}

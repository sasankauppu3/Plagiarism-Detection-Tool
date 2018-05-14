package edu.northeastern.CS5500.team111.PlagiarismDetector.controllers;

import com.google.gson.Gson;
import edu.northeastern.cs5500.team111.comparisonstrategies.ComparisonAlgorithm;
import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import edu.northeastern.cs5500.team111.plagiarismdetector.controllers.FileController;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.Counter;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.PythonFile;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.Report;
import edu.northeastern.cs5500.team111.plagiarismdetector.domain.RootFolder;
import edu.northeastern.cs5500.team111.plagiarismdetector.repo.CounterRepository;
import edu.northeastern.cs5500.team111.plagiarismdetector.repo.FileRepository;
import edu.northeastern.cs5500.team111.plagiarismdetector.repo.RootFolderRepository;
import edu.northeastern.cs5500.team111.plagiarismdetector.service.FileCompareService;
import edu.northeastern.cs5500.team111.plagiarismdetector.service.FileUploadService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *  Test case for FileController Class and ensure that files upload/download and delete functionality are proper
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PlagiarismdetectorApplication.class})
public class FileControllerTest {


    private MockMvc mockMvc;

    @Mock
    private FileRepository fileRepository;

    @Mock
    private RootFolderRepository rootFolderRepository;

    @Mock
    private FileUploadService fileUploadService;

    @Mock
    private FileCompareService fileCompareService;

    @Mock
    private CounterRepository counterRepository;

    @InjectMocks
    private FileController fileController;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(fileController)
                .build();
    }


    @Test
    public void getAllFilesForUser() throws Exception {
        //Mock return
        String mockContent = "adsadasdasd";
        PythonFile mockFile1 = new PythonFile("mock1", mockContent.getBytes(),
                123L);
        PythonFile mockFile2 = new PythonFile("mock2", mockContent.getBytes()
                , 123L);

        List<PythonFile> mockFileList = Arrays.asList(
                mockFile1,
                mockFile2
        );
        //mock repository
        Mockito.when(fileRepository.findByUserId(123L)).thenReturn
                (mockFileList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/123/getFiles")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"id\":null,\"filename\":\"mock1\"," +
                "\"fileContent\":\"YWRzYWRhc2Rhc2Q=\",\"userId\":123}," +
                "{\"id\":null,\"filename\":\"mock2\"," +
                "\"fileContent\":\"YWRzYWRhc2Rhc2Q=\",\"userId\":123}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);

    }

    @Test
    public void getAllFoldersForUser() throws Exception {
        Long userId = 123L;
        RootFolder folder1 = new RootFolder(Arrays.asList(1L, 2L), "folder1",
                userId);
        List<RootFolder> rootFolderList = Arrays.asList(folder1);
        Mockito.when(rootFolderRepository.getByUserId(userId)).thenReturn
                (rootFolderList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/123/getFolders")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Gson gson = new Gson();
        String json = gson.toJson(rootFolderList);
        JSONAssert.assertEquals(json, result.getResponse().getContentAsString
                (), false);

    }

    @Test
    public void deleteFolder() throws Exception {
        Long folderid = 12L;
        RootFolderRepository repository = Mockito.mock(RootFolderRepository.class);
        Mockito.doNothing().when(repository).delete(folderid);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/12/getFolder")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("\"OK\"", result.getResponse().getContentAsString());

    }

    @Test
    public void testCompareFilesWhenEmpty() throws Exception {

        Mockito.when(fileRepository.findByRootFolderId(1L)).thenReturn(Collections.emptyList());
        Mockito.when(fileCompareService.compareUploadedFiles(Collections.emptyList(), ComparisonAlgorithm.LCS))
                .thenReturn(Collections.emptyList());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/1/LCS/compare")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(Collections.emptyList().size(), result.getResponse().getContentLength());
    }

    @Test
    public void testCompareFiles() throws Exception {

        Mockito.when(fileRepository.findByRootFolderId(1L)).thenReturn(Collections.emptyList());
        List<Report> response = Arrays.asList(new Report("code1", "code2", 10.0), new Report("code2", "code3",
                10.0));
        Mockito.when(fileCompareService.compareUploadedFiles(Collections.emptyList(), ComparisonAlgorithm.LCS))
                .thenReturn(response);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/1/LCS/compare")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"file1\":\"code1\",\"file2\":\"code2\",\"similarity\":10.0,\"file1Content\":null," +
                "\"file2Content\":null},{\"file1\":\"code2\",\"file2\":\"code3\",\"similarity\":10.0," +
                "\"file1Content\":null,\"file2Content\":null}]";
        Assert.assertTrue(expected.equals(result.getResponse().getContentAsString()));
    }

    @Test
    public void testCounterStatswhenEmpty() throws Exception {
        Mockito.when(counterRepository.findByRootFolderId(1L)).thenReturn(Collections.emptyList());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/1/statistics")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(0, result.getResponse().getContentLength());
    }

    @Test
    public void testCounterStats() throws Exception {
        Mockito.when(counterRepository.findByRootFolderId(1L)).thenReturn(
                Arrays.asList(new Counter(1L, "LCS", 1),
                        new Counter(1L, "LCS with AST", 2)));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/1/statistics")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"id\":null,\"rootFolderId\":1,\"algorithmName\":\"LCS\",\"count\":1},{\"id\":null," +
                "\"rootFolderId\":1,\"algorithmName\":\"LCS with AST\",\"count\":2}]";
        Assert.assertEquals(expected, result.getResponse().getContentAsString());
    }





}
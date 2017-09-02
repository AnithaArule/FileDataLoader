package com.identity.e2e.service.impl;


import com.identity.e2e.service.FileReaderService;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.regex.Pattern;

public class FileReaderServiceImplTest {

    public static final String CONFIG_DIR = "/tmp/demo";
    public static final String FILE_NAME = "cars";
    public static final String XLSX_EXTENSION = ".xlsx";
    public static final String CSV_EXTENSION = ".csv";

    FileReaderService testee = null;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        testee = new FileReaderServiceImpl(CONFIG_DIR);
        //Clean before test
        FileUtils.cleanDirectory(new File(CONFIG_DIR));
    }

    @Test
    public void testExpectedNumberOfFiles() throws Exception {
        //Given
        createFileInConfigDirectory(FILE_NAME+XLSX_EXTENSION);

        //When
        File[] fileList = testee.getFilesByType(XLSX_EXTENSION);

        //Then
        File file = fileList[0];
        Assert.assertTrue("Expected one file ",fileList.length == 1);
    }

    @Test
    public void testExpectedFileNameIsRetrieved() throws Exception {
        //Given
        createFileInConfigDirectory(FILE_NAME+XLSX_EXTENSION);

        //When
        File[] fileList = testee.getFilesByType(XLSX_EXTENSION);

        //Then
        File file = fileList[0];
        Assert.assertTrue("Expected different file name",file.getName().equalsIgnoreCase(FILE_NAME+XLSX_EXTENSION));
    }

    @Test
    public void testXSLXExtension() throws Exception {
        //Given
        createFileInConfigDirectory(FILE_NAME+XLSX_EXTENSION);

        //When
        File[] fileList = testee.getFilesByType(XLSX_EXTENSION);

        //Then
        File file = fileList[0];
        Assert.assertTrue("Expected extension type is not matching",getFileExtention(file.getName()).equalsIgnoreCase(XLSX_EXTENSION));
    }

    @Test
    public void testCSVExtension() throws Exception {
        //Given
        createFileInConfigDirectory(FILE_NAME+CSV_EXTENSION);

        //When
        File[] fileList = testee.getFilesByType(CSV_EXTENSION);

        //Then
        File file = fileList[0];
        Assert.assertTrue("Expected extension type is not matching",getFileExtention(file.getName()).equalsIgnoreCase(CSV_EXTENSION));
    }



    @Test
    public void testGetAllSupportedFiles() throws Exception {
        thrown.expectMessage("To be implemented");

        testee.getAllSupportedFiles();
    }


    private String getFileExtention(String fileName) {
        final String[] split = fileName.split(Pattern.quote("."));
        return "."+split[1];
    }

    private boolean createFileInConfigDirectory(String fileName) throws Exception {
        File newFile = new File(CONFIG_DIR + "/" + fileName);
        return newFile.createNewFile();
    }
}
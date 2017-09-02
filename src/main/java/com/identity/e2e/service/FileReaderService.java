package com.identity.e2e.service;


import java.io.File;

public interface FileReaderService {

    File[] getFilesByType(String extention) throws Exception;

    File[] getAllSupportedFiles() throws Exception;

}

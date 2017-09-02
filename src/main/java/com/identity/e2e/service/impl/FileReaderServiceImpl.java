package com.identity.e2e.service.impl;

import com.identity.e2e.service.FileReaderService;

import java.io.File;
import java.io.FilenameFilter;


public class FileReaderServiceImpl implements FileReaderService {


    private File dir = null;

    public FileReaderServiceImpl(String path){
        dir = new File(path);
    }

    public File[] getFilesByType(final String extention) throws Exception{

            return dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(extention);
                }
            });
    }

    public File[] getAllSupportedFiles() throws Exception {
         throw new Exception("To be implemented");
    }
}

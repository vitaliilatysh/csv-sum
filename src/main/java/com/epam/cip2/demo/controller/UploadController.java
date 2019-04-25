package com.epam.cip2.demo.controller;

import com.epam.cip2.demo.service.FileService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Objects;

@RestController
public class UploadController {

    @Autowired
    private FileService fileService;

    @PostMapping({"/upload"})
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
        CSVReader csvReader = fileService.processFile(file);
        int sumValue = fileService.sumTheColumn(csvReader);

        return String.valueOf(sumValue);
    }

}




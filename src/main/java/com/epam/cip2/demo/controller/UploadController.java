package com.epam.cip2.demo.controller;

import com.epam.cip2.demo.service.FileService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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




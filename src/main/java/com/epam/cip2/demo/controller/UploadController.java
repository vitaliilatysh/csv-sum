package com.epam.cip2.demo.controller;

import com.epam.cip2.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class UploadController {

    @Autowired
    private FileService fileService;

    @PostMapping({"/sum"})
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
        List<String[]> rows = fileService.getRows(file);
        int sumValue = fileService.sumTheColumn(rows, 1);

        return String.valueOf(sumValue);
    }

}




package com.epam.cip2.demo.controller;

import com.epam.cip2.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;
import java.util.List;

import static com.epam.cip2.demo.constant.MessageConstants.INDEX_MODEL;
import static com.epam.cip2.demo.constant.MessageConstants.TOTAL_SUM_ATTR;

@RestController
public class UploadController {

    @Autowired
    private FileService fileService;

    @PostMapping({"/sum"})
    public ModelAndView singleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("column") String columnName, Model model) throws FileNotFoundException {
        List<String[]> rows = fileService.getRows(file);
        int columnIndex = fileService.getColumnIndex(rows, columnName);
        model.addAttribute(TOTAL_SUM_ATTR, fileService.totalSum(rows,columnIndex));
        return new ModelAndView(INDEX_MODEL);
    }

    @GetMapping({"/"})
    public ModelAndView getIndex(Model model) {
        model.addAttribute(TOTAL_SUM_ATTR);
        return new ModelAndView(INDEX_MODEL);
    }
}




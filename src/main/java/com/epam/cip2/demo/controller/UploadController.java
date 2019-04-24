package com.epam.cip2.demo.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
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

@RestController
public class UploadController {

    private Path rootLocation;

    @PostMapping({"/upload"})
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {

        int sum = 0;

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(filename);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            Reader reader = Files.newBufferedReader(path);
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

            sum = csvReader.readAll().stream()
                    .flatMap(Arrays::stream)
                    .mapToInt(Integer::parseInt)
                    .sum();

        } catch (IOException e) {
            System.out.println();
        }

        return String.valueOf(sum);
    }

}




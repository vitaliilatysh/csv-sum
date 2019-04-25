package com.epam.cip2.demo.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;
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

@Service
public class FileService {

    public int sumTheColumn(CSVReader csvReader) {
        int sum;

        try {
            sum = csvReader.readAll().stream()
                    .flatMap(Arrays::stream)
                    .mapToInt(Integer::parseInt)
                    .sum();
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return sum;
    }

    public CSVReader processFile(MultipartFile file) {

        Path path = Paths.get(Objects.requireNonNull(file.getOriginalFilename()));

        CSVReader csvReader;
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            Reader reader = Files.newBufferedReader(path);
            csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();


        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return Objects.requireNonNull(csvReader);
    }
}

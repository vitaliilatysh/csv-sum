package com.epam.cip2.demo.service;

import com.epam.cip2.demo.exceptions.CsvReadException;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {

    public int sumTheColumn(List<String[]> rows, int columnNumber) {
        return rows.stream()
                .map(row -> row[columnNumber])
                .mapToInt(Integer::parseInt)
                .sum();
    }

    public List<String[]> getRows(MultipartFile file) throws FileNotFoundException {

        checkFileUploaded(file);

        Path path = Paths.get(Objects.requireNonNull(file.getOriginalFilename()));

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            Reader reader = Files.newBufferedReader(path);
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

            return csvReader.readAll();
        } catch (IOException e) {
            throw new CsvReadException("Not valid csv file. Please, correct the file.");
        }
    }

    private void checkFileUploaded(MultipartFile file) throws FileNotFoundException {
        if (Objects.isNull(file)) {
            throw new FileNotFoundException("No file uploaded. Please, upload the csv file.");
        }
    }
}

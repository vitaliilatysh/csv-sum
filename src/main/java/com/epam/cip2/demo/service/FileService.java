package com.epam.cip2.demo.service;

import com.epam.cip2.demo.exceptions.CsvProcessException;
import com.epam.cip2.demo.exceptions.FileNotFoundException;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

import static com.epam.cip2.demo.constant.MessageConstants.CSV;
import static com.epam.cip2.demo.constant.MessageConstants.ERROR_PROCESSING_CSV;
import static com.epam.cip2.demo.constant.MessageConstants.NOT_CSV_FILE;
import static com.epam.cip2.demo.constant.MessageConstants.NO_FILE_UPLOAD;

@Service
public class FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);


    public int sumTheColumn(List<String[]> rows, int columnNumber) {
        return rows.stream()
                .map(row -> row[columnNumber])
                .mapToInt(Integer::parseInt)
                .sum();
    }

    public List<String[]> getRows(MultipartFile file) {
        String fileName = Objects.requireNonNull(file.getOriginalFilename());

        checkFileUploaded(fileName);
        checkFileExtension(fileName);

        Path path = Paths.get(fileName);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            Reader reader = Files.newBufferedReader(path);
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

            return csvReader.readAll();
        } catch (IOException e) {
            LOGGER.error(ERROR_PROCESSING_CSV);
            throw new CsvProcessException(ERROR_PROCESSING_CSV);
        }
    }

    private void checkFileUploaded(String fileName) {
        if (fileName.isEmpty()) {
            throw new FileNotFoundException(NO_FILE_UPLOAD);
        }
    }

    private void checkFileExtension(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);
        if (!extension.equalsIgnoreCase(CSV)) {
            throw new IllegalArgumentException(NOT_CSV_FILE);
        }
    }
}


package com.mikhail.tarasevich.resulttablemaker.validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ValidatorImpl implements Validator {

    @Override
    public void validateFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        final List<String> textFromFileInString = Files.readAllLines(path);
        if (!path.toFile().isFile()) {
            throw new FileNotFoundException("There is no file on this path.");
        }
        if (textFromFileInString.isEmpty()) {
            throw new IOException("File is empty");
        }

        for (int i = 0; i < textFromFileInString.size(); i++) {
            if (textFromFileInString.get(i).substring(0, 3).matches("A-Z")) {
                throw new IllegalArgumentException("File has incorrect format.");
            }
        }

    }

}

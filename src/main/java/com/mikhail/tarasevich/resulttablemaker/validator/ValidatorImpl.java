package com.mikhail.tarasevich.resulttablemaker.validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ValidatorImpl implements Validator {

    @Override
    public void validateFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        final List<String> textFromFileInString = Files.readAllLines(path);
       
        if (textFromFileInString.isEmpty()) {
            throw new IOException("File is empty");
        }

        if (textFromFileInString.stream().map(string -> string.substring(0, 3).matches("[A-Z]+"))
                                         .collect(Collectors.toList()).contains(false)) {
            throw new IllegalArgumentException("File has incorrect format.");
        }
    }

}

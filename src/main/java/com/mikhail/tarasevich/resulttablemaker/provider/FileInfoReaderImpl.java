package com.mikhail.tarasevich.resulttablemaker.provider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileInfoReaderImpl implements FileInfoReader {

    @Override
    public List<String> readInfoFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        final List<String> textFromFileInString = Files.readAllLines(path);
        return textFromFileInString;
    }
}

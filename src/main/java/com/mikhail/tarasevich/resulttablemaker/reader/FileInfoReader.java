package com.mikhail.tarasevich.resulttablemaker.reader;

import java.io.IOException;
import java.util.List;

public interface FileInfoReader {
    List<String> readInfoFromFile(String filePath) throws IOException;
}

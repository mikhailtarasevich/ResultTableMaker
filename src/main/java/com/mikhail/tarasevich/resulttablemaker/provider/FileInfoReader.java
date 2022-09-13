package com.mikhail.tarasevich.resulttablemaker.provider;

import java.io.IOException;
import java.util.List;

public interface FileInfoReader {
    List<String> readInfoFromFile(String filePath) throws IOException;
}

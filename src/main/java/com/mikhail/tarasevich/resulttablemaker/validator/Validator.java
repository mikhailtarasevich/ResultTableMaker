package com.mikhail.tarasevich.resulttablemaker.validator;

import java.io.IOException;

public interface Validator {
    void validateFile(String filePath) throws IOException;
}

package com.mikhail.tarasevich.resulttablemaker.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class ValidatorImplTest{

    final ValidatorImpl validatorImpl = new ValidatorImpl();

//    @Test
//    void validateFile_inputNotExistPath_expectedFileNotFoundException() {
//        String path = "notExist.txt";
//        assertThatThrownBy(() -> validatorImpl.validateFile(path)).isInstanceOf(FileNotFoundException.class)
//                .hasMessageContaining("There is no file on this path.");
//    }
    
    @Test
    void validateFile_inputEmptyFile_expectedIOException() {
        String path = "src\\test\\resources\\validateFileEmptyFile.txt";
        assertThatThrownBy(() -> validatorImpl.validateFile(path)).isInstanceOf(IOException.class)
                .hasMessageContaining("File is empty");
    }
   
//    @Test
//    void validateFile_inputFileIncorrectFormat_expectedIllegalArgumentException() {
//        String path = "src\\test\\resources\\validateFileHasIncorrectFormat.txt";
//        assertThatThrownBy(() -> validatorImpl.validateFile(path)).isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("File has incorrect format.");
//    }
}

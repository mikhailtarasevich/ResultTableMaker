package com.mikhail.tarasevich.resulttablemaker.validator;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class ValidatorImplTest {

    final ValidatorImpl validatorImpl = new ValidatorImpl();

    @Test
    void validateFile_inputEmptyFile_expectedIOException() {
        String path = "src\\test\\resources\\validateFileEmptyFile.txt";
        assertThatThrownBy(() -> validatorImpl.validateFile(path)).isInstanceOf(IOException.class)
                .hasMessageContaining("File is empty");
    }

    @Test
    void validateFile_inputFileIncorrectFormat_expectedIllegalArgumentException() {
        String path = "src\\test\\resources\\validateFileHasIncorrectFormat.txt";
        assertThatThrownBy(() -> validatorImpl.validateFile(path)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("File has incorrect format.");
    }

    @Test
    void validate_inputDivisionAndDivisorPositive_expectedNothing() {
        String path = "src\\test\\resources\\validateFileFileIsFine.txt";
        assertThatCode(() -> validatorImpl.validateFile(path)).doesNotThrowAnyException();
    }

}

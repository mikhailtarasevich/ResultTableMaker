package com.mikhail.tarasevich.resulttablemaker.validator;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ValidatorImplTest {

    final ValidatorImpl validatorImpl = new ValidatorImpl();

    @Test
    void validateFile_inputEmptyFile_expectedIOException() {
        String path = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "validateFileEmptyFile.txt";
        assertThatThrownBy(() -> validatorImpl.validateFile(path)).isInstanceOf(IOException.class)
                .hasMessageContaining("File is empty");
    }

    @Test
    void validateFile_inputFileIncorrectFormat_expectedIllegalArgumentException() {
        String path = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "validateFileHasIncorrectFormat.txt";
        assertThatThrownBy(() -> validatorImpl.validateFile(path)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("File has incorrect format.");
    }

    @Test
    void validate_inputDivisionAndDivisorPositive_expectedNothing() {
        String path = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "validateFileFileIsFine.txt";
        assertThatCode(() -> validatorImpl.validateFile(path)).doesNotThrowAnyException();
    }

}

package com.mikhail.tarasevich.resulttablemaker;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;
import com.mikhail.tarasevich.resulttablemaker.parser.RacerParser;
import com.mikhail.tarasevich.resulttablemaker.parser.RacerParserImpl;
import com.mikhail.tarasevich.resulttablemaker.provider.ViewProvider;
import com.mikhail.tarasevich.resulttablemaker.provider.ViewProviderImpl;
import com.mikhail.tarasevich.resulttablemaker.reader.FileInfoReader;
import com.mikhail.tarasevich.resulttablemaker.reader.FileInfoReaderImpl;
import com.mikhail.tarasevich.resulttablemaker.validator.Validator;
import com.mikhail.tarasevich.resulttablemaker.validator.ValidatorImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class StatisticAnalyzerTest2 {

    private final int outOfQualificationLine = 1;

    private Validator validator = Mockito.mock(ValidatorImpl.class);

    private FileInfoReader fileInfoReader = Mockito.mock(FileInfoReaderImpl.class);

    private RacerParser racerParser = Mockito.mock(RacerParserImpl.class);

    private ViewProvider viewProvider = Mockito.mock(ViewProviderImpl.class);

    @BeforeAll
    public void setUp() {
        StatisticAnalyzer statisticAnalyzer = new StatisticAnalyzer(validator, fileInfoReader, racerParser, viewProvider, outOfQualificationLine);
    }


    @Test
    void provideStatistic_inputReferencesOnFiles_expectedResultTableInString() throws IOException, ParseException {


    }
}

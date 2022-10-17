package com.mikhail.tarasevich.resulttablemaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;
import com.mikhail.tarasevich.resulttablemaker.parser.RacerParser;
import com.mikhail.tarasevich.resulttablemaker.provider.ViewProvider;
import com.mikhail.tarasevich.resulttablemaker.reader.FileInfoReader;
import com.mikhail.tarasevich.resulttablemaker.validator.Validator;

@ExtendWith(MockitoExtension.class)
class StatisticAnalyzerTest {

    private final int outOfQualificationLine = 1;

    @Mock
    private Validator validator;
    @Mock
    private FileInfoReader fileInfoReader;
    @Mock
    private RacerParser racerParser;
    @Mock
    private ViewProvider viewProvider;

    @InjectMocks
    StatisticAnalyzer statisticAnalyzer = new StatisticAnalyzer(validator, fileInfoReader, racerParser, viewProvider, outOfQualificationLine);

    @Test
    void provideStatistic_inputReferencesOnFiles_expectedResultTableInString() throws IOException, ParseException {

        StatisticAnalyzer statisticAnalyzer = new StatisticAnalyzer(validator, fileInfoReader, racerParser, viewProvider, outOfQualificationLine);

        final String racerListReference = "src" + File.separator
                + "test" + File.separator + "resources" + File.separator + "StatisticAnalyzerTestAbb.txt";
        final String startListReference = "src" + File.separator
                + "test" + File.separator + "resources" + File.separator + "StatisticAnalyzerTestStart.log";
        final String finishListReference ="src" + File.separator
                + "test" + File.separator + "resources" + File.separator +  "StatisticAnalyzerTestEnd.log";
        final String TIME_FORMAT = "yyyy-MM-dd_HH:mm:ss.SSS";

        final String expected = "Position  Racer name                    Team name                     Time      \n" +
                "1         Sebastian Vettel              FERRARI                       01:04.415 \n" +
                "-------------------------------------------------------------------------------\n" +
                "2         Daniel Ricciardo              RED BULL RACING TAG HEUER     01:12.013 \n" +
                "3         Lewis Hamilton                MERCEDES                      01:12.460 \n";

        final List<String> listOfRacers = new ArrayList<>();
        listOfRacers.add("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
        listOfRacers.add("SVF_Sebastian Vettel_FERRARI");
        listOfRacers.add("LHM_Lewis Hamilton_MERCEDES");

        final List<String> listOfStartTime = new ArrayList<>();
        listOfStartTime.add("SVF2018-05-24_12:02:58.917");
        listOfStartTime.add("DRR2018-05-24_12:14:12.054");
        listOfStartTime.add("LHM2018-05-24_12:18:20.125");

        final List<String> listOfFinishTime = new ArrayList<>();
        listOfFinishTime.add("SVF2018-05-24_12:04:03.332");
        listOfFinishTime.add("LHM2018-05-24_12:19:32.585");
        listOfFinishTime.add("DRR2018-05-24_12:15:24.067");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);

        LocalDateTime start1 = LocalDateTime.parse("2018-05-24_12:14:12.054", formatter);
        LocalDateTime finish1 = LocalDateTime.parse("2018-05-24_12:15:24.067", formatter);
        final Racer racer1 = Racer.Builder().racerName("Daniel Ricciardo").teamName("RED BULL RACING TAG HEUER")
                .startTime(start1).finishTime(finish1).build();

        LocalDateTime start2 = LocalDateTime.parse("2018-05-24_12:02:58.917", formatter);
        LocalDateTime finish2 = LocalDateTime.parse("2018-05-24_12:04:03.332", formatter);
        final Racer racer2 = Racer.Builder().racerName("Sebastian Vettel").teamName("FERRARI").startTime(start2)
                .finishTime(finish2).build();

        LocalDateTime start3 = LocalDateTime.parse("2018-05-24_12:18:20.125", formatter);
        LocalDateTime finish3 = LocalDateTime.parse("2018-05-24_12:19:32.585", formatter);
        final Racer racer3 = Racer.Builder().racerName("Lewis Hamilton").teamName("MERCEDES").startTime(start3)
                .finishTime(finish3).build();

        final List<Racer> racerList = new ArrayList<>();
        racerList.add(racer1);
        racerList.add(racer2);
        racerList.add(racer3);

        List<Racer> comparedRacerList =  racerList.stream()
                .sorted((r1, r2) -> r1.getTimeOfLap().compareTo(r2.getTimeOfLap()))
                .collect(Collectors.toList());

        doNothing().when(validator).validateFile(racerListReference);
        doNothing().when(validator).validateFile(startListReference);
        doNothing().when(validator).validateFile(finishListReference);
//        when(fileInfoReader.readInfoFromFile(racerListReference)).thenReturn(listOfRacers);
//        when(fileInfoReader.readInfoFromFile(startListReference)).thenReturn(listOfStartTime);
//        when(fileInfoReader.readInfoFromFile(finishListReference)).thenReturn(listOfFinishTime);
        lenient().when(fileInfoReader.readInfoFromFile(racerListReference)).thenReturn(listOfRacers);
        lenient().when(fileInfoReader.readInfoFromFile(startListReference)).thenReturn(listOfStartTime);
        lenient().when(fileInfoReader.readInfoFromFile(finishListReference)).thenReturn(listOfFinishTime);
        when(racerParser.createRacersList(listOfRacers, listOfStartTime, listOfFinishTime)).thenReturn(racerList);
        when(viewProvider.provideResultTableView(comparedRacerList, outOfQualificationLine)).thenReturn(expected);

        assertEquals(statisticAnalyzer.provideStatistic(racerListReference, startListReference, finishListReference), expected);

        verify(validator).validateFile(racerListReference);
        verify(validator).validateFile(startListReference);
        verify(validator).validateFile(finishListReference);
        verify(fileInfoReader).readInfoFromFile(racerListReference);
        verify(fileInfoReader).readInfoFromFile(startListReference);
        verify(fileInfoReader).readInfoFromFile(finishListReference);
        verify(racerParser).createRacersList(listOfRacers, listOfStartTime, listOfFinishTime);
        verify(viewProvider).provideResultTableView(comparedRacerList, outOfQualificationLine);
//        verify(statisticAnalyzer).provideStatistic(racerListReference, startListReference, finishListReference);

    }
}


/*@ExtendWith(MockitoExtension.class)
class StatisticAnalyzerTest {

    private final int outOfQualificationLine = 1;

    @Mock
    private Validator validator;
    @Mock
    private FileInfoReader fileInfoReader;
    @Mock
    private RacerParser racerParser;
    @Mock
    private ViewProvider viewProvider;

    @InjectMocks
    StatisticAnalyzer statisticAnalyzer = new StatisticAnalyzer(validator, fileInfoReader, racerParser, viewProvider, outOfQualificationLine);

    @Test
    void provideStatistic_inputReferencesOnFiles_expectedResultTableInString() throws IOException, ParseException {

        final String racerListReference = "src" + File.separator
                + "test" + File.separator + "resources" + File.separator + "StatisticAnalyzerTestAbb.txt";
        final String startListReference = "src" + File.separator
                + "test" + File.separator + "resources" + File.separator + "StatisticAnalyzerTestStart.log";
        final String finishListReference ="src" + File.separator
                + "test" + File.separator + "resources" + File.separator +  "StatisticAnalyzerTestEnd.log";
        final String TIME_FORMAT = "yyyy-MM-dd_HH:mm:ss.SSS";

        final String expected = "Position  Racer name                    Team name                     Time      \n" +
                "1         Sebastian Vettel              FERRARI                       01:04.415 \n" +
                "-------------------------------------------------------------------------------\n" +
                "2         Daniel Ricciardo              RED BULL RACING TAG HEUER     01:12.013 \n" +
                "3         Lewis Hamilton                MERCEDES                      01:12.460 \n";

        final List<String> listOfRacers = new ArrayList<>();
        listOfRacers.add("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
        listOfRacers.add("SVF_Sebastian Vettel_FERRARI");
        listOfRacers.add("LHM_Lewis Hamilton_MERCEDES");

        final List<String> listOfStartTime = new ArrayList<>();
        listOfStartTime.add("SVF2018-05-24_12:02:58.917");
        listOfStartTime.add("DRR2018-05-24_12:14:12.054");
        listOfStartTime.add("LHM2018-05-24_12:18:20.125");

        final List<String> listOfFinishTime = new ArrayList<>();
        listOfFinishTime.add("SVF2018-05-24_12:04:03.332");
        listOfFinishTime.add("LHM2018-05-24_12:19:32.585");
        listOfFinishTime.add("DRR2018-05-24_12:15:24.067");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);

        LocalDateTime start1 = LocalDateTime.parse("2018-05-24_12:14:12.054", formatter);
        LocalDateTime finish1 = LocalDateTime.parse("2018-05-24_12:15:24.067", formatter);
        final Racer racer1 = Racer.Builder().racerName("Daniel Ricciardo").teamName("RED BULL RACING TAG HEUER")
                .startTime(start1).finishTime(finish1).build();

        LocalDateTime start2 = LocalDateTime.parse("2018-05-24_12:02:58.917", formatter);
        LocalDateTime finish2 = LocalDateTime.parse("2018-05-24_12:04:03.332", formatter);
        final Racer racer2 = Racer.Builder().racerName("Sebastian Vettel").teamName("FERRARI").startTime(start2)
                .finishTime(finish2).build();

        LocalDateTime start3 = LocalDateTime.parse("2018-05-24_12:18:20.125", formatter);
        LocalDateTime finish3 = LocalDateTime.parse("2018-05-24_12:19:32.585", formatter);
        final Racer racer3 = Racer.Builder().racerName("Lewis Hamilton").teamName("MERCEDES").startTime(start3)
                .finishTime(finish3).build();

        final List<Racer> racerList = new ArrayList<>();
        racerList.add(racer1);
        racerList.add(racer2);
        racerList.add(racer3);

        List<Racer> comparedRacerList =  racerList.stream()
                .sorted((r1, r2) -> r1.getTimeOfLap().compareTo(r2.getTimeOfLap()))
                .collect(Collectors.toList());

        lenient().doNothing().when(validator).validateFile(racerListReference);
        lenient().doNothing().when(validator).validateFile(startListReference);
        lenient().doNothing().when(validator).validateFile(finishListReference);
        lenient().when(fileInfoReader.readInfoFromFile(racerListReference)).thenReturn(listOfRacers);
        lenient().when(fileInfoReader.readInfoFromFile(startListReference)).thenReturn(listOfStartTime);
        lenient().when(fileInfoReader.readInfoFromFile(finishListReference)).thenReturn(listOfFinishTime);
        lenient().when(racerParser.createRacersList(listOfRacers, listOfStartTime, listOfFinishTime)).thenReturn(racerList);
        lenient().when(viewProvider.provideResultTableView(comparedRacerList, outOfQualificationLine)).thenReturn(expected);

        assertEquals(statisticAnalyzer.provideStatistic(racerListReference, startListReference, finishListReference), expected);

        verify(validator).validateFile(racerListReference);
        verify(validator).validateFile(startListReference);
        verify(validator).validateFile(finishListReference);
        verify(fileInfoReader).readInfoFromFile(racerListReference);
        verify(fileInfoReader).readInfoFromFile(startListReference);
        verify(fileInfoReader).readInfoFromFile(finishListReference);
        verify(racerParser).createRacersList(listOfRacers, listOfStartTime, listOfFinishTime);
        verify(viewProvider).provideResultTableView(comparedRacerList, outOfQualificationLine);
        verify(statisticAnalyzer).provideStatistic(racerListReference, startListReference, finishListReference);

    }
}*/

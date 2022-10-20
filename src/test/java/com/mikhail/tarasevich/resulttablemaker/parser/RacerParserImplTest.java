package com.mikhail.tarasevich.resulttablemaker.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

class RacerParserImplTest {

    private static final String TIME_FORMAT = "yyyy-MM-dd_HH:mm:ss.SSS";

    @Test
    void createRacersList_inputCorrectInformation_expectedListOfRacers() {

        RacerParser racerParser = new RacerParserImpl();

        final List<String> racerInfoList = new ArrayList<>();
        final List<String> startTimeList = new ArrayList<>();
        final List<String> finishTimeList = new ArrayList<>();

        racerInfoList.add("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
        racerInfoList.add("SVF_Sebastian Vettel_FERRARI");
        racerInfoList.add("LHM_Lewis Hamilton_MERCEDES");

        startTimeList.add("SVF2018-05-24_12:02:58.917");
        startTimeList.add("DRR2018-05-24_12:14:12.054");
        startTimeList.add("LHM2018-05-24_12:18:20.125");

        finishTimeList.add("SVF2018-05-24_12:04:03.332");
        finishTimeList.add("LHM2018-05-24_12:19:32.585");
        finishTimeList.add("DRR2018-05-24_12:15:24.067");

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

        final List<Racer> actual = racerParser.createRacersList(racerInfoList, startTimeList, finishTimeList);

        final List<Racer> expected = new ArrayList<>();
        expected.add(racer3);
        expected.add(racer2);
        expected.add(racer1);

        assertThat(actual).isEqualTo(expected);
    }

}

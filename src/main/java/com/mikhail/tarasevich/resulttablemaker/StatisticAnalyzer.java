package com.mikhail.tarasevich.resulttablemaker;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import com.mikhail.tarasevich.resulttablemaker.domain.Racer;
import com.mikhail.tarasevich.resulttablemaker.parser.RacerParser;
import com.mikhail.tarasevich.resulttablemaker.provider.ViewProvider;
import com.mikhail.tarasevich.resulttablemaker.reader.FileInfoReader;
import com.mikhail.tarasevich.resulttablemaker.validator.Validator;

public class StatisticAnalyzer {

    private final Validator validator;
    private final FileInfoReader fileInfoReader;
    private final RacerParser racerParser;
    private final ViewProvider viewProvider;
    private final int outOfQualificationLine;

    public StatisticAnalyzer(Validator validator, FileInfoReader fileInfoReader, RacerParser racerParser,
            ViewProvider viewProvider, int outOfQualificationLine) {
        this.validator = validator;
        this.fileInfoReader = fileInfoReader;
        this.racerParser = racerParser;
        this.viewProvider = viewProvider;
        this.outOfQualificationLine = outOfQualificationLine;
    }

    public String provideStatistic(String racerListReference, String startListReference, String finishListReference)
            throws IOException {

        validator.validateFile(racerListReference);
        validator.validateFile(startListReference);
        validator.validateFile(finishListReference);
        
        List<String> listOfRacers = fileInfoReader.readInfoFromFile(racerListReference);
        List<String> listOfStartTime = fileInfoReader.readInfoFromFile(startListReference);
        List<String> listOfFinishTime = fileInfoReader.readInfoFromFile(finishListReference);
        
        List<Racer> racerList = racerParser.createRacersList(listOfRacers, listOfStartTime, listOfFinishTime);
        
        List<Racer> comparedRacerList =  racerList.stream()
                                                  .sorted((r1, r2) -> r1.getTimeOfLap().compareTo(r2.getTimeOfLap()))
                                                  .collect(Collectors.toList());

        return viewProvider.provideResultTableView(comparedRacerList, outOfQualificationLine);
    }

}

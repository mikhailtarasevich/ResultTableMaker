package com.mikhail.tarasevich.resulttablemaker;

import java.io.IOException;
import java.text.ParseException;
import java.util.Set;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;
import com.mikhail.tarasevich.resulttablemaker.provider.FileInfoReader;
import com.mikhail.tarasevich.resulttablemaker.provider.RacerParser;
import com.mikhail.tarasevich.resulttablemaker.provider.ViewProvider;
import com.mikhail.tarasevich.resulttablemaker.validator.Validator;

public class StatisticAnalyzer {
    
    private final Validator validator;
    private final FileInfoReader fileInfoReader;
    private final RacerParser racerParser;
    private final ViewProvider viewProvider;
    
    public StatisticAnalyzer(Validator validator, FileInfoReader fileInfoReader, RacerParser racerParser, ViewProvider viewProvider) {
        this.validator = validator;
        this.fileInfoReader = fileInfoReader;
        this.racerParser = racerParser;
        this.viewProvider = viewProvider;
    }
    
    public String provideStatistic(String racerListReference,String startListReference,String finishListReference) throws ParseException, IOException {
        
        String resultTable = null;
        
        validator.validateFile(racerListReference);
        validator.validateFile(startListReference);
        validator.validateFile(finishListReference);
        
        Set<Racer> racerList = racerParser.createRacersList(fileInfoReader.readInfoFromFile(racerListReference),
                fileInfoReader.readInfoFromFile(startListReference), fileInfoReader.readInfoFromFile(finishListReference));
        
        
        return resultTable;
    }
}

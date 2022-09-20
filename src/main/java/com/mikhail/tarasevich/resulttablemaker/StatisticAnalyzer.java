package com.mikhail.tarasevich.resulttablemaker;

import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

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
    private final int outOfQualificationLine;
    
    public StatisticAnalyzer(Validator validator, FileInfoReader fileInfoReader, RacerParser racerParser, ViewProvider viewProvider, int outOfQualificationLine) {
        this.validator = validator;
        this.fileInfoReader = fileInfoReader;
        this.racerParser = racerParser;
        this.viewProvider = viewProvider;
        this.outOfQualificationLine = outOfQualificationLine;
    }
    
    public String provideStatistic(String racerListReference,String startListReference,String finishListReference) throws ParseException, IOException {
        
        validator.validateFile(racerListReference);
        validator.validateFile(startListReference);
        validator.validateFile(finishListReference);
        
        Set<Racer> racerList = racerParser.createRacersList(fileInfoReader.readInfoFromFile(racerListReference),
                fileInfoReader.readInfoFromFile(startListReference), fileInfoReader.readInfoFromFile(finishListReference));
        
      //  LapTimeComparator lapTimeComparator = new LapTimeComparator();
        Comparator<Racer> LapTimeComparator = (r1, r2)-> r1.getTimeOfLap().compareTo(r2.getTimeOfLap());
        
        Set<Racer> comparedRacerList = new TreeSet<>(LapTimeComparator);
        for (Racer racer : racerList) {
            comparedRacerList.add(racer);
        }
        
        
        
        return viewProvider.provideResultTableView(comparedRacerList, outOfQualificationLine).toString();
    }
    
//    private class LapTimeComparator implements Comparator<Racer>{
//
//        @Override
//        public int compare(Racer o1, Racer o2) {
//            return (int)o1.getTimeOfLap().toMillis() - (int)o2.getTimeOfLap().toMillis();          
//        }
//        
//    }
}

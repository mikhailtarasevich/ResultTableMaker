package com.mikhail.tarasevich.resulttablemaker;

import java.io.IOException;
import java.text.ParseException;

import com.mikhail.tarasevich.resulttablemaker.parser.RacerParser;
import com.mikhail.tarasevich.resulttablemaker.parser.RacerParserImpl;
import com.mikhail.tarasevich.resulttablemaker.provider.ViewProvider;
import com.mikhail.tarasevich.resulttablemaker.provider.ViewProviderImpl;
import com.mikhail.tarasevich.resulttablemaker.reader.FileInfoReader;
import com.mikhail.tarasevich.resulttablemaker.reader.FileInfoReaderImpl;
import com.mikhail.tarasevich.resulttablemaker.validator.Validator;
import com.mikhail.tarasevich.resulttablemaker.validator.ValidatorImpl;

public class ResultTableMakerApplication {

    public static void main(String[] args) throws ParseException, IOException  {

//        Validator validator = new ValidatorImpl();
//        FileInfoReader fileInfoReader = new FileInfoReaderImpl();
//        RacerParser racerInfoList = new RacerParserImpl();
//        ViewProvider viewProvider = new ViewProviderImpl();
//        int outOfQualificationLine = 16;
//        StatisticAnalyzer statisticAnalyzer = new StatisticAnalyzer(validator, fileInfoReader, racerInfoList,
//                viewProvider, outOfQualificationLine);
//
//        final String racer = "abbreviations.txt";
//        final String start = "start.log";
//        final String finish = "end.log";
//
//        System.out.println(statisticAnalyzer.provideStatistic(racer, start, finish));
        
        Validator validator = new ValidatorImpl();
        FileInfoReader fileInfoReader = new FileInfoReaderImpl();
        RacerParser racerInfoList = new RacerParserImpl();
        ViewProvider viewProvider = new ViewProviderImpl();
        int outOfQualificationLine = 1;
        StatisticAnalyzer statisticAnalyzer = new StatisticAnalyzer(validator, fileInfoReader, racerInfoList,
                viewProvider, outOfQualificationLine);
        
        final String racerListReference = "src\\test\\resources\\StatisticAnalyzerTestAbb.txt";
        final String startListReference = "src\\test\\resources\\StatisticAnalyzerTestStart.log";
        final String finishListReference = "src\\test\\resources\\StatisticAnalyzerTestEnd.log";

        System.out.println(statisticAnalyzer.provideStatistic(racerListReference, startListReference, finishListReference));
        
        
    }
}

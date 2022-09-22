package com.mikhail.tarasevich.resulttablemaker;

import com.mikhail.tarasevich.resulttablemaker.parser.RacerParser;
import com.mikhail.tarasevich.resulttablemaker.parser.RacerParserImpl;
import com.mikhail.tarasevich.resulttablemaker.provider.ViewProvider;
import com.mikhail.tarasevich.resulttablemaker.provider.ViewProviderImpl;
import com.mikhail.tarasevich.resulttablemaker.reader.FileInfoReader;
import com.mikhail.tarasevich.resulttablemaker.reader.FileInfoReaderImpl;
import com.mikhail.tarasevich.resulttablemaker.validator.Validator;
import com.mikhail.tarasevich.resulttablemaker.validator.ValidatorImpl;

public class ResultTableMakerApplication {

    public static void main(String[] args) throws Exception {

        Validator validator = new ValidatorImpl();
        FileInfoReader fileInfoReader = new FileInfoReaderImpl();
        RacerParser racerInfoList = new RacerParserImpl();
        ViewProvider viewProvider = new ViewProviderImpl();
        int outOfQualificationLine = 16;
        StatisticAnalyzer statisticAnalyzer = new StatisticAnalyzer(validator, fileInfoReader, racerInfoList,
                viewProvider, outOfQualificationLine);

        String racer = "abbreviations.txt";
        String start = "start.log";
        String finish = "end.log";

        System.out.println(statisticAnalyzer.provideStatistic(racer, start, finish));
    }
}

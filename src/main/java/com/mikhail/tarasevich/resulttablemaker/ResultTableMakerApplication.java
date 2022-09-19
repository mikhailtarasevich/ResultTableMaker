package com.mikhail.tarasevich.resulttablemaker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;
import com.mikhail.tarasevich.resulttablemaker.provider.FileInfoReader;
import com.mikhail.tarasevich.resulttablemaker.provider.FileInfoReaderImpl;
import com.mikhail.tarasevich.resulttablemaker.provider.RacerParser;
import com.mikhail.tarasevich.resulttablemaker.provider.RacerParserImpl;
import com.mikhail.tarasevich.resulttablemaker.provider.ViewProvider;
import com.mikhail.tarasevich.resulttablemaker.provider.ViewProviderImpl;
import com.mikhail.tarasevich.resulttablemaker.validator.Validator;
import com.mikhail.tarasevich.resulttablemaker.validator.ValidatorImpl;

public class ResultTableMakerApplication { 
    
    public static void main(String[] args) throws Exception {
          
        Validator validator = new ValidatorImpl();   
        FileInfoReader fileInfoReader = new FileInfoReaderImpl();
        RacerParser racerInfoList = new RacerParserImpl();
        ViewProvider viewProvider = new ViewProviderImpl();
        StatisticAnalyzer statisticAnalyzer = new StatisticAnalyzer(validator,fileInfoReader, racerInfoList, viewProvider);
        
        String racer = "src\\main\\resources\\abbreviations.txt";
        String start = "src\\main\\resources\\start.log";
        String finish = "src\\main\\resources\\end.log";

        
        System.out.println(statisticAnalyzer.provideStatistic(racer, start, finish));
        
        
        
        
        
//        Date vre1 = new Date(725000);
//        Date vre2 = new Date(720000);
//        System.out.println(vre1);
//        System.out.println(String.format("%0"+ (20 - "Apple".length() )+"d%s %n",0 ,"Apple"));
//        System.out.println(String.format("Now is %tT",vre1));
//        System.out.println(String.format("Now is %tA %<tB %<tB %<td, %<tY",vre1));
//       
//        Duration t12 = Duration.between(vre1.toInstant(), vre2.toInstant());
//        System.out.println(t12.getNano());
//        
//        //        
//        
//        //Date date = new Date("05/12/222");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
//        Date date = simpleDateFormat.parse("20009-05-05_12:35:33.222");
//        System.out.println(date.getDate());
//        System.out.println(date.getDay());
//        System.out.println(date.getHours());
//        System.out.println(date.getMinutes());
//        System.out.println(date.getSeconds());
//        System.out.println(date.getTimezoneOffset());
//        
//        LocalTime lt = LocalTime.parse("12:35:33.222");
//        System.out.println(lt);
//        
        
//        ViewProvider viewProvider = new ViewProviderImpl();
//        System.out.println(viewProvider.provideResultTableView(racerList));
//        System.out.println("dd");
        
//        System.out.println(racerList.get(0).toString());
//        System.out.println(racerList.get(1).toString());
//
//        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
//        Date time1 = dateTimeFormat.parse(racerList.get(0).getStartTime());
//        Date time2 = dateTimeFormat.parse(racerList.get(0).getFinishTime());
//
//        Duration duration = Duration.between(dateTimeFormat.parse(racerList.get(0).getStartTime()).toInstant(),
//                dateTimeFormat.parse(racerList.get(0).getFinishTime()).toInstant()).abs();
//
//        Duration duration2 = Duration.between(dateTimeFormat.parse(racerList.get(1).getStartTime()).toInstant(),
//                dateTimeFormat.parse(racerList.get(1).getFinishTime()).toInstant()).abs();
//
//        Map<Duration, Racer> racerResultMap = new LinkedHashMap<>();
//        System.out.println("hello");
//        System.out.println(duration.compareTo(duration2));
//        System.out.println("byy");
//
//        racerResultMap.put(duration, racerList.get(0));
//        racerResultMap.put(duration2, racerList.get(1));
//
//
//        System.out.println(racerResultMap.entrySet());
//
//        Map<Duration, Racer> treeMap = new TreeMap<>();
//        treeMap.put(duration, racerList.get(0));
//        treeMap.put(duration2, racerList.get(1));
//        System.out.println(racerResultMap.keySet());
//        System.out.println(treeMap.entrySet());
//        System.out.println();
//        System.out.println();
//        System.out.println("Arra");
//        
//        Set<Entry<Duration, Racer>> gh = treeMap.entrySet();
//        System.out.println(gh.iterator().next().getKey().getClass());
//        System.out.println(gh.iterator().next().getValue().getClass());
//        
//        
//        System.out.println(duration.toHours());
//        System.out.println(duration.toMinutes());
//        System.out.println(duration.getSeconds());
//        System.out.println(duration.toMillis());
//        System.out.println();
//
//        System.out.println(duration.toMinutes());
//        System.out.println(duration.toString());

    }
}

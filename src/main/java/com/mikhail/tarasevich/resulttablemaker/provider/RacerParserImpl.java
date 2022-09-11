package com.mikhail.tarasevich.resulttablemaker.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

public class RacerParserImpl implements RacerParser {

    @Override

 public List<Racer> createRacersList(List<String> racerInfoList, List<String> startTimeList,
            List<String> finishTimeList) {
        
        List<Racer> racerList = new ArrayList<>();
        
        List<String[]> infoListAboutRacers = addRacersInformationToList(racerInfoList);
        Map<String, String> infoListAboutStartTime = addTimeInformationToMap(startTimeList);
        Map<String, String> infoListAboutFinishTime = addTimeInformationToMap(finishTimeList);
        
        for(int i = 0; i < racerInfoList.size(); i++) {         
            
            Racer racer = new Racer.Builder().racerName(infoListAboutRacers.get(i)[1])
                    .teamName(infoListAboutRacers.get(i)[2])
                    .startTime(infoListAboutStartTime.get(infoListAboutRacers.get(i)[0]))
                    .finishTime(infoListAboutFinishTime.get(infoListAboutRacers.get(i)[0]))
                    .build();
            racerList.add(racer);
        }
                
        return racerList;
    }
    
    private List<String[]> addRacersInformationToList(List<String> racerList) {
        
        List<String[]> racersList = new ArrayList<>();
               
        for(int i = 0; i < racerList.size(); i++) {           
            racersList.add(racerList.get(i).split("_"));
        }
        
        return racersList;
    }
    
    private Map<String, String> addTimeInformationToMap(List<String> timeList) {
        
        Map<String, String> timeMap = new HashMap<>();
        
        for (int i = 0; i < timeList.size(); i++) {
            timeMap.put(timeList.get(i).substring(0, 3), timeList.get(i).substring(3));
        }
        
        return timeMap;
    }
    
}

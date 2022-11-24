package com.mikhail.tarasevich.resulttablemaker.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

public class RacerParserImpl implements RacerParser {
    
    private static final String TIME_FORMAT = "yyyy-MM-dd_HH:mm:ss.SSS";
    
    @Override
    public List<Racer> createRacersList(List<String> racerInfoList, List<String> startTimeList,
            List<String> finishTimeList){

        Map<String, String> racerNameMap = addRacerNameToMap(racerInfoList);
        Map<String, String> teamNameMap = addTeamNameToMap(racerInfoList);
        Map<String, LocalDateTime> startTimeMap = addTimeToMap(startTimeList);
        Map<String, LocalDateTime> finishTimeMap = addTimeToMap(finishTimeList);

        return racerNameMap.keySet()
                           .stream()
                           .map(key -> Racer.Builder().racerName(racerNameMap.get(key)).teamName(teamNameMap.get(key))
                           .startTime(startTimeMap.get(key)).finishTime(finishTimeMap.get(key)).build())
                           .collect(Collectors.toList());
    }

    private Map<String, String> addRacerNameToMap(List<String> racerList) {

        return racerList.stream()
                        .map(s -> s.split("_"))
                        .collect(Collectors.toMap(s -> s[0], s -> s[1]));
    }

    private Map<String, String> addTeamNameToMap(List<String> racerList) {

        return racerList.stream()
                        .map(s -> s.split("_"))
                        .collect(Collectors.toMap(s -> s[0], s -> s[2]));
    }

    private Map<String, LocalDateTime> addTimeToMap(List<String> timeList) {

        return timeList.stream()
                       .collect(Collectors.toMap(s -> s.substring(0, 3), s -> convertStringTimeToDateTime(s.substring(3))));
    }

    private LocalDateTime convertStringTimeToDateTime(String stringTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        return LocalDateTime.parse(stringTime, formatter);
    }

}

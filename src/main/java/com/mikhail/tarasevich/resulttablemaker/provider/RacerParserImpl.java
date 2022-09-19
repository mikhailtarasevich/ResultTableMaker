package com.mikhail.tarasevich.resulttablemaker.provider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

public class RacerParserImpl implements RacerParser {

    @Override
    public Set<Racer> createRacersList(List<String> racerInfoList, List<String> startTimeList, List<String> finishTimeList) throws ParseException {

        Set<Racer> racerList = new HashSet<>();
        Map<String, String> racerNameMap = addRacerNameToMap(racerInfoList);
        Map<String, String> teamNameMap = addTeamNameToMap(racerInfoList);
        Map<String, String> startTimeMap = addTimeToMap(startTimeList);
        Map<String, String> finishTimeMap = addTimeToMap(finishTimeList);

        for (String key : racerNameMap.keySet()) {
            Racer racer = Racer.Builder()
                               .racerName(racerNameMap.get(key)).teamName(teamNameMap.get(key))
                               .startTime(convertStringTimeToDateTime(startTimeMap.get(key)))
                               .finishTime(convertStringTimeToDateTime(finishTimeMap.get(key))).build();
            racerList.add(racer);
        }

        return racerList;
    }

    private Map<String, String> addRacerNameToMap(List<String> racerList) {

        Map<String, String> nameMap = new HashMap<>();

        for (String string : racerList) {
            nameMap.put(string.split("_")[0], string.split("_")[1]);
        }

        return nameMap;
    }

    private Map<String, String> addTeamNameToMap(List<String> racerList) {

        Map<String, String> nameMap = new HashMap<>();

        for (String string : racerList) {
            nameMap.put(string.split("_")[0], string.split("_")[2]);
        }

        return nameMap;
    }

    private Map<String, String> addTimeToMap(List<String> timeList) {

        Map<String, String> timeMap = new HashMap<>();

        for (int i = 0; i < timeList.size(); i++) {
            timeMap.put(timeList.get(i).substring(0, 3), timeList.get(i).substring(3));
        }

        return timeMap;
    }

    private Date convertStringTimeToDateTime(String stringTime) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        
        return simpleDateFormat.parse(stringTime);
    }

}

package com.mikhail.tarasevich.resulttablemaker.provider;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

public interface RacerParser {
    Set<Racer> createRacersList(List<String> racerInfoList, List<String> startTimeList,List<String> finishTimeList) throws ParseException;
}

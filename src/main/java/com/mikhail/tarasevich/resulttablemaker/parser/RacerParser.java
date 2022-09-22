package com.mikhail.tarasevich.resulttablemaker.parser;

import java.text.ParseException;
import java.util.List;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

public interface RacerParser {
    List<Racer> createRacersList(List<String> racerInfoList, List<String> startTimeList,List<String> finishTimeList) throws ParseException;
}

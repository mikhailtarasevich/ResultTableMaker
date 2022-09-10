package com.mikhail.tarasevich.resulttablemaker.provider;

import java.util.List;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

public interface RaceParcer {
    List<Racer> createRacersList(List<String> racerInfoList, List<String> startTimeList,List<String> finishTimeList);
}

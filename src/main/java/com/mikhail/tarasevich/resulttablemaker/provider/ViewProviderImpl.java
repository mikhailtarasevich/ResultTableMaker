package com.mikhail.tarasevich.resulttablemaker.provider;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

public class ViewProviderImpl implements ViewProvider {

    @Override
    public String provideResultTableView(List<Racer> racerList) throws ParseException {
        String resultTable = "";
            
        Set<Entry<Duration, Racer>> lapTimeRacerSet = calculateLapTimeAndCreateMapInOrderFromFastestLap(racerList).entrySet();
      
        while(lapTimeRacerSet.iterator().hasNext()) {
            resultTable += lapTimeRacerSet.iterator().next().getKey() + lapTimeRacerSet.iterator().next().toString() + "\n";              
        }
        return resultTable;
    }
    
    private Map<Duration, Racer> calculateLapTimeAndCreateMapInOrderFromFastestLap (List<Racer> racerList) throws ParseException{
        
        Map<Duration, Racer> racersMapInOrderFromFastestLap = new TreeMap<>();
                
        for(int i = 0; i < racerList.size(); i++) {
            Duration lapTime = Duration.between(startTime.toInstant(), finishTime.toInstant()).abs();
            racersMapInOrderFromFastestLap.put(lapTime, racerList.get(i));
        }
                
        return racersMapInOrderFromFastestLap;
    }
    
}

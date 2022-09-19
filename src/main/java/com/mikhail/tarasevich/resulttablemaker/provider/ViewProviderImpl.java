package com.mikhail.tarasevich.resulttablemaker.provider;

import java.time.Duration;
import java.util.Set;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

public class ViewProviderImpl implements ViewProvider {
    
    private static final String LINE_BRAKE = "\n";
    private static final String NOTHING = "";
    private static final String MINUS = "-";
    private static final String COLON = ":";
    private static final String DOT = "."; 
    
    @Override
    public StringBuilder provideResultTableView(Set<Racer> racerList) {
        StringBuilder resultTable = new StringBuilder();
        int position = 0;
        int racerNameWidthColumn = calculateRacerNameColumnWidth(racerList);
        int teamNameWidthColumn = calculateTeamNameColumnWidth(racerList);
        
        resultTable.append(String.format("%-10s%-" + racerNameWidthColumn + "s%-" + teamNameWidthColumn + "s%-10s"
                    ,"Position", "Racer name", "Team name", "Time"));
        resultTable.append(LINE_BRAKE);
        
        int tableWidth = resultTable.length();
        
        for (Racer racer : racerList) {
            position++;
            LapTime lapTime = new LapTime(racer.getTimeOfLap());
            resultTable.append(String.format("%-10d%-" + racerNameWidthColumn + "s%-" + teamNameWidthColumn + "s%s%s%s%s%s"
                    ,position, racer.getRacerName(), racer.getTeamName() 
                    ,lapTime.getDays(), lapTime.getHours(), lapTime.getMinutes(), lapTime.getSeconds(), lapTime.getMilliseconds()));
            resultTable.append(LINE_BRAKE);
            if (position == 15) { 
                for(int i = 0; i < tableWidth; i++) resultTable.append(MINUS);
                resultTable.append(LINE_BRAKE);
            }
        }
        
        return resultTable;
    }
    
    private class LapTime{
        private long days;
        private long hours;
        private int minutes;
        private int seconds;
        private int milliseconds;
        
        LapTime(Duration time){
            this.days = (int)time.toDays();
            this.hours = (int)time.toHours();
            this.minutes = (int)time.toMinutes();
            this.seconds = (int)(time.toMillis()/1000 - time.toMinutes()*60);
            this.milliseconds = (int)time.toMillis()%1000;
        }
        
        private String getDays() {
            if(days == 0) return NOTHING;
            if(days == 1) return String.valueOf(days) + " day ";
            return String.valueOf(days) + " days ";
        }
        
        private String getHours() {
            if(hours == 0) return NOTHING;         
            return String.format("%02d", hours) + COLON;
        }
        
        private String getMinutes() {
            if(minutes == 0) return NOTHING;
            return String.format("%02d", minutes) + COLON;
        }
        
        private String getSeconds() {
            if(seconds == 0) return NOTHING;
            return String.format("%02d", seconds) + DOT;
        }
        
        public String getMilliseconds() {
            if(milliseconds == 0) return NOTHING;
            return String.format("%03d", milliseconds);
        }
        
    }
    
    private int calculateRacerNameColumnWidth (Set<Racer> set) {
        int racerNameWidthColumn = 0;
        for (Racer racer : set) {
            int lineLength = racer.getRacerName().length();
            if (lineLength > racerNameWidthColumn) racerNameWidthColumn = lineLength;
        }
        return racerNameWidthColumn + 4;    
    }
    
    private int calculateTeamNameColumnWidth (Set<Racer> set) {
        int teamNameWidthColumn = 0;
        for (Racer racer : set) {
            int lineLength = racer.getTeamName().length();
            if (lineLength > teamNameWidthColumn) teamNameWidthColumn = lineLength;
        }
        return teamNameWidthColumn + 4;    
    }
    
}

//public class ViewProviderImpl implements ViewProvider {
//private static final String LINE_BRAKE = "\n";
//private static final String MINUS = "--";
//
//@Override
//public StringBuilder provideResultTableView(Set<Racer> racerList) {
//  StringBuilder resultTable = new StringBuilder();
//  int position = 0;
//  int tableWidth = resultTable.length();
//  
//  resultTable.append(String.format("%-10s%-20s%-30s%-20s", "Position", "Racer name", "Team name", "Time"));
//  resultTable.append(LINE_BRAKE);
//  
//  for (Racer racer : racerList) {
//      position++;
//      Date timeLap = new Date(racer.getTimeOfLap().minusHours(3).toMillis());
//      resultTable.append(String.format("%-10d%-20s%-30s%tT"
//              ,position, racer.getRacerName(), racer.getTeamName() 
//              ,timeLap));
//      resultTable.append(LINE_BRAKE);
//      if (position == 15) { 
//          for(int i = 0; i < tableWidth; i++) resultTable.append(MINUS);
//          resultTable.append(LINE_BRAKE);
//      }
//  }
//
//  return resultTable;
//}
//
//}

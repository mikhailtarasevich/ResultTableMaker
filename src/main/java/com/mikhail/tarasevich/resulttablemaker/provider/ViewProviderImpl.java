package com.mikhail.tarasevich.resulttablemaker.provider;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

public class ViewProviderImpl implements ViewProvider {

    private static final String LINE_BRAKE = "\n";
    private static final String MINUS = "-";
    private static final String COLON = ":";
    private static final String DOT = ".";

    @Override
    public String provideResultTableView(List<Racer> racerList, int outOfQualificationLine) {

        StringBuilder resultTable = new StringBuilder();
        int position = 0;
        int racerNameWidthColumn = calculateRacerNameColumnWidth(racerList);
        int teamNameWidthColumn = calculateTeamNameColumnWidth(racerList);
        String template = "%-10s%-" + racerNameWidthColumn + "s%-" + teamNameWidthColumn + "s%-10s";

        resultTable.append(String.format(template, "Position", "Racer name", "Team name", "Time"));
        resultTable.append(LINE_BRAKE);

        int tableWidth = resultTable.length();

        for (Racer racer : racerList) {
            position++;
            resultTable.append(String.format(template
                       ,position, racer.getRacerName(), racer.getTeamName(), durationTimeToStringTime(racer.getTimeOfLap())));
            resultTable.append(LINE_BRAKE);
            if (position == outOfQualificationLine) { 
                for(int i = 0; i < tableWidth; i++) resultTable.append(MINUS);
                resultTable.append(LINE_BRAKE);
            }
        }

        return resultTable.toString();
    }

    private String durationTimeToStringTime(Duration time) {

        StringBuilder timeString = new StringBuilder();
        long days = (int) time.toDays();
        long hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) (time.toMillis() / 1000 - time.toMinutes() * 60);
        int milliseconds = (int) time.toMillis() % 1000;

        if (days == 1)
            timeString.append(String.valueOf(days) + " day ");
        if (days != 0 && days != 1)
            timeString.append(String.valueOf(days) + " days ");
        if (hours != 0)
            timeString.append(String.format("%02d", hours) + COLON);
        timeString.append(String.format("%02d", minutes) + COLON);
        timeString.append(String.format("%02d", seconds) + DOT);
        timeString.append(String.format("%03d", milliseconds));

        return timeString.toString();
    }

    private int calculateRacerNameColumnWidth(List<Racer> set) {

        return set.stream().map(s -> s.getRacerName().length()).max(Integer::compareTo)
                .orElseThrow(NoSuchElementException::new) + 4;
    }

    private int calculateTeamNameColumnWidth(List<Racer> set) {

        return set.stream().map(s -> s.getTeamName().length()).max(Integer::compareTo)
                .orElseThrow(NoSuchElementException::new) + 4;
    }

}

package com.mikhail.tarasevich.resulttablemaker.provider;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

public class ViewProviderImpl implements ViewProvider {

    private static final String LINE_BRAKE = "\n";
    private static final String MINUS = "-";
    private static final String COLON = ":";
    private static final String DOT = ".";
    private static final String TEMPLATE = "%-10s%-30s%-30s%-10s\n";

    @Override
    public String provideResultTableView(List<Racer> racerList, int outOfQualificationLine) {

        AtomicInteger counter = new AtomicInteger();

        return racerList.stream().map(racer -> {
            StringBuilder resultTable = new StringBuilder();
            counter.getAndIncrement();
            if (counter.get() == 1) {
                resultTable.append(String.format(TEMPLATE, "Position", "Racer name", "Team name", "Time"));
            }
            if (counter.get() == outOfQualificationLine + 1) {
                resultTable.append(qualificationLineMaker());
            }
            return resultTable.append(tableLineMaker(counter.get(), racer));
        }).collect(Collectors.joining());

    }

    private String tableLineMaker(int position, Racer racer) {

        StringBuilder resultTable = new StringBuilder();

        return resultTable.append(String.format(TEMPLATE, position, racer.getRacerName(), racer.getTeamName(),
                durationTimeToStringTime(racer.getTimeOfLap()))).toString();
    }

    private String qualificationLineMaker() {

        StringBuilder resultTable = new StringBuilder();

        int i = 0;
        while (i <= 78) {
            resultTable.append(MINUS);
            if (i == 78) resultTable.append(LINE_BRAKE);
            i++;
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

        if (days == 1) timeString.append(String.valueOf(days) + " day ");
        if (days != 0 && days != 1) timeString.append(String.valueOf(days) + " days ");
        if (hours != 0) timeString.append(String.format("%02d", hours) + COLON);
        timeString.append(String.format("%02d", minutes) + COLON);
        timeString.append(String.format("%02d", seconds) + DOT);
        timeString.append(String.format("%03d", milliseconds));

        return timeString.toString();
    }

}

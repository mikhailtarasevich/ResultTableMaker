package com.mikhail.tarasevich.resulttablemaker.provider;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;

class ViewProviderImplTest {
    
    private static final String TIME_FORMAT = "yyyy-MM-dd_HH:mm:ss.SSS";
    
    @Test
    void provideResultTableView_inputAreLisOfDivisionStepAndTwoIntegerDigit_expectedStringOfDivision1() {
        
        ViewProvider viewProvider = new ViewProviderImpl();
        final int outOfQualificationLine = 1;
        final List<Racer> racerList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        
        LocalDateTime start = LocalDateTime.parse("2022-09-27_10:00:00.000", formatter);
        LocalDateTime finish = LocalDateTime.parse("2022-09-27_10:01:00.000", formatter); 
        final Racer racer1 = Racer.Builder().racerName("Daniel Ricciardo").teamName("RED BULL RACING TAG HEUER")
                .startTime(start).finishTime(finish).build();
        
        start = LocalDateTime.parse("2022-09-27_10:03:00.000", formatter);
        finish = LocalDateTime.parse("2022-09-28_10:08:00.000", formatter);
        final Racer racer2 = Racer.Builder().racerName("Sebastian Vettel").teamName("FERRARI")
                .startTime(start).finishTime(finish).build();
        
        start = LocalDateTime.parse("2022-09-27_09:00:00.000", formatter);
        finish = LocalDateTime.parse("2022-09-29_10:01:00.000", formatter);
        final Racer racer3 = Racer.Builder().racerName("Lewis Hamilton").teamName("MERCEDES")
                .startTime(start).finishTime(finish).build();

        racerList.add(racer1);
        racerList.add(racer2);
        racerList.add(racer3);

        final String actual = viewProvider.provideResultTableView(racerList, outOfQualificationLine);
        final String expected = "Position  Racer name                    Team name                     Time      \n" + 
                                "1         Daniel Ricciardo              RED BULL RACING TAG HEUER     01:00.000 \n" + 
                                "-------------------------------------------------------------------------------\n" + 
                                "2         Sebastian Vettel              FERRARI                       1 day 24:1445:00.000\n" + 
                                "3         Lewis Hamilton                MERCEDES                      2 days 49:2941:00.000\n";

        assertThat(actual).isEqualTo(expected);
    }

}

package com.mikhail.tarasevich.resulttablemaker;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.mikhail.tarasevich.resulttablemaker.domain.Racer;
import com.mikhail.tarasevich.resulttablemaker.provider.FileInfoReader;
import com.mikhail.tarasevich.resulttablemaker.provider.FileInfoReaderImpl;

public class ResultTableMakerApplication {
   
    public static void main(String[] args) throws FileNotFoundException {
        
        Racer racer = new Racer.Builder().racerName("TsLox")
                                         .teamName("ef")
                                         .startTime("fef")
                                         .build();
        
        FileInfoReader reader = new FileInfoReaderImpl();
        List<String> startTime = new ArrayList<>();
        startTime = reader.readInfoFromFile("C:\\workspace\\ResultTable\\target\\end.log");
        
        System.out.println(racer);
        for(int i = 0; i < startTime.size(); i++) {
            System.out.println(startTime.get(i));
        }
    }
}

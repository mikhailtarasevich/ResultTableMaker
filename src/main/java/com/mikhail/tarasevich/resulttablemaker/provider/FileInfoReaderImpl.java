package com.mikhail.tarasevich.resulttablemaker.provider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileInfoReaderImpl implements FileInfoReader {

    @Override
    public List<String> readInfoFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        List<String> textFromFileInString = new ArrayList<>();
        Scanner scanner = new Scanner(file); 
        
        while(scanner.hasNextLine()) textFromFileInString.add(scanner.nextLine());
        scanner.close();
        return textFromFileInString; 
    }
}

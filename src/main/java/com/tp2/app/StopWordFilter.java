package com.tp2.app;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class StopWordFilter {
    List<String> stopWords = new ArrayList<>();
    StopWordFilter(WordFrequencyFramework wfapp){
        wfapp.registerForLoadEvent((Consumer<String>) this::load);
    }
    public void load(String pathToFile) {
        try {
            BufferedReader br =new BufferedReader(new FileReader(new File("src/main/resources/stopWords.txt")));
            String linha;
            while((linha = br.readLine()) != null){
                stopWords.addAll(Arrays.stream(linha.split(",")).toList());
            }
            System.out.println(stopWords.toString());
            System.out.println("Stop Words OK");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isStopWord(String word){
        return stopWords.contains(word);
    }
}


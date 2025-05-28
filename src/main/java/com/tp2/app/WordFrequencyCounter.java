package com.tp2.app;

import java.util.*;
import java.util.stream.*;

public class WordFrequencyCounter {
    private final Map<String, Integer> wordFreqs = new HashMap<>();

    public WordFrequencyCounter(WordFrequencyFramework wfapp, DataStorage dataStorage) {
        dataStorage.registerForWordEvent(this::incrementCount);
        wfapp.registerForEndEvent(this::printFreqs);
    }

    public void incrementCount(String word) {
        wordFreqs.put(word, wordFreqs.getOrDefault(word, 0) + 1);
    }

    public void printFreqs() {
        // Limpa o array result antes de preencher
        // Cria uma lista temporária para armazenar os resultados
        List<String> tempResults = wordFreqs.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                .limit(25)
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.toList());

        // Imprime no console

        tempResults.forEach(System.out::println);

        // Copia para o array result
        for (int i = 0; i < tempResults.size() && i < TP2Application.result.length; i++) {
            if (tempResults.get(i) != null) {
                TP2Application.result[i] = tempResults.get(i);
            }

        }
    }
}
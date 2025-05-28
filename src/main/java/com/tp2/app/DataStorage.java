package com.tp2.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class DataStorage {
    String data = "";
    StopWordFilter stopWordFilter;
    List<Consumer<String>> wordEventsHandlers = new ArrayList<>();

    public DataStorage(WordFrequencyFramework wfapp, StopWordFilter stopWordFilter) {
        this.stopWordFilter = stopWordFilter;
        wfapp.registerForLoadEvent(this::load);
        wfapp.registerForDoworkEvent(this::produceWords);
    }

    // Carrega o conteúdo do arquivo e limpa os caracteres não alfabéticos
    public void load(String pathToFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(pathToFile)))) {
            StringBuilder content = new StringBuilder();
            String linha;
            while ((linha = br.readLine()) != null) {
                // Corrigindo a regex para preservar palavras com apóstrofos
                content.append(linha.replaceAll("[^a-zA-Z']", " ").toLowerCase())
                        .append(" ");
            }
            data = content.toString().trim();
            System.out.println("Dados processados: " + data.substring(0, Math.min(50, data.length())) + "...");
        } catch (Exception e) {
            throw new RuntimeException("Falha ao carregar arquivo: " + e.getMessage());
        }
    }

    // Produz as palavras (excluindo as stop words) e dispara os eventos
    public void produceWords() {
        if (data == null || data.isEmpty()) {
            System.out.println("Nenhum dado para processar!");
            return;
        }

        Arrays.stream(data.split("\\s+"))
                .filter(word -> word.length() > 1) // Ignora palavras de 1 caractere
                .peek(word -> System.out.println("Antes do filtro: " + word))
                .filter(word -> !stopWordFilter.isStopWord(word))
                .peek(word -> System.out.println("Enviando para contagem: " + word))
                .forEach(word -> wordEventsHandlers.forEach(h -> h.accept(word)));
    }

    // Permite que outras classes registrem funções para receber as palavras
    public void registerForWordEvent(Consumer<String> handler) {
        wordEventsHandlers.add(handler);
    }
}

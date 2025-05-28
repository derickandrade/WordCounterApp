package com.tp2.app;

public class Main {
    public static void main(String[] args) {
        WordFrequencyFramework wfapp = new WordFrequencyFramework();
        System.out.println("WFF OK");
        StopWordFilter stopWordFilter = new StopWordFilter(wfapp);
        System.out.println("SWF OK");
        DataStorage dataStorage = new DataStorage(wfapp,stopWordFilter);
        System.out.println("DS OK");
        WordFrequencyCounter wordFrequencyCounter = new WordFrequencyCounter(wfapp,dataStorage);
        System.out.println("WFC OK");
        wfapp.run(TP2Application.textPath);
        // No Main.main():
        System.out.println("Handlers Load: " + wfapp.LoadEventHandlers.size());
        System.out.println("Handlers Work: " + wfapp.DoworkEventHandlers.size());
        System.out.println("Handlers End: " + wfapp.EndEventHandlers.size());
    }
}

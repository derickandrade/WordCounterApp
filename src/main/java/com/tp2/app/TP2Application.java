package com.tp2.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TP2Application extends Application {

    public static String textPath;
    public static String[] result = new String[25];
    public static String stopWordPath;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TP2Application.class.getResource("tp2-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 940, 600);
        stage.setTitle("Word Counter");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
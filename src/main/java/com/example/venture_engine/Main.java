package com.example.venture_engine;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public void start(Stage stage) throws IOException {
        View v = new View();
        Controller c = new Controller();
        v.setController(c);
        v.initialize(stage);
        c.setView(v);
        c.initialize();
    }

    public static void main(String[] args) {
        launch();
    }
}

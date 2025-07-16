package com.example.venture_engine;

import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


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

    /*
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("1. Continue,  2. New Game");
        int input = s.nextInt();
        Model adventure = new Model();
        switch(input) {
            case 1:
                adventure.NewGame_or_Load();
                adventure.play();
                break;
            case 2:
                adventure.new_Game = true;
                adventure.NewGame_or_Load();
                adventure.play();
        }
    }
     */
}

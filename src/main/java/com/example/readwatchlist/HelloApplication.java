package com.example.readwatchlist;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        HelloController helloController = new HelloController();

        // Create the tabs
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab booksToReadTab = helloController.createBooksToReadTab();
        tabPane.getTabs().add(booksToReadTab);
        Tab moviesToWatchTab = helloController.createMoviesToWatchTab();
        tabPane.getTabs().add(moviesToWatchTab);

        // Create Scene
        Scene scene = new Scene(tabPane);

        // Configure Stage
        stage.setTitle("Read and Watch List");
        stage.setWidth(600);
        stage.setHeight(500);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        Application.launch(HelloApplication.class);
    }
}
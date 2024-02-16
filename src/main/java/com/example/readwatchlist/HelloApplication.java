package com.example.readwatchlist;

import com.example.readwatchlist.domain.Book;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

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
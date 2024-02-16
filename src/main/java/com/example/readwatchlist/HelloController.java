package com.example.readwatchlist;

import com.example.readwatchlist.domain.Book;
import com.example.readwatchlist.domain.Movie;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tab;

public class HelloController {

    File file;
    Scanner fileReader;
    PrintWriter printWriter;
    ObservableList<Book> booksToRead;
    ObservableList<Movie> moviesToWatch;

    public HelloController() {

        file = new File("read_watch_list.txt");
        booksToRead = FXCollections.observableArrayList();
        moviesToWatch = FXCollections.observableArrayList();

        loadFromFile();

    }

    protected void loadFromFile() {
        try {
            fileReader = new Scanner(file);
            while(fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                String[] rowParts = row.split(":::");
                if (rowParts[0].equals("BOOK")) {
                    String title = rowParts[1];
                    String author = rowParts[2];
                    Book book = new Book(title, author);
                    booksToRead.add(book);
                }
                if (rowParts[0].equals("MOVIE")) {
                    String title = rowParts[1];
                    String location = rowParts[2];
                    Movie movie = new Movie(title, location);
                    moviesToWatch.add(movie);
                }
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Unable to read file");
        }
    }

    protected void saveToFile() {
        try {
            printWriter = new PrintWriter(file);
            for (Book book : booksToRead) {
                printWriter.println("BOOK:::" + book.getTitle() + ":::" + book.getAuthor());
            }
            for (Movie movie : moviesToWatch) {
                printWriter.println("MOVIE:::" + movie.getTitle() + ":::" + movie.getLocation());
            }
            printWriter.close();
        } catch (Exception e) {
            System.out.println("Error saving to file.");
        }
    }

    protected Tab createBooksToReadTab() {

        // Create Table
        TableView<Book> bookTable = new TableView<>();
        bookTable.setEditable(false);
        bookTable.setPlaceholder(new Label("No books in reading list"));
        TableColumn<Book, String> bookTitleColumn = new TableColumn<>("Title");
        TableColumn<Book, String> bookAuthorColumn = new TableColumn<>("Author");
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        bookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        bookTable.getColumns().add(bookTitleColumn);
        bookTable.getColumns().add(bookAuthorColumn);
        bookTable.setColumnResizePolicy(bookTable.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);


        // Add data to the Table
        bookTable.setItems(booksToRead);

        // Create input Textfields
        TextField bookTitleInput = new TextField();
        bookTitleInput.setPromptText("Book title");
        TextField bookAuthorInput = new TextField();
        bookAuthorInput.setPromptText("Book author");
        Button addBookInputButton = new Button("Add");
        Button deleteBookButton = new Button("Delete");
        HBox newBookTextInputContainer = new HBox();
        newBookTextInputContainer.setSpacing(5);
        newBookTextInputContainer.getChildren().add(bookTitleInput);
        newBookTextInputContainer.getChildren().add(bookAuthorInput);
        newBookTextInputContainer.getChildren().add(addBookInputButton);
        newBookTextInputContainer.getChildren().add(deleteBookButton);

        // Create VBox
        VBox bookVbox = new VBox();
        bookVbox.setSpacing(5);
        bookVbox.setPadding(new Insets(10, 10, 10, 10));
        bookVbox.getChildren().add(bookTable);
        bookVbox.getChildren().add(newBookTextInputContainer);

        // Configure action handlers
        bookTable.setRowFactory(tableView -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Book clickedBook = row.getItem();
                if (clickedBook == null) {
                    return;
                }
                bookTitleInput.setText(clickedBook.getTitle());
                bookAuthorInput.setText(clickedBook.getAuthor());
            });
            return row;
        });
        bookTitleInput.setOnAction(event -> {
            this.addBook(bookTitleInput, bookAuthorInput, booksToRead);
            saveToFile();
        });
        bookAuthorInput.setOnAction(event -> {
            this.addBook(bookTitleInput, bookAuthorInput, booksToRead);
            saveToFile();
        });
        addBookInputButton.setOnAction(event -> {
            this.addBook(bookTitleInput, bookAuthorInput, booksToRead);
            saveToFile();
        });
        deleteBookButton.setOnAction(event -> {
            String deleteTitle = bookTitleInput.getText();
            String deleteAuthor = bookAuthorInput.getText();
            Book bookToDelete = new Book(deleteTitle, deleteAuthor);
            booksToRead.remove(bookToDelete);
            saveToFile();
        });

        // Create Tab
        Tab booksToReadTab = new Tab();
        booksToReadTab.setText("Books to read");
        booksToReadTab.setContent(bookVbox);

        return booksToReadTab;
    }

    private void addBook(TextField bookTitleInput, TextField bookAuthorInput, ObservableList<Book> booksToRead) {
        String newTitle = bookTitleInput.getText();
        String newAuthor = bookAuthorInput.getText();
        Book newBook = new Book(newTitle, newAuthor);
        if (newTitle.length() > 0 && newAuthor.length() > 0 && !booksToRead.contains(newBook)) {
            booksToRead.add(newBook);
            bookTitleInput.clear();
            bookAuthorInput.clear();
            bookTitleInput.requestFocus();
        }
    }

    protected Tab createMoviesToWatchTab() {

        // Create Table
        TableView<Movie> movieTable = new TableView<>();
        movieTable.setEditable(false);
        movieTable.setPlaceholder(new Label("No movies in watch list"));
        TableColumn<Movie, String> movieTitleColumn = new TableColumn<>("Title");
        TableColumn<Movie, String> movieLocationColumn = new TableColumn<>("Location");
        movieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        movieLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        movieTable.getColumns().add(movieTitleColumn);
        movieTable.getColumns().add(movieLocationColumn);
        movieTable.setColumnResizePolicy(movieTable.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        // Add data to the Table
        movieTable.setItems(moviesToWatch);

        // Create input Textfields
        TextField movieTitleInput = new TextField();
        movieTitleInput.setPromptText("Movie title");
        TextField movieLocationInput = new TextField();
        movieLocationInput.setPromptText("Movie location");
        Button addMovieInputButton = new Button("Add");
        Button deleteMovieButton = new Button("Delete");
        HBox newMovieTextInputContainer = new HBox();
        newMovieTextInputContainer.setSpacing(5);
        newMovieTextInputContainer.getChildren().add(movieTitleInput);
        newMovieTextInputContainer.getChildren().add(movieLocationInput);
        newMovieTextInputContainer.getChildren().add(addMovieInputButton);
        newMovieTextInputContainer.getChildren().add(deleteMovieButton);

        // Create VBox
        VBox movieVbox = new VBox();
        movieVbox.setSpacing(5);
        movieVbox.setPadding(new Insets(10, 10, 10, 10));
        movieVbox.getChildren().add(movieTable);
        movieVbox.getChildren().add(newMovieTextInputContainer);

        // Configure action handlers
        movieTable.setRowFactory(tableView -> {
            TableRow<Movie> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Movie clickedMovie = row.getItem();
                if (clickedMovie == null) {
                    return;
                }
                movieTitleInput.setText(clickedMovie.getTitle());
                movieLocationInput.setText(clickedMovie.getLocation());
            });
            return row;
        });
        movieTitleInput.setOnAction(event -> {
            addMovie(movieTitleInput, movieLocationInput, moviesToWatch);
            saveToFile();
        });
        movieLocationInput.setOnAction(event -> {
            addMovie(movieTitleInput, movieLocationInput, moviesToWatch);
            saveToFile();
        });
        addMovieInputButton.setOnAction(event -> {
            addMovie(movieTitleInput, movieLocationInput, moviesToWatch);
            saveToFile();
        });
        deleteMovieButton.setOnAction(event -> {
            String deleteTitle = movieTitleInput.getText();
            String deleteLocation = movieLocationInput.getText();
            Movie movieToDelete = new Movie(deleteTitle, deleteLocation);
            moviesToWatch.remove(movieToDelete);
            saveToFile();
        });

        // Create Tab
        Tab moviesToWatchTab = new Tab();
        moviesToWatchTab.setText("Movies to watch");
        moviesToWatchTab.setContent(movieVbox);

        return moviesToWatchTab;
    }

    private void addMovie(TextField movieTitleInput, TextField movieLocationInput, ObservableList<Movie> moviesToWatch) {
        String newTitle = movieTitleInput.getText();
        String newLocation = movieLocationInput.getText();
        Movie newMovie = new Movie(newTitle, newLocation);
        if (newTitle.length() > 0 && newLocation.length() > 0 && !moviesToWatch.contains(newMovie)) {
            moviesToWatch.add(newMovie);
            movieTitleInput.clear();
            movieLocationInput.clear();
            movieTitleInput.requestFocus();
        }
    }

}
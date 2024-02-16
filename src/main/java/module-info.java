module com.example.readwatchlist {
    requires javafx.controls;
    requires javafx.base;


    opens com.example.readwatchlist to javafx.fxml;
    exports com.example.readwatchlist;

    opens com.example.readwatchlist.domain to javafx.fxml;
    exports com.example.readwatchlist.domain;
}
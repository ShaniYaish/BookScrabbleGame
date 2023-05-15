module view.bookscrabble {
    requires javafx.controls;
    requires javafx.fxml;


    opens view.bookscrabble to javafx.fxml;
    exports view.bookscrabble;
}
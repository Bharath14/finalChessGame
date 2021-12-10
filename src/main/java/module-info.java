module com.example.finalchessgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finalchessgame to javafx.fxml;
    exports com.example.finalchessgame;
}
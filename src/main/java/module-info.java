module com.example.hellofx3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hellofx3 to javafx.fxml;
    exports com.example.hellofx3;
}
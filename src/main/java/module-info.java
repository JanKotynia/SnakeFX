module com.example.snakefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.snakefx to javafx.fxml;
    exports com.example.snakefx;
}
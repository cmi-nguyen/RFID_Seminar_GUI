module com.example.rfid_seminar_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens App to javafx.fxml;
    exports App;
}
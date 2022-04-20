module com.example.rfid_seminar_gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rfid_seminar_gui to javafx.fxml;
    exports com.example.rfid_seminar_gui;
}
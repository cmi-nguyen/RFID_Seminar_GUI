module RFID.Seminar {
    requires java.sql;
    requires javafx.fxml;
    requires javafx.controls;
    requires CAENRFIDLibrary;



    opens DTO;
    exports DTO to java.base;
    exports UI;
    opens UI to javafx.fxml;

}
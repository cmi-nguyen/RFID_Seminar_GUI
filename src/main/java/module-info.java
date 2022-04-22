module RFID.Seminar {
    requires java.sql;
    requires javafx.fxml;
    requires javafx.controls;
    requires CAENRFIDLibrary;




    exports UI;
    opens UI to javafx.fxml;

}
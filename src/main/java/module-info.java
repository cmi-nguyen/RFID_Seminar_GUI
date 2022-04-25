module RFID.Seminar {
    requires java.sql;
    requires javafx.fxml;
    requires javafx.controls;
    requires CAENRFIDLibrary;



    opens DTO;
    exports DTO to java.base; // update module so that DTO can be used in UI

    exports UI;
    opens UI to javafx.fxml;

}
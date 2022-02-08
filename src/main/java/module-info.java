module com.example.szotaksimon_etlap {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.szotaksimon_etlap to javafx.fxml;
    exports com.example.szotaksimon_etlap;
}
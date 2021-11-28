module com.example.netcracker_lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires lombok;
    requires java.sql;

    opens com.example.netcracker_lab to javafx.fxml;
    exports com.example.netcracker_lab;
    exports com.example.netcracker_lab.controller;
    opens com.example.netcracker_lab.controller to javafx.fxml;
}
open module com.example.netcracker_lab {
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
    requires jackson.core;
    requires jackson.databind;
    requires jackson.annotations;

    exports com.example.netcracker_lab;
    exports com.example.netcracker_lab.controller;
    exports com.example.netcracker_lab.pojo;
    exports com.example.netcracker_lab.model;
    exports com.example.netcracker_lab.utility;
}
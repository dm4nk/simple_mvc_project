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

    exports com.example.simple_mvc_project;
    exports com.example.simple_mvc_project.controller;
    exports com.example.simple_mvc_project.pojo;
    exports com.example.simple_mvc_project.model;
    exports com.example.simple_mvc_project.utility;
}
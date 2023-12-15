module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.net.http;
    requires lombok;
    requires com.google.gson;

    opens com.example.demo1 to javafx.fxml;
    opens com.example.demo1.models to com.google.gson, javafx.base;
    exports com.example.demo1;
    exports com.example.demo1.pages;
    opens com.example.demo1.pages to javafx.fxml;
}
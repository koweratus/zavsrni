module ZavrsniProjektanz {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.media;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;
    requires gson;
    requires org.apache.servicemix.bundles.commons.codec;
    requires org.fxmisc.richtext;
    requires com.jfoenix;
    requires jbcrypt;
    opens controllers;
    opens repo;
    opens model;
    opens utils;
    opens main;


}
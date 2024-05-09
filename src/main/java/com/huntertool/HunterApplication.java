package com.huntertool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HunterApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HunterApplication.class.getResource("view_main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hunter Tool");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> System.exit(0));//同时退出子线程
        stage.getIcons().add(new Image(getClass().getResource("1.jpeg").toExternalForm()));
        stage.show();
//        System.getProperties().setProperty("https.proxyHost", "127.0.0.1");
//        System.getProperties().setProperty("https.proxyPort", "8080");
    }

    public static void main(String[] args) {
        launch();
    }
}
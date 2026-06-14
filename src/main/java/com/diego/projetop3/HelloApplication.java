package com.diego.projetop3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TelaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 550);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();

    }
}

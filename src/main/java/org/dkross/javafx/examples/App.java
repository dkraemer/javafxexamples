package org.dkross.javafx.examples;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public final class App extends Application {

    private static final String fxmlFilename = "/needleScene.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final URL fxmlUrl = getClass().getResource(fxmlFilename);
        Parent root = FXMLLoader.load(fxmlUrl);
        primaryStage.setTitle("DKrOSS JavaFX Samples");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

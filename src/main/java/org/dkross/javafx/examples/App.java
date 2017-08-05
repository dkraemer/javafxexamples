package org.dkross.javafx.examples;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

public final class App extends Application {

    private static final String fxmlFilename = "/needleScene.fxml";
    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {
        logger.entry((Object[]) args);
        launch(args);
        logger.traceExit();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.traceEntry();
        final URL fxmlUrl = getClass().getResource(fxmlFilename);
        Parent root = FXMLLoader.load(fxmlUrl);
        primaryStage.setTitle("DKrOSS JavaFX Samples");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        logger.traceExit();
    }
}

package com.example.project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main Application class
 * @author Ryan Pollack, Michael Kang
 */
public class MainApplication extends Application 
{
    /**
     * Main applicaton method
     */
    @Override
    public void start(Stage stage) throws IOException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 390, 500);
        stage.setTitle("RU Pizza Store Front");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch();
    }
}
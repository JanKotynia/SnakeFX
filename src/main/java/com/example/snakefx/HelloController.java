package com.example.snakefx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    private Stage currentStage;

    public void setStage(Stage stage) {
        this.currentStage = stage;
    }

    @FXML
    protected void onHelloButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = fxmlLoader.load();

            Stage newStage = new Stage();
            Scene scene = new Scene(root, 750, 750);
            newStage.setScene(scene);
            newStage.setTitle("New Window");
            newStage.show();

            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

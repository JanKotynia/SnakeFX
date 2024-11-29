package com.example.snakefx;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameController {
    @FXML
    private Canvas gameCanvas;

    private GraphicsContext gc;
    private GameManager gm = new GameManager();
    private AnimationTimer gameLoop;

    private long lastUpdate = 0;

    @FXML
    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();

        gameCanvas.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(KeyHandler::handleKeyPress);
                newScene.setOnKeyReleased(KeyHandler::handleKeyRelease);
            }
        });

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdate == 0) {
                    lastUpdate = now;
                    return;
                }

                double deltaTime = (now - lastUpdate) / 1e9;
                if (deltaTime >= 1.0 / 20) {
                    if(!GameManager.GameOver){
                        gm.updateG();
                        update(deltaTime);
                        render();
                        lastUpdate = now;}
                    else
                        gameOver();

                }
            }
        };

        gameLoop.start();
    }

    private void update(double deltaTime) {
    }

    private void gameOver()
    {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        gc.setFill(Color.RED);
        Font font = new Font("Arial", 40);
        gc.setFont(font);
        gc.fillText("GAME OVER",320,375);
    }
    private void fillBackground(){
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        gc.setStroke(Color.GOLD);
        gc.setLineWidth(30);
        gc.strokeRect(0, 0, 750, 750);
    }
    private void render() {
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        fillBackground();
        gm.draw(gc);
    }
}

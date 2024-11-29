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
    private final GameManager gm = new GameManager();

    private long lastUpdateKG = 0;
    private long lastUpdateU = 0;

    @FXML
    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();

        gameCanvas.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(KeyHandler::handleKeyPress);
                newScene.setOnKeyReleased(KeyHandler::handleKeyRelease);
            }
        });

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdateKG == 0) {
                    lastUpdateKG = now;
                    return;
                }

                double deltaTimeKeyGraphic = (now - lastUpdateKG) / 1e9;
                double deltaTimeUpdate = (now - lastUpdateU) / 1e9;
                if (deltaTimeKeyGraphic >= 1.0 / 24) {
                    if (!GameManager.GameOver) {
                        if (deltaTimeUpdate >= 1.0 / 4) {
                            gm.updateG();
                            lastUpdateU = now;
                        }
                        gm.keyPress();
                        update(deltaTimeKeyGraphic);
                        render();
                        lastUpdateKG = now;
                    } else
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
        gc.setLineWidth(60);
        gc.strokeRect(0, 0, 750, 750);
    }
    private void render() {
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
        fillBackground();
        gm.draw(gc);
    }
}

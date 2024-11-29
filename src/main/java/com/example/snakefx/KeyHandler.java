package com.example.snakefx;

public class KeyHandler {

    public static boolean up, down, left, right, pause;

    public static void handleKeyPress(javafx.scene.input.KeyEvent e) {
        switch (e.getCode()) {
            case UP -> up = true;
            case DOWN -> down = true;
            case LEFT -> left = true;
            case RIGHT -> right = true;
            case ESCAPE -> pause = !pause;
        }
    }

    public static void handleKeyRelease(javafx.scene.input.KeyEvent e) {
        switch (e.getCode()) {
            case UP -> up = false;
            case DOWN -> down = false;
            case LEFT -> left = false;
            case RIGHT -> right = false;
        }
    }
}

package com.example.snakefx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.ArrayList;


public class Snake {
    public ArrayList<PartsOfSnake> sTab = new ArrayList<>();

    public Snake(){
        int x = 360;
        int y = 360;
        PartsOfSnake h = new PartsOfSnake(x, y);
        PartsOfSnake b = new PartsOfSnake(x -PartsOfSnake.size, y);
        PartsOfSnake t = new PartsOfSnake(x -(2*PartsOfSnake.size), y);
        sTab.add(h);
        sTab.add(b);
        sTab.add(t);

    }

    public void setTail()
    {
        InputStream imageStream = HelloApplication.class.getResourceAsStream("/IMAGES/snakeTail.png");
        if (imageStream == null) {
            System.err.println("No image: " + "/IMAGES/snakeTail.png");
        } else {
                sTab.getLast().img = new Image(imageStream);
        }

        InputStream imageStream2 = HelloApplication.class.getResourceAsStream("/IMAGES/snakeBody.png");
        if (imageStream2 == null) {
            System.err.println("No image: " + "/IMAGES/snakeTail.png");
        } else {
                sTab.get(sTab.size()-2).img = new Image(imageStream2);
        }
    }

    public void setHead()
    {
        InputStream imageStream = HelloApplication.class.getResourceAsStream("/IMAGES/snakeHead.png");
        if (imageStream == null) {
            System.err.println("No image: " + "/IMAGES/snakeHead.png");
        } else {
            sTab.getFirst().img = new Image(imageStream);
        }

        InputStream imageStream2 = HelloApplication.class.getResourceAsStream("/IMAGES/snakeBody.png");
        if (imageStream2 == null) {
            System.err.println("No image: " + "/IMAGES/snakeTail.png");
        } else {

            sTab.get(1).img = new Image(imageStream2);
        }
    }



    public void draw(GraphicsContext g2, int r) {
        g2.save();

        switch (r) {
            case 1: {
                g2.translate(sTab.getFirst().pos_x, sTab.getFirst().pos_y + PartsOfSnake.size);
                g2.rotate(-90);
                g2.drawImage(sTab.getFirst().img, 0, 0, PartsOfSnake.size, PartsOfSnake.size);
                g2.restore();
            }
            break;
            case 2: {
                g2.translate(sTab.getFirst().pos_x + PartsOfSnake.size, sTab.getFirst().pos_y);
                g2.rotate(90);
                g2.drawImage(sTab.getFirst().img, 0, 0, PartsOfSnake.size, PartsOfSnake.size);
                g2.restore();
            }
            break;
            case 4: {
                g2.translate(sTab.getFirst().pos_x + PartsOfSnake.size, sTab.getFirst().pos_y + PartsOfSnake.size);
                g2.rotate(180);
                g2.drawImage(sTab.getFirst().img, 0, 0, PartsOfSnake.size, PartsOfSnake.size);
                g2.restore();
            }
            break;
            default:
                sTab.getFirst().draw(g2);
                break;
        }

        for (int i = 1; i < sTab.size() - 1; i++) {
            if (sTab.get(i - 1).pos_y != sTab.get(i).pos_y) {
                g2.save();
                g2.translate(sTab.get(i).pos_x + PartsOfSnake.size, sTab.get(i).pos_y);
                g2.rotate(90);
                g2.drawImage(sTab.get(i).img, 0, 0, PartsOfSnake.size, PartsOfSnake.size);
                g2.restore();
            } else {
                sTab.get(i).draw(g2);
            }
        }

        g2.save();
        if (sTab.get(sTab.size() - 2).pos_x < sTab.getLast().pos_x) {
            g2.translate(sTab.getLast().pos_x, sTab.getLast().pos_y);
            g2.scale(-1, 1);
            g2.translate(-PartsOfSnake.size, 0);
            g2.drawImage(sTab.getLast().img, 0, 0, PartsOfSnake.size, PartsOfSnake.size);
        } else if (sTab.get(sTab.size() - 2).pos_y > sTab.getLast().pos_y) {
            g2.translate(sTab.getLast().pos_x + PartsOfSnake.size, sTab.getLast().pos_y);
            g2.rotate(90);
            g2.drawImage(sTab.getLast().img, 0, 0, PartsOfSnake.size, PartsOfSnake.size);
        } else if (sTab.get(sTab.size() - 2).pos_y < sTab.getLast().pos_y) {
            g2.translate(sTab.getLast().pos_x, sTab.getLast().pos_y + PartsOfSnake.size);
            g2.rotate(90);
            g2.scale(-1, 1);
            g2.translate(0, -PartsOfSnake.size);
            g2.drawImage(sTab.getLast().img, 0, 0, PartsOfSnake.size, PartsOfSnake.size);
        } else {
            sTab.getLast().draw(g2);
        }
        g2.restore();
    }
}

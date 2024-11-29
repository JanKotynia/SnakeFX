package com.example.snakefx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.InputStream;

public class PartsOfSnake {
    public int pos_x,pos_y;
    public static final int size = 30;
    Image img;

    public PartsOfSnake(int x,int y){
        pos_x=x;
        pos_y=y;

        InputStream imageStream = HelloApplication.class.getResourceAsStream("/IMAGES/snakeBody.png");
        if (imageStream == null) {
            System.err.println("No image: " + "/IMAGES/snakeBody.png");
        } else {
                img = new Image(imageStream);
        }
    }

    public void draw(GraphicsContext g2)
    {

        if (img != null) {
            g2.drawImage(img, pos_x, pos_y, size,size);
        }
    }

    public void setXY(int x, int y)
    {
        pos_x=x;
        pos_y=y;
    }


}

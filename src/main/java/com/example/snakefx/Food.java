package com.example.snakefx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.InputStream;
import java.util.Random;

public class Food {
    public double pos_x,pos_y;
    public static final int size = 30;
    protected Image img;
    public final boolean effect = true;

    public Food()
    {
        InputStream imageStream = HelloApplication.class.getResourceAsStream("/IMAGES/Rat.png");
        if (imageStream == null) {
            System.err.println("No image: " + "/IMAGES/Rat.png");
        } else {
            img =  new Image(imageStream);
        }
        int rand1 = new Random().nextInt(22) +2;
        int rand2 = new Random().nextInt(22) +2;
        pos_x =rand1 * size + 150;
        pos_y =rand2 * size;
        System.out.println(pos_x + " " + pos_y);
    }

    public void newPosition()
    {
        int rand1 = new Random().nextInt(22) +2;
        int rand2 = new Random().nextInt(22) +2;
        pos_x =rand1 * size + 150;
        pos_y =rand2 * size;
        System.out.println(pos_x + " " + pos_y);
        GameController.score++;

    }
    public void draw(GraphicsContext g2)
    {
        if (img != null) {
            g2.drawImage(img,pos_x,pos_y,size,size);
        }
    }

}

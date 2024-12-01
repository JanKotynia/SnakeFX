package com.example.snakefx;

import javafx.scene.image.Image;

import java.io.InputStream;

public class Spikes extends Food{
    public Spikes()
    {
        super();
        InputStream imageStream = HelloApplication.class.getResourceAsStream("/IMAGES/spikes.png");
        if (imageStream == null) {
            System.err.println("No image: " + "/IMAGES/spikes.png");
        } else {
            img =  new Image(imageStream);
        }
    }
}

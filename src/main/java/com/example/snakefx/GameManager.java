package com.example.snakefx;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

public class GameManager {
    public int moveD = 0;
    public static  boolean GameOver;
    Food f = new Food();
    private final Snake snake = new Snake();
    public ArrayList<PartsOfSnake> sTmp = new ArrayList<>();
    public static int left_x= 0;
    public static int top_y = 0;

    public GameManager() {
        for (PartsOfSnake part : snake.sTab) {
            sTmp.add(new PartsOfSnake(part.pos_x, part.pos_y));
        }
        snake.setTail();
        snake.setHead();

    }

    public void draw(GraphicsContext gc)
    {
    f.draw(gc);
    snake.draw(gc,moveD);
    }

    public void keyPress()
    {
        if (KeyHandler.up) {
            if(moveD !=2)
                moveD = 1;

            KeyHandler.up = false;
        }
        if (KeyHandler.down) {
            if(moveD !=1)
                moveD = 2;
            KeyHandler.down = false;
        }
        if (KeyHandler.right) {
            if(moveD !=4)
                moveD = 3;
            KeyHandler.right = false;
        }
        if (KeyHandler.left) {
            if(moveD !=3)
                moveD = 4;
            KeyHandler.left = false;
        }
    }

    public void updateG()
    {

        if (moveD != 0)
            move(moveD);

        if (snake.sTab.getFirst().pos_x == f.pos_x && snake.sTab.getFirst().pos_y == f.pos_y) {
            int temp_x = snake.sTab.getLast().pos_x;
            int temp_y = snake.sTab.getLast().pos_y;

            f.newPosition();
            PartsOfSnake newbody = new PartsOfSnake(temp_x,temp_y);
            snake.sTab.add(newbody);
            sTmp.add(new PartsOfSnake(temp_x, temp_y));
            snake.setTail();
        }
    }

    private void move(int direction)
    {
        for (int i = 0; i < snake.sTab.size(); i++) {
            sTmp.get(i).pos_x = snake.sTab.get(i).pos_x;
            sTmp.get(i).pos_y = snake.sTab.get(i).pos_y;
        }
        System.out.println(snake.sTab.get(0).pos_x + " / " + snake.sTab.get(0).pos_y);
        switch (direction) {

            case 1 : {
                //up
                if (snake.sTab.getFirst().pos_y - PartsOfSnake.size > top_y && checkCollision(1))
                    snake.sTab.getFirst().pos_y = snake.sTab.getFirst().pos_y - PartsOfSnake.size;
                else
                    GameOver = true;
            } break;
            case 2 : {
                //down
                if (snake.sTab.getFirst().pos_y + PartsOfSnake.size < top_y + 720 && checkCollision(2))
                    snake.sTab.getFirst().pos_y = snake.sTab.getFirst().pos_y + PartsOfSnake.size;
                else
                    GameOver=true;
            } break;
            case 3 : {
                //right
                if (snake.sTab.getFirst().pos_x + PartsOfSnake.size < left_x + 720 && checkCollision(3))
                    snake.sTab.getFirst().pos_x = snake.sTab.getFirst().pos_x + PartsOfSnake.size;
                else
                    GameOver=true;
            } break;
            case 4 : {
                //left
                if (snake.sTab.getFirst().pos_x - PartsOfSnake.size > left_x && checkCollision(4))
                    snake.sTab.getFirst().pos_x = snake.sTab.getFirst().pos_x - PartsOfSnake.size;
                else
                    GameOver=true;
            } break;

        }
        if(!GameOver) {
            for (int i = 1; i < snake.sTab.size(); i++) {
                snake.sTab.get(i).pos_x = sTmp.get(i - 1).pos_x;
                snake.sTab.get(i).pos_y = sTmp.get(i - 1).pos_y;
            }
        }



    }

    private boolean checkCollision(int direction) {
        for (int i = 1; i < snake.sTab.size(); i++) {
            if (snake.sTab.get(i).pos_x == snake.sTab.getFirst().pos_x && snake.sTab.get(i).pos_y == snake.sTab.getFirst().pos_y)
                return false;
        }
        return true;
    }

}

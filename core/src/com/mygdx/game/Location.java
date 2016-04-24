package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Connor Valenti on 3/29/2016.
 */
public class Location {

    private String name;
    private boolean isFull;

    private int x;
    private int y;
    private Rectangle rectangle;
    private int id;

    public Location(int x, int y, int id){
        this.isFull = false;
        this.x = x;
        this.y = y;
        this.rectangle = new Rectangle(x, y, 65, 25);
        this.id = id;
    }

    public void addPassenger(){
        isFull = true;
    }

    public void removePassenger(){
        isFull = false;
    }

    public String getName(){
        return name;
    }

    public boolean isFull(){
        return isFull;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

    public int getId(){
        return id;
    }
}

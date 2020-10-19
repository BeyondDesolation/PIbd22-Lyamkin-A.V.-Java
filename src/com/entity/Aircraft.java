package com.entity;

import com.interfaces.ITransport;
import com.utils.Direction;

import java.awt.*;

abstract class Aircraft implements ITransport {

    protected float posX;
    protected float posY;

    protected int fieldWidth;
    protected int fieldHeight;

    protected int maxSpeed;
    protected float weight;

    protected Color mainColor;

    public void setPosition(int x, int y, int width, int height) {
        fieldHeight = height;
        fieldWidth = width;
        posX = x;
        posY = y;
    }

    public abstract void draw(Graphics2D g2);

    public abstract void move(Direction direction);

    public int getMaxSpeed() {
        return maxSpeed;
    }
    public float getWeight() {
        return weight;
    }
    public Color getMainColor() {
        return mainColor;
    }
}
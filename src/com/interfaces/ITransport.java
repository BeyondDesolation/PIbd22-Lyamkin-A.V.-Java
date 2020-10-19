package com.interfaces;

import com.utils.Direction;

import java.awt.*;

public interface ITransport {

    void setPosition(int x, int y, int width, int height);

    void move(Direction direction);

    void draw(Graphics2D g2);

}

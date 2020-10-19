package com.entity;

import com.utils.Direction;

import java.awt.*;

public class Plane extends Aircraft {

    protected int planeWidth = 200;
    protected int planeHeight = 100;

    public Plane(int maxSpeed, float weight, Color mainColor) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
    }

    protected Plane(int maxSpeed, float weight, Color mainColor, int planeWidth, int planeHeight) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.planeWidth = planeWidth;
        this.planeHeight = planeHeight;
    }

    @Override
    public void draw(Graphics2D g2) {

        Color dopColor = new Color(150, 150, 150);

        //хвост
        g2.setPaint(mainColor);
        Polygon p1 = new Polygon();
        p1.addPoint((int) posX + 8, (int) posY + 10);
        p1.addPoint((int) posX + 65, (int) posY + 15);
        p1.addPoint((int) posX + 60, (int) posY + 25);
        p1.addPoint((int) posX + 80, (int) posY + 50);
        p1.addPoint((int) posX + 55, (int) posY + 55);
        p1.addPoint((int) posX + 42, (int) posY + 22);
        p1.addPoint((int) posX + 8, (int) posY + 10);
        g2.fillPolygon(p1);

        //корпус

        p1 = new Polygon();
        p1.addPoint((int) posX + 50, (int) posY + 60);
        p1.addPoint((int) posX + 90, (int) posY + 75);
        p1.addPoint((int) posX + 120, (int) posY + 80);
        p1.addPoint((int) posX + 185, (int) posY + 70);
        p1.addPoint((int) posX + 195, (int) posY + 65);
        p1.addPoint((int) posX + 195, (int) posY + 60);
        p1.addPoint((int) posX + 180, (int) posY + 50);
        p1.addPoint((int) posX + 170, (int) posY + 50);
        p1.addPoint((int) posX + 160, (int) posY + 45);
        p1.addPoint((int) posX + 55, (int) posY + 50);
        g2.fillPolygon(p1);

        // Двигатели.
        // Ближе к кобине.
        g2.setPaint(dopColor);
        p1 = new Polygon();
        p1.addPoint((int) posX + 100, (int) posY + 58);
        p1.addPoint((int) posX + 120, (int) posY + 70);
        p1.addPoint((int) posX + 125, (int) posY + 58);
        g2.fillPolygon(p1);

        //крылья
        p1 = new Polygon();
        p1.addPoint((int) posX + 37, (int) posY + 58);
        p1.addPoint((int) posX + 140, (int) posY + 50);
        p1.addPoint((int) posX + 145, (int) posY + 60);
        p1.addPoint((int) posX + 135, (int) posY + 64);
        p1.addPoint((int) posX + 90, (int) posY + 59);
        p1.addPoint((int) posX + 30, (int) posY + 65);
        g2.fillPolygon(p1);
    }

    @Override
    public void move(Direction direction) {
        int step = (int) (maxSpeed * 100 / weight);
        switch (direction) {
            case Left:
                if (posX - step > 0)
                    posX -= step;
                break;

            case Up:
                if (posY - step > 0)
                    posY -= step;
                break;

            case Right:
                if (posX + step < fieldWidth - planeWidth)
                    posX += step;
                break;

            case Down:
                if (posY + step < fieldHeight - planeHeight)
                    posY += step;
                break;
        }
    }
}

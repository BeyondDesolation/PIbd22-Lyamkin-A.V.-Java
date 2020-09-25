package com.entity;

import com.utils.Direction;

import java.awt.*;

import static com.utils.Direction.*;

public class PlaneWithRadar {
    private boolean hasRadar;
    private boolean moreEngine;
    private int radarRage;
    public int numRadars;

    private AdditionalRadars addRadars;

    private int maxSpeed;
    private int weight;

    private int posX = 0;
    private int posY = 0;

    private int fieldHeight;
    private int fieldWidth;

    private final int planeHeight = 100;
    private final int planeWidth = 200;

    public Color mainColor;
    public Color dopColor;
    public Color detailColor;

    public PlaneWithRadar(int maxSpeed, int weight, Color main, Color dop, Color dop2, boolean radar, boolean moreEngine) {
        this.maxSpeed = maxSpeed;
       this.weight = weight;
        mainColor = main;
        dopColor = dop;
        detailColor = dop2;
        hasRadar = radar;
        this.moreEngine = moreEngine;
        addRadars = new AdditionalRadars();
    }

    public void setPosition(int x, int y, int width, int height) {
        fieldHeight = height;
        fieldWidth = width;
        posX = x;
        posY = y;
        addRadars.setPosition(posX, posY);
    }
    public void setNumRadars(int num){
        addRadars.setNumRadars(num);
    }

    public void movePlane(Direction direction) {
        int step = maxSpeed * 100 / weight;
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
        addRadars.setPosition(posX, posY);
    }

    public void render(Graphics2D g2) {
        //хвост
        g2.setPaint(mainColor);
        Polygon p1 = new Polygon();
        p1.addPoint(posX + 8, posY + 10);
        p1.addPoint(posX + 65, posY + 15);
        p1.addPoint(posX + 60, posY + 25);
        p1.addPoint(posX + 80, posY + 50);
        p1.addPoint(posX + 55, posY + 55);
        p1.addPoint(posX + 42, posY + 22);
        p1.addPoint(posX + 8, posY + 10);
        g2.fillPolygon(p1);

        //корпус
        g2.setPaint(dopColor);
        p1 = new Polygon();

        p1.addPoint(posX + 50, posY + 60);
        p1.addPoint(posX + 90, posY + 75);
        p1.addPoint(posX + 120, posY + 80);
        p1.addPoint(posX + 185, posY + 70);
        p1.addPoint(posX + 195, posY + 65);
        p1.addPoint(posX + 195, posY + 60);
        p1.addPoint(posX + 180, posY + 50);
        p1.addPoint(posX + 170, posY + 50);
        p1.addPoint(posX + 160, posY + 45);
        p1.addPoint(posX + 55, posY + 50);
        g2.fillPolygon(p1);

        // Двигатели.
        if(moreEngine){
            // Ближе к кобине.
            g2.setPaint(detailColor);
            p1 = new Polygon();
            p1.addPoint(posX + 100, posY + 58);
            p1.addPoint(posX + 120, posY + 70);
            p1.addPoint(posX + 125, posY + 58);
            g2.fillPolygon(p1);
        }

        // Ближе к хвосту.
        p1 = new Polygon();
        p1.addPoint(posX + 83, posY + 56);
        p1.addPoint(posX + 103, posY + 68);
        p1.addPoint(posX + 108, posY + 56);
        g2.fillPolygon(p1);

        //крылья
        p1 = new Polygon();
        g2.setPaint(mainColor);
        p1.addPoint(posX + 37, posY + 58);
        p1.addPoint(posX + 140, posY + 50);
        p1.addPoint(posX + 145, posY + 60);
        p1.addPoint(posX + 135, posY + 64);
        p1.addPoint(posX + 90, posY + 59);
        p1.addPoint(posX + 30, posY + 65);
        g2.fillPolygon(p1);

        //радар
        if (hasRadar) {
            addRadars.draw(detailColor, g2);
        }
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getWeight() {
        return weight;
    }

    public boolean hasRadar() {
        return hasRadar;
    }
}

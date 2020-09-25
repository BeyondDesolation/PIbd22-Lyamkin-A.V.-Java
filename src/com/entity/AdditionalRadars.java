package com.entity;

import com.utils.RadarsNumber;

import java.awt.*;

public class AdditionalRadars {

    private RadarsNumber radarsNumber;
    public int numRadars;
    private int posX, posY;

    public AdditionalRadars() {

        radarsNumber = RadarsNumber.fromNumeric(0);
    }

    public void setPosition(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }
    public int getNumRadars() {
        return numRadars;
    }

    public void setNumRadars(int numRadars) {
        if (numRadars > 3) {
            numRadars = 3;
        }
        if (numRadars < 0) {
            numRadars = 0;
        }
        this.numRadars = numRadars;
        radarsNumber = RadarsNumber.fromNumeric(this.numRadars);
    }

    public void draw(Color color, Graphics2D g2) {
        if (radarsNumber == RadarsNumber.ONE) {
           drawFirstRadar(g2, color);
        }
        else if(radarsNumber == RadarsNumber.TWO){
            drawSecondRadars(g2, color);
        }
        else if(radarsNumber == RadarsNumber.THREE){
            drawThirdRadars(g2, color);
        }
    }
    private void drawFirstRadar(Graphics2D g2, Color color){
        g2.setPaint(color);
        Polygon p1 = new Polygon();
        p1.addPoint(posX + 110, posY + 50);
        p1.addPoint(posX + 112, posY + 35);
        p1.addPoint(posX + 120, posY + 35);
        p1.addPoint(posX + 122, posY + 50);

        g2.fillPolygon(p1);
        g2.fillOval(posX + 92, posY + 30, 49, 10);
    }
    private void drawSecondRadars(Graphics2D g2, Color color){
        drawFirstRadar(g2, color);
        g2.setPaint(color);
        Polygon p1 = new Polygon();
        p1.addPoint(posX + 110, posY + 50);
        p1.addPoint(posX + 112, posY + 20);
        p1.addPoint(posX + 120, posY + 20);
        p1.addPoint(posX + 122, posY + 50);

        g2.fillPolygon(p1);
        g2.fillOval(posX + 97, posY + 20, 39, 8);
    }
    private void drawThirdRadars(Graphics2D g2, Color color){
        drawSecondRadars(g2, color);
        g2.setPaint(color);
        Polygon p1 = new Polygon();
        p1.addPoint(posX + 110, posY + 50);
        p1.addPoint(posX + 112, posY + 12);
        p1.addPoint(posX + 120, posY + 12);
        p1.addPoint(posX + 122, posY + 50);

        g2.fillPolygon(p1);
        g2.fillOval(posX + 101, posY + 12, 32, 6);
    }
}

package com.entity;

import com.interfaces.IDopDraw;
import com.utils.DetailsNumber;

import java.awt.*;

public class AdditionalRadars implements IDopDraw {

    private DetailsNumber radarsNumber;
    public int numRadars;

    public AdditionalRadars() {
        radarsNumber = DetailsNumber.fromNumeric(0);
    }
    public void setNumDetails(int numRadars) {
        if (numRadars > 3) {
            numRadars = 3;
        }
        if (numRadars < 0) {
            numRadars = 0;
        }
        this.numRadars = numRadars;
        radarsNumber = DetailsNumber.fromNumeric(this.numRadars);
    }

    public void draw(int posX, int posY, Color color, Graphics2D g2) {
        if (radarsNumber == DetailsNumber.ONE) {
            drawFirstRadar(posX, posY, g2, color);
        }
        else if(radarsNumber == DetailsNumber.TWO){
            drawSecondRadars(posX, posY, g2, color);
        }
        else if(radarsNumber == DetailsNumber.THREE){
            drawThirdRadars(posX, posY, g2, color);
        }
    }
    private void drawFirstRadar(int posX, int posY, Graphics2D g2, Color color){
        g2.setPaint(color);
        Polygon p1 = new Polygon();
        p1.addPoint(posX + 110, posY + 50);
        p1.addPoint(posX + 112, posY + 35);
        p1.addPoint(posX + 120, posY + 35);
        p1.addPoint(posX + 122, posY + 50);

        g2.fillPolygon(p1);
        g2.fillOval(posX + 92, posY + 30, 49, 10);
    }
    private void drawSecondRadars(int posX, int posY,Graphics2D g2, Color color){
        drawFirstRadar(posX, posY, g2, color);
        g2.setPaint(color);
        Polygon p1 = new Polygon();
        p1.addPoint(posX + 110, posY + 50);
        p1.addPoint(posX + 112, posY + 20);
        p1.addPoint(posX + 120, posY + 20);
        p1.addPoint(posX + 122, posY + 50);

        g2.fillPolygon(p1);
        g2.fillOval(posX + 97, posY + 20, 39, 8);
    }
    private void drawThirdRadars(int posX, int posY, Graphics2D g2, Color color){
        drawSecondRadars(posX, posY, g2, color);
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

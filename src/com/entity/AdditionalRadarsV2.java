package com.entity;

import com.interfaces.IDopDraw;
import com.utils.DetailsNumber;

import java.awt.*;

public class AdditionalRadarsV2 implements IDopDraw {

    public int numRadars;
    private DetailsNumber radarsNumber;

    public AdditionalRadarsV2() {
        radarsNumber = DetailsNumber.fromNumeric(0);
    }
    @Override
    public void setNumDetails(int numDetails) {
        if (numRadars > 3) {
            numRadars = 3;
        }
        if (numRadars < 0) {
            numRadars = 0;
        }
        this.numRadars = numDetails;
        radarsNumber = DetailsNumber.fromNumeric(this.numRadars);
    }

    @Override
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

        g2.fillRect(posX + 92, posY + 33, 47, 4);
        g2.fillRect(posX + 92, posY + 30, 3, 12);
        g2.fillRect(posX + 137, posY + 30, 3, 12);
    }
    private void drawSecondRadars(int posX, int posY,Graphics2D g2, Color color){
        drawFirstRadar(posX, posY, g2, color);
        g2.setPaint(color);
        Polygon p1 = new Polygon();
        p1.addPoint(posX + 110, posY + 50);
        p1.addPoint(posX + 112, posY + 25);
        p1.addPoint(posX + 120, posY + 25);
        p1.addPoint(posX + 122, posY + 50);

        g2.fillPolygon(p1);
        g2.fillRect(posX + 97, posY + 24, 37, 4);
        g2.fillRect(posX + 97, posY + 21, 3, 12);
        g2.fillRect(posX + 132, posY + 21, 3, 12);


    }
    private void drawThirdRadars(int posX, int posY, Graphics2D g2, Color color){
        drawSecondRadars(posX, posY, g2, color);
        g2.setPaint(color);
        Polygon p1 = new Polygon();
        p1.addPoint(posX + 110, posY + 50);
        p1.addPoint(posX + 112, posY + 15);
        p1.addPoint(posX + 120, posY + 15);
        p1.addPoint(posX + 122, posY + 50);

        g2.fillPolygon(p1);

        g2.fillRect(posX + 101, posY + 15, 29, 4);
        g2.fillRect(posX + 101, posY + 12, 3, 12);
        g2.fillRect(posX + 127, posY + 12, 3, 12);

        // g2.fillOval(posX + 101, posY + 12, 32, 6);
    }

}

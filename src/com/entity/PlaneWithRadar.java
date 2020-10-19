package com.entity;

import com.interfaces.IDopDraw;
import com.utils.DetailsType;

import java.awt.*;

public class PlaneWithRadar extends Plane {
    private boolean hasRadar;
    private boolean moreEngine;
    private int radarRage;
    public int numRadars;

    private IDopDraw dopDetails;

    public Color mainColor;
    public Color dopColor;
    public Color detailColor;

    public PlaneWithRadar(int maxSpeed, int weight, Color main, Color dop, Color dop2, boolean radar, boolean moreEngine) {
        super(maxSpeed, weight, main, 200, 100);
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        mainColor = main;
        dopColor = dop;
        detailColor = dop2;
        hasRadar = radar;
        this.moreEngine = moreEngine;

    }

    public void setTypeDetails(DetailsType detailsType) {
        if (detailsType == DetailsType.OLD_RADAR) {
            dopDetails = new AdditionalRadars();
        } else if (detailsType == DetailsType.NEW_RADAR) {
            dopDetails = new AdditionalRadarsV2();
        } else if (detailsType == DetailsType.LINES) {
            dopDetails = new AdditionalLines();
        }
    }

    public void setNumDetails(int n) {
        if (dopDetails != null)
            dopDetails.setNumDetails(n);
    }

    public void draw(Graphics2D g2) {

        int posX = (int) this.posX;
        int posY = (int) this.posY;
        super.draw(g2);

        // Детали
        if (dopDetails != null) {
            dopDetails.draw(posX, posY, detailColor, g2);
        }

        //хвост
        g2.setPaint(dopColor);
        Polygon p1 = new Polygon();
        p1.addPoint(posX + 8, posY + 10);
        p1.addPoint(posX + 65, posY + 15);
        p1.addPoint(posX + 60, posY + 25);
        p1.addPoint(posX + 80, posY + 50);
        p1.addPoint(posX + 55, posY + 55);
        p1.addPoint(posX + 42, posY + 22);
        p1.addPoint(posX + 8, posY + 10);
        g2.fillPolygon(p1);


        // Двигатели.
        if (moreEngine) {
            g2.setPaint(detailColor);
            // Ближе к хвосту.
            p1 = new Polygon();
            p1.addPoint(posX + 83, posY + 56);
            p1.addPoint(posX + 103, posY + 68);
            p1.addPoint(posX + 108, posY + 56);
            g2.fillPolygon(p1);
        }
        // Двигатели.
        // Ближе к кобине.
        p1 = new Polygon();
        p1.addPoint(posX + 100,  posY + 58);
        p1.addPoint( posX + 120,  posY + 70);
        p1.addPoint( posX + 125,  posY + 58);
        g2.fillPolygon(p1);

        //крылья
        p1 = new Polygon();
        g2.setPaint(dopColor);
        p1.addPoint(posX + 37, posY + 58);
        p1.addPoint(posX + 140, posY + 50);
        p1.addPoint(posX + 145, posY + 60);
        p1.addPoint(posX + 135, posY + 64);
        p1.addPoint(posX + 90, posY + 59);
        p1.addPoint(posX + 30, posY + 65);
        g2.fillPolygon(p1);


    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public float getWeight() {
        return weight;
    }

    public boolean hasRadar() {
        return hasRadar;
    }
}

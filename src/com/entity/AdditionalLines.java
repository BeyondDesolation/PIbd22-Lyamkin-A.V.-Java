package com.entity;

import com.interfaces.IDopDraw;
import com.utils.DetailsNumber;

import java.awt.*;

public class AdditionalLines implements IDopDraw {
    private int numOfLines;
    private DetailsNumber numOfLinesEnum;
    @Override
    public void draw(int posX, int posY, Color color, Graphics2D g2) {

        if (numOfLinesEnum == DetailsNumber.ONE) {
            drawFirstLine(posX, posY, g2, color);
        }
        else if(numOfLinesEnum == DetailsNumber.TWO){
            drawSecondLine(posX, posY, g2, color);
        }
        else if(numOfLinesEnum == DetailsNumber.THREE){
            drawThirdLine(posX, posY, g2, color);
        }
    }
    private void drawFirstLine(int posX, int posY, Graphics2D g2, Color color) {
        g2.setPaint(color);
        g2.fillRect(posX + 70, posY + 50, 100, 3);
    }
    private void drawSecondLine(int posX, int posY, Graphics2D g2, Color color) {
        drawFirstLine(posX, posY,g2, color);
        g2.fillRect(posX + 50, posY + 58, 145, 3);
    }
    private void drawThirdLine(int posX, int posY, Graphics2D g2, Color color) {
        drawSecondLine(posX, posY,g2, color);
        g2.fillRect(posX + 70, posY + 66, 120, 3);
    }

    @Override
    public void setNumDetails(int numDetails) {
        if (numOfLines > 3) {
            numOfLines = 3;
        }
        if (numOfLines < 0) {
            numOfLines = 0;
        }
        this.numOfLines = numDetails;
        numOfLinesEnum = DetailsNumber.fromNumeric(this.numOfLines);
    }
}

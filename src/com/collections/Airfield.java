package com.collections;

import com.interfaces.IDopDraw;
import com.interfaces.ITransport;

import java.awt.*;

public class Airfield<T extends ITransport, E extends IDopDraw> {

    private Object[] places;

    private final int fieldWidth;
    private final int fieldHeight;
    private final int maxSize;

    private static final int placeWidth = 350;
    private static final int placeHeight = 100;

    public Airfield(int fieldWidth, int fieldHeight) {

        int cols = fieldWidth / placeWidth;
        int rows = fieldHeight / placeHeight;
        maxSize = cols * rows;
        places = new Object[maxSize];

        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    public boolean plus(T plane) {

            int rows = fieldHeight / placeHeight;
            for (int i = 0; i < places.length ; i++) {
                if( places[i] == null ) {
                    plane.setPosition((i / rows) * placeWidth, (i % rows) * placeHeight, fieldWidth, fieldHeight);
                    places[i] = plane;
                    return true;
                }
            }
        return false;
    }

    public T minus(int index) {
        if (index >= places.length || index < 0) {
            return null;
        }
        T temp = (T)places[index];
        places[index] = null;
        return temp;
    }

    public boolean more(int value){
        return places.length > value;
    }
    public boolean less(int value){
        return places.length < value;
    }

    public void draw(Graphics2D g) {
        drawMarking(g);
        for (int i = 0; i < places.length; i++) {
            if (places[i] != null) {
                ((T)places[i]).draw(g);
            }
        }
    }

    private void drawMarking(Graphics2D g) {
        int rows = fieldHeight / placeHeight;
        int cols = fieldWidth / placeWidth;

        g.setPaint(Color.BLACK);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows + 1; ++j) {
                g.drawLine(i * placeWidth, j * placeHeight, i *
                        placeWidth + placeWidth - 120, j * placeHeight);
            }
            g.drawLine(i * placeWidth, 0, i * placeWidth,
                    (fieldHeight / placeHeight) * placeHeight);
        }
    }

    public int getMaxSize() {
        return maxSize;
    }
}
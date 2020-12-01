package com.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class AirfieldFrame3 extends JFrame {



    private BufferedImage buffer;
    private Canvas content;
   private JPanel optionPanel;
//
private int[] bufferData;
private Graphics bufferGraphics;
    private int clearColor;
//


    private JButton bPutPlane;
    private JButton bPutPlaneWithRadar;
    private JButton bTakePlane;

//    private JSpinner spinNumOfPlace;
//
   // private Font mainFont;



    public AirfieldFrame3(int width, int height, String title) {

        setTitle(title);
        content = new Canvas();

        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());
        add(content, BorderLayout.CENTER);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        add(new JButton("asldkja"), BorderLayout.SOUTH);
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = 0Xffffffff;
    }
}



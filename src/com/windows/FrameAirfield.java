package com.windows;

import com.collections.Airfield;
import com.entity.Aircraft;
import com.entity.Plane;
import com.entity.PlaneWithRadar;
import com.interfaces.IDopDraw;
import com.utils.DetailsType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class FrameAirfield {

    private int width, height;
    private JFrame frame;

    private Canvas content;
    private JPanel optionPanel;

    private BufferedImage buffer;
    private int[] bufferData;
    private Graphics bufferGraphics;
    private int clearColor;

    private JButton bPutPlane;
    private JButton bPutPlaneWithRadar;
    private JButton bTakePlane;

    private JSpinner spinNumOfPlace;
    private Font mainFont;
    private JColorChooser colorChooser;

    private Airfield<Aircraft, IDopDraw> airfield;

    private FramePlane framePlane;

    public FrameAirfield(int width, int height, String title) {

        this.width = width;
        this.height = height;
        frame = new JFrame(title);

        content = new Canvas();

        mainFont = new Font("Area", Font.LAYOUT_NO_LIMIT_CONTEXT, 16);

        colorChooser = new JColorChooser();

        // Кнопки
        bPutPlane = new JButton("Посадить самолет");
        bPutPlane.setBackground(Color.GRAY);
        bPutPlane.setFont(mainFont);
        bPutPlane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                putPlane_Click(e);
            }
        });
        bPutPlaneWithRadar = new JButton("Посадить самолет с радаром");
        bPutPlaneWithRadar.setBackground(Color.GRAY);
        bPutPlaneWithRadar.setFont(mainFont);
        bPutPlaneWithRadar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                putPlaneWithRadar_Click(e);
            }
        });
        bTakePlane = new JButton("Взять");
        bTakePlane.setBackground(Color.GRAY);
        bTakePlane.setFont(mainFont);
        bTakePlane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                takePlane_Click(e);
            }
        });
        // Спинер (
        spinNumOfPlace = new JSpinner(new SpinnerNumberModel(0, 0, 11, 1));
        spinNumOfPlace.setFont(mainFont);
        spinNumOfPlace.setPreferredSize(new Dimension(50, 50));

        // Панель для того, чтобы поставить "Номер места: " и выбор места в одну строчку.
        JPanel panel = new JPanel();
        Label l = new Label("Номер места: ");
        l.setFont(mainFont);
        panel.add(l);
        panel.add(spinNumOfPlace);

        // Панель управления
        optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(10, 1, 3, 3));
        optionPanel.add(bPutPlane);
        optionPanel.add(bPutPlaneWithRadar);
        optionPanel.add(panel);
        optionPanel.add(bTakePlane);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setLayout(new BorderLayout());
        frame.add(content, BorderLayout.CENTER);
        frame.add(optionPanel, BorderLayout.EAST);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = 0Xffffffff;

        initialComponent();
    }

    private void initialComponent() {
        System.out.println(content.getWidth() + " " + content.getHeight());
        airfield = new Airfield<>(content.getWidth(), content.getHeight());
        render();
    }

    private void takePlane_Click(ActionEvent e) {
        if ((int) spinNumOfPlace.getValue() >= 0 && (int) spinNumOfPlace.getValue() < airfield.getMaxSize()) {
            Aircraft plane = airfield.minus((int) spinNumOfPlace.getValue());
            if (plane != null) {
                framePlane = new FramePlane(width, height, "Летим");
                framePlane.setPlane(plane);
            }
            render();
        }

    }

    private void putPlaneWithRadar_Click(ActionEvent e) {
        JColorChooser colorChooser = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorChooser);
        Color color1 = colorChooser.getColor();
        if (color1 != null) {
            JColorChooser colorChooser2 = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorChooser2);
            Color color2 = colorChooser2.getColor();
            if(color2 != null){
                PlaneWithRadar plane = new PlaneWithRadar(100, 1000, color1, color2, color2, true, true);
                plane.setTypeDetails(DetailsType.NEW_RADAR);
                plane.setNumDetails(2);

                if (airfield.plus(plane)) {
                    render();
                } else {
                    JOptionPane.showMessageDialog(content, "Аэропорт переполнен");
                }
            }
        }
    }

    private void putPlane_Click(ActionEvent e) {

        JColorChooser colorChooser = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorChooser);
        if (colorChooser.getColor() != null) {
            Plane plane = new Plane(100, 1000, colorChooser.getColor());

            if (airfield.plus(plane)) {
                render();
            } else {
                JOptionPane.showMessageDialog(content, "Аэропорт переполнен");
            }
        }
    }

    public Graphics2D getGraphics() {
        return (Graphics2D) bufferGraphics;
    }

    public void clear(int color) {
        Arrays.fill(bufferData, color);
    }

    public void render() {
        clear(0XFFFFFFFF);
        airfield.draw(getGraphics());
        Graphics g = content.getGraphics();
        g.drawImage(buffer, 0, 0, null);
    }
}

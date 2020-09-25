package com.graphics;

import com.entity.PlaneWithRadar;
import com.utils.Direction;
import com.utils.ImageReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class WoPGUI {

    private BufferedImage buffer;
    private int[] bufferData;
    private Graphics bufferGraphics;
    private int clearColor;

    private JFrame window;
    private Canvas content;
    private Font mainFont;

    private JPanel optionPanel;

    private JButton bLeft;
    private JButton bUp;
    private JButton bRight;
    private JButton bDown;
    private JButton bStart;

    private JSpinner spinNumRadars;

    private PlaneWithRadar plane;

    public WoPGUI(int width, int height, String title, PlaneWithRadar plane) {

        this.plane = plane;

        window = new JFrame(title);
        content = new Canvas();
        optionPanel = new JPanel();
        mainFont = new Font("Area", Font.LAYOUT_NO_LIMIT_CONTEXT,16);

        bLeft = new JButton(new ImageIcon(ImageReader.loadImage("bLeft.png")));
        bLeft.setBackground(Color.GRAY);
        bLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bLeft_Click(e);
            }
        });
        bUp = new JButton(new ImageIcon(ImageReader.loadImage("bUp.png")));
        bUp.setBackground(Color.GRAY);
        bUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bUp_Click(e);
            }
        });
        bRight = new JButton(new ImageIcon(ImageReader.loadImage("bRight.png")));
        bRight.setBackground(Color.GRAY);
        bRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bRight_Click(e);
            }
        });
        bDown = new JButton(new ImageIcon(ImageReader.loadImage("bDown.png")));
        bDown.setBackground(Color.GRAY);
        bDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bDown_Click(e);
            }
        });
        bStart = new JButton("Start");
        bStart.setBackground(Color.GRAY);
        bStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bStart_Click(e);
            }
        });

        spinNumRadars = new JSpinner(new SpinnerNumberModel(0,0,3,1));
        spinNumRadars.setPreferredSize(new Dimension(50,40));
        spinNumRadars.setFont(mainFont);

        // optionPanel.setLayout(new GridLayout(5, 1, 3, 3));
        optionPanel.setLayout(new GridLayout(5, 1, 3, 3));

        // Буду коментить. Панель с кнопками управления. Идет в 6ю строку
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 3, 3, 3));
        controlPanel.add(new Label());
        controlPanel.add(bUp);
        controlPanel.add(new Label());
        controlPanel.add(bLeft);
        controlPanel.add(bDown);
        controlPanel.add(bRight);

        // Панель для выбора стартового сетапа и старта.
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new GridLayout(3, 1, 0,0));
        startPanel.add(bStart);

        // Еще панель, чтобы подписать счетчик. Пора учить сложные лайауты...
        JPanel panelWithSpinner = new JPanel();
        JLabel label = new JLabel("Количество радаров");
        label.setFont(mainFont);
        panelWithSpinner.add(label);
        panelWithSpinner.add(spinNumRadars);
        startPanel.add(panelWithSpinner);

        // Итоговая панель со всеми элементами управления.
        optionPanel.add(startPanel);
        optionPanel.add(new Label());
        optionPanel.add(new Label());
        optionPanel.add(new Label());
        optionPanel.add(controlPanel);

        window.setPreferredSize(new Dimension(width, height));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setLayout(new BorderLayout());
        window.add(optionPanel, BorderLayout.EAST);
        window.add(content, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D)bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = 0Xffffffff;
    }

    private void bStart_Click(ActionEvent e) {
        plane = new PlaneWithRadar
                ((int) (Math.random() * 800 + 500),
                        (int) (Math.random() * 4000 + 2000),
                        new Color(100, 150, 200),
                        new Color(50, 70, 140),
                        new Color(180, 200, 240),
                        true, true);
        plane.setPosition((int) (Math.random() * 200), (int) (Math.random() * 200), content.getWidth(), content.getHeight());
        plane.setNumRadars((int)spinNumRadars.getValue());
        render();
    }

    private void bDown_Click(ActionEvent e) {
        plane.movePlane(Direction.Down);
        render();
    }

    private void bRight_Click(ActionEvent e) {
        plane.movePlane(Direction.Right);
        render();
    }

    private void bUp_Click(ActionEvent e) {
        plane.movePlane(Direction.Up);
        render();
    }

    private void bLeft_Click(ActionEvent e) {
        plane.movePlane(Direction.Left);
        render();
    }

    public Graphics2D getGraphics() {
        return (Graphics2D) bufferGraphics;
    }

    public void clear(int color) {
        Arrays.fill(bufferData, color);
    }

    public void render() {
        clear(0XFFFFFFFF);
        plane.render(getGraphics());
        Graphics g = content.getGraphics();
        g.drawImage(buffer, 0, 0, null);
    }
}

package com.graphics;

import com.entity.Plane;
import com.entity.PlaneWithRadar;
import com.interfaces.ITransport;
import com.utils.DetailsType;
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
    private JButton bStartPlane;
    private JButton bStartPlaneWithRadar;


    private JSpinner spinNumDetails;
    private JMenu menuTypeDetails;

    private ITransport plane;
    private DetailsType detailsType;

    public WoPGUI(int width, int height, String title, ITransport plane) {

        this.plane = plane;


        window = new JFrame(title);
        content = new Canvas();
        optionPanel = new JPanel();
        mainFont = new Font("Area", Font.LAYOUT_NO_LIMIT_CONTEXT, 16);

        bLeft = new JButton(new ImageIcon(ImageReader.loadImage("bLeft.png")));
        bLeft.setBackground(Color.GRAY);
        //bLeft.setIcon(new ImageIcon("/GUI/bDown.png"));
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
        bStartPlane = new JButton("Create a plane");
        bStartPlane.setBackground(Color.GRAY);
        bStartPlane.setFont(mainFont);
        bStartPlane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bStartPlane_Click(e);
            }
        });
        bStartPlaneWithRadar = new JButton("Create a plane with radar");
        bStartPlaneWithRadar.setBackground(Color.GRAY);
        bStartPlaneWithRadar.setFont(mainFont);
        bStartPlaneWithRadar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bStartPlaneWithRadar_Click(e);
            }
        });

        spinNumDetails = new JSpinner(new SpinnerNumberModel(0, 0, 3, 1));
        spinNumDetails.setPreferredSize(new Dimension(50, 40));
        spinNumDetails.setFont(mainFont);

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

        // Панель с кнопками для старта.
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new GridLayout(3, 1, 0, 0));
        startPanel.add(bStartPlane);
        startPanel.add(bStartPlaneWithRadar);

        // Панель со спинерами для выбора стартового сетапа.
        JPanel startPanel2 = new JPanel();
        startPanel2.setLayout(new GridLayout(3, 1, 0, 0));

        // Меню с выбором типа дополнительной рисовки
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.LIGHT_GRAY);
        menuBar.setFont(mainFont);

        menuTypeDetails = new JMenu("Детали");
        menuTypeDetails.setPreferredSize(new Dimension(120,60));
        menuTypeDetails.setFont(mainFont);
        JMenuItem itemOldRadar = new JMenuItem("Старый радар");
        itemOldRadar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailsType = DetailsType.OLD_RADAR;
                menuTypeDetails.setText("Старый радар");
            }
        });
        menuTypeDetails.add(itemOldRadar);
        JMenuItem itemNewRadar = new JMenuItem("Новый радар");
        itemNewRadar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailsType = DetailsType.NEW_RADAR;
                menuTypeDetails.setText("Новый радар");

            }
        });
        menuTypeDetails.add(itemNewRadar);
        JMenuItem itemLines = new JMenuItem("Полоски");
        itemLines.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailsType = DetailsType.LINES;
                menuTypeDetails.setText("Полоски");
            }
        });
        menuTypeDetails.add(itemLines);

        menuBar.add(menuTypeDetails);

        // Еще панели, чтобы тип дополнения и его количество встали в строчку. Пора учить сложные лайауты...
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2,3,3));
        panel.add(menuBar);
        panel.add(spinNumDetails);
        startPanel.add(panel);


        // Итоговая панель со всеми элементами управления.
        optionPanel.add(startPanel);

        optionPanel.add(new JLabel());
        optionPanel.add(new JLabel());
        optionPanel.add(new JLabel());
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
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = 0Xffffffff;
    }

    private void bStartPlaneWithRadar_Click(ActionEvent e) {
        plane = new PlaneWithRadar
                ((int) (Math.random() * 800 + 500),
                        (int) (Math.random() * 4000 + 2000),
                        new Color(50, 70, 140),
                        new Color(100, 150, 200),
                        new Color(180, 200, 240),
                        true, true);
        plane.setPosition((int) (Math.random() * 200), (int) (Math.random() * 200), content.getWidth(), content.getHeight());
        ((PlaneWithRadar) plane).setTypeDetails(detailsType);
        ((PlaneWithRadar) plane).setNumDetails((int) spinNumDetails.getValue());

        render();
    }

    private void bStartPlane_Click(ActionEvent e) {
        plane = new Plane((int) (Math.random() * 800 + 500),
                (int) (Math.random() * 4000 + 2000),
                new Color(50, 70, 140));

        plane.setPosition((int) (Math.random() * 200), (int) (Math.random() * 200), content.getWidth(), content.getHeight());
        render();
    }

    private void bDown_Click(ActionEvent e) {
        plane.move(Direction.Down);
        render();
    }

    private void bRight_Click(ActionEvent e) {
        plane.move(Direction.Right);
        render();
    }

    private void bUp_Click(ActionEvent e) {
        plane.move(Direction.Up);
        render();
    }

    private void bLeft_Click(ActionEvent e) {
        plane.move(Direction.Left);
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
        plane.draw(getGraphics());
        Graphics g = content.getGraphics();
        g.drawImage(buffer, 0, 0, null);
    }
}

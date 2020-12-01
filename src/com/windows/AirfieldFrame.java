package com.windows;

import com.collections.Airfield;

import com.entity.Plane;
import com.interfaces.IDopDraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class AirfieldFrame extends JFrame {

    private BufferedImage buffer;
    private Canvas content;
    private JPanel optionPanel;

    private int[] bufferData;
    private Graphics bufferGraphics;
    private int clearColor;

    private JButton bPutPlane;
    private JButton bPutPlaneWithRadar;
    private JButton bTakePlane;

    private JSpinner spinNumOfPlace;

    private Font mainFont;

   // private  JColorChooser colorChooser;


    //private Airfield<Aircraft, IDopDraw> airfield;

    public AirfieldFrame(int width, int height, String title) {
        //Интерфейс
        /////////////////////////
        setTitle(title);
        content = new Canvas();
        mainFont = new Font("Area", Font.LAYOUT_NO_LIMIT_CONTEXT, 16);
     //   colorChooser = new JColorChooser();

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
        spinNumOfPlace = new JSpinner(new SpinnerNumberModel(0, 0, 3, 1));
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
        optionPanel.add(new Label("asldka"));


        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());
        add(content, BorderLayout.CENTER);
        add(optionPanel, BorderLayout.EAST);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = 0Xffffffff;
        // Конец интерфейса
        ////////////////////////////

      //  airfield = new Airfield<>(content.getWidth(), content.getHeight());
      //  render();
    }

    private void takePlane_Click(ActionEvent e) {
//        if (maskedTextBox.Text != "") {
//            var car = airfield - Convert.ToInt32(maskedTextBox.Text);
//            if (car != null) {
//                var form = new PlaneWithRadarForm();
//                form.SetPlane(car);
//                form.ShowDialog();
//            }
//            Draw();
//        }
    }

    private void putPlaneWithRadar_Click(ActionEvent e) {
//        ColorDialog dialog = new ColorDialog();
//        if (dialog.ShowDialog() == DialogResult.OK) {
//            ColorDialog dialogDop = new ColorDialog();
//            if (dialogDop.ShowDialog() == DialogResult.OK) {
//                var car = new PlaneWithRadar(100, 1000, dialog.Color, dialogDop.Color, dialogDop.Color, true, true);
//                if (airfield + car) {
//                    Draw();
//                } else {
//                    MessageBox.Show("Аэропорт переполнен");
//                }
//            }
//        }
    }

    private void putPlane_Click(ActionEvent e) {

//        add(colorChooser, BorderLayout.CENTER);
//        Color color;
//        if ((color = colorChooser.getColor()) != null) {
//            Plane plane = new Plane(100, 1000, color);
//
//            if (airfield.plus(plane)) {
//                render();
//            } else {
//                JOptionPane.showMessageDialog(content, "Аэропорт переполнен");
//
//            }
//        }
    }

    public Graphics2D getGraphics() {
        return (Graphics2D) bufferGraphics;
    }

    public void clear(int color) {
        Arrays.fill(bufferData, color);
    }

    public void render() {
        clear(0XFFFFFFFF);
        //airfield.draw(getGraphics());
        Graphics g = content.getGraphics();
        g.drawImage(buffer, 0, 0, null);
    }
}

package com.company;

import com.entity.PlaneWithRadar;
import com.graphics.WoPGUI;

public class GameEngine {
    // Никто не знает, для чего существует этот класс...
    private WoPGUI gui;
    private PlaneWithRadar plane;

    public GameEngine(int width, int height, String title){
        gui = new WoPGUI(width, height, title, plane);
        System.out.println("Да как работает этот гит");
    }

}

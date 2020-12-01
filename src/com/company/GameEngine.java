package com.company;

import com.entity.PlaneWithRadar;
import com.windows.FramePlane;
import com.windows.AirfieldFrame;
import com.windows.FrameAirfield;

public class GameEngine {
    // Никто не знает, для чего существует этот класс...
    private FramePlane gui;
    private PlaneWithRadar plane;
    private AirfieldFrame airfieldFrame;
    private FrameAirfield frameAirfield;

    public GameEngine(int width, int height, String title){
       // gui = new WoPGUI(width, height, title, plane);
       //airfieldFrame = new AirfieldFrame(width, height, title);
        frameAirfield = new FrameAirfield(width, height, title);

    }

}

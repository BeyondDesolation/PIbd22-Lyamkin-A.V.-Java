package com.utils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageReader {

    public static BufferedImage loadImage(String imageName){

        BufferedImage image = null;

        try {
            image = ImageIO.read(ImageReader.class.getResource("/GUI/" + imageName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return image;
    }
}

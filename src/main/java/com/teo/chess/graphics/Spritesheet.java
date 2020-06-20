package com.teo.chess.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet {

    private String path;
    public int width;
    public int height;
    public int spriteSize;

    public int[] pixels;



    public Spritesheet(String path, int width, int height, int spriteSize) {
        this.path = path;
        this.width = width;
        this.height = height;
        this.spriteSize = spriteSize;
        pixels = new int[width * height];
        load();
    }

    private void load() {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(path));
        Image tmpImage = imageIcon.getImage();

        BufferedImage image = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(tmpImage, 0, 0, null);
        tmpImage.flush();

        int w = image.getWidth();
        int h = image.getHeight();
        image.getRGB(0, 0, w, h, pixels, 0, w);
    }
}

package com.teo.chess.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Spritesheet {

    private final String path;
    public final int spriteSize;
    public final int[] pixels;
    public final int width;

    public Spritesheet(String path, int width, int height, int spriteSize) {
        this.path = path;
        this.spriteSize = spriteSize;
        this.width = width;
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

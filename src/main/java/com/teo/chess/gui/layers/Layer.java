package com.teo.chess.gui.layers;

import com.teo.chess.Location;
import com.teo.chess.graphics.Sprite;
import com.teo.chess.gui.Board;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Layer {

    public BufferedImage image;
    public int pixels[];

    public Layer() {
        image = new BufferedImage(Board.WIDTH, Board.HEIGHT, BufferedImage.TYPE_INT_ARGB);
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    }

    public void renderSprite(Sprite sprite, Location location) {
        int yp = location.getPixelY();
        int xp = location.getPixelX();

        for (int y = 0; y < Board.TILE_SIZE; y++) {
            int ya = yp + y;
            for (int x = 0; x < Board.TILE_SIZE; x++) {
                int xa = xp + x;
                pixels[xa + ya * Board.WIDTH] = sprite.pixels[x + y * Board.TILE_SIZE];
            }
        }
    }

    public void clearLayer() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
}

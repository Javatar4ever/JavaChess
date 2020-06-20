package com.teo.chess.gui.layers;

import com.teo.chess.Game;
import com.teo.chess.Location;
import com.teo.chess.graphics.Sprite;
import com.teo.chess.gui.Board;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


public class TextLayer extends Layer {

    public BufferedImage image;
    public int pixels[];
    public static final int LETTER_SIZE = 9;
    public static final int SCALE = 3;
    private Sprite[] letters = new Sprite[3 * 11];
    private int alpha = 255;


    public TextLayer() {
        image = new BufferedImage(Board.WIDTH, Board.HEIGHT, BufferedImage.TYPE_INT_ARGB);
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        loadSprites();
    }

    private void loadSprites() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 11; x++) {
                letters[x + y * 11] = new Sprite(x, y, Game.lettersSheet);
            }
        }
    }

    public void fadeInString(String string) {
        alpha = 0;

        for (int i = 0; i < 255; i++) {
            alpha++;
            renderString(string);

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fadeOutString(string);
    }

    private void fadeOutString(String string) {
        for (int i = 0; i < 255; i++) {
            alpha--;
            renderString(string);

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        clearLayer();
    }

    public void renderString(String string) {
        char[] chars = string.toCharArray();

        int textLength = chars.length * (LETTER_SIZE * SCALE);
        int xOffset = (Board.WIDTH - textLength) / 2;
        int yOffset = (Board.HEIGHT / 2) - ((LETTER_SIZE * SCALE) / 2);


        for (int i = 0; i < chars.length; i++) {
            int ascii = (int) chars[i];
            Location location = new Location(0, 0);
            location.setPixelX((i * (LETTER_SIZE * SCALE)) + xOffset);
            location.setPixelY(yOffset);
            if (ascii == 32) {
                renderSprite(letters[26], location);
            } else {
                renderSprite(letters[(int) chars[i] - 65], location);
            }
        }
    }

    public void renderSprite(Sprite sprite, Location location) {
        int yp = location.getPixelY();
        int xp = location.getPixelX();

        for (int y = 0; y < LETTER_SIZE * SCALE; y++) {
            int ya = yp + y;
            for (int x = 0; x < LETTER_SIZE * SCALE; x++) {
                int xa = xp + x;

                pixels[xa + ya * Board.WIDTH] = (alpha << 24) | (sprite.pixels[(x / SCALE) + (y / SCALE) * LETTER_SIZE] & 0x00FFFFFF);
            }
        }

    }

    public void clearLayer() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
}

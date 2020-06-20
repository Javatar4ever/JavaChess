package com.teo.chess.graphics;

import com.teo.chess.Game;


public class Sprite {

    private int x;
    private int y;
    private Spritesheet spritesheet;
    public int[] pixels;

    public Sprite(int x, int y, Spritesheet spritesheet) {
        this.x = x * spritesheet.spriteSize;
        this.y = y * spritesheet.spriteSize;
        this.spritesheet = spritesheet;

        pixels = new int[spritesheet.spriteSize * spritesheet.spriteSize];
        load();
    }

    private void load() {
        for (int y = 0; y < spritesheet.spriteSize; y++) {
            for (int x = 0; x < spritesheet.spriteSize; x++) {
                pixels[x + y * spritesheet.spriteSize] = spritesheet.pixels[(x + this.x) + (y + this.y) * spritesheet.width];
            }
        }
    }
}

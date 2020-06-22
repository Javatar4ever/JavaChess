package com.teo.chess.graphics;

import java.io.Serializable;

public class Sprite implements Serializable {

    private final int x;
    private final int y;
    private final Spritesheet spritesheet;
    public final int[] pixels;

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

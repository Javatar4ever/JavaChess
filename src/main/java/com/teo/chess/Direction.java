package com.teo.chess;

public enum Direction {
    NO_DIRECTION(0, 0),
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(-1, -1),
    UP_RIGHT(1, -1),
    DOWN_LEFT(-1, 1),
    DOWN_RIGHT(1, 1);

    private final int xDir;
    private final int yDir;

    Direction(int xDir, int yDir) {
        this.xDir = xDir;
        this.yDir = yDir;
    }

    public int getXDir() {
        return xDir;
    }

    public int getYDir() {
        return yDir;
    }
}

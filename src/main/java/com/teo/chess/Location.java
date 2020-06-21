package com.teo.chess;

import com.teo.chess.gui.Board;


public class Location {

    private final int boardX;
    private final int boardY;
    private int pixelX;
    private int pixelY;
    private final String sanName;

    public Location(int boardX, int boardY) {
        this.boardX = boardX;
        this.boardY = boardY;
        pixelX = boardX * Board.TILE_SIZE;
        pixelY = boardY * Board.TILE_SIZE;

        sanName = String.valueOf((char)(boardX + 97)) + (Board.TILES_ACROSS - boardY);
    }

    public int getBoardX() {
        return boardX;
    }
    public int getBoardY() {
        return boardY;
    }
    public int getPixelX() {
        return pixelX;
    }

    public void setPixelX(int pixelX) {
        this.pixelX = pixelX;
    }

    public int getPixelY() {
        return pixelY;
    }

    public void setPixelY(int pixelY) {
        this.pixelY = pixelY;
    }

    public String getSanName() {return sanName;}

    @Override
    public boolean equals(Object location) {
        return location instanceof Location && boardX == ((Location) location).getBoardX() && boardY == ((Location) location).getBoardY();
    }

    @Override
    public String toString() {
        return "Location{" +
                "boardX=" + boardX +
                ", boardY=" + boardY +
                ", pixelX=" + pixelX +
                ", pixelY=" + pixelY +
                '}';
    }
}

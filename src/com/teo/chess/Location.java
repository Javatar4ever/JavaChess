package com.teo.chess;

import com.teo.chess.gui.Board;


public class Location {

    private int boardX;
    private int boardY;
    private int pixelX;
    private int pixelY;
    private String sanName;

    public Location(int boardX, int boardY) {
        this.boardX = boardX;
        this.boardY = boardY;
        pixelX = boardX * Board.TILE_SIZE;
        pixelY = boardY * Board.TILE_SIZE;

        sanName = String.valueOf((char)(boardX + 97)) + (Board.TILES_ACROSS - boardY);
    }

    public Location(Location location) {
        this(location.getBoardX(), location.getBoardY());
    }


    public int getDistanceX(Location location) {
        return location.getBoardX() - getBoardX();
    }
    public int getAbsDistanceX(Location location) {
        return Math.abs(getBoardX() - location.getBoardX());
    }
    public int getDistanceY(Location location) {
        return location.getBoardY() - getBoardY();
    }
    public int getAbsDistanceY(Location location) {
        return Math.abs(getBoardY() - location.getBoardY());
    }

    public boolean isSameTile(Location location) {
        return getDistanceX(location) == 0 && getDistanceY(location) == 0;
    }




    public int getBoardX() {
        return boardX;
    }

    public void setBoardX(int boardX) {
        this.boardX = boardX;
    }

    public int getBoardY() {
        return boardY;
    }

    public void setBoardY(int boardY) {
        this.boardY = boardY;
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

    public void setSanName(String sanName) {this.sanName = sanName;}
}

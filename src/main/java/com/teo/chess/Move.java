package com.teo.chess;


public class Move {

    private MoveType moveType;
    private Location startLocation;
    private Location endLocation;
    private int xDir;
    private int yDir;

    public Move(MoveType moveType, Location startLocation, Location endLocation, int xDir, int yDir) {
        this.moveType = moveType;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.xDir = xDir;
        this.yDir = yDir;
    }

    public MoveType getMoveType() {return moveType;}
    public void setMoveType(MoveType moveType) {this.moveType = moveType;}
    public Location getStartLocation() {return startLocation;}
    public void setStartLocation(Location startLocation) {this.startLocation = startLocation;}
    public Location getEndLocation() {return endLocation;}
    public void setEndLocation(Location endLocation) {this.endLocation = endLocation;}
    public int getxDir() {return xDir;}
    public void setxDir(int xDir) {this.xDir = xDir;}
    public int getyDir() {return yDir;}
    public void setyDir(int yDir) {this.yDir = yDir;}
}

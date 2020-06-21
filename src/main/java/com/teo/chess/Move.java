package com.teo.chess;


public class Move {

    private MoveType moveType;
    private Location endLocation;
    private Direction direction;

    public Move(MoveType moveType, Location endLocation, Direction direction) {
        this.moveType = moveType;
        this.endLocation = endLocation;
        this.direction = direction;
    }

    public Move(MoveType moveType, Location endLocation) {
        this.moveType = moveType;
        this.endLocation = endLocation;
    }

    public Move(MoveType moveType, Direction direction) {
        this.moveType = moveType;
        this.direction = direction;
        this.endLocation = new Location(direction.getXDir(), direction.getYDir()); //TODO: remove?
    }

    public MoveType getMoveType() {return moveType;}
    public Location getEndLocation() {return endLocation;}
    public int getxDir() {return direction.getXDir();}
    public int getyDir() {return direction.getYDir();}

    @Override
    public String toString() {
        return "Move{" +
                "moveType=" + moveType +
                ", endLocation=" + endLocation +
                ", direction=" + direction +
                '}';
    }
}

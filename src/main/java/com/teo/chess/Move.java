package com.teo.chess;


public class Move {

    private final MoveType moveType;
    private final Location endLocation;
    private final Direction direction;

    public Move(MoveType moveType, Location endLocation, Direction direction) {
        this.moveType = moveType;
        this.endLocation = endLocation;
        this.direction = direction;
    }

    public Move(MoveType moveType, Location endLocation) {
        this.moveType = moveType;
        this.endLocation = endLocation;
        this.direction = Direction.NO_DIRECTION;
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

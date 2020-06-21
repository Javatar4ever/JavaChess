package com.teo.chess.pieces;

import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.graphics.Sprite;

public abstract class Piece {
    private final PieceType type;
    private Location startLocation;
    private final Sprite sprite;
    private final PieceColor color;
    private final String sanName;

    public Piece(PieceType type, Location location, Sprite sprite, PieceColor color, String sanName) {
        this.type = type;
        this.startLocation = location;
        this.sprite = sprite;
        this.color = color;
        this.sanName = sanName;
    }

    /**
     * Returns an array of moves that the piece is able to do from the provided location, not accounting for placement of other
     * pieces
     * @param startLocation : Location where all moves originate from
     * @return : Array of moves
     */
    public abstract Move[] getMoveset(Location startLocation);

    /**
     * Returns an array of captures that the piece is able to do from the provided location, not accounting for placement of other
     * pieces
     * @param startLocation : Location where all captures originate from
     * @return : Array of captures
     */
    public abstract Move[] getCaptures(Location startLocation);

    public PieceType getType() {return type;}
    public Location getLocation() {return startLocation;}
    public void setLocation(Location location) {this.startLocation = location;}
    public Sprite getSprite() {return sprite;}
    public PieceColor getColor() {return color;}
    public String getSanName() {return sanName;}
}

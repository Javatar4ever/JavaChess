package com.teo.chess.pieces;

import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.graphics.Sprite;


public abstract class Piece {
    private PieceType type;
    private Location startLocation;
    private Sprite sprite;
    private PieceColor color;
    private String sanName;


    public Piece(PieceType type, Location location, Sprite sprite, PieceColor color, String sanName) {
        this.type = type;
        this.startLocation = location;
        this.sprite = sprite;
        this.color = color;
        this.sanName = sanName;
    }

    public Piece(Piece piece) {
        this(piece.getType(), new Location(piece.getLocation()), piece.getSprite(), piece.getColor(), piece.getSanName());
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

    /**
     * Method that gets called to create a copy of every Piece in the provided array.
     *
     * @param piecesArr : Array to be copied
     * @return : Copied array
     */
    public static Piece[][] copyPieceArray(Piece[][] piecesArr) {

        Piece[][] outputArr = new Piece[Board.TILES_ACROSS][Board.TILES_ACROSS];

        for (int y = 0; y < piecesArr.length; y++) {
            for (int x = 0; x < piecesArr[y].length; x++) {
                if (piecesArr[y][x] != null) {
                    switch (piecesArr[y][x].getType()) {
                        case BISHOP:
                            outputArr[y][x] = new Bishop(piecesArr[y][x]);
                            break;
                        case KING:
                            outputArr[y][x] = new King(piecesArr[y][x]);
                            break;
                        case KNIGHT:
                            outputArr[y][x] = new Knight(piecesArr[y][x]);
                            break;
                        case PAWN:
                            outputArr[y][x] = new Pawn(piecesArr[y][x]);
                            break;
                        case QUEEN:
                            outputArr[y][x] = new Queen(piecesArr[y][x]);
                            break;
                        case ROOK:
                            outputArr[y][x] = new Rook(piecesArr[y][x]);
                            break;
                    }
                }
            }
        }

        return outputArr;
    }

    public PieceType getType() {return type;}
    public void setType(PieceType type) {this.type = type;}
    public Location getLocation() {return startLocation;}
    public void setLocation(Location location) {this.startLocation = location;}
    public Sprite getSprite() {return sprite;}
    public void setSprite(Sprite sprite) {this.sprite = sprite;}
    public PieceColor getColor() {return color;}
    public void setColor(PieceColor color) {this.color = color;}
    public String getSanName() {return sanName;}
    public void setSanName(String sanName) {this.sanName = sanName;}
}

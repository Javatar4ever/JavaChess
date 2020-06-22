package com.teo.chess.states;

import com.teo.chess.Location;
import com.teo.chess.gui.Board;
import com.teo.chess.pieces.Piece;

import java.io.Serializable;

public class BoardState implements Serializable {

    private Piece[][] pieces = new Piece[Board.TILES_ACROSS][Board.TILES_ACROSS];

    public BoardState() {

    }
    public BoardState(Piece[][] pieces) {
        this.pieces = pieces;
    }

    public Piece[][] getState() {
        return pieces;
    }

    public Piece getPiece(Location location) {
        int x = location.getBoardX();
        int y = location.getBoardY();

        return pieces[y][x];
    }
}

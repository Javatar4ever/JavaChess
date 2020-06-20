package com.teo.chess.states;

import com.teo.chess.Location;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.pieces.Piece;

public class BoardState {

    private Piece[][] pieces = new Piece[Board.TILES_ACROSS][Board.TILES_ACROSS];
    private PieceColor currentTurn;

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

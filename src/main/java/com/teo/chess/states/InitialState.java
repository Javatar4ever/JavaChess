package com.teo.chess.states;

import com.teo.chess.Game;
import com.teo.chess.Location;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.graphics.Sprite;
import com.teo.chess.pieces.*;

public class InitialState extends BoardState {

    private Piece[][] pieces = new Piece[Board.TILES_ACROSS][Board.TILES_ACROSS];

    public InitialState() {
        //black
        pieces[0][0] = new Rook(new Location(0, 0), new Sprite(2, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[0][1] = new Knight(new Location(1, 0), new Sprite(3, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[0][2] = new Bishop(new Location(2, 0), new Sprite(4, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[0][3] = new Queen(new Location(3, 0), new Sprite(0, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[0][4] = new King(new Location(4, 0), new Sprite(1, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[0][5] = new Bishop(new Location(5, 0), new Sprite(4, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[0][6] = new Knight(new Location(6, 0), new Sprite(3, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[0][7] = new Rook(new Location(7, 0), new Sprite(2, 0, Game.spritesheet), PieceColor.BLACK);

        pieces[1][0] = new Pawn(new Location(0, 1), new Sprite(5, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[1][1] = new Pawn(new Location(1, 1), new Sprite(5, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[1][2] = new Pawn(new Location(2, 1), new Sprite(5, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[1][3] = new Pawn(new Location(3, 1), new Sprite(5, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[1][4] = new Pawn(new Location(4, 1), new Sprite(5, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[1][5] = new Pawn(new Location(5, 1), new Sprite(5, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[1][6] = new Pawn(new Location(6, 1), new Sprite(5, 0, Game.spritesheet), PieceColor.BLACK);
        pieces[1][7] = new Pawn(new Location(7, 1), new Sprite(5, 0, Game.spritesheet), PieceColor.BLACK);

        //white
        pieces[6][0] = new Pawn(new Location(0, 6), new Sprite(5, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[6][1] = new Pawn(new Location(1, 6), new Sprite(5, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[6][2] = new Pawn(new Location(2, 6), new Sprite(5, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[6][3] = new Pawn(new Location(3, 6), new Sprite(5, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[6][4] = new Pawn(new Location(4, 6), new Sprite(5, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[6][5] = new Pawn(new Location(5, 6), new Sprite(5, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[6][6] = new Pawn(new Location(6, 6), new Sprite(5, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[6][7] = new Pawn(new Location(7, 6), new Sprite(5, 1, Game.spritesheet), PieceColor.WHITE);

        pieces[7][0] = new Rook(new Location(0, 7), new Sprite(2, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[7][1] = new Knight(new Location(1, 7), new Sprite(3, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[7][2] = new Bishop(new Location(2, 7), new Sprite(4, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[7][3] = new Queen(new Location(3, 7), new Sprite(0, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[7][4] = new King(new Location(4, 7), new Sprite(1, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[7][5] = new Bishop(new Location(5, 7), new Sprite(4, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[7][6] = new Knight(new Location(6, 7), new Sprite(3, 1, Game.spritesheet), PieceColor.WHITE);
        pieces[7][7] = new Rook(new Location(7, 7), new Sprite(2, 1, Game.spritesheet), PieceColor.WHITE);
    }

    public Piece[][] getState() {
        return pieces;
    }

}

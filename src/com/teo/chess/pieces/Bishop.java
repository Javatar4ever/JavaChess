package com.teo.chess.pieces;

import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.MoveType;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.graphics.Sprite;

import java.util.ArrayList;


public class Bishop extends Piece {

    private Location location;

    public Bishop(Location location, Sprite sprite, PieceColor color) {
        super(PieceType.BISHOP, location, sprite, color, "B");
    }
    public Bishop(Piece bishop) {
        this(new Location(bishop.getLocation()), bishop.getSprite(), bishop.getColor());
    }

    @Override
    public Move[] getMoveset(Location startLocation) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = startLocation.getBoardX();
        int y = startLocation.getBoardY();

        if (y > 0 && x > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x - 1, y - 1), -1, -1)); //up left
        if (y > 0 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x + 1, y - 1), 1, -1)); //up right
        if (y < Board.TILES_ACROSS - 1 && x > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x - 1, y + 1), -1, 1)); //down left
        if (y < Board.TILES_ACROSS - 1 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x + 1, y + 1), 1, 1)); //down right

        return moveset.toArray(new Move[moveset.size()]);
    }

    public Move[] getCaptures(Location startLocation) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = startLocation.getBoardX();
        int y = startLocation.getBoardY();

        if (y > 0 && x > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, startLocation, new Location(x - 1, y - 1), -1, -1)); //up left
        if (y > 0 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, startLocation, new Location(x + 1, y - 1), 1, -1)); //up right
        if (y < Board.TILES_ACROSS - 1 && x > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, startLocation, new Location(x - 1, y + 1), -1, 1)); //down left
        if (y < Board.TILES_ACROSS - 1 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, startLocation, new Location(x + 1, y + 1), 1, 1)); //down right

        return moveset.toArray(new Move[moveset.size()]);
    }
}

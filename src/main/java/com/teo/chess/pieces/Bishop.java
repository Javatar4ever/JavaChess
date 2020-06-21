package com.teo.chess.pieces;

import com.teo.chess.Direction;
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
            moveset.add(new Move(MoveType.CONTINUOUS, Direction.UP_LEFT));
        if (y > 0 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, Direction.UP_RIGHT));
        if (y < Board.TILES_ACROSS - 1 && x > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, Direction.DOWN_LEFT));
        if (y < Board.TILES_ACROSS - 1 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, Direction.DOWN_RIGHT));

        return moveset.toArray(new Move[moveset.size()]);
    }

    public Move[] getCaptures(Location startLocation) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = startLocation.getBoardX();
        int y = startLocation.getBoardY();

        if (y > 0 && x > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, Direction.UP_LEFT));
        if (y > 0 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, Direction.UP_RIGHT));
        if (y < Board.TILES_ACROSS - 1 && x > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, Direction.DOWN_LEFT));
        if (y < Board.TILES_ACROSS - 1 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, Direction.DOWN_RIGHT));

        return moveset.toArray(new Move[moveset.size()]);
    }
}

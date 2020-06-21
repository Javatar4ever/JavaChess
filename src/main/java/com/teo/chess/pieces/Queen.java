package com.teo.chess.pieces;

import com.teo.chess.Direction;
import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.MoveType;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.graphics.Sprite;

import java.util.ArrayList;


public class Queen extends Piece {

    public Queen(Location location, Sprite sprite, PieceColor color) {
        super(PieceType.QUEEN, location, sprite, color, "Q");
    }

    @Override
    public Move[] getMoveset(Location startLocation) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = startLocation.getBoardX();
        int y = startLocation.getBoardY();

        if (y > 0 && x > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, new Location(x - 1, y - 1), Direction.UP_LEFT));
        if (y > 0 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, new Location(x + 1, y - 1), Direction.UP_RIGHT));
        if (y < Board.TILES_ACROSS - 1 && x > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, new Location(x - 1, y + 1), Direction.DOWN_LEFT));
        if (y < Board.TILES_ACROSS - 1 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, new Location(x + 1, y + 1), Direction.DOWN_RIGHT));

        if (y > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, new Location(x, y - 1), Direction.UP));
        if (x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, new Location(x + 1, y), Direction.RIGHT));
        if (x > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, new Location(x - 1, y), Direction.LEFT));
        if (y < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, new Location(x, y + 1), Direction.DOWN));

        return moveset.toArray(new Move[0]);
    }

    public Move[] getCaptures(Location startLocation) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = startLocation.getBoardX();
        int y = startLocation.getBoardY();

        if (y > 0 && x > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, new Location(x - 1, y - 1), Direction.UP_LEFT));
        if (y > 0 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, new Location(x + 1, y - 1), Direction.UP_RIGHT));
        if (y < Board.TILES_ACROSS - 1 && x > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, new Location(x - 1, y + 1), Direction.DOWN_LEFT));
        if (y < Board.TILES_ACROSS - 1 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, new Location(x + 1, y + 1), Direction.DOWN_RIGHT));

        if (y > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, new Location(x, y - 1), Direction.UP));
        if (x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, new Location(x + 1, y), Direction.RIGHT));
        if (x > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, new Location(x - 1, y), Direction.LEFT));
        if (y < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, new Location(x, y + 1), Direction.DOWN));

        return moveset.toArray(new Move[0]);
    }
}

package com.teo.chess.pieces;

import com.teo.chess.Direction;
import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.MoveType;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.graphics.Sprite;

import java.util.ArrayList;


public class Rook extends Piece {

    public Rook(Location location, Sprite sprite, PieceColor color) {
        super(PieceType.ROOK, location, sprite, color, "R");
    }

    @Override
    public Move[] getMoveset(Location startLocation) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = startLocation.getBoardX();
        int y = startLocation.getBoardY();

        if (y > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, new Location(x, y - 1), Direction.UP));
        if (y < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, new Location(x, y + 1), Direction.DOWN));
        if (x > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, new Location(x - 1, y), Direction.LEFT));
        if (x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, new Location(x + 1, y), Direction.RIGHT));

        return moveset.toArray(new Move[0]);
    }

    public Move[] getCaptures(Location startLocation) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = startLocation.getBoardX();
        int y = startLocation.getBoardY();

        if (y > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, new Location(x, y - 1), Direction.UP));
        if (y < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, new Location(x, y + 1), Direction.DOWN));
        if (x > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, new Location(x - 1, y), Direction.LEFT));
        if (x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, new Location(x + 1, y), Direction.RIGHT));

        return moveset.toArray(new Move[0]);
    }
}

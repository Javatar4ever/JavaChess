package com.teo.chess.pieces;

import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.MoveType;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.graphics.Sprite;

import java.util.ArrayList;


public class Pawn extends Piece {

    public Pawn(Location location, Sprite sprite, PieceColor color) {
        super(PieceType.PAWN, location, sprite, color, "");
    }

    public Pawn(Piece pawn) {
        this(new Location(pawn.getLocation()), pawn.getSprite(), pawn.getColor());
    }

    public Move[] getMoveset(Location location) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = location.getBoardX();
        int y = location.getBoardY();

        if (getColor() == PieceColor.WHITE) {
            if (y > 0)
                moveset.add(new Move(MoveType.STATIC, location, new Location(x, y - 1), 0, 0));
            if (y == 6)
                moveset.add(new Move(MoveType.PAWN_START, location, new Location(x, y - 2), 0, 0));
        }
        else if (getColor() == PieceColor.BLACK) {
            if (y < Board.TILES_ACROSS - 1)
                moveset.add(new Move(MoveType.STATIC, location, new Location(x, y + 1), 0, 0));
            if (y == 1)
                moveset.add(new Move(MoveType.PAWN_START, location, new Location(x, y + 2), 0, 0));
        }

        return moveset.toArray(new Move[moveset.size()]);
    }

    public Move[] getCaptures(Location startLocation) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = startLocation.getBoardX();
        int y = startLocation.getBoardY();

        if (getColor() == PieceColor.WHITE) {
            if (x > 0 && y > 0)
                moveset.add(new Move(MoveType.CAPTURE_STATIC, startLocation, new Location(x - 1, y - 1), -1, -1));
            if (x < Board.TILES_ACROSS - 1 && y > 0)
                moveset.add(new Move(MoveType.CAPTURE_STATIC, startLocation, new Location(x + 1, y - 1), 1, -1));
        }
        else if (getColor() == PieceColor.BLACK) {
            if (x > 0)
                moveset.add(new Move(MoveType.CAPTURE_STATIC, startLocation, new Location(x - 1, y + 1), -1, 1));
            if (x < Board.TILES_ACROSS - 1)
                moveset.add(new Move(MoveType.CAPTURE_STATIC, startLocation, new Location(x + 1, y + 1), 1, 1));
        }

        return moveset.toArray(new Move[moveset.size()]);
    }


}

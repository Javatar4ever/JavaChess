package com.teo.chess.pieces;

import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.MoveType;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.graphics.Sprite;

import java.util.ArrayList;


public class Rook extends Piece {

    private boolean can_castle = true; //changes to false when rook moves

    public Rook(Location location, Sprite sprite, PieceColor color) {
        super(PieceType.ROOK, location, sprite, color, "R");
    }

    public Rook(Piece rook) {
        this(new Location(rook.getLocation()), rook.getSprite(), rook.getColor());
    }

    @Override
    public Move[] getMoveset(Location startLocation) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = startLocation.getBoardX();
        int y = startLocation.getBoardY();

        if (y > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x, y - 1), 0, -1)); //up
        if (y < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x, y + 1), 0, 1)); //down
        if (x > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x - 1, y), -1, 0)); //left
        if (x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x + 1, y), 1, 0)); //right

        return moveset.toArray(new Move[moveset.size()]);
    }

    public Move[] getCaptures(Location startLocation) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = startLocation.getBoardX();
        int y = startLocation.getBoardY();

        if (y > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, startLocation, new Location(x, y - 1), 0, -1)); //up
        if (y < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, startLocation, new Location(x, y + 1), 0, 1)); //down
        if (x > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, startLocation, new Location(x - 1, y), -1, 0)); //left
        if (x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, startLocation, new Location(x + 1, y), 1, 0)); //right

        return moveset.toArray(new Move[moveset.size()]);
    }
}
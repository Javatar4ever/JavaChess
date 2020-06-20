package com.teo.chess.pieces;

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

    public Queen(Piece queen) {
        this(new Location(queen.getLocation()), queen.getSprite(), queen.getColor());
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
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x - 1, y + 1), - 1, 1)); //down left
        if (y < Board.TILES_ACROSS - 1 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x + 1, y + 1), 1, 1)); //down right

        if (y > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x, y - 1), 0, -1)); //up
        if (x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x + 1, y), 1, 0)); //right
        if (x > 0)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x - 1, y), -1, 0)); //left
        if (y < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CONTINUOUS, startLocation, new Location(x, y + 1), 0, 1)); //down

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

        if (y > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, startLocation, new Location(x, y - 1), 0, -1)); //up
        if (x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, startLocation, new Location(x + 1, y), 1, 0)); //right
        if (x > 0)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, startLocation, new Location(x - 1, y), -1, 0)); //left
        if (y < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_CONTINUOUS, startLocation, new Location(x, y + 1), 0, 1)); //down

        return moveset.toArray(new Move[moveset.size()]);
    }
}

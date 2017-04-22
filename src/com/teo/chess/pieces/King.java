package com.teo.chess.pieces;

import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.MoveType;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.graphics.Sprite;

import java.util.ArrayList;

public class King extends Piece {

    private boolean can_castle = true; //changes to false when king moves

    public King(Location location, Sprite sprite, PieceColor color) {
        super(PieceType.KING, location, sprite, color, "K");
    }

    public King(Piece king) {
        this(new Location(king.getLocation()), king.getSprite(), king.getColor());
    }

    @Override
    public Move[] getMoveset(Location startLocation) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = startLocation.getBoardX();
        int y = startLocation.getBoardY();

        if (y > 0 && x > 0)
            moveset.add(new Move(MoveType.STATIC, startLocation, new Location(x - 1, y - 1), 0, 0)); //up left
        if (y > 0 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.STATIC, startLocation, new Location(x + 1, y - 1), 0, 0)); //up right
        if (y < Board.TILES_ACROSS - 1 && x > 0)
            moveset.add(new Move(MoveType.STATIC, startLocation, new Location(x - 1, y + 1), 0, 0)); //down left
        if (y < Board.TILES_ACROSS - 1 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.STATIC, startLocation, new Location(x + 1, y + 1), 0, 0)); //down right

        if (y > 0)
            moveset.add(new Move(MoveType.STATIC, startLocation, new Location(x, y - 1), 0, 0)); //up
        if (x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.STATIC, startLocation, new Location(x + 1, y), 0, 0)); //right
        if (x > 0)
            moveset.add(new Move(MoveType.STATIC, startLocation, new Location(x - 1, y), 0, 0)); //left
        if (y < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.STATIC, startLocation, new Location(x, y + 1), 0, 0)); //down

        return moveset.toArray(new Move[moveset.size()]);
    }

    public Move[] getCaptures(Location startLocation) {
        ArrayList<Move> moveset = new ArrayList<>();

        int x = startLocation.getBoardX();
        int y = startLocation.getBoardY();

        if (y > 0 && x > 0)
            moveset.add(new Move(MoveType.CAPTURE_STATIC, startLocation, new Location(x - 1, y - 1), 0, 0)); //up left
        if (y > 0 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_STATIC, startLocation, new Location(x + 1, y - 1), 0, 0)); //up right
        if (y < Board.TILES_ACROSS - 1 && x > 0)
            moveset.add(new Move(MoveType.CAPTURE_STATIC, startLocation, new Location(x - 1, y + 1), 0, 0)); //down left
        if (y < Board.TILES_ACROSS - 1 && x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_STATIC, startLocation, new Location(x + 1, y + 1), 0, 0)); //down right

        if (y > 0)
            moveset.add(new Move(MoveType.CAPTURE_STATIC, startLocation, new Location(x, y - 1), 0, 0)); //up
        if (x < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_STATIC, startLocation, new Location(x + 1, y), 0, 0)); //right
        if (x > 0)
            moveset.add(new Move(MoveType.CAPTURE_STATIC, startLocation, new Location(x - 1, y), 0, 0)); //left
        if (y < Board.TILES_ACROSS - 1)
            moveset.add(new Move(MoveType.CAPTURE_STATIC, startLocation, new Location(x, y + 1), 0, 0)); //down

        return moveset.toArray(new Move[moveset.size()]);
    }
}

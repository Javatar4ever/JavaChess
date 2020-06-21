package com.teo.chess.pieces;

import com.teo.chess.Direction;
import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.MoveType;
import com.teo.chess.ascii.AsciiBoardBuilder;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class KingMoveTest {

    private final Location centerOfBoard = new Location(Board.TILES_ACROSS / 2, Board.TILES_ACROSS / 2);

    //Verify King can move in all valid directions

    @Test
    public void testGetMovesetUp() {
        verifyValidMove(centerOfBoard, Direction.UP);
    }

    @Test
    public void testGetMovesetDown() {
        verifyValidMove(centerOfBoard, Direction.DOWN);
    }

    @Test
    public void testGetMovesetLeft() {
        verifyValidMove(centerOfBoard, Direction.LEFT);
    }

    @Test
    public void testGetMovesetRight() {
        verifyValidMove(centerOfBoard, Direction.RIGHT);
    }

    @Test
    public void testGetMovesetUpLeft() {
        verifyValidMove(centerOfBoard, Direction.UP_LEFT);
    }

    @Test
    public void testGetMovesetUpRight() {
        verifyValidMove(centerOfBoard, Direction.UP_RIGHT);
    }

    @Test
    public void testGetMovesetDownLeft() {
        verifyValidMove(centerOfBoard, Direction.DOWN_LEFT);
    }

    @Test
    public void testGetMovesetDownRight() {
        verifyValidMove(centerOfBoard, Direction.DOWN_RIGHT);
    }

    //Verify King cannot move outside Board tiles

    @Test
    public void testGetMovesetInvalidUp() {
        verifyInvalidMove(new Location(0, 0), Direction.UP);
    }

    @Test
    public void testGetMovesetInvalidDown() {
        verifyInvalidMove(new Location(0, Board.TILES_ACROSS - 1), Direction.DOWN);
    }

    @Test
    public void testGetMovesetInvalidLeft() {
        verifyInvalidMove(new Location(0, 0), Direction.LEFT);
    }

    @Test
    public void testGetMovesetInvalidRight() {
        verifyInvalidMove(new Location(Board.TILES_ACROSS - 1, 0), Direction.RIGHT);
    }

    @Test
    public void testGetMovesetInvalidUpLeft() {
        verifyInvalidMove(new Location(0, 0), Direction.UP_LEFT);
    }

    @Test
    public void testGetMovesetInvalidUpRight() {
        verifyInvalidMove(new Location(Board.TILES_ACROSS - 1, 0), Direction.UP_RIGHT);
    }

    @Test
    public void testGetMovesetInvalidDownLeft() {
        verifyInvalidMove(new Location(0, Board.TILES_ACROSS - 1), Direction.DOWN_LEFT);
    }

    @Test
    public void testGetMovesetInvalidDownRight() {
        verifyInvalidMove(new Location(Board.TILES_ACROSS - 1, Board.TILES_ACROSS - 1), Direction.DOWN_RIGHT);
    }

    //Verify King cannot move continuously

    @Test
    public void testGetMovesetOnlyStaticMoves() {
        King king = new King(centerOfBoard, null, PieceColor.WHITE);
        Move[] possibleMoves = king.getMoveset(centerOfBoard);

        System.out.println("Verify that king cannot move continuously");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(king));
        long numFound = Arrays.stream(possibleMoves).filter(m -> m.getMoveType().equals(MoveType.CONTINUOUS)).count();
        assertEquals(0, numFound);
    }


    private void verifyValidMove(Location startLocation, Direction direction) {
        King king = new King(startLocation, null, PieceColor.WHITE);
        Location endLocation = new Location(startLocation.getBoardX() + direction.getXDir(), startLocation.getBoardY() + direction.getYDir());
        Move[] possibleMoves = king.getMoveset(startLocation);

        System.out.println("Verify that king can move " + direction);
        System.out.println(AsciiBoardBuilder.getAsciiBoard(king));
        int numFound = MoveFilter.getStaticMoves(endLocation, possibleMoves).length;
        assertEquals(1, numFound);
    }

    private void verifyInvalidMove(Location startLocation, Direction direction) {
        King king = new King(startLocation, null, PieceColor.WHITE);
        Location endLocation = new Location(startLocation.getBoardX() + direction.getXDir(), startLocation.getBoardY() + direction.getYDir());
        Move[] possibleMoves = king.getMoveset(startLocation);

        System.out.println("Verify that king cannot move " + direction);
        System.out.println(AsciiBoardBuilder.getAsciiBoard(king));
        int numFound = MoveFilter.getStaticMoves(endLocation, possibleMoves).length;
        assertNotEquals(1, numFound);
    }
}

package com.teo.chess.pieces;

import com.teo.chess.Direction;
import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.ascii.AsciiBoardBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopMoveTest {

    private final Location centerOfBoard = new Location(Board.TILES_ACROSS / 2, Board.TILES_ACROSS / 2);

    //Verify that Bishop can move in all valid directions

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

    //Verify Bishop cannot move to tiles outside of board

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

    //Verify that Bishop cannot move in any other direction

    @Test
    public void testGetMovesetInvalidUp() {
        verifyInvalidMove(centerOfBoard, Direction.UP);
    }

    @Test
    public void testGetMovesetInvalidDown() {
        verifyInvalidMove(centerOfBoard, Direction.DOWN);
    }

    @Test
    public void testGetMovesetInvalidLeft() {
        verifyInvalidMove(centerOfBoard, Direction.LEFT);
    }

    @Test
    public void testGetMovesetInvalidRight() {
        verifyInvalidMove(centerOfBoard, Direction.RIGHT);
    }

    private void verifyValidMove(Location startLocation, Direction direction) {
        Bishop bishop = new Bishop(startLocation, null, PieceColor.WHITE);
        Move[] possibleMoves = bishop.getMoveset(startLocation);

        System.out.println("Verify that bishop can move " + direction);
        System.out.println(AsciiBoardBuilder.getAsciiBoard(bishop));
        int numFound = MoveFilter.getContinousMoves(direction, possibleMoves).length;
        assertEquals(1, numFound);
    }

    private void verifyInvalidMove(Location startLocation, Direction direction) {
        Bishop bishop = new Bishop(startLocation, null, PieceColor.WHITE);
        Move[] possibleMoves = bishop.getMoveset(startLocation);

        System.out.println("Verify that bishop cannot move " + direction);
        System.out.println(AsciiBoardBuilder.getAsciiBoard(bishop));
        int numFound = MoveFilter.getContinousMoves(direction, possibleMoves).length;
        assertNotEquals(1, numFound);
    }
}
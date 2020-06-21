package com.teo.chess.pieces;

import com.teo.chess.Direction;
import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopMoveTest {

    //Verify that Bishop can move in all valid directions

    @Test
    public void testGetMovesetUpLeft() {
        verifyValidMove(4, 4, Direction.UP_LEFT);
    }

    @Test
    public void testGetMovesetUpRight() {
        verifyValidMove(4, 4, Direction.UP_RIGHT);
    }

    @Test
    public void testGetMovesetDownLeft() {
        verifyValidMove(4, 4, Direction.DOWN_LEFT);
    }

    @Test
    public void testGetMovesetDownRight() {
        verifyValidMove(4, 4, Direction.DOWN_RIGHT);
    }

    //Verify Bishop cannot move to tiles outside of board

    @Test
    public void testGetMovesetInvalidUpLeft() {
        verifyInvalidMove(0, 0, Direction.UP_LEFT);
    }

    @Test
    public void testGetMovesetInvalidUpRight() {
        verifyInvalidMove(Board.TILES_ACROSS - 1, 0, Direction.UP_RIGHT);
    }

    @Test
    public void testGetMovesetInvalidDownLeft() {
        verifyInvalidMove(0, Board.TILES_ACROSS - 1, Direction.DOWN_LEFT);
    }

    @Test
    public void testGetMovesetInvalidDownRight() {
        verifyInvalidMove(Board.TILES_ACROSS - 1, Board.TILES_ACROSS - 1, Direction.DOWN_RIGHT);
    }

    //Verify that Bishop cannot move in any other direction

    @Test
    public void testGetMovesetInvalidUp() {
        verifyInvalidMove(4, 4, Direction.UP);
    }

    @Test
    public void testGetMovesetInvalidDown() {
        verifyInvalidMove(4, 4, Direction.DOWN);
    }

    @Test
    public void testGetMovesetInvalidLeft() {
        verifyInvalidMove(4, 4, Direction.LEFT);
    }

    @Test
    public void testGetMovesetInvalidRight() {
        verifyInvalidMove(4, 4, Direction.RIGHT);
    }

    private void verifyValidMove(int startX, int startY, Direction direction) {
        Bishop bishop = createBishop();
        Location startLocation = new Location(startX, startY);
        Move[] possibleMoves = bishop.getMoveset(startLocation);

        System.out.println("Verify that bishop can move " + direction);
        int numFound = MoveFilter.getContinousMoves(direction, possibleMoves).length;
        assertEquals(1, numFound);
    }

    private void verifyInvalidMove(int startX, int startY, Direction direction) {
        Bishop bishop = createBishop();
        Location startLocation = new Location(startX, startY);
        Move[] possibleMoves = bishop.getMoveset(startLocation);

        System.out.println("Verify that bishop cannot move " + direction);
        int numFound = MoveFilter.getContinousMoves(direction, possibleMoves).length;
        assertNotEquals(1, numFound);
    }

    private Bishop createBishop() {
        return new Bishop(new Location(0, 0), null, PieceColor.WHITE);
    }
}
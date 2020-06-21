package com.teo.chess.pieces;

import com.teo.chess.Direction;
import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BishopCaptureTest {

    //Make sure Bishop can capture in all valid directions

    @Test
    public void testGetCapturesUpLeft() {
        verifyValidCapture(4, 4, Direction.UP_LEFT);
    }

    @Test
    public void testGetCapturesUpRight() {
        verifyValidCapture(4, 4, Direction.UP_RIGHT);
    }

    @Test
    public void testGetCapturesDownLeft() {
        verifyValidCapture(4, 4, Direction.DOWN_LEFT);
    }

    @Test
    public void testGetCapturesDownRight() {
        verifyValidCapture(4, 4, Direction.DOWN_RIGHT);
    }

    //Make sure Bishop cannot capture on tiles outside of board

    @Test
    public void testGetCapturesInvalidUpLeft() {
        verifyInvalidCapture(0, 0, Direction.UP_LEFT);
    }

    @Test
    public void testGetCapturesInvalidUpRight() {
        verifyInvalidCapture(Board.TILES_ACROSS - 1, 0, Direction.UP_RIGHT);
    }

    @Test
    public void testGetCapturesInvalidDownLeft() {
        verifyInvalidCapture(0, Board.TILES_ACROSS - 1, Direction.DOWN_LEFT);
    }

    @Test
    public void testGetCapturesInvalidDownRight() {
        verifyInvalidCapture(Board.TILES_ACROSS - 1, Board.TILES_ACROSS - 1, Direction.DOWN_RIGHT);
    }

    //Make sure Bishop cannot capture in any other direction

    @Test
    public void testGetCapturesInvalidUp() {
        verifyInvalidCapture(4, 4, Direction.UP);
    }

    @Test
    public void testGetCapturesInvalidDown() {
        verifyInvalidCapture(4, 4, Direction.DOWN);
    }

    @Test
    public void testGetCapturesInvalidLeft() {
        verifyInvalidCapture(4, 4, Direction.LEFT);
    }

    @Test
    public void testGetCapturesInvalidRight() {
        verifyInvalidCapture(4, 4, Direction.RIGHT);
    }

    private void verifyValidCapture(int startX, int startY, Direction direction) {
        Bishop bishop = createBishop();
        Location startLocation = new Location(startX, startY);
        Move[] possibleCaptures = bishop.getCaptures(startLocation);

        System.out.println("Verify that bishop can capture in direction " + direction);
        int numFound = MoveFilter.getContinousCaptures(direction, possibleCaptures).length;
        assertEquals(1, numFound);
    }

    private void verifyInvalidCapture(int startX, int startY, Direction direction) {
        Bishop bishop = createBishop();
        Location startLocation = new Location(startX, startY);
        Move[] possibleCaptures = bishop.getCaptures(startLocation);

        System.out.println("Verify that bishop cannot capture in direction " + direction);
        int numFound = MoveFilter.getContinousCaptures(direction, possibleCaptures).length;
        assertNotEquals(1, numFound);
    }

    private Bishop createBishop() {
        return new Bishop(new Location(0, 0), null, PieceColor.WHITE);
    }
}
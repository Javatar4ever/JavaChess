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

    private final Location centerOfBoard = new Location(Board.TILES_ACROSS / 2, Board.TILES_ACROSS / 2);

    //Make sure Bishop can capture in all valid directions

    @Test
    public void testGetCapturesUpLeft() {
        verifyValidCapture(centerOfBoard, Direction.UP_LEFT);
    }

    @Test
    public void testGetCapturesUpRight() {
        verifyValidCapture(centerOfBoard, Direction.UP_RIGHT);
    }

    @Test
    public void testGetCapturesDownLeft() {
        verifyValidCapture(centerOfBoard, Direction.DOWN_LEFT);
    }

    @Test
    public void testGetCapturesDownRight() {
        verifyValidCapture(centerOfBoard, Direction.DOWN_RIGHT);
    }

    //Make sure Bishop cannot capture on tiles outside of board

    @Test
    public void testGetCapturesInvalidUpLeft() {
        verifyInvalidCapture(new Location(0, 0), Direction.UP_LEFT);
    }

    @Test
    public void testGetCapturesInvalidUpRight() {
        verifyInvalidCapture(new Location(Board.TILES_ACROSS - 1, 0), Direction.UP_RIGHT);
    }

    @Test
    public void testGetCapturesInvalidDownLeft() {
        verifyInvalidCapture(new Location(0, Board.TILES_ACROSS - 1), Direction.DOWN_LEFT);
    }

    @Test
    public void testGetCapturesInvalidDownRight() {
        verifyInvalidCapture(new Location(Board.TILES_ACROSS - 1, Board.TILES_ACROSS - 1), Direction.DOWN_RIGHT);
    }

    //Make sure Bishop cannot capture in any other direction

    @Test
    public void testGetCapturesInvalidUp() {
        verifyInvalidCapture(centerOfBoard, Direction.UP);
    }

    @Test
    public void testGetCapturesInvalidDown() {
        verifyInvalidCapture(centerOfBoard, Direction.DOWN);
    }

    @Test
    public void testGetCapturesInvalidLeft() {
        verifyInvalidCapture(centerOfBoard, Direction.LEFT);
    }

    @Test
    public void testGetCapturesInvalidRight() {
        verifyInvalidCapture(centerOfBoard, Direction.RIGHT);
    }

    private void verifyValidCapture(Location startLocation, Direction direction) {
        Bishop bishop = createBishop();
        Move[] possibleCaptures = bishop.getCaptures(startLocation);

        System.out.println("Verify that bishop can capture in direction " + direction);
        int numFound = MoveFilter.getContinousCaptures(direction, possibleCaptures).length;
        assertEquals(1, numFound);
    }

    private void verifyInvalidCapture(Location startLocation, Direction direction) {
        Bishop bishop = createBishop();
        Move[] possibleCaptures = bishop.getCaptures(startLocation);

        System.out.println("Verify that bishop cannot capture in direction " + direction);
        int numFound = MoveFilter.getContinousCaptures(direction, possibleCaptures).length;
        assertNotEquals(1, numFound);
    }

    private Bishop createBishop() {
        return new Bishop(new Location(0, 0), null, PieceColor.WHITE);
    }
}
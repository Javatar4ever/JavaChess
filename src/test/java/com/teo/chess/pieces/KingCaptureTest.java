package com.teo.chess.pieces;

import com.teo.chess.Direction;
import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.MoveType;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class KingCaptureTest {

    //Verify King can capture in all valid directions

    @Test
    public void testGetCapturesUp() {
        verifyValidCapture(4, 4, Direction.UP);
    }

    @Test
    public void testGetCapturesDown() {
        verifyValidCapture(4, 4, Direction.DOWN);
    }

    @Test
    public void testGetCapturesLeft() {
        verifyValidCapture(4, 4, Direction.LEFT);
    }

    @Test
    public void testGetCapturesRight() {
        verifyValidCapture(4, 4, Direction.RIGHT);
    }

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

    //Verify King cannot capture outside Board tiles

    @Test
    public void testGetCapturesInvalidUp() {
        verifyInvalidCapture(0, 0, Direction.UP);
    }

    @Test
    public void testGetCapturesInvalidDown() {
        verifyInvalidCapture(0, Board.TILES_ACROSS - 1, Direction.DOWN);
    }

    @Test
    public void testGetCapturesInvalidLeft() {
        verifyInvalidCapture(0, 0, Direction.LEFT);
    }

    @Test
    public void testGetCapturesInvalidRight() {
        verifyInvalidCapture(Board.TILES_ACROSS - 1, 0, Direction.RIGHT);
    }

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

    //Verify King cannot capture continuously

    @Test
    public void testGetCapturesOnlyStaticMoves() {
        King king = createKing();
        Location startLocation = new Location(4, 4);
        Move[] possibleCaptures = king.getCaptures(startLocation);

        System.out.println("Verify that king cannot capture continuously");
        long numFound = Arrays.stream(possibleCaptures).filter(m -> m.getMoveType().equals(MoveType.CAPTURE_CONTINUOUS)).count();
        assertEquals(0, numFound);
    }


    private void verifyValidCapture(int startX, int startY, Direction direction) {
        King king = createKing();
        Location startLocation = new Location(startX, startY);
        Location endLocation = new Location(startX + direction.getXDir(), startY + direction.getYDir());
        Move[] possibleCaptures = king.getCaptures(startLocation);

        System.out.println("Verify that king can capture in direction " + direction);
        int numFound = MoveFilter.getStaticCaptures(endLocation, possibleCaptures).length;
        assertEquals(1, numFound);
    }

    private void verifyInvalidCapture(int startX, int startY, Direction direction) {
        King king = createKing();
        Location startLocation = new Location(startX, startY);
        Location endLocation = new Location(startX + direction.getXDir(), startY + direction.getYDir());
        Move[] possibleCaptures = king.getCaptures(startLocation);

        System.out.println("Verify that king cannot capture in direction " + direction);
        int numFound = MoveFilter.getStaticCaptures(endLocation, possibleCaptures).length;
        assertNotEquals(1, numFound);
    }

    private King createKing() {
        return new King(new Location(0, 0), null, PieceColor.WHITE);
    }
}

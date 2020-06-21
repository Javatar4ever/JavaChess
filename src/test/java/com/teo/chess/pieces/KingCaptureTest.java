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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class KingCaptureTest {

    private final Location centerOfBoard = new Location(Board.TILES_ACROSS / 2, Board.TILES_ACROSS / 2);

    //Verify King can capture in all valid directions

    @Test
    public void testGetCapturesUp() {
        verifyValidCapture(centerOfBoard, Direction.UP);
    }

    @Test
    public void testGetCapturesDown() {
        verifyValidCapture(centerOfBoard, Direction.DOWN);
    }

    @Test
    public void testGetCapturesLeft() {
        verifyValidCapture(centerOfBoard, Direction.LEFT);
    }

    @Test
    public void testGetCapturesRight() {
        verifyValidCapture(centerOfBoard, Direction.RIGHT);
    }

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

    //Verify King cannot capture outside Board tiles

    @Test
    public void testGetCapturesInvalidUp() {
        verifyInvalidCapture(new Location(0, 0), Direction.UP);
    }

    @Test
    public void testGetCapturesInvalidDown() {
        verifyInvalidCapture(new Location(0, Board.TILES_ACROSS - 1), Direction.DOWN);
    }

    @Test
    public void testGetCapturesInvalidLeft() {
        verifyInvalidCapture(new Location(0, 0), Direction.LEFT);
    }

    @Test
    public void testGetCapturesInvalidRight() {
        verifyInvalidCapture(new Location(Board.TILES_ACROSS - 1, 0), Direction.RIGHT);
    }

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

    //Verify King cannot capture continuously

    @Test
    public void testGetCapturesOnlyStaticMoves() {
        King king = new King(centerOfBoard, null, PieceColor.WHITE);
        Move[] possibleCaptures = king.getCaptures(centerOfBoard);

        System.out.println("Verify that king cannot capture continuously");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(king));
        long numFound = Arrays.stream(possibleCaptures).filter(m -> m.getMoveType().equals(MoveType.CAPTURE_CONTINUOUS)).count();
        assertEquals(0, numFound);
    }


    private void verifyValidCapture(Location startLocation, Direction direction) {
        King king = new King(startLocation, null, PieceColor.WHITE);
        Location endLocation = new Location(startLocation.getBoardX() + direction.getXDir(), startLocation.getBoardY() + direction.getYDir());
        Move[] possibleCaptures = king.getCaptures(startLocation);

        System.out.println("Verify that king can capture in direction " + direction);
        System.out.println(AsciiBoardBuilder.getAsciiBoard(king));
        int numFound = MoveFilter.getStaticCaptures(endLocation, possibleCaptures).length;
        assertEquals(1, numFound);
    }

    private void verifyInvalidCapture(Location startLocation, Direction direction) {
        King king = new King(startLocation, null, PieceColor.WHITE);
        Location endLocation = new Location(startLocation.getBoardX() + direction.getXDir(), startLocation.getBoardY() + direction.getYDir());
        Move[] possibleCaptures = king.getCaptures(startLocation);

        System.out.println("Verify that king cannot capture in direction " + direction);
        System.out.println(AsciiBoardBuilder.getAsciiBoard(king));
        int numFound = MoveFilter.getStaticCaptures(endLocation, possibleCaptures).length;
        assertNotEquals(1, numFound);
    }
}

package com.teo.chess.pieces;

import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.ascii.AsciiBoardBuilder;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightCaptureTest {

    private final Location centerOfBoard = new Location(Board.TILES_ACROSS / 2, Board.TILES_ACROSS / 2);

    //TODO: Move definition somewhere?
    private enum KnightMove {
        ONE_LEFT_TWO_UP(-1, -2),
        ONE_RIGHT_TWO_UP(1, -2),
        TWO_LEFT_ONE_UP(-2, -1),
        TWO_RIGHT_ONE_UP(2, -1),
        TWO_LEFT_ONE_DOWN(-2, 1),
        TWO_RIGHT_ONE_DOWN(2, 1),
        ONE_LEFT_TWO_DOWN(-1, 2),
        ONE_RIGHT_TWO_DOWN(1, 2);

        private final int xVel;
        private final int yVel;

        KnightMove(int xVel, int yVel) {
            this.xVel = xVel;
            this.yVel = yVel;
        }
    }

    @Test
    public void testGetCapturesOneLeftTwoUp() {
        verifyValidCapture(centerOfBoard, KnightMove.ONE_LEFT_TWO_UP);
    }

    @Test
    public void testGetCapturesOneRightTwoUp() {
        verifyValidCapture(centerOfBoard, KnightMove.ONE_RIGHT_TWO_UP);
    }

    @Test
    public void testGetCapturesTwoLeftOneUp() {
        verifyValidCapture(centerOfBoard, KnightMove.TWO_LEFT_ONE_UP);
    }

    @Test
    public void testGetCapturesTwoRightOneUp() {
        verifyValidCapture(centerOfBoard, KnightMove.TWO_RIGHT_ONE_UP);
    }

    @Test
    public void testGetCapturesTwoLeftOneDown() {
        verifyValidCapture(centerOfBoard, KnightMove.TWO_LEFT_ONE_DOWN);
    }

    @Test
    public void testGetCapturesTwoRightOneDown() {
        verifyValidCapture(centerOfBoard, KnightMove.TWO_RIGHT_ONE_DOWN);
    }

    @Test
    public void testGetCapturesOneLeftTwoDown() {
        verifyValidCapture(centerOfBoard, KnightMove.ONE_LEFT_TWO_DOWN);
    }

    @Test
    public void testGetCapturesOneRightTwoDown() {
        verifyValidCapture(centerOfBoard, KnightMove.ONE_RIGHT_TWO_DOWN);
    }

    private void verifyValidCapture(Location startLocation, KnightMove direction) {
        Knight knight = new Knight(startLocation, null, PieceColor.WHITE);
        Location endLocation = new Location(startLocation.getBoardX() + direction.xVel, startLocation.getBoardY() + direction.yVel);
        Move[] possibleCaptures = knight.getCaptures(startLocation);

        System.out.println("Verify that knight can capture in direction " + direction);
        System.out.println(AsciiBoardBuilder.getAsciiBoard(knight));
        int numFound = MoveFilter.getStaticCaptures(endLocation, possibleCaptures).length;
        assertEquals(1, numFound);
    }
}


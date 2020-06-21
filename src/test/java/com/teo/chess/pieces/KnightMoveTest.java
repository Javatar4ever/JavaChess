package com.teo.chess.pieces;

import com.teo.chess.Direction;
import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class KnightMoveTest {

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
    public void testGetMovesetOneLeftTwoUp() {
        verifyValidMove(centerOfBoard, KnightMove.ONE_LEFT_TWO_UP);
    }

    @Test
    public void testGetMovesetOneRightTwoUp() {
        verifyValidMove(centerOfBoard, KnightMove.ONE_RIGHT_TWO_UP);
    }

    @Test
    public void testGetMovesetTwoLeftOneUp() {
        verifyValidMove(centerOfBoard, KnightMove.TWO_LEFT_ONE_UP);
    }

    @Test
    public void testGetMovesetTwoRightOneUp() {
        verifyValidMove(centerOfBoard, KnightMove.TWO_RIGHT_ONE_UP);
    }

    @Test
    public void testGetMovesetTwoLeftOneDown() {
        verifyValidMove(centerOfBoard, KnightMove.TWO_LEFT_ONE_DOWN);
    }

    @Test
    public void testGetMovesetTwoRightOneDown() {
        verifyValidMove(centerOfBoard, KnightMove.TWO_RIGHT_ONE_DOWN);
    }

    @Test
    public void testGetMovesetOneLeftTwoDown() {
        verifyValidMove(centerOfBoard, KnightMove.ONE_LEFT_TWO_DOWN);
    }

    @Test
    public void testGetMovesetOneRightTwoDown() {
        verifyValidMove(centerOfBoard, KnightMove.ONE_RIGHT_TWO_DOWN);
    }

    private void verifyValidMove(Location startLocation, KnightMove direction) {
        Knight knight = createKnight();
        Location endLocation = new Location(startLocation.getBoardX() + direction.xVel, startLocation.getBoardY() + direction.yVel);
        Move[] possibleMoves = knight.getMoveset(startLocation);

        System.out.println("Verify that knight can move " + direction);
        int numFound = MoveFilter.getStaticMoves(endLocation, possibleMoves).length;
        assertEquals(1, numFound);
    }

    private Knight createKnight() {
        return new Knight(new Location(0, 0), null, PieceColor.WHITE);
    }
}

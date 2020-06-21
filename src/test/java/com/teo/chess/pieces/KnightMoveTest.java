package com.teo.chess.pieces;

import com.teo.chess.Direction;
import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.gui.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class KnightMoveTest {

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

        private int xVel;
        private int yVel;

        KnightMove(int xVel, int yVel) {
            this.xVel = xVel;
            this.yVel = yVel;
        }
    }
    @Test
    public void testGetMovesetOneLeftTwoUp() {
        verifyValidMove(4, 4, KnightMove.ONE_LEFT_TWO_UP);
    }

    @Test
    public void testGetMovesetOneRightTwoUp() {
        verifyValidMove(4, 4, KnightMove.ONE_RIGHT_TWO_UP);
    }

    @Test
    public void testGetMovesetTwoLeftOneUp() {
        verifyValidMove(4, 4, KnightMove.TWO_LEFT_ONE_UP);
    }

    @Test
    public void testGetMovesetTwoRightOneUp() {
        verifyValidMove(4, 4, KnightMove.TWO_RIGHT_ONE_UP);
    }

    @Test
    public void testGetMovesetTwoLeftOneDown() {
        verifyValidMove(4, 4, KnightMove.TWO_LEFT_ONE_DOWN);
    }

    @Test
    public void testGetMovesetTwoRightOneDown() {
        verifyValidMove(4, 4, KnightMove.TWO_RIGHT_ONE_DOWN);
    }

    @Test
    public void testGetMovesetOneLeftTwoDown() {
        verifyValidMove(4, 4, KnightMove.ONE_LEFT_TWO_DOWN);
    }

    @Test
    public void testGetMovesetOneRightTwoDown() {
        verifyValidMove(4, 4, KnightMove.ONE_RIGHT_TWO_DOWN);
    }

    private void verifyValidMove(int startX, int startY, KnightMove direction) {
        Knight knight = createKnight();
        Location startLocation = new Location(startX, startY);
        Location endLocation = new Location(startX + direction.xVel, startY + direction.yVel);
        Move[] possibleMoves = knight.getMoveset(startLocation);

        System.out.println("Verify that knight can move " + direction);
        int numFound = MoveFilter.getStaticMoves(endLocation, possibleMoves).length;
        assertEquals(1, numFound);
    }

    private void verifyInvalidMove(int startX, int startY, Direction direction) {
        Knight knight = createKnight();
        Location startLocation = new Location(startX, startY);
        Location endLocation = new Location(startX + direction.getXDir(), startY + direction.getYDir());
        Move[] possibleMoves = knight.getMoveset(startLocation);

        System.out.println("Verify that king cannot move " + direction);
        int numFound = MoveFilter.getStaticMoves(endLocation, possibleMoves).length;
        assertNotEquals(1, numFound);
    }

    private Knight createKnight() {
        return new Knight(new Location(0, 0), null, PieceColor.WHITE);
    }
}

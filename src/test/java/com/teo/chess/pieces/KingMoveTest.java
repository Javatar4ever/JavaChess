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

import static org.junit.jupiter.api.Assertions.*;

public class KingMoveTest {

    //Verify King can move in all valid directions

    @Test
    public void testGetMovesetUp() {
        verifyValidMove(4, 4, Direction.UP);
    }

    @Test
    public void testGetMovesetDown() {
        verifyValidMove(4, 4, Direction.DOWN);
    }

    @Test
    public void testGetMovesetLeft() {
        verifyValidMove(4, 4, Direction.LEFT);
    }

    @Test
    public void testGetMovesetRight() {
        verifyValidMove(4, 4, Direction.RIGHT);
    }

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

    //Verify King cannot move outside Board tiles

    @Test
    public void testGetMovesetInvalidUp() {
        verifyInvalidMove(0, 0, Direction.UP);
    }

    @Test
    public void testGetMovesetInvalidDown() {
        verifyInvalidMove(0, Board.TILES_ACROSS - 1, Direction.DOWN);
    }

    @Test
    public void testGetMovesetInvalidLeft() {
        verifyInvalidMove(0, 0, Direction.LEFT);
    }

    @Test
    public void testGetMovesetInvalidRight() {
        verifyInvalidMove(Board.TILES_ACROSS - 1, 0, Direction.RIGHT);
    }

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

    //Verify King cannot move continuously

    @Test
    public void testGetMovesetOnlyStaticMoves() {
        King king = createKing();
        Location startLocation = new Location(4, 4);
        Move[] possibleMoves = king.getMoveset(startLocation);

        System.out.println("Verify that king cannot move continuously");
        long numFound = Arrays.stream(possibleMoves).filter(m -> m.getMoveType().equals(MoveType.CONTINUOUS)).count();
        assertEquals(0, numFound);
    }


    private void verifyValidMove(int startX, int startY, Direction direction) {
        King king = createKing();
        Location startLocation = new Location(startX, startY);
        Location endLocation = new Location(startX + direction.getXDir(), startY + direction.getYDir());
        Move[] possibleMoves = king.getMoveset(startLocation);

        System.out.println("Verify that king can move " + direction);
        int numFound = MoveFilter.getStaticMoves(endLocation, possibleMoves).length;
        assertEquals(1, numFound);
    }

    private void verifyInvalidMove(int startX, int startY, Direction direction) {
        King king = createKing();
        Location startLocation = new Location(startX, startY);
        Location endLocation = new Location(startX + direction.getXDir(), startY + direction.getYDir());
        Move[] possibleMoves = king.getMoveset(startLocation);

        System.out.println("Verify that king cannot move " + direction);
        int numFound = MoveFilter.getStaticMoves(endLocation, possibleMoves).length;
        assertNotEquals(1, numFound);
    }

    private King createKing() {
        return new King(new Location(0, 0), null, PieceColor.WHITE);
    }
}

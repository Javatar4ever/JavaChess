package com.teo.chess.pieces;

import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * This test only works where the player plays as white pawns
 */
public class PawnMoveTest {

    @Test
    public void testGetMovesetWhitePawn() {
        Pawn pawn = createWhitePawn();
        Location startLocation = new Location(4, 4);
        Location endLocation = new Location(startLocation.getBoardX(), startLocation.getBoardY() - 1);
        Move[] possibleMoves = pawn.getMoveset(startLocation);

        System.out.println("Verify White Pawn can move upwards");
        int numFound = MoveFilter.getStaticMoves(endLocation, possibleMoves).length;
        assertEquals(1, numFound);
    }

    @Test
    public void testGetMovesetBlackPawn() {
        Pawn pawn = createBlackPawn();
        Location startLocation = new Location(4, 4);
        Location endLocation = new Location(startLocation.getBoardX(), startLocation.getBoardY() + 1);
        Move[] possibleMoves = pawn.getMoveset(startLocation);

        System.out.println("Verify Black Pawn can move downwards");
        int numFound = MoveFilter.getStaticMoves(endLocation, possibleMoves).length;
        assertEquals(1, numFound);
    }

    @Test
    public void testGetMovesetWhitePawnStartAdvance() {
        Pawn pawn = createWhitePawn();
        Location startLocation = new Location(0, Board.TILES_ACROSS - 2);
        Location endLocation = new Location(startLocation.getBoardX(), startLocation.getBoardY() - 2);
        Move[] possibleMoves = pawn.getMoveset(startLocation);

        int numFound = MoveFilter.getPawnStarts(endLocation, possibleMoves).length;
        assertEquals(1, numFound);
    }

    @Test
    public void testGetMovesetBlackPawnStartAdvance() {
        Pawn pawn = createBlackPawn();
        Location startLocation = new Location(0, 1);
        Location endLocation = new Location(startLocation.getBoardX(), startLocation.getBoardY() + 2);
        Move[] possibleMoves = pawn.getMoveset(startLocation);

        int numFound = MoveFilter.getPawnStarts(endLocation, possibleMoves).length;
        assertEquals(1, numFound);
    }

    // Verify that you're only able to perform Pawn start at home row

    @Test
    public void testGetMovesetWhiteInvalidStartAdvance() {
        Pawn pawn = createWhitePawn();
        Location startLocation = new Location(0, 3);
        Location endLocation = new Location(startLocation.getBoardX(), startLocation.getBoardY() - 2);
        Move[] possibleMoves = pawn.getMoveset(startLocation);

        System.out.println("Verify that White pawn is not able to perform start move");
        int numFound = MoveFilter.getPawnStarts(endLocation, possibleMoves).length;
        assertNotEquals(1, numFound);
    }

    @Test
    public void testGetMovesetBlackInvalidStartAdvance() {
        Pawn pawn = createBlackPawn();
        Location startLocation = new Location(0, 3);
        Location endLocation = new Location(startLocation.getBoardX(), startLocation.getBoardY() + 2);
        Move[] possibleMoves = pawn.getMoveset(startLocation);

        System.out.println("Verify that Black pawn is not able to perform start move");
        int numFound = MoveFilter.getPawnStarts(endLocation, possibleMoves).length;
        assertNotEquals(1, numFound);
    }


    private Pawn createWhitePawn() {
        return new Pawn(new Location(0, 0), null, PieceColor.WHITE);
    }
    private Pawn createBlackPawn() {
        return new Pawn(new Location(0, 0), null, PieceColor.BLACK);
    }
}

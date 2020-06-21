package com.teo.chess.pieces;

import com.teo.chess.Location;
import com.teo.chess.Move;
import com.teo.chess.ascii.AsciiBoardBuilder;
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
        Location startLocation = new Location(4, 4);
        Pawn pawn = new Pawn(startLocation, null, PieceColor.WHITE);
        Location endLocation = new Location(startLocation.getBoardX(), startLocation.getBoardY() - 1);
        Move[] possibleMoves = pawn.getMoveset(startLocation);

        System.out.println("Verify White Pawn can move upwards");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(pawn));
        int numFound = MoveFilter.getStaticMoves(endLocation, possibleMoves).length;
        assertEquals(1, numFound);
    }

    @Test
    public void testGetMovesetBlackPawn() {
        Location startLocation = new Location(4, 4);
        Pawn pawn = new Pawn(startLocation, null, PieceColor.BLACK);
        Location endLocation = new Location(startLocation.getBoardX(), startLocation.getBoardY() + 1);
        Move[] possibleMoves = pawn.getMoveset(startLocation);

        System.out.println("Verify Black Pawn can move downwards");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(pawn));
        int numFound = MoveFilter.getStaticMoves(endLocation, possibleMoves).length;
        assertEquals(1, numFound);
    }

    @Test
    public void testGetMovesetWhitePawnStartAdvance() {
        Location startLocation = new Location(0, Board.TILES_ACROSS - 2);
        Pawn pawn = new Pawn(startLocation, null, PieceColor.WHITE);
        Location endLocation = new Location(startLocation.getBoardX(), startLocation.getBoardY() - 2);
        Move[] possibleMoves = pawn.getMoveset(startLocation);

        System.out.println("Verify White Pawn can perform start advance move");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(pawn));
        int numFound = MoveFilter.getPawnStarts(endLocation, possibleMoves).length;
        assertEquals(1, numFound);
    }

    @Test
    public void testGetMovesetBlackPawnStartAdvance() {
        Location startLocation = new Location(0, 1);
        Pawn pawn = new Pawn(startLocation, null, PieceColor.BLACK);
        Location endLocation = new Location(startLocation.getBoardX(), startLocation.getBoardY() + 2);
        Move[] possibleMoves = pawn.getMoveset(startLocation);

        System.out.println("Verify Black Pawn can perform start advance move");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(pawn));
        int numFound = MoveFilter.getPawnStarts(endLocation, possibleMoves).length;
        assertEquals(1, numFound);
    }

    // Verify that you're only able to perform Pawn start at home row

    @Test
    public void testGetMovesetWhiteInvalidStartAdvance() {
        Location startLocation = new Location(0, 3);
        Pawn pawn = new Pawn(startLocation, null, PieceColor.WHITE);
        Location endLocation = new Location(startLocation.getBoardX(), startLocation.getBoardY() - 2);
        Move[] possibleMoves = pawn.getMoveset(startLocation);

        System.out.println("Verify that White pawn is not able to perform start move");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(pawn));
        int numFound = MoveFilter.getPawnStarts(endLocation, possibleMoves).length;
        assertNotEquals(1, numFound);
    }

    @Test
    public void testGetMovesetBlackInvalidStartAdvance() {
        Location startLocation = new Location(0, 3);
        Pawn pawn = new Pawn(startLocation, null, PieceColor.BLACK);
        Location endLocation = new Location(startLocation.getBoardX(), startLocation.getBoardY() + 2);
        Move[] possibleMoves = pawn.getMoveset(startLocation);

        System.out.println("Verify that Black pawn is not able to perform start move");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(pawn));
        int numFound = MoveFilter.getPawnStarts(endLocation, possibleMoves).length;
        assertNotEquals(1, numFound);
    }
}

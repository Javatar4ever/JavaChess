package com.teo.chess.ascii;

import com.teo.chess.Location;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.pieces.Bishop;
import com.teo.chess.pieces.Piece;
import com.teo.chess.states.BoardState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsciiBoardBuilderTest {

    private static final String EXPECTED_ASCII_OUTPUT =
            "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
            "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
            "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
            "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
            "[ ][ ][ ][ ][♗][ ][ ][ ]\n" +
            "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
            "[ ][ ][ ][ ][ ][ ][ ][ ]\n" +
            "[ ][ ][ ][ ][ ][ ][ ][ ]\n";

    @Test
    void testGetAsciiBoard() {
        System.out.println("Verify that we can generate a ASCII picture like below");
        System.out.println(EXPECTED_ASCII_OUTPUT);

        Piece[][] boardPieces = new Piece[Board.TILES_ACROSS][Board.TILES_ACROSS];
        boardPieces[4][4] = new Bishop(new Location(4, 4), null, PieceColor.WHITE);

        String output = AsciiBoardBuilder.getAsciiBoard(new BoardState(boardPieces));

        System.out.println("\nActual output:");
        System.out.println(output);
        assertEquals(EXPECTED_ASCII_OUTPUT, output);
    }

}
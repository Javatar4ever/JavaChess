package com.teo.chess;

import com.teo.chess.ascii.AsciiBoardBuilder;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.pieces.*;
import com.teo.chess.states.BoardState;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MoveValidatorTest {

    @Test
    void verifyBishopCannotMoveThroughPieces() {
        BoardState boardState = getBoardState("boardstates/bishopMoveBlocked.ser");
        System.out.println("Validating White Bishop moves at board state below");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(boardState));

        Bishop whiteBishop = (Bishop) boardState.getPiece(new Location(1, 4));
        assertTrue(whiteBishop.getType().equals(PieceType.BISHOP) && whiteBishop.getColor().equals(PieceColor.WHITE),
                "Failed to find a White Bishop at expected location");

        List<Location> expectedBishopMoves = Arrays.asList(
                new Location(0, 3),
                new Location(2, 3),
                new Location(0, 5),
                new Location(2, 5),
                new Location(3, 6)
        );

        MoveValidator moveValidator = new MoveValidator();

        Location[] bishopMoves = moveValidator.getPossibleMoves(whiteBishop, boardState);

        boolean testSuccessful = expectedBishopMoves.size() == bishopMoves.length && Arrays.asList(bishopMoves).containsAll(expectedBishopMoves);
        assertTrue(testSuccessful);
    }

    @Test
    void verifyNormalMovementNotAllowedInCheck() {
        BoardState boardState = getBoardState("boardstates/rookCheck.ser");
        System.out.println("Validate that no black piece except the king can move because of check");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(boardState));

        List<Piece> blackPieces = Arrays.stream(boardState.getState())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(p -> p.getColor().equals(PieceColor.BLACK))
                .filter(p -> !p.getType().equals(PieceType.KING))
                .collect(Collectors.toList());

        MoveValidator moveValidator = new MoveValidator();
        blackPieces.forEach(p -> {
            int possibleMoves = moveValidator.getPossibleMoves(p, boardState).length;
            assertEquals(0, possibleMoves, p.getType() + " was able to move during check");
        });

        King blackKing = (King) Arrays.stream(boardState.getState())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(p -> p.getType().equals(PieceType.KING))
                .collect(Collectors.toList()).get(0);
        int possibleMoves = moveValidator.getPossibleMoves(blackKing, boardState).length;
        assertTrue(possibleMoves != 0, "King was not able to move");

    }

    @Test
    void verifyWhitePawnCanCapture() {
        BoardState boardState = getBoardState("boardstates/knightCapture.ser");
        System.out.println("Make sure White Knight can capture black pawn");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(boardState));

        MoveValidator moveValidator = new MoveValidator();

        Location expectedCapture = new Location(5, 5);

        Knight whiteKnight = new Knight(new Location(6, 7), null, PieceColor.WHITE);
        Location[] possibleCaptures = moveValidator.getPossibleCaptures(whiteKnight, boardState, true);
        assertEquals(possibleCaptures.length, 1, "Incorrect number of valid captures found");
        assertEquals(possibleCaptures[0], expectedCapture, "Capture found, but at incorrect location");
    }

    @Test
    void verifyLegalTrapCheckmateIsDetected() {
        BoardState boardState = getBoardState("boardstates/legalTrapCheckMate.ser");
        System.out.println("Make sure check mate is detected");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(boardState));

        MoveValidator moveValidator = new MoveValidator();

        boolean isCheckMate = moveValidator.isCheckmate(PieceColor.BLACK, boardState);
        assertTrue(isCheckMate);
    }

    @Test
    void verifyBoardStateIsNotCheckmate() {
        BoardState boardState = getBoardState("boardstates/bishopMoveBlocked.ser");
        System.out.println("Make sure check mate is not detected");
        System.out.println(AsciiBoardBuilder.getAsciiBoard(boardState));

        MoveValidator moveValidator = new MoveValidator();

        boolean isCheckMate = moveValidator.isCheckmate(PieceColor.BLACK, boardState);
        isCheckMate &= moveValidator.isCheckmate(PieceColor.WHITE, boardState);
        assertFalse(isCheckMate);
    }

    private BoardState getBoardState(String serializablePath) {
        BoardState boardState = null;
        try {
            FileInputStream fileIn = new FileInputStream("src/test/resources/" + serializablePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            boardState = (BoardState) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("BoardState class not found");
            c.printStackTrace();
        }
        return boardState;
    }
}
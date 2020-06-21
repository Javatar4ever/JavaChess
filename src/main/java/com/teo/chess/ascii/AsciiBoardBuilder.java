package com.teo.chess.ascii;

import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.pieces.Piece;
import com.teo.chess.pieces.PieceType;
import com.teo.chess.states.BoardState;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that will show the state of a chess board in ASCII format. Unicode codes fetched from:
 *
 * https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode
 * http://jkorpela.fi/chars/spaces.html
 */
public class AsciiBoardBuilder {

    private static final List<PieceType> CHESS_PIECES_UNICODE_ORDER = Arrays.asList(
            PieceType.KING,
            PieceType.QUEEN,
            PieceType.ROOK,
            PieceType.BISHOP,
            PieceType.KNIGHT,
            PieceType.PAWN
    );
    private static final List<PieceColor> COLOR_UNICODE_ORDER = Arrays.asList(
            PieceColor.WHITE,
            PieceColor.BLACK
    );
    //Unicode for first chess piece. Will be incremented based on chess piece order in unicode
    private static final char BASE_UNICODE = '\u2654';
    private static final char WHITESPACE_UNICODE = '\u2003';

    /**
     * Return ASCII picture based on a complete board state
     *
     * @param boardState Wanted board state to display
     * @return ASCII picture as String
     */
    public static String getAsciiBoard(BoardState boardState) {
        Piece[][] boardPieces = boardState.getState();
        return drawBoard(boardPieces);
    }

    /**
     * Return ASCII picture of pieces on a board. Method will assume
     * default board size
     *
     * @param pieces one or more pieces to draw
     * @return ASCII picture as String
     */
    public static String getAsciiBoard(Piece... pieces) {
        Piece[][] boardPieces = new Piece[Board.TILES_ACROSS][Board.TILES_ACROSS];
        for (Piece piece : pieces) {
            boardPieces[piece.getLocation().getBoardY()][piece.getLocation().getBoardX()] = piece;
        }
        return drawBoard(boardPieces);
    }

    private static String drawBoard(Piece[][] boardPieces) {
        return Arrays.stream(boardPieces)
                .map(py -> Arrays.stream(py)
                        .map(AsciiBoardBuilder::getPieceAsString)
                        .collect(Collectors.toList())
                )
                .map(p -> String.join("", p) + "\n")
                .collect(Collectors.joining());
    }

    private static String getPieceAsString(Piece piece) {
        if (piece == null) {
            return "[" + WHITESPACE_UNICODE + "]";
        }

        int increment = CHESS_PIECES_UNICODE_ORDER.indexOf(piece.getType());
        increment += piece.getColor() == COLOR_UNICODE_ORDER.get(0) ? 0 : CHESS_PIECES_UNICODE_ORDER.size();

        char unicode = (char) (BASE_UNICODE + increment);

        return "[" + unicode + "]";
    }
}

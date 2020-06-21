package com.teo.chess;

import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.pieces.*;
import com.teo.chess.states.BoardState;

import java.util.ArrayList;

public class MoveValidator {

    private Board board;

    public MoveValidator(Board board) {
        this.board = board;
    }

    /**
     * Method that returns all possible captures for one piece in the state that is provided.
     *
     * @param piece : Piece to check for possible moves
     * @param boardState : State to check for moves in
     * @return : Array of Locations of possible destinations after a capture
     */
    public Location[] getPossibleMoves(Piece piece, BoardState boardState) {
        ArrayList<Location> endlocations = new ArrayList<>();
        Location start = piece.getLocation();
        Move[] moveset = piece.getMoveset(start);

        for (int i = 0; i < moveset.length; i++) {

            MoveType moveType = moveset[i].getMoveType();

            switch(moveType) {
                case PAWN_START:
                case STATIC:
                    Location end = moveset[i].getEndLocation();
                    if (boardState.getPiece(end) == null) {
                        if (validateMove(piece, start, end, boardState)) endlocations.add(end);
                    }
                    break;
                case CONTINUOUS:

                    int xDir = moveset[i].getxDir();
                    int yDir = moveset[i].getyDir();
                    int x = start.getBoardX() + xDir;
                    int y = start.getBoardY() + yDir;

                    do {
                        Location nextLocation = new Location(x, y);
                        if (boardState.getPiece(nextLocation) == null) {
                            if (validateMove(piece, start, nextLocation, boardState)) endlocations.add(nextLocation);
                        } else {
                            break;
                        }
                        x += xDir;
                        y += yDir;
                    } while(x >= 0 && y >= 0 && x < (Board.WIDTH / Board.TILE_SIZE) && y < (Board.HEIGHT / Board.TILE_SIZE));

                    break;
            }
        }
        return endlocations.toArray(new Location[endlocations.size()]);
    }

    /**
     * Method that returns all possible captures for one piece in the state that is provided.
     *
     * @param piece : Piece to check for possible captures
     * @param boardState : State to check for captures in
     * @param lookForPossibleCheck : Is true if we want to make sure that a captures does not put the player in a check position.
     * @return : Array of Locations of possible destinations after a capture
     */
    public Location[] getPossibleCaptures(Piece piece, BoardState boardState, boolean lookForPossibleCheck) {
        ArrayList<Location> endlocations = new ArrayList<>();
        Location start = piece.getLocation();
        Move[] moveset = piece.getCaptures(start);

        for (int i = 0; i < moveset.length; i++) {

            MoveType moveType = moveset[i].getMoveType();

            switch(moveType) {

                case CAPTURE_STATIC:
                    Location end = moveset[i].getEndLocation();
                    if (boardState.getPiece(end) != null && boardState.getPiece(end).getColor() != piece.getColor()) {
                        if (lookForPossibleCheck && validateMove(piece, start, end, boardState)) endlocations.add(end);
                        else if (!lookForPossibleCheck) endlocations.add(end);
                    }
                    break;
                case CAPTURE_CONTINUOUS:

                    int xDir = moveset[i].getxDir();
                    int yDir = moveset[i].getyDir();
                    int x = start.getBoardX() + xDir;
                    int y = start.getBoardY() + yDir;

                    do {
                        Location nextLocation = new Location(x, y);
                        if (boardState.getPiece(nextLocation) != null) {
                            if (boardState.getPiece(nextLocation).getColor() != piece.getColor()) {
                                if (lookForPossibleCheck && validateMove(piece, start, nextLocation, boardState)) endlocations.add(nextLocation);
                                else if (!lookForPossibleCheck) endlocations.add(nextLocation);
                            }
                            break;
                        }
                        x += xDir;
                        y += yDir;
                    } while(x >= 0 && y >= 0 && x < (Board.WIDTH / Board.TILE_SIZE) && y < (Board.HEIGHT / Board.TILE_SIZE));

                    break;
            }
        }
        return endlocations.toArray(new Location[endlocations.size()]);
    }


    /**
     * Method that checks if the move from the startLocation to the endLocation would result in a check in the provided state.
     * It does this by creating a temporary state were the move is made and sending it to the isCheck method.
     *
     * @param piece : Piece to be moved
     * @param startLocation : Starting location of the move
     * @param endLocation : End location of the move
     * @param boardState : The state that the piece resides in
     * @return : True if the move will not result in a check, false if it will
     */
    private boolean validateMove(Piece piece, Location startLocation, Location endLocation, BoardState boardState) {
        Piece[][] tempState = Piece.copyPieceArray(boardState.getState());

        int startX = startLocation.getBoardX();
        int startY = startLocation.getBoardY();
        int endX = endLocation.getBoardX();
        int endY = endLocation.getBoardY();

        tempState[endY][endX] = piece;
        tempState[startY][startX] = null;

        if (isCheck(piece.getColor(), new BoardState(tempState))) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Method that checks if the player is in check in the state provided. It does this by looping through everyone of
     * the other player's pieces and looks if any of the possible capture locations is occupied by the player's king.
     * If so, they are in check.
     *
     * @param color : Color of the player to look for check
     * @param boardState : The state to look for the check in
     * @return : True if the player is in check, false if not
     */
    public boolean isCheck(PieceColor color, BoardState boardState) {

        Piece[][] pieces = boardState.getState();

        for (int y = 0; y < pieces.length; y++) {
            for (int x = 0; x < pieces[y].length; x++) {
                Piece piece = pieces[y][x];
                if (piece != null && piece.getColor() != color) {

                    Location[] captures = getPossibleCaptures(piece, boardState, false);
                    for (int i = 0; i < captures.length; i++) {
                        if (boardState.getPiece(captures[i]).getType() == PieceType.KING) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * Loops through every piece of the player to check for checkmate. If they are not able to make any moves or
     * captures, then they are in checkmate.
     *
     * @param color : Color of the player to search
     * @param boardState : State of board to search from
     * @return : true if player is in checkmate, false if player is not
     */
    public boolean isCheckmate(PieceColor color, BoardState boardState) {

        Piece[][] pieces = boardState.getState();

        for (int y = 0; y < pieces.length; y++) {
            for (int x = 0; x < pieces[y].length; x++) {
                Piece piece = pieces[y][x];
                if (piece != null && piece.getColor() == color) {

                    Location[] moveset = getPossibleMoves(piece, boardState);
                    Location[] captures = getPossibleCaptures(piece, boardState, true);

                    if(moveset.length != 0 || captures.length != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}

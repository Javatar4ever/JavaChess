package com.teo.chess.input;

import com.teo.chess.Game;
import com.teo.chess.Location;
import com.teo.chess.MoveValidator;
import com.teo.chess.graphics.Sprite;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.layers.LayerType;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.gui.Screen;
import com.teo.chess.pieces.Piece;
import com.teo.chess.pieces.PieceType;
import com.teo.chess.states.BoardState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Mouse implements MouseListener, MouseMotionListener{

    private Board board;
    private MoveValidator validator;
    private Screen screen;
    private Piece currentPiece;

    private Location startLocation;
    private Location endLocation;
    private Location[] moveLocations;
    private Location[] captureLocations;

    private boolean isCapture = false;
    private boolean isCheck = false;
    private boolean isMate = false;

    private Sprite moveSprite = new Sprite(2,2, Game.spritesheet);
    private Sprite captureSprite = new Sprite(3,2, Game.spritesheet);
    private Sprite recentSprite = new Sprite(4,2, Game.spritesheet);

    public Mouse(Board board, MoveValidator validator, Screen screen) {
        this.board = board;
        this.validator = validator;
        this.screen = screen;
    }

    /**
     * Gets called when mouse button is pressed. Checks so that the button triggering the event is the left mouse button.
     * @param e
     */
    public synchronized void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            startLocation = new Location(getX(e) / Board.TILE_SIZE, getY(e) / Board.TILE_SIZE);

            if (board.getPiece(startLocation) != null && board.getPiece(startLocation).getColor() == Game.current_player) {

                LayerType.OVERLAY.getObj().clearLayer();
                board.setPieceAsMoving(startLocation);

                currentPiece = board.movingPieces[getY(e) / Board.TILE_SIZE][getX(e) / Board.TILE_SIZE];

                if (currentPiece != null) {
                    moveLocations = validator.getPossibleMoves(currentPiece, new BoardState(board.staticPieces));
                    captureLocations = validator.getPossibleCaptures(currentPiece, new BoardState(board.staticPieces), true);

                    for (int i = 0; i < moveLocations.length; i++) {
                        LayerType.OVERLAY.getObj().renderSprite(moveSprite, moveLocations[i]);
                    }
                    for (int i = 0; i < captureLocations.length; i++) {
                        LayerType.OVERLAY.getObj().renderSprite(captureSprite, captureLocations[i]);
                    }
                }
            }
        }
    }

    /**
     * Updates location of the selected piece
     * @param e
     */
    public synchronized void mouseDragged(MouseEvent e) {
        if (currentPiece != null) {

            int x = getX(e) - (Board.TILE_SIZE / 2);
            int y = getY(e) - (Board.TILE_SIZE / 2);

            if (x < 0) x = 0;
            if (x > Board.WIDTH - Board.TILE_SIZE) x = Board.WIDTH - Board.TILE_SIZE;
            if (y < 0) y = 0;
            if (y > Board.HEIGHT - Board.TILE_SIZE) y = Board.HEIGHT - Board.TILE_SIZE;

            currentPiece.getLocation().setPixelX(x);
            currentPiece.getLocation().setPixelY(y);
        }
    }

    /**
     * Gets called when mouse button is released. Checks whether the current location exists within the possible locations.
     * If it does, update piece to that location. If it does not, reset to starting location and display error message.
     * @param e
     */
    public synchronized void mouseReleased(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1 && currentPiece != null) {

            endLocation = null;
            isCapture = false;
            isCheck = false;
            isMate = false;

            Location currentLocation = new Location(getX(e) / Board.TILE_SIZE, getY(e) / Board.TILE_SIZE);

            if (containsLocation(currentLocation, moveLocations)) {
                endLocation = currentLocation;
            }
            else if (containsLocation(currentLocation, captureLocations)) {
                endLocation = currentLocation;
                isCapture = true;
            }
            else {
                currentPiece.setLocation(startLocation);
                board.setPieceAsStatic(currentPiece, startLocation);
                currentPiece = null;
                LayerType.OVERLAY.getObj().clearLayer();
                LayerType.MOVING.getObj().clearLayer();

                return;
            }

            currentPiece.setLocation(endLocation);
            board.setPieceAsStatic(currentPiece, endLocation);

            Game.current_player = Game.current_player == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;

            if (validator.isCheck(Game.current_player, new BoardState(board.staticPieces))) {
                if (validator.isCheckmate(Game.current_player, new BoardState(board.staticPieces))) {
                    board.showText("CHECKMATE");
                    isMate = true;
                } else {
                    board.showText("CHECK");
                    isCheck = true;
                }
            }

            LayerType.OVERLAY.getObj().clearLayer();
            LayerType.OVERLAY.getObj().renderSprite(recentSprite, endLocation);
            LayerType.OVERLAY.getObj().renderSprite(recentSprite, startLocation);
            renderSAN();
            currentPiece = null;
            LayerType.MOVING.getObj().clearLayer();
        }
    }

    /**
     * Method that checks if a board location exists in an array of locations. It does this by using the isSameTile method
     * from the Location class.
     *
     * @param location : Location to check if it is included
     * @param locations : Array of locations to search in
     * @return : True if array contains location, false if it does not
     */
    private boolean containsLocation(Location location, Location[] locations) {
        for (int i = 0; i < locations.length; i++) {
            if (location.isSameTile(locations[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets called after successful move and rendersStandard Algebraic Notation (SAN). The SAN text gets sent to the list.
     */
    private void renderSAN() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(
                ((currentPiece.getType() == PieceType.PAWN && isCapture) ?
                        startLocation.getSanName().substring(0,1) :
                        currentPiece.getSanName()));

        stringBuilder.append((isCapture ? "x" : ""));
        stringBuilder.append(endLocation.getSanName());
        stringBuilder.append((isCheck ? "+" : ""));
        stringBuilder.append((isMate ? "#" : ""));

        screen.updateList(stringBuilder.toString());
    }

    /**
     * Transforms x location from MouseEvent so it is within the board's boundaries
     * @param e
     * @return Transformed x value
     */
    private int getX(MouseEvent e) {
        if (e.getX() >= Board.WIDTH) return Board.WIDTH - 1;
        else return e.getX();
    }

    /**
     * Transforms y location from MouseEvent so it is within the board's boundaries
     * @param e
     * @return Transformed y value
     */
    private int getY(MouseEvent e) {
        if (e.getY() >= Board.HEIGHT) return Board.HEIGHT - 1;
        else return e.getY();
    }

    //UNUSED METHODS
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}

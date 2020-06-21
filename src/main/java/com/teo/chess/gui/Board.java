package com.teo.chess.gui;

import com.teo.chess.Game;
import com.teo.chess.Location;
import com.teo.chess.gui.layers.LayerType;
import com.teo.chess.gui.layers.TextLayer;
import com.teo.chess.graphics.Sprite;
import com.teo.chess.pieces.*;
import com.teo.chess.states.BoardState;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class Board extends Canvas {

    public static final int TILE_SIZE = 60;
    public static final int TILES_ACROSS = 8;
    public static final int WIDTH = TILES_ACROSS * TILE_SIZE;
    public static final int HEIGHT = TILES_ACROSS * TILE_SIZE;

    //Pieces
    public Piece[][] staticPieces = new Piece[TILES_ACROSS][TILES_ACROSS];
    public Piece[][] movingPieces = new Piece[TILES_ACROSS][TILES_ACROSS];

    private boolean textOnBoard = false;

    public Board() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        renderBoardLayer();
    }
int c = 0;
    /**
     * Method that gets called to render board
     */
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        LayerType.PIECE.getObj().clearLayer();
        LayerType.MOVING.getObj().clearLayer();

        for (int y = 0; y < TILES_ACROSS; y++) {
            for (int x = 0; x < TILES_ACROSS; x++) {
                Piece staticPiece = staticPieces[y][x];
                if(staticPiece != null)
                    LayerType.PIECE.getObj().renderSprite(staticPiece.getSprite(), staticPiece.getLocation());

                Piece movingPiece = movingPieces[y][x];
                if(movingPiece != null)
                    LayerType.MOVING.getObj().renderSprite(movingPiece.getSprite(), movingPiece.getLocation());
            }
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(LayerType.BOARD.getObj().image, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(LayerType.OVERLAY.getObj().image, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(LayerType.PIECE.getObj().image, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(LayerType.MOVING.getObj().image, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(((TextLayer)LayerType.TEXT.getObj()).image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();

        bs.show();
    }

    /**
     * Method for displaying text on the board. A thread is called as to not hinder movement of pieces during text display.
     * @param string : Text to be displayed on board
     */
    public void showText(String string) {
        if (!textOnBoard) {
            textOnBoard = true;
            new TextThread(string).start();
        }
    }
    //TODO: Workaround since mutable variable couldn't be sent to inner class
    private class TextThread extends Thread {
        private final String text;

        public TextThread(String text) {
            this.text = text;
        }
        public void run() {
            ((TextLayer)LayerType.TEXT.getObj()).fadeInString(text);
            textOnBoard = false;
        }
    }


    /**
     * Method for retrieving a static piece from the current board state. Returns null if there is no piece to retrieve.
     * @param location
     * @return
     */
    public Piece getPiece(Location location) {
        int x = location.getBoardX();
        int y = location.getBoardY();

        return staticPieces[y][x];
    }

    /**
     * Moves piece from the static layer to the moving layer
     * @param location : The location of the piece to be moved. It's used to find piece in the static array
     */
    public void setPieceAsMoving(Location location) {
        int x = location.getBoardX();
        int y = location.getBoardY();
        movingPieces[y][x] = staticPieces[y][x];
        staticPieces[y][x] = null;
    }

    /**
     * Moves piece from the moving layer to the static layer
     * @param piece : Piece to be set static
     * @param location : The location of the tile the piece should be set at
     */
    public void setPieceAsStatic(Piece piece, Location location) {
        int x = location.getBoardX();
        int y = location.getBoardY();
        staticPieces[y][x] = piece;
        movingPieces = new Piece[TILES_ACROSS][TILES_ACROSS];
    }

    /**
     * Renders the board. Is only called at startup.
     */
    private void renderBoardLayer() {
        Sprite black = new Sprite(1, 2, Game.spritesheet);
        Sprite white = new Sprite(0, 2, Game.spritesheet);

        for (int y = 0; y < TILES_ACROSS; y++) {
            for (int x = 0; x < TILES_ACROSS; x++) {
                if ((x + y) % 2 == 0) {
                    LayerType.BOARD.getObj().renderSprite(white, new Location(x, y));
                } else {
                    LayerType.BOARD.getObj().renderSprite(black, new Location(x, y));
                }
            }
        }
    }

    /**
     * Loads gamestate into static pieces
     * @param state : The state to be
     */
    public void loadState(BoardState state) {
        staticPieces = state.getState();
    }

}

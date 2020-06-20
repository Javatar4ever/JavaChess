package com.teo.chess;

import com.teo.chess.graphics.Spritesheet;
import com.teo.chess.gui.Board;
import com.teo.chess.gui.PieceColor;
import com.teo.chess.gui.Screen;
import com.teo.chess.gui.layers.TextLayer;
import com.teo.chess.input.Mouse;
import com.teo.chess.states.BoardState;
import com.teo.chess.states.InitialState;

public class Game implements Runnable {

    public static Spritesheet spritesheet = new Spritesheet("/spritesheet.png", 360, 180, Board.TILE_SIZE);
    public static Spritesheet lettersSheet = new Spritesheet("/letters.png", 99, 54, TextLayer.LETTER_SIZE);
    private Board board;
    private boolean running;

    public static PieceColor current_player = PieceColor.WHITE;


    /**
     * Method that gets called to start game loop
     */
    public void start() {
        init();
        running = true;

        new Thread(this).start();
    }

    /**
     * Initializes objects
     */
    private void init() {
        board = new Board();
        Screen screen = new Screen(board);
        BoardState state = new InitialState();
        board.loadState(state);
        MoveValidator validator = new MoveValidator(board);
        Mouse mouseListener = new Mouse(board, validator, screen);
        board.addMouseMotionListener(mouseListener);
        board.addMouseListener(mouseListener);
    }

    /**
     * Run method that contains game loop
     */
    public void run() {

        long lastTimer = System.currentTimeMillis();
        int frames = 0;

        while (running) {

            board.render();
            frames++;

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (System.currentTimeMillis() - lastTimer > 1000) {
                lastTimer += 1000;
                //System.out.println(frames + "fps");
                frames = 0;
            }

        }
    }

    public static void main(String[] args) {
        new Game().start();
    }
}

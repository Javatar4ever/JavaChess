package com.teo.chess.gui;


import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    private final Board board;
    private SidePanel sidepanel;

    public Screen(Board board) {
        this.board = board;
        setupScreen();
    }

    private void setupScreen() {
        setTitle("Java Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardpanel = new JPanel();
        boardpanel.setPreferredSize(new Dimension(Board.WIDTH + 20, Board.HEIGHT + 20));
        boardpanel.setLayout(new BorderLayout());
        boardpanel.add(board, BorderLayout.EAST);

        JPanel leftpanel = new JPanel();
        leftpanel.setPreferredSize(new Dimension(20, Board.HEIGHT));
        boardpanel.add(leftpanel, BorderLayout.WEST);

        for (int i = 8; i >= 0; i--) {
            JLabel l = new JLabel(Integer.toString(i), SwingConstants.CENTER);
            l.setPreferredSize(new Dimension(20, Board.TILE_SIZE - 4));
            leftpanel.add(l);
        }

        JPanel bottompanel = new JPanel();
        bottompanel.setPreferredSize(new Dimension(Board.WIDTH, 20));
        boardpanel.add(bottompanel, BorderLayout.SOUTH);

        for (int i = 0; i < 8; i++) {
            JLabel l = new JLabel(String.valueOf((char)(i + 97)), SwingConstants.CENTER);
            l.setPreferredSize(new Dimension(Board.TILE_SIZE - 6, 20));
            bottompanel.add(l);
        }

        add(boardpanel, BorderLayout.WEST);

        sidepanel = new SidePanel();
        add(sidepanel, BorderLayout.EAST);

        pack();

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }



    public void updateList(String string) {
        sidepanel.addMoveToList(string);
    }

}

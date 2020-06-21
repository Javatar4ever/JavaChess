package com.teo.chess.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class SidePanel extends JPanel {

    private final DefaultTableModel dtm;
    private int moveCounter;

    public SidePanel() {
        setPreferredSize(new Dimension(250, Board.HEIGHT + 20));
        setMinimumSize(new Dimension(250, Board.HEIGHT + 20));
        setMaximumSize(new Dimension(250, Board.HEIGHT + 20));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setBackground(Color.WHITE);

        JTable table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setCellSelectionEnabled(true);
        dtm = new DefaultTableModel(0, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] header = new String[] {"White", "Black"};
        dtm.setColumnIdentifiers(header);
        table.setModel(dtm);

        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(240, 200));

        add(new JLabel("Moves"));
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addMoveToList(String move) {
        if (moveCounter % 2 == 0) {
            dtm.addRow(new String[]{(moveCounter / 2) + 1 + ". " + move, ""});
        }
        else {
            dtm.setValueAt(move, moveCounter / 2, 1);
        }
        moveCounter++;
    }
}

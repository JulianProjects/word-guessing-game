// File: gui/eingabefeld/CellFactory.java
package gui.eingabefeld;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

final class CellFactory {
    private CellFactory() {}

    static JLabel createCell() {
        JLabel l = new JLabel("", SwingConstants.CENTER);
        l.setOpaque(true);
        l.setFont(l.getFont().deriveFont(Font.BOLD, Theme.FONT_PT));
        l.setPreferredSize(new Dimension(Theme.CELL_W, Theme.CELL_H));
        l.setMinimumSize(new Dimension(Theme.CELL_W, Theme.CELL_H));
        l.setMaximumSize(new Dimension(Theme.CELL_W, Theme.CELL_H));
        l.setBackground(Theme.CELL_BG_DEFAULT);
        l.setForeground(Color.BLACK);
        l.setBorder(new CompoundBorder(
                new LineBorder(Theme.CELL_BD_DEFAULT, 2, true),
                new EmptyBorder(Theme.PAD_V, Theme.PAD_H, Theme.PAD_V, Theme.PAD_H)
        ));
        return l;
    }
}

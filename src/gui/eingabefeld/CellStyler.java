// File: gui/eingabefeld/CellStyler.java
package gui.eingabefeld;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

final class CellStyler {
    private CellStyler() {}

    private static Color borderForBG(Color bg) {
        if (bg.equals(Theme.CELL_BG_GREEN))   return Theme.CELL_BD_GREEN;
        if (bg.equals(Theme.CELL_BG_YELLOW))  return Theme.CELL_BD_YELLOW;
        if (bg.equals(Theme.CELL_BG_ABSENT))  return Theme.CELL_BD_ABSENT;
        return Theme.CELL_BD_DEFAULT;
    }

    static void styleDefault(SixRowWordInput host, int r, int c, boolean focus) {
        JLabel cell = host.cells[r][c];
        cell.setBackground(Theme.CELL_BG_DEFAULT);
        cell.setForeground(Color.BLACK);
        Color bd = borderForBG(cell.getBackground());
        cell.setBorder(new CompoundBorder(
                new LineBorder(bd, focus ? 3 : 2, true),
                new EmptyBorder(Theme.PAD_V, Theme.PAD_H, Theme.PAD_V, Theme.PAD_H)
        ));
    }

    static void styleGreen(SixRowWordInput host, int r, int c) {
        JLabel cell = host.cells[r][c];
        cell.setBackground(Theme.CELL_BG_GREEN);
        cell.setForeground(Color.WHITE);
        cell.setBorder(new CompoundBorder(
                new LineBorder(Theme.CELL_BD_GREEN, 2, true),
                new EmptyBorder(Theme.PAD_V, Theme.PAD_H, Theme.PAD_V, Theme.PAD_H)
        ));
    }

    static void styleYellow(SixRowWordInput host, int r, int c) {
        JLabel cell = host.cells[r][c];
        cell.setBackground(Theme.CELL_BG_YELLOW);
        cell.setForeground(Color.WHITE);
        cell.setBorder(new CompoundBorder(
                new LineBorder(Theme.CELL_BD_YELLOW, 2, true),
                new EmptyBorder(Theme.PAD_V, Theme.PAD_H, Theme.PAD_V, Theme.PAD_H)
        ));
    }

    static void styleAbsent(SixRowWordInput host, int r, int c) {
        JLabel cell = host.cells[r][c];
        cell.setBackground(Theme.CELL_BG_ABSENT);
        cell.setForeground(Color.WHITE);
        cell.setBorder(new CompoundBorder(
                new LineBorder(Theme.CELL_BD_ABSENT, 2, true),
                new EmptyBorder(Theme.PAD_V, Theme.PAD_H, Theme.PAD_V, Theme.PAD_H)
        ));
    }
}

// File: gui/eingabefeld/GridBuilder.java
package gui.eingabefeld;

import javax.swing.*;
import java.awt.*;
import logic.AppConfig;

final class GridBuilder {
    private GridBuilder() {}

    static JPanel buildGrid(SixRowWordInput host) {
        JPanel grid = new JPanel();
        grid.setOpaque(false);
        grid.setLayout(new GridLayout(Theme.ROWS, AppConfig.WORD_LENGTH, Theme.GAP, Theme.GAP));

        for (int r = 0; r < Theme.ROWS; r++) {
            for (int c = 0; c < AppConfig.WORD_LENGTH; c++) {
                JLabel cell = CellFactory.createCell();
                host.cells[r][c] = cell;
                grid.add(cell);
            }
        }
        return grid;
    }
}

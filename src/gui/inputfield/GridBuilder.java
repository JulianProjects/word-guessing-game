package gui.inputfield;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.AppConfig;

final class GridBuilder {

  private GridBuilder() {}

  static JPanel buildGrid(SixRowWordInput host) {
    JPanel grid = new JPanel();

    grid.setOpaque(false);
    grid.setLayout(
        new GridLayout(
            Theme.ROWS,
            AppConfig.WORD_LENGTH,
            Theme.GAP,
            Theme.GAP));

    for (int row = 0; row < Theme.ROWS; row++) {
      for (int column = 0; column < AppConfig.WORD_LENGTH; column++) {
        JLabel cell = CellFactory.createCell();
        host.cells[row][column] = cell;
        grid.add(cell);
      }
    }

    return grid;
  }
}
package gui.inputfield;

import logic.AppConfig;

final class FocusUpdater {

  private FocusUpdater() {}

  static void update(SixRowWordInput host) {
    int row = host.row;

    if (row < 0 || row >= Theme.ROWS) {
      return;
    }

    if (host.rowLocked[row]) {
      return;
    }

    for (int column = 0; column < AppConfig.WORD_LENGTH; column++) {
      boolean focused = column == host.col;
      CellStyler.styleDefault(host, row, column, focused);
    }
  }
}
package gui.inputfield;

import logic.AppConfig;

public class Reset extends SixRowWordInput {

  private final SixRowWordInput host;

  public Reset(SixRowWordInput host) {
    this.host = host;
  }

  public void run() {
    for (int row = 0; row < Theme.ROWS; row++) {
      for (int column = 0; column < AppConfig.WORD_LENGTH; column++) {
        host.cells[row][column].setText("");
        CellStyler.styleDefault(host, row, column, false);
      }

      host.rowLocked[row] = false;
    }

    host.row = 0;
    host.col = 0;
    FocusUpdater.update(host);
  }
}
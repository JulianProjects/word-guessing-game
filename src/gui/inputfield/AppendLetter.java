package gui.inputfield;

import logic.AppConfig;

public class AppendLetter extends SixRowWordInput {

  private final SixRowWordInput host;
  private final char character;

  public AppendLetter(SixRowWordInput host, char character) {
    this.host = host;
    this.character = character;
  }

  public void run() {
    if (host.rowLocked[host.row]) {
      FocusUpdater.update(host);
      return;
    }

    if (!Character.isLetter(character)) {
      return;
    }

    if (host.row >= Theme.ROWS) {
      return;
    }

    if (host.col >= AppConfig.WORD_LENGTH) {
      return;
    }

    host.cells[host.row][host.col].setText(
        String.valueOf(Character.toUpperCase(character)));
    CellStyler.styleDefault(host, host.row, host.col, true);

    host.col++;
    FocusUpdater.update(host);
  }
}

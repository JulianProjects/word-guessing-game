// File: gui/eingabefeld/AppendLetter.java
package gui.eingabefeld;

import logic.AppConfig;

public class AppendLetter extends SixRowWordInput {

    private final SixRowWordInput host;
    private final char ch;

    public AppendLetter(SixRowWordInput host, char ch) {
        this.host = host;
        this.ch = ch;
    }

    public void run() {
        if (host.rowLocked[host.row]) { FocusUpdater.update(host); return; }
        if (!Character.isLetter(ch)) return;
        if (host.row >= Theme.ROWS) return;
        if (host.col >= AppConfig.WORD_LENGTH) return;

        host.cells[host.row][host.col].setText(String.valueOf(Character.toUpperCase(ch)));
        CellStyler.styleDefault(host, host.row, host.col, true);
        host.col++;
        FocusUpdater.update(host);
    }
}

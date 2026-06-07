// File: gui/eingabefeld/Reset.java
package gui.eingabefeld;

import logic.AppConfig;

public class Reset extends SixRowWordInput {

    private final SixRowWordInput host;

    public Reset(SixRowWordInput host) {
        this.host = host;
    }

    public void run() {
        for (int r = 0; r < Theme.ROWS; r++) {
            for (int c = 0; c < AppConfig.WORD_LENGTH; c++) {
                host.cells[r][c].setText("");
                CellStyler.styleDefault(host, r, c, false);
            }
            host.rowLocked[r] = false;
        }
        host.row = 0;
        host.col = 0;
        FocusUpdater.update(host);
    }
}

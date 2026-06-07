// File: gui/eingabefeld/FocusUpdater.java
package gui.eingabefeld;

import logic.AppConfig;

final class FocusUpdater {
    private FocusUpdater() {}

    static void update(SixRowWordInput host) {
        int rr = host.row;
        if (rr < 0 || rr >= Theme.ROWS) return;
        if (host.rowLocked[rr]) return;

        for (int cc = 0; cc < AppConfig.WORD_LENGTH; cc++) {
            boolean focus = (cc == host.col);
            CellStyler.styleDefault(host, rr, cc, focus);
        }
    }
}

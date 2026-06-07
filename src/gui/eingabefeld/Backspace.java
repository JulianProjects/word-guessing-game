// File: gui/eingabefeld/Backspace.java
package gui.eingabefeld;

public class Backspace extends SixRowWordInput {

    private final SixRowWordInput host;

    public Backspace(SixRowWordInput host) {
        this.host = host;
    }

    public void run() {
        if (host.rowLocked[host.row]) { FocusUpdater.update(host); return; }
        if (host.row >= Theme.ROWS) return;
        if (host.col > 0) host.col--;
        host.cells[host.row][host.col].setText("");
        CellStyler.styleDefault(host, host.row, host.col, true);
        FocusUpdater.update(host);
    }
}

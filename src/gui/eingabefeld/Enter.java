// File: gui/eingabefeld/Enter.java
package gui.eingabefeld;

import logic.AppConfig;
import logic.WordChecker;

import javax.swing.*;
import java.util.Locale;

public class Enter extends SixRowWordInput {

    private final SixRowWordInput host;

    public Enter(SixRowWordInput host) {
        this.host = host;
    }

    public void run() {
        if (host.row >= Theme.ROWS) return;

        if (host.col < AppConfig.WORD_LENGTH) {
            JOptionPane.showMessageDialog(host,
                    "Bitte ein Wort mit " + AppConfig.WORD_LENGTH + " Buchstaben eingeben.",
                    "Unvollständig", JOptionPane.INFORMATION_MESSAGE);
            FocusUpdater.update(host);
            return;
        }

        String guess = RowTextUtil.rowText(host, host.row).toUpperCase(Locale.ROOT);

        // Sonderfall: geratenes Wort "ENTER"
        if ("ENTER".equals(guess)) {
            new NewGame(host).run();
            SwingUtilities.invokeLater(() -> {
                boolean accepted = TargetWordDialog.openAndSet(host);
                if (accepted) new Reset(host).run();
            });
            return;
        }

        if (!WordChecker.check(guess)) {
            JOptionPane.showMessageDialog(host, "Das Wort existiert nicht.",
                    "Ungültiges Wort", JOptionPane.INFORMATION_MESSAGE);
            FocusUpdater.update(host);
            return;
        }

        String target = host.targetWord.toUpperCase(Locale.ROOT);
        WordleLogic.colorizeRow(host, host.row, guess, target);

        if (guess.equals(target)) {
            host.rowLocked[host.row] = true;
            JOptionPane.showMessageDialog(host,
                    "Richtig! Das Wort war „" + host.targetWord + "“.",
                    "Gewonnen", JOptionPane.INFORMATION_MESSAGE);
            for (int r = host.row + 1; r < Theme.ROWS; r++) host.rowLocked[r] = true;
            FocusUpdater.update(host);
            return;
        }

        host.rowLocked[host.row] = true;
        host.row++;
        host.col = 0;

        if (host.row >= Theme.ROWS) {
            JOptionPane.showMessageDialog(host,
                    "Versuche aufgebraucht. Das Wort war „" + host.targetWord + "“.",
                    "Vorbei", JOptionPane.INFORMATION_MESSAGE);
        }

        FocusUpdater.update(host);
    }
}

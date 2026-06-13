package gui.inputfield;

import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import logic.AppConfig;
import logic.WordChecker;

public class Enter extends SixRowWordInput {

  private final SixRowWordInput host;

  public Enter(SixRowWordInput host) {
    this.host = host;
  }

  public void run() {
    if (host.row >= Theme.ROWS) {
      return;
    }

    if (host.col < AppConfig.WORD_LENGTH) {
      JOptionPane.showMessageDialog(
          host,
          "Please enter a word with " + AppConfig.WORD_LENGTH + " letters.",
          "Incomplete",
          JOptionPane.INFORMATION_MESSAGE);
      FocusUpdater.update(host);
      return;
    }

    String guess = RowTextUtil.rowText(host, host.row).toUpperCase(Locale.ROOT);

    if ("ENTER".equals(guess)) {
      new NewGame(host).run();
      SwingUtilities.invokeLater(
          () -> {
            boolean accepted = TargetWordDialog.openAndSet(host);

            if (accepted) {
              new Reset(host).run();
            }
          });
      return;
    }

    if (!WordChecker.check(guess)) {
      JOptionPane.showMessageDialog(
          host,
          "The word does not exist.",
          "Invalid word",
          JOptionPane.INFORMATION_MESSAGE);
      FocusUpdater.update(host);
      return;
    }

    String targetWord = host.targetWord.toUpperCase(Locale.ROOT);
    WordleLogic.colorizeRow(host, host.row, guess, targetWord);

    if (guess.equals(targetWord)) {
      host.rowLocked[host.row] = true;
      JOptionPane.showMessageDialog(
          host,
          "Correct! The word was \"" + host.targetWord + "\".",
          "You won",
          JOptionPane.INFORMATION_MESSAGE);

      for (int rowIndex = host.row + 1; rowIndex < Theme.ROWS; rowIndex++) {
        host.rowLocked[rowIndex] = true;
      }

      FocusUpdater.update(host);
      return;
    }

    host.rowLocked[host.row] = true;
    host.row++;
    host.col = 0;

    if (host.row >= Theme.ROWS) {
      JOptionPane.showMessageDialog(
          host,
          "No attempts remaining. The word was \"" + host.targetWord + "\".",
          "Game over",
          JOptionPane.INFORMATION_MESSAGE);
    }

    FocusUpdater.update(host);
  }
}

package ui;

import javax.swing.UIManager;

/** Applies a preferred Swing look and feel when available. */
public final class LookAndFeelUtil {

  private LookAndFeelUtil() {}

  public static void applyPreferred() {
    try {
      // Select the first supported look and feel in the preferred order.
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Windows".equals(info.getName())
            || "Nimbus".equals(info.getName())
            || "Metal".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception exception) {
      // Keep the default look and feel if the preferred one cannot be applied.
    }
  }
}
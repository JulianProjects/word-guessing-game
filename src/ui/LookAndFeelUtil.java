// File: src/gui/app/LookAndFeelUtil.java
package ui;

import javax.swing.UIManager;

public final class LookAndFeelUtil {
    private LookAndFeelUtil() {}

    public static void applyPreferred() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName()) || "Nimbus".equals(info.getName()) || "Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignore) {}
    }
}

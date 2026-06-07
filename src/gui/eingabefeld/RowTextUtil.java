// File: gui/eingabefeld/RowTextUtil.java
package gui.eingabefeld;

import logic.AppConfig;

final class RowTextUtil {
    private RowTextUtil() {}

    static String rowText(SixRowWordInput host, int r) {
        StringBuilder sb = new StringBuilder(AppConfig.WORD_LENGTH);
        for (int c = 0; c < AppConfig.WORD_LENGTH; c++) {
            String t = host.cells[r][c].getText();
            sb.append((t == null || t.isEmpty()) ? ' ' : t.charAt(0));
        }
        return sb.toString();
    }
}

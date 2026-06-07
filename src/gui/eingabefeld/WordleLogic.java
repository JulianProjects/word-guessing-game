// File: gui/eingabefeld/WordleLogic.java
package gui.eingabefeld;

import logic.AppConfig;

final class WordleLogic {
    private WordleLogic() {}

    enum Hit { GREEN, YELLOW, ABSENT }

    static Hit[] compareWordle(String guess, String target) {
        int n = AppConfig.WORD_LENGTH;
        Hit[] res = new Hit[n];
        boolean[] used = new boolean[n];

        for (int i = 0; i < n; i++) res[i] = Hit.ABSENT;

        for (int i = 0; i < n; i++) {
            if (guess.charAt(i) == target.charAt(i)) {
                res[i] = Hit.GREEN;
                used[i] = true;
            }
        }
        // Yellows
        for (int i = 0; i < n; i++) {
            if (res[i] == Hit.GREEN) continue;
            char g = guess.charAt(i);
            for (int j = 0; j < n; j++) {
                if (used[j]) continue;
                if (g == target.charAt(j)) {
                    res[i] = Hit.YELLOW;
                    used[j] = true;
                    break;
                }
            }
        }
        return res;
    }

    static void colorizeRow(SixRowWordInput host, int r, String guess, String target) {
        Hit[] hits = compareWordle(guess, target);
        for (int c = 0; c < AppConfig.WORD_LENGTH; c++) {
            switch (hits[c]) {
                case GREEN:
                    CellStyler.styleGreen(host, r, c);
                    break;
                case YELLOW:
                    CellStyler.styleYellow(host, r, c);
                    break;
                case ABSENT:
                    CellStyler.styleAbsent(host, r, c);
                    break;
            }
        }
    }
}

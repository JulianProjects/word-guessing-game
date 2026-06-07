package gui.button;

import javax.swing.*;

/** Kapselt die Sichtbarkeit der Buttons. */
public class ButtonsVisibility extends Buttons {
    private final JButton play, home, restart;

    public ButtonsVisibility(JButton play, JButton home, JButton restart) {
        this.play = play; this.home = home; this.restart = restart;
    }

    /** Sichtbarkeit für Hauptmenü. */
    public void showMainButtons() {
        play.setVisible(true);
        home.setVisible(false);
        restart.setVisible(false); // ← NEU
    }

    /** Sichtbarkeit fürs Spiel: Home + Neustart. */
    public void showHomeButtonOnly() {
        play.setVisible(false);
        home.setVisible(true);
        restart.setVisible(true); // ← NEU
    }

    @Override public void onPlay() {}
    @Override public void onHome() {}
    @Override public void onRestart() {}
}

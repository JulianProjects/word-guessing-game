package gui.button;

import javax.swing.*;

/** Abstrakte Basis für Button-Gruppen. */
public abstract class Buttons {
    public abstract void onPlay();
    public abstract void onHome();
    public abstract void onRestart();  // ← NEU

    public JComponent getComponent() { return null; }
}

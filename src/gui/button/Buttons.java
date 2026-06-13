package gui.button;

import javax.swing.JComponent;

/** Base class for button groups. */
public abstract class Buttons {

  public abstract void onPlay();

  public abstract void onHome();

  public abstract void onRestart();

  public JComponent getComponent() {
    return null;
  }
}
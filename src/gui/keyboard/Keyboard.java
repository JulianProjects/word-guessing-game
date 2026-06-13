package gui.keyboard;

/** Defines the actions supported by a keyboard component. */
public abstract class Keyboard {

  public abstract void onLetter(char character);

  public abstract void onEnter();

  public abstract void onBackspace();
}

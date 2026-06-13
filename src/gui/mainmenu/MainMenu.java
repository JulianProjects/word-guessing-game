package gui.mainmenu;

import gui.button.ButtonsBar;
import javax.swing.JComponent;

/** Defines the components and behavior of the application's main menu. */
public abstract class MainMenu {

  public abstract JComponent getHeader();

  public abstract JComponent getCenterCard();

  public abstract JComponent getSouth();

  public abstract ButtonsBar getButtonsBar();

  public abstract void loadHomeImage(String path);

  public abstract void setHintHtml(String html);

  public abstract void onResized();
}
package mvp;

import ui.WordleUI;

/** Implements the Wordle view using the Swing user interface. */
public class WordleSwingView implements WordleView {

  private final WordleUI ui;

  public WordleSwingView() {
    // Create the concrete Swing interface used by this view.
    ui = new WordleUI();
  }

  @Override
  public void show() {
    ui.show();
  }
}
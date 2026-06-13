package mvp;

import javax.swing.SwingUtilities;

/** Coordinates the Wordle view and starts the application interface. */
public class WordlePresenter {

  private final WordleView view;

  public WordlePresenter(WordleView view) {
    this.view = view;
  }

  public void start() {
    // Create and display the Swing interface on the event dispatch thread.
    SwingUtilities.invokeLater(() -> view.show());
  }
}
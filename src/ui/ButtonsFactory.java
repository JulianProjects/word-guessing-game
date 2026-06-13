package ui;

import gui.button.ButtonsBar;
import gui.inputfield.SixRowWordInput;
import java.awt.Window;
import java.util.concurrent.atomic.AtomicReference;

public final class ButtonsFactory {

  private ButtonsFactory() {}

  public static ButtonsBar create(
      SixRowWordInput wordInput,
      AtomicReference<Runnable> showGameOnlyReference,
      AtomicReference<Runnable> showMainMenuReference) {

    // Connect the button actions to navigation and game controls.
    return new ButtonsBar() {
      @Override
      public void onPlay() {
        Runnable showGameOnly = showGameOnlyReference.get();

        if (showGameOnly != null) {
          showGameOnly.run();
        }
      }

      @Override
      public void onHome() {
        // Close the currently active application window.
        for (Window window : Window.getWindows()) {
          if (window.isActive()) {
            window.dispose();
            return;
          }
        }

        System.exit(0);
      }

      @Override
      public void onRestart() {
        wordInput.newGame();
      }
    };
  }
}
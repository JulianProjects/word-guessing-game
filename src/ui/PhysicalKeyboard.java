package ui;

import gui.inputfield.SixRowWordInput;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Window;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import logic.AppConfig;

/** Registers physical keyboard input for the Wordle game. */
public final class PhysicalKeyboard {

  private PhysicalKeyboard() {}

  public static KeyEventDispatcher register(SixRowWordInput wordInput) {
    if (!AppConfig.USE_PHYSICAL_KEYBOARD) {
      return null;
    }

    KeyEventDispatcher dispatcher =
        event -> {
          Window activeWindow =
              KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();

          // Ignore keyboard input while a modal dialog is open.
          if (activeWindow instanceof JDialog
              && ((JDialog) activeWindow).isModal()) {
            return false;
          }

          if (event.getID() == KeyEvent.KEY_TYPED) {
            char character = event.getKeyChar();

            if (Character.isLetter(character)) {
              wordInput.appendLetter(character);
              return true;
            }

            return false;
          }

          if (event.getID() == KeyEvent.KEY_PRESSED) {
            int keyCode = event.getKeyCode();

            if (keyCode == KeyEvent.VK_BACK_SPACE) {
              wordInput.backspace();
              return true;
            }

            if (keyCode == KeyEvent.VK_ENTER) {
              wordInput.enter();
              return true;
            }
          }

          return false;
        };

    // Register the dispatcher globally for the current application.
    KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addKeyEventDispatcher(dispatcher);

    return dispatcher;
  }

  public static void unregister(KeyEventDispatcher dispatcher) {
    if (dispatcher == null) {
      return;
    }

    KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .removeKeyEventDispatcher(dispatcher);
  }
}
// File: src/gui/tastatur/PhysischeTastatur.java
package ui;

import gui.eingabefeld.SixRowWordInput;
import logic.AppConfig;

import javax.swing.JDialog;
import java.awt.KeyboardFocusManager;
import java.awt.KeyEventDispatcher;
import java.awt.Window;
import java.awt.event.KeyEvent;

public final class PhysischeTastatur {
    private PhysischeTastatur() {}

    public static KeyEventDispatcher registrieren(SixRowWordInput wordInput) {
        if (!AppConfig.USE_PHYSICAL_KEYBOARD) return null;

        KeyEventDispatcher dispatcher = e -> {
            Window aw = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
            if (aw instanceof JDialog && ((JDialog) aw).isModal()) return false;

            if (e.getID() == KeyEvent.KEY_TYPED) {
                char ch = e.getKeyChar();
                if (Character.isLetter(ch)) {
                    wordInput.appendLetter(ch);
                    return true;
                }
                return false;
            } else if (e.getID() == KeyEvent.KEY_PRESSED) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_BACK_SPACE) {
                    wordInput.backspace();
                    return true;
                } else if (code == KeyEvent.VK_ENTER) {
                    wordInput.enter();
                    return true;
                }
                return false;
            }
            return false;
        };

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
        return dispatcher;
    }

    public static void deregistrieren(KeyEventDispatcher dispatcher) {
        if (dispatcher == null) return;
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(dispatcher);
    }
}

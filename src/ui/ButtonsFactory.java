// File: src/gui/app/ButtonsFactory.java
package ui;

import gui.button.ButtonsBar;
import gui.eingabefeld.SixRowWordInput;

import java.util.concurrent.atomic.AtomicReference;

public final class ButtonsFactory {
    private ButtonsFactory() {}


    public static ButtonsBar create(SixRowWordInput wordInput,
                                    AtomicReference<Runnable> showGameOnlyRef,
                                    AtomicReference<Runnable> showMainMenuRef) {
        return new ButtonsBar() {
            @Override public void onPlay()   { if (showGameOnlyRef.get() != null) showGameOnlyRef.get().run(); }
            @Override public void onHome() {
                for (java.awt.Window w : java.awt.Window.getWindows()) {
                    if (w.isActive()) {
                        w.dispose();
                        return;
                    }
                }
                System.exit(0);
            }
            @Override public void onRestart(){ wordInput.newGame(); }
        };
    }
}

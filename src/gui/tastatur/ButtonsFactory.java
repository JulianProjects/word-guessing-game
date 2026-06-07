package gui.tastatur;

import javax.swing.*;

import gui.button.Buttons;

import java.awt.*;

/** Fabrik für einheitlich gestylte Buttons. */
public class ButtonsFactory extends Buttons {
    public JButton create(String text, Runnable action) {
        JButton b = new JButton(text);
        b.setFont(b.getFont().deriveFont(Font.BOLD, 14f));
        b.setPreferredSize(new Dimension(120, 38));
        b.setFocusPainted(false);
        if (action != null) b.addActionListener(e -> action.run());
        return b;
    }
    @Override public void onPlay() {}
    @Override public void onHome() {}
    @Override public void onRestart() {} // ← NEU, leer
}

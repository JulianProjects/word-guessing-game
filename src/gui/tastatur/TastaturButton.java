package gui.tastatur;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class TastaturButton extends Tastatur {
    public static final int KEY_WIDTH  = 48;
    public static final int KEY_HEIGHT = 50;

    private static final Color KEY_BG     = new Color(0x818384);
    private static final Color KEY_BORDER = new Color(0x3A3A3C);
    private static final Color KEY_FG     = Color.WHITE;

    private final JButton button;

    public TastaturButton(String label, Runnable action) {
        button = new JButton(label);
        button.setFocusPainted(false);
        button.setForeground(KEY_FG);
        button.setBackground(KEY_BG);
        button.setOpaque(true);
        button.setBorder(new LineBorder(KEY_BORDER, 1, false));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Dimension d = new Dimension(KEY_WIDTH, KEY_HEIGHT);
        button.setPreferredSize(d);
        button.setMinimumSize(d);
        button.setMaximumSize(d);

        button.setFont(button.getFont().deriveFont(Font.BOLD, 14f));
        button.setMargin(new Insets(0, 0, 0, 0));

        if (action != null) button.addActionListener(e -> action.run());
    }

    public JButton getButton() { return button; }

    @Override public void onLetter(char c) {}
    @Override public void onEnter() {}
    @Override public void onBackspace() {}
}

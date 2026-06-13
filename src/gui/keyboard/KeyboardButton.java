package gui.keyboard;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class KeyboardButton extends Keyboard {

  public static final int KEY_WIDTH = 48;
  public static final int KEY_HEIGHT = 50;

  private static final Color KEY_BACKGROUND = new Color(0x818384);
  private static final Color KEY_BORDER = new Color(0x3A3A3C);
  private static final Color KEY_FOREGROUND = Color.WHITE;

  private final JButton button;

  public KeyboardButton(String label, Runnable action) {
    button = new JButton(label);
    button.setFocusPainted(false);
    button.setForeground(KEY_FOREGROUND);
    button.setBackground(KEY_BACKGROUND);
    button.setOpaque(true);
    button.setBorder(new LineBorder(KEY_BORDER, 1, false));
    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    // Keep every keyboard button at the same fixed size.
    Dimension keySize = new Dimension(KEY_WIDTH, KEY_HEIGHT);
    button.setPreferredSize(keySize);
    button.setMinimumSize(keySize);
    button.setMaximumSize(keySize);

    button.setFont(button.getFont().deriveFont(Font.BOLD, 14.0f));
    button.setMargin(new Insets(0, 0, 0, 0));

    if (action != null) {
      button.addActionListener(event -> action.run());
    }
  }

  public JButton getButton() {
    return button;
  }

  @Override
  public void onLetter(char character) {
  }

  @Override
  public void onEnter() {
  }

  @Override
  public void onBackspace() {
  }
}

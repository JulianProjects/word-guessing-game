package gui.mainmenu;

import gui.keyboard.Keyboard;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TextFieldStyler extends Keyboard {

  private static final Color BACKGROUND_COLOR = new Color(0x121213);
  private static final Color FOREGROUND_COLOR = Color.WHITE;
  private static final Color BORDER_COLOR = new Color(0x3A3A3C);

  public static void applyWordleLook(JTextField textField) {
    // Apply the dark Wordle-style appearance to the text field.
    textField.setHorizontalAlignment(SwingConstants.CENTER);
    textField.setFont(textField.getFont().deriveFont(Font.BOLD, 28.0f));
    textField.setForeground(FOREGROUND_COLOR);
    textField.setBackground(BACKGROUND_COLOR);
    textField.setCaretColor(BACKGROUND_COLOR);
    textField.setBorder(
        new CompoundBorder(
            new LineBorder(BORDER_COLOR, 2, true),
            new EmptyBorder(6, 10, 6, 10)));
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
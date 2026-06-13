package gui.keyboard;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class OnScreenKeyboard extends Keyboard {

  private final JPanel panel;

  public OnScreenKeyboard() {
    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setOpaque(false);
    panel.setBorder(new EmptyBorder(6, 0, 0, 0));

    // Build the keyboard row by row.
    panel.add(new KeyboardRow("QWERTYUIOP", this).getComponent());
    panel.add(Box.createVerticalStrut(8));
    panel.add(new KeyboardRow("ASDFGHJKL", this).getComponent());
    panel.add(Box.createVerticalStrut(8));

    String[] thirdRowKeys = {"⏎", "Z", "X", "C", "V", "B", "N", "M", "⌫"};
    panel.add(new KeyboardRow(thirdRowKeys, this).getComponent());
  }

  public JComponent getComponent() {
    return panel;
  }

  @Override
  public void onLetter(char character) {
    System.out.println("Letter: " + character);
  }

  @Override
  public void onEnter() {
    System.out.println("ENTER");
  }

  @Override
  public void onBackspace() {
    System.out.println("BACKSPACE");
  }
}
package gui.keyboard;

import gui.button.Buttons;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/** Creates consistently styled buttons. */
public class ButtonsFactory extends Buttons {

  public JButton create(String text, Runnable action) {
    JButton button = new JButton(text);

    // Apply the shared appearance to every created button.
    button.setFont(button.getFont().deriveFont(Font.BOLD, 14.0f));
    button.setPreferredSize(new Dimension(120, 38));
    button.setFocusPainted(false);

    if (action != null) {
      button.addActionListener(event -> action.run());
    }

    return button;
  }

  @Override
  public void onPlay() {
  }

  @Override
  public void onHome() {
  }

  @Override
  public void onRestart() {
  }
}

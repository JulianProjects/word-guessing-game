package gui.keyboard;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class KeyboardRow extends Keyboard {

  private final JPanel rowPanel;

  public KeyboardRow(String letters, Keyboard parent) {
    this(
        letters
            .chars()
            .mapToObj(character -> String.valueOf((char) character))
            .toArray(String[]::new),
        parent);
  }

  public KeyboardRow(String[] labels, Keyboard parent) {
    int columnCount = labels.length;

    rowPanel = new JPanel(new GridLayout(1, columnCount, 0, 0));
    rowPanel.setOpaque(false);

    int rowWidth = KeyboardButton.KEY_WIDTH * columnCount;
    int rowHeight = KeyboardButton.KEY_HEIGHT;
    Dimension rowSize = new Dimension(rowWidth, rowHeight);

    rowPanel.setPreferredSize(rowSize);
    rowPanel.setMinimumSize(rowSize);
    rowPanel.setMaximumSize(rowSize);

    // Connect each key label to the corresponding keyboard action.
    for (String label : labels) {
      Runnable action;

      if ("⏎".equals(label) || "ENTER".equalsIgnoreCase(label)) {
        action = parent::onEnter;
      } else if ("⌫".equals(label) || "←".equals(label)) {
        action = parent::onBackspace;
      } else {
        char character = label.charAt(0);
        action = () -> parent.onLetter(character);
      }

      rowPanel.add(new KeyboardButton(label, action).getButton());
    }
  }

  public JPanel getComponent() {
    return rowPanel;
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
package gui.inputfield;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import logic.AppConfig;
import logic.WordGenerator;

public class SixRowWordInput extends JPanel {

  protected final JLabel[][] cells = new JLabel[Theme.ROWS][AppConfig.WORD_LENGTH];
  protected final boolean[] rowLocked = new boolean[Theme.ROWS];

  protected int row = 0;
  protected int col = 0;
  protected String targetWord = WordGenerator.randomWord();

  public SixRowWordInput() {
    super(new GridBagLayout());

    setOpaque(false);

    // Center the complete word grid inside this panel.
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.CENTER;

    JPanel grid = GridBuilder.buildGrid(this);
    add(grid, constraints);

    FocusUpdater.update(this);

    // Reset the current input when Escape is pressed.
    getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
        .put(KeyStroke.getKeyStroke("ESCAPE"), "reset");
    getActionMap()
        .put(
            "reset",
            new AbstractAction() {
              @Override
              public void actionPerformed(ActionEvent event) {
                new Reset(SixRowWordInput.this).run();
              }
            });

    // Submit the current word when Enter is pressed.
    getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
        .put(KeyStroke.getKeyStroke("ENTER"), "submitWord");
    getActionMap()
        .put(
            "submitWord",
            new AbstractAction() {
              @Override
              public void actionPerformed(ActionEvent event) {
                new Enter(SixRowWordInput.this).run();
              }
            });
  }

  public void appendLetter(char character) {
    new AppendLetter(this, character).run();
  }

  public void backspace() {
    new Backspace(this).run();
  }

  public void enter() {
    new Enter(this).run();
  }

  public void reset() {
    new Reset(this).run();
  }

  public void newGame() {
    new NewGame(this).run();
  }
}

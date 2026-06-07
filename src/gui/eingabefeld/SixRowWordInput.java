// File: gui/eingabefeld/SixRowWordInput.java
package gui.eingabefeld;

import javax.swing.*;
import java.awt.*;
import logic.AppConfig;
import logic.WordGenerator;

public class SixRowWordInput extends JPanel {

    protected final JLabel[][] cells = new JLabel[Theme.ROWS][AppConfig.WORD_LENGTH];
    protected final boolean[]  rowLocked = new boolean[Theme.ROWS];
    protected int row = 0;  // aktive Zeile
    protected int col = 0;  // aktive Spalte innerhalb der Zeile
    protected String targetWord = WordGenerator.randomWord();

    public SixRowWordInput() {
        super(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel grid = GridBuilder.buildGrid(this);
        add(grid, gbc);

        FocusUpdater.update(this);

        // ESC -> Reset
        getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("ESCAPE"), "reset");
        getActionMap().put("reset", new AbstractAction() {
            @Override public void actionPerformed(java.awt.event.ActionEvent e) {
                new Reset(SixRowWordInput.this).run();
            }
        });

        // ENTER -> enter()
        getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("ENTER"), "submitWord");
        getActionMap().put("submitWord", new AbstractAction() {
            @Override public void actionPerformed(java.awt.event.ActionEvent e) {
                new Enter(SixRowWordInput.this).run();
            }
        });
    }


    public void appendLetter(char ch) { new AppendLetter(this, ch).run(); }
    public void backspace()           { new Backspace(this).run(); }
    public void enter()               { new Enter(this).run(); }
    public void reset()               { new Reset(this).run(); }
    public void newGame()             { new NewGame(this).run(); }
}

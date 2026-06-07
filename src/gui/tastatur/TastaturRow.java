package gui.tastatur;

import javax.swing.*;

import java.awt.*;

public class TastaturRow extends Tastatur {
    private final JPanel row;

    public TastaturRow(String letters, Tastatur parent) {
        this(letters.chars().mapToObj(c -> String.valueOf((char)c)).toArray(String[]::new), parent);
    }

    public TastaturRow(String[] labels, Tastatur parent) {
        int cols = labels.length;
        row = new JPanel(new GridLayout(1, cols, 0, 0));
        row.setOpaque(false);

        int w = TastaturButton.KEY_WIDTH * cols;
        int h = TastaturButton.KEY_HEIGHT;
        row.setPreferredSize(new Dimension(w, h));
        row.setMinimumSize(new Dimension(w, h));
        row.setMaximumSize(new Dimension(w, h));

        for (String label : labels) {
            Runnable action;
            if ("⏎".equals(label) || "ENTER".equalsIgnoreCase(label)) {
                action = parent::onEnter;
            } else if ("⌫".equals(label) || "←".equals(label)) {
                action = parent::onBackspace;
            } else {
                char c = label.charAt(0);
                action = () -> parent.onLetter(c);
            }
            row.add(new TastaturButton(label, action).getButton());
        }
    }

    public JPanel getComponent() { return row; }

    @Override public void onLetter(char c) {}
    @Override public void onEnter() {}
    @Override public void onBackspace() {}
}

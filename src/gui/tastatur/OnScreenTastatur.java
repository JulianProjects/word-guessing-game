package gui.tastatur;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class OnScreenTastatur extends Tastatur {
    private final JPanel panel;

    public OnScreenTastatur() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(6, 0, 0, 0));

        panel.add(new TastaturRow("QWERTYUIOP", this).getComponent());
        panel.add(Box.createVerticalStrut(8));
        panel.add(new TastaturRow("ASDFGHJKL",  this).getComponent());
        panel.add(Box.createVerticalStrut(8));

        String[] row3 = {"⏎","Z","X","C","V","B","N","M","⌫"};
        panel.add(new TastaturRow(row3, this).getComponent());
    }

    public JComponent getComponent() { return panel; }

    @Override public void onLetter(char c)  { System.out.println("Letter: " + c); }
    @Override public void onEnter()         { System.out.println("ENTER"); }
    @Override public void onBackspace()     { System.out.println("BACKSPACE"); }
}

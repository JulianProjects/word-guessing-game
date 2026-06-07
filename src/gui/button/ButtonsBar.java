package gui.button;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.tastatur.ButtonsFactory;

import java.awt.*;

/** Untere Button-Leiste (Play/Home/Neustart). */
public class ButtonsBar extends Buttons {
    private final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
    private final JButton btnPlay, btnHome, btnRestart;
    private final ButtonsVisibility visibility;

    public ButtonsBar() {
        panel.setBorder(new EmptyBorder(6, 0, 14, 0));
        ButtonsFactory factory = new ButtonsFactory();

        btnPlay    = factory.create("Play",      this::onPlay);
        btnHome    = factory.create("Spiel schließen", this::onHome);
        btnRestart = factory.create("Neustart",  this::onRestart);

        panel.add(btnPlay);
        panel.add(btnHome);
        panel.add(btnRestart);

        visibility = new ButtonsVisibility(btnPlay, btnHome, btnRestart);
        visibility.showMainButtons(); // Start im Hauptmenü
    }

    @Override public JComponent getComponent() { return panel; }

    public void showMainButtons()    { visibility.showMainButtons(); }
    public void showHomeButtonOnly() { visibility.showHomeButtonOnly(); }

    @Override public void onPlay() {}
    @Override public void onHome() {}
    @Override public void onRestart() {}
}

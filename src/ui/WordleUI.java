// File: src/gui/app/WordleUI.java
package ui;

import gui.bild.BildPanel;
import gui.button.ButtonsBar;
import gui.eingabefeld.SixRowWordInput;
import ui.PhysischeTastatur;
import logic.AppConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.concurrent.atomic.AtomicReference;

public class WordleUI {

    private JFrame frame;

    public void show() {
        // Frame
        frame = new JFrame("Wordle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header
        frame.add(HeaderFactory.create("Wordle"), BorderLayout.NORTH);

        // Center (Cards + wichtige Referenzen)
        CenterCards.Refs refs = CenterCards.build();
        JPanel centerPanel = refs.centerPanel;
        BildPanel homeImage = refs.homeImage;
        SixRowWordInput wordInput = refs.wordInput;
        JLabel hint = refs.hint;

        frame.add(centerPanel, BorderLayout.CENTER);

        // Buttons (unten) – Navigation als Referenzen
        AtomicReference<Runnable> showMainMenuRef = new AtomicReference<>();
        AtomicReference<Runnable> showGameOnlyRef = new AtomicReference<>();
        ButtonsBar buttons = ButtonsFactory.create(wordInput, showGameOnlyRef, showMainMenuRef);
        frame.add(buttons.getComponent(), BorderLayout.SOUTH);

        CardLayout centerLayout = (CardLayout) centerPanel.getLayout();

        // Navigation implementieren und Referenzen setzen
        Runnable showMainMenu = () -> {
            buttons.showMainButtons();
            centerLayout.show(centerPanel, AppConfig.CARD_IMAGE);
            homeImage.load(AppConfig.HOME_IMAGE);
            hint.setText("<html>Errate das Wordle in 6 Versuchen.<br>" +
                    "Jeder Versuch muss ein gültiges Wort mit 5 Buchstaben sein.<br>" +
                    "Die Farbe der Kacheln ändert sich,<br>" +
                    "um zu zeigen, wie nah dein Versuch am gesuchten Wort ist.</html>");
            wordInput.reset();
        };
        Runnable showGameOnly = () -> {
            buttons.showHomeButtonOnly(); // zeigt jetzt Home + Neustart
            centerLayout.show(centerPanel, AppConfig.CARD_INPUT);
            wordInput.reset();
            hint.setText(" ");
        };
        showMainMenuRef.set(showMainMenu);
        showGameOnlyRef.set(showGameOnly);

        // Physische Tastatur
        KeyEventDispatcher physKeyDispatcher = PhysischeTastatur.registrieren(wordInput);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosed(java.awt.event.WindowEvent e) {
                PhysischeTastatur.deregistrieren(physKeyDispatcher);
            }
        });

        // Resize Reaktionen
        frame.addComponentListener(new ComponentAdapter() {
            @Override public void componentResized(ComponentEvent e) {
                frame.revalidate();
                frame.repaint();
            }
        });

        // Startzustand
        showMainMenu.run();

        // Anzeigen
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}

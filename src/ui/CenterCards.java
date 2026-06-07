// File: src/gui/app/CenterCards.java
package ui;

import gui.bild.BildPanel;
import gui.eingabefeld.SixRowWordInput;
import gui.tastatur.OnScreenTastatur;
import logic.AppConfig;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public final class CenterCards {

    private CenterCards() {}

    public static final class Refs {
        public final JPanel centerPanel;
        public final BildPanel homeImage;
        public final SixRowWordInput wordInput;
        public final JLabel hint;

        private Refs(JPanel centerPanel, BildPanel homeImage, SixRowWordInput wordInput, JLabel hint) {
            this.centerPanel = centerPanel;
            this.homeImage = homeImage;
            this.wordInput = wordInput;
            this.hint = hint;
        }
    }

    public static Refs build() {
        // Card-Container
        JPanel centerPanel = new JPanel(new CardLayout());

        // ==== Bild-Card ====
        JPanel imageCard = new JPanel(new BorderLayout());
        BildPanel homeImage = new BildPanel(AppConfig.SCALE_FACTOR);
        imageCard.add(homeImage.getComponent(), BorderLayout.CENTER);

        // ==== Eingabe-Card ====
        JPanel inputCard = new JPanel();
        inputCard.setLayout(new BoxLayout(inputCard, BoxLayout.Y_AXIS));

        JLabel prompt = new JLabel("Gib ein Wort mit " + AppConfig.WORD_LENGTH + " Buchstaben ein:");
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        prompt.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel hint = new JLabel(" ");
        hint.setAlignmentX(Component.CENTER_ALIGNMENT);
        hint.setBorder(new EmptyBorder(6, 10, 10, 10));

        SixRowWordInput wordInput = new SixRowWordInput();

        inputCard.add(Box.createVerticalGlue());
        inputCard.add(prompt);
        inputCard.add(wordInput);
        inputCard.add(Box.createVerticalStrut(12));

        // On-Screen Tastatur (delegiert Eingaben)
        OnScreenTastatur keyboard = new OnScreenTastatur() {
            @Override public void onLetter(char c)  { wordInput.appendLetter(c); }
            @Override public void onBackspace()     { wordInput.backspace(); }
            @Override public void onEnter()         { wordInput.enter(); }
        };
        inputCard.add(keyboard.getComponent());

        // Hinweis unten (jetzt wirklich sichtbar)
        inputCard.add(Box.createVerticalStrut(8));
        inputCard.add(hint);
        inputCard.add(Box.createVerticalGlue());

        // Karten registrieren
        centerPanel.add(imageCard, AppConfig.CARD_IMAGE);
        centerPanel.add(inputCard, AppConfig.CARD_INPUT);

        return new Refs(centerPanel, homeImage, wordInput, hint);
    }
}

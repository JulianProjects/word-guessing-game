package ui;

import gui.image.ImagePanel;
import gui.inputfield.SixRowWordInput;
import gui.keyboard.OnScreenKeyboard;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logic.AppConfig;

/** Builds the image and word-input cards displayed in the center area. */
public final class CenterCards {

  private CenterCards() {}

  /** Provides references to the components required by the main interface. */
  public static final class Refs {

    public final JPanel centerPanel;
    public final ImagePanel homeImagePanel;
    public final SixRowWordInput wordInput;
    public final JLabel hintLabel;

    private Refs(
        JPanel centerPanel,
        ImagePanel homeImagePanel,
        SixRowWordInput wordInput,
        JLabel hintLabel) {
      this.centerPanel = centerPanel;
      this.homeImagePanel = homeImagePanel;
      this.wordInput = wordInput;
      this.hintLabel = hintLabel;
    }
  }

  public static Refs build() {
    JPanel centerPanel = new JPanel(new CardLayout());

    // Build the card containing the home image.
    JPanel imageCard = new JPanel(new BorderLayout());
    ImagePanel homeImagePanel = new ImagePanel(AppConfig.SCALE_FACTOR);
    imageCard.add(homeImagePanel.getComponent(), BorderLayout.CENTER);

    // Build the card containing the game input controls.
    JPanel inputCard = new JPanel();
    inputCard.setLayout(new BoxLayout(inputCard, BoxLayout.Y_AXIS));

    JLabel promptLabel =
        new JLabel(
            "Enter a word with " + AppConfig.WORD_LENGTH + " letters:");
    promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    promptLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JLabel hintLabel = new JLabel(" ");
    hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    hintLabel.setBorder(new EmptyBorder(6, 10, 10, 10));

    SixRowWordInput wordInput = new SixRowWordInput();

    inputCard.add(Box.createVerticalGlue());
    inputCard.add(promptLabel);
    inputCard.add(wordInput);
    inputCard.add(Box.createVerticalStrut(12));

    // Forward virtual keyboard actions to the word input component.
    OnScreenKeyboard keyboard =
        new OnScreenKeyboard() {
          @Override
          public void onLetter(char character) {
            wordInput.appendLetter(character);
          }

          @Override
          public void onBackspace() {
            wordInput.backspace();
          }

          @Override
          public void onEnter() {
            wordInput.enter();
          }
        };

    inputCard.add(keyboard.getComponent());
    inputCard.add(Box.createVerticalStrut(8));
    inputCard.add(hintLabel);
    inputCard.add(Box.createVerticalGlue());

    // Register both views in the shared card container.
    centerPanel.add(imageCard, AppConfig.CARD_IMAGE);
    centerPanel.add(inputCard, AppConfig.CARD_INPUT);

    return new Refs(centerPanel, homeImagePanel, wordInput, hintLabel);
  }
}